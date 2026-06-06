#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Web GUI — HTTP 服务器 + Bootstrap 浏览器界面 v6.1
通过 run.py --web 或直接运行启动.
"""
import sys, io, os, json, webbrowser
from pathlib import Path
from http.server import HTTPServer, BaseHTTPRequestHandler
from urllib.parse import urlparse, parse_qs
from datetime import datetime

sys.path.insert(0, str(Path(__file__).parent))
from core.config import get_replay_paths
from core.analysis_pipeline import (
    load_and_analyze, run_simulation_bridge,
    batch_analyze_all, HEADERS_35, verify_engine_data,
)

# ── HTML 页面 (Bootstrap 5 CDN) ──────────────────────
HTML = r"""<!DOCTYPE html>
<html lang="zh-CN" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Rusted Warfare 回放分析器 v6.1</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
body{font-family:'Microsoft YaHei','Segoe UI',sans-serif;background:#0d1117}
.sidebar{width:320px;min-width:280px;background:#161b22;border-right:1px solid #30363d;display:flex;flex-direction:column;height:100vh;position:fixed;left:0;top:0;z-index:100}
.sidebar-header{padding:14px 16px;background:#1f2937;color:#58a6ff;border-bottom:1px solid #30363d;font-size:14px;font-weight:600}
.replay-list{flex:1;overflow-y:auto;padding:4px 0}
.replay-item{padding:8px 14px;cursor:pointer;border-bottom:1px solid #21262d;font-size:12px;transition:all .1s}
.replay-item:hover{background:#1f2937}
.replay-item.active{background:#0d419d;color:#fff}
.replay-item .rname{color:#e6edf3;font-size:12px;word-break:break-all;line-height:1.3}
.replay-item .rinfo{color:#8b949e;font-size:10px}
.replay-item.active .rinfo{color:#bcc7d9}
.sidebar-btns{padding:8px 10px;border-top:1px solid #30363d;display:flex;flex-wrap:wrap;gap:5px}
.sidebar-btns .btn{font-size:11px;padding:5px 8px}
.main-area{margin-left:320px;display:flex;flex-direction:column;height:100vh}
.main-header{padding:10px 20px;background:#161b22;color:#8b949e;border-bottom:1px solid #30363d;font-size:13px;flex-shrink:0}
.main-content{flex:1;overflow-y:auto;padding:16px 20px;background:#0d1117}
.status-bar{font-size:11px;padding:6px 20px;background:#161b22;color:#484f58;border-top:1px solid #30363d;flex-shrink:0}

/* Replay group */
.replay-group{margin-bottom:16px;border:1px solid #21262d;border-radius:8px;overflow:hidden}
.replay-group-header{padding:10px 16px;background:#161b22;cursor:pointer;display:flex;justify-content:space-between;align-items:center;border-bottom:1px solid #21262d}
.replay-group-header:hover{background:#1c2333}
.replay-group-header .rgn{color:#58a6ff;font-size:13px;font-weight:600;flex:1}
.replay-group-header .rgs{color:#8b949e;font-size:11px;margin-left:12px}
.replay-group-body{overflow-x:auto}

/* Table */
.table-sm{font-size:11px;margin:0}
.table-sm th{background:#1f2937;color:#58a6ff;position:sticky;top:0;z-index:1;white-space:nowrap;padding:6px 10px;font-weight:500;border-color:#30363d}
.table-sm td{padding:4px 10px;white-space:nowrap;vertical-align:middle;border-color:#21262d}
.table-sm tr:hover td{background:#161b22}
.table-sm .text-good{color:#3fb950!important}
.table-sm .text-bad{color:#f85149!important}
.table-sm .text-warn{color:#d2991d!important}

/* Badge */
.badge-win{background:#238636!important}
.badge-lose{background:#6e7681!important}
.badge-surr{background:#da3633!important}

/* Card */
.info-card{background:#161b22;border:1px solid #30363d;border-radius:8px;padding:12px 16px}
.info-card .ic-label{font-size:10px;color:#8b949e;text-transform:uppercase}
.info-card .ic-value{font-size:20px;font-weight:700;color:#e6edf3}

/* Search */
.search-box{background:#0d1117;border:1px solid #30363d;color:#c9d1d9;padding:6px 12px;border-radius:6px;font-size:13px;width:100%}
.search-box:focus{outline:none;border-color:#58a6ff}

/* Toggle */
.toggle-detail{cursor:pointer;color:#58a6ff;font-size:11px;user-select:none}
.toggle-detail:hover{text-decoration:underline}
.col-detail{display:none}
.col-detail.show{display:table-cell}

/* Pre text */
.pre-report{font-family:'Consolas','Courier New',monospace;font-size:12px;line-height:1.6;white-space:pre-wrap;color:#c9d1d9;background:#0d1117;padding:12px;border-radius:6px;border:1px solid #21262d}

/* Scrollbar */
::-webkit-scrollbar{width:6px;height:6px}
::-webkit-scrollbar-track{background:#0d1117}
::-webkit-scrollbar-thumb{background:#30363d;border-radius:3px}
</style>
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
  <div class="sidebar-header">⚙ RW 回放分析器 v6.1</div>
  <div class="replay-list" id="replay-list"></div>
  <div class="sidebar-btns">
    <button class="btn btn-success btn-sm flex-grow-1" onclick="analyzeSelected()">分析选中</button>
    <button class="btn btn-primary btn-sm flex-grow-1" onclick="batchAll()">批量对比</button>
    <button class="btn btn-outline-secondary btn-sm flex-grow-1" onclick="verifyEngine()">验证</button>
    <button class="btn btn-outline-secondary btn-sm flex-grow-1" onclick="refresh()">刷新</button>
  </div>
</div>

<!-- Main -->
<div class="main-area">
  <div class="main-header" id="title">📊 分析报告 — 请从左侧选择回放</div>
  <div class="main-content" id="output">
    <div class="text-secondary">请从左侧列表选择回放文件，点击"分析选中"查看单回放详情，或点击"批量对比"查看所有回放对比。</div>
  </div>
  <div class="status-bar" id="status">就绪</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
let replays=[],selectedIdx=-1,showDetail=false;

async function api(p){try{const c=new AbortController(),t=setTimeout(()=>c.abort(),180000),r=await fetch(p,{signal:c.signal});clearTimeout(t);if(!r.ok)return{error:'HTTP '+r.status};return await r.json()}catch(e){return{error:e.name==='AbortError'?'请求超时':e.message}}}
function esc(s){return s.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;')}
function fmt(v,t){if(typeof v==='number'&&v.toFixed){if(t==='int')return v.toFixed(0);if(t==='f1')return v.toFixed(1);if(t==='f2')return v.toFixed(2);if(t==='pct')return v.toFixed(1);return v.toFixed(0)}return String(v)}

async function refresh(){
  document.getElementById('status').textContent='加载中...';
  const d=await api('/api/replays');
  if(d.error){document.getElementById('status').textContent=d.error;return}
  replays=d;
  document.getElementById('replay-list').innerHTML=replays.map((r,i)=>
    `<div class="replay-item${i===selectedIdx?' active':''}" onclick="select(${i})">
      <div class="rname">${esc(r.name)}</div>
      <div class="rinfo">${r.size}KB | ${r.date}</div>
    </div>`).join('');
  document.getElementById('status').textContent=`${replays.length} 个回放就绪`;
}

function select(i){selectedIdx=i;document.querySelectorAll('.replay-item').forEach((e,j)=>e.classList.toggle('active',j===i))}

async function analyzeSelected(){
  if(selectedIdx<0){alert('请先选择一个回放');return}
  const r=replays[selectedIdx];
  document.getElementById('title').textContent='分析: '+r.name;
  document.getElementById('status').textContent='分析中...';
  document.getElementById('output').innerHTML='<div class="text-secondary">加载中...</div>';
  const d=await api('/api/analyze?idx='+selectedIdx);
  if(d.error){document.getElementById('output').innerHTML='<div class="text-danger">ERROR: '+esc(d.error)+'</div>';return}
  renderSingle(d);
  document.getElementById('status').textContent='分析完成';
}

async function batchAll(){
  document.getElementById('title').textContent='批量对比';
  document.getElementById('status').textContent='批量分析中...';
  document.getElementById('output').innerHTML='<div class="text-secondary">批量分析可能需要较长时间，请耐心等待...</div>';
  const d=await api('/api/batch');
  if(d.error){document.getElementById('output').innerHTML='<div class="text-danger">ERROR: '+esc(d.error)+'</div>';return}
  renderBatch(d);
  document.getElementById('status').textContent='批量分析完成';
}

async function verifyEngine(){
  document.getElementById('title').textContent='引擎验证';
  document.getElementById('status').textContent='验证中...';
  document.getElementById('output').innerHTML='<div class="text-secondary">加载中...</div>';
  const d=await api('/api/verify');
  if(d.error){document.getElementById('output').innerHTML='<div class="text-danger">ERROR: '+esc(d.error)+'</div>';return}
  document.getElementById('output').innerHTML='<pre class="pre-report">'+esc(d.text)+'</pre>';
  document.getElementById('status').textContent='验证完成';
}

// ── Single analysis render ─────────────────
function renderSingle(d){
  if(!d.players||!d.players.length){document.getElementById('output').innerHTML='<div class="text-warning">无玩家数据</div>';return}

  let h='';
  // ── Header bar ──
  h+=`<div class="d-flex flex-wrap justify-content-between align-items-start mb-3 pb-2" style="border-bottom:1px solid #30363d">
    <div>
      <span class="text-info fw-bold" style="font-size:15px">📊 ${esc(d.replay||'')}</span>
      <span class="text-secondary ms-3" style="font-size:12px">⏱ ${fmt(d.game_duration,'int')}秒 | ${d.total_events||0} 事件 | ${d.players.length} 玩家</span>
    </div>
    <button class="btn btn-outline-secondary btn-sm" onclick="showTextReport()">📝 文本报告</button>
  </div>`;

    // --- Map info + Engine winner ---
  if(d.map||d.sim_winner){
    h+='<div class="d-flex flex-wrap gap-3 mb-3" style="font-size:11px">';
    if(d.map){
      h+='<span class="info-card px-2 py-1">&#x1F5FA; <b>'+esc(d.map.name||'?')+'</b> '+d.map.width+'x'+d.map.height+' tiles ('+d.map.px_width+'x'+d.map.px_height+'px)'+(d.map.type?' | '+esc(d.map.type):'')+(d.map.players>0?' | '+d.map.players+'p':'')+'</span>';
    }
    if(d.sim_winner){
      h+='<span class="info-card px-2 py-1" style="border-color:#3fb950">&#x1F3C6; Engine Winner: <b style="color:#3fb950">Team '+esc(d.sim_winner)+'</b></span>';
    }
    h+='</div>';
  }

  // --- Simulation verification (engine data) ---
  if(d.sim&&Object.keys(d.sim).length){
    h+='<div class="d-flex flex-wrap gap-3 mb-3" style="font-size:11px">';
    for(const[pid,s]of Object.entries(d.sim)){
      const dc=Math.abs(s.economic_drift)<1?'text-good':(Math.abs(s.economic_drift)>10?'text-bad':'');
      const ok=Math.abs(s.economic_drift)<1 && s.final_credits>0;
      const w=s.is_winner===true?' &#x1F3C6;':(s.is_winner===false?' &#x1F480;':'');
      h+='<span class="info-card px-2 py-1"><b>P'+pid+'</b>'+w+' '+s.team+' team  Credits:'+fmt(s.final_credits,'int')+'  inc/s:'+fmt(s.income_per_second,'f1')+' <span class="'+dc+'">drift:'+fmt(s.economic_drift,'f2')+'</span>'+(s.team_kills>0||s.team_losses>0?' K'+s.team_kills+'L'+s.team_losses:'')+(ok?'<span class="text-good"> OK</span>':(s.final_credits>0?'<span class="text-bad"> WARN</span>':''))+'</span>';
    }
    h+='</div>';
  }
lse{
          h+=`<span style="color:#484f58">${String(a.time).padStart(5)}s</span> ${actionIcon} <span style="color:${actionCls}">${a.action}</span>\n`;
        }
      }
      h+='</div>';
    }
    h+='</div>';

    // Unit composition
    h+=`<div class="col-md-5">`;
    if(p.units&&p.units.length){
      h+=`<div style="font-size:11px;color:#8b949e;margin-bottom:4px">🎯 单位构成 <span class="text-secondary">— 数量 × 种类</span></div>
      <div style="max-height:180px;overflow-y:auto;font-size:10px">`;
      for(const u of p.units.slice(0,20)){
        const catCls=u.category==='经济'?'text-success':u.category==='科技'?'text-warning':'text-danger';
        h+=`<span class="badge bg-dark border border-secondary me-1 mb-1">
          <span class="${catCls}">●</span> ${esc(u.cn_name||u.name)} <b>×${u.count}</b>
        </span>`;
      }
      h+='</div>';
    }
    // Army value summary
    h+=`<div style="font-size:10px;color:#8b949e;margin-top:6px">
      部队 <b class="text-info">${fmt(p.army_value,'int')}</b> &nbsp;
      建筑 <b class="text-info">${fmt(p.building_value,'int')}</b> &nbsp;
      总计 <b class="text-info">${fmt(p.total_value,'int')}</b>
    </div>`;
    h+='</div></div>';

    h+='</div></div>'; // close card-body + card
  }

  // Store text report
  if(d.text_report){
    h+=`<div id="text-report" style="display:none"><pre class="pre-report">${esc(d.text_report)}</pre></div>`;
  }

  document.getElementById('output').innerHTML=h;
}

function showTextReport(){
  const el=document.getElementById('text-report');
  if(el){
    el.style.display=el.style.display==='none'?'block':'none';
    if(el.style.display==='block')el.scrollIntoView({behavior:'smooth'});
  }
}

// ── Batch render: grouped by replay ─────────
function renderBatch(d){
  let h='<div class="mb-3"><span class="text-secondary">'+esc(d.summary)+'</span> ';
  h+='<input class="search-box mt-2" id="search" placeholder="🔍 搜索玩家名或回放..." oninput="filterRows()" style="max-width:320px">';
  h+='<label class="ms-3 toggle-detail" onclick="toggleDetail()">📋 '+ (showDetail?'隐藏详情':'显示详情列') +'</label></div>';

  if(!d.rows||!d.rows.length){h+='<div class="text-secondary">无数据</div>';document.getElementById('output').innerHTML=h;return}

  // Group by replay
  const groups=new Map();
  for(const r of d.rows){
    const k=r.replay;
    if(!groups.has(k))groups.set(k,[]);
    groups.get(k).push(r);
  }

  // Core + detail columns
  const coreCols=[
    {k:'player',l:'玩家',w:'auto',align:'left'},
    {k:'team',l:'队',w:'40px'},
    {k:'income',l:'收入',w:'80px',t:'int'},
    {k:'spent',l:'消费',w:'80px',t:'int'},
    {k:'net_spent',l:'净投入',w:'80px',t:'int'},
    {k:'roi',l:'ROI',w:'60px',t:'f2'},
    {k:'income_rate',l:'入/s',w:'65px',t:'f1'},
    {k:'apm',l:'APM',w:'55px',t:'f1'},
    {k:'eapm',l:'EAPM',w:'55px',t:'f1'},
    {k:'kills',l:'杀',w:'40px',t:'int'},
    {k:'losses',l:'损',w:'40px',t:'int'},
    {k:'winner',l:'结果',w:'60px'},
  ];
  const detailCols=[
    {k:'surplus',l:'剩余%',w:'55px',t:'pct'},
    {k:'survival',l:'存活%',w:'55px',t:'pct'},
    {k:'econ_pct',l:'经济%',w:'55px',t:'pct'},
    {k:'tech_pct',l:'科技%',w:'55px',t:'pct'},
    {k:'mil_pct',l:'军事%',w:'55px',t:'pct'},
    {k:'exchange',l:'交换比',w:'60px',t:'f2'},
    {k:'army_value',l:'部队价',w:'75px',t:'int'},
    {k:'building_value',l:'建筑价',w:'75px',t:'int'},
    {k:'total_value',l:'总价值',w:'75px',t:'int'},
    {k:'gaps',l:'空窗',w:'45px',t:'int'},
    {k:'first_extractor',l:'首提',w:'50px'},
    {k:'first_factory',l:'首工',w:'50px'},
    {k:'sim_drift',l:'漂移',w:'55px',t:'f2'},
  ];

  const allCols=[...coreCols,...detailCols];
  const dcShow=showDetail?'show':'';

  let gidx=0;
  for(const[replay,rows]of groups){
    // Summary for this replay
    const pCount=rows.length;
    const tA=rows.filter(r=>r.team==='A').length;
    const tB=rows.filter(r=>r.team==='B').length;
    const maxNet=Math.max(...rows.map(r=>typeof r.net_spent==='number'?r.net_spent:0));
    const wins=rows.filter(r=>r.winner==='Yes').length;

    h+=`<div class="replay-group" data-replay="${esc(replay).toLowerCase()}">
      <div class="replay-group-header" onclick="this.nextElementSibling.classList.toggle('d-none')">
        <span class="rgn">📁 ${esc(replay)}</span>
        <span class="rgs">${pCount}人 | A队${tA} B队${tB} | 最高净投入${fmt(maxNet,'int')} | 胜${wins}</span>
      </div>
      <div class="replay-group-body">
      <table class="table table-sm table-borderless mb-0">
      <thead><tr>`;

    for(const c of allCols){
      h+=`<th style="width:${c.w};text-align:${c.align||'right'}">${c.l}</th>`;
    }
    h+='</tr></thead><tbody>';

    for(const row of rows){
      h+='<tr data-player="'+esc(String(row.player)).toLowerCase()+'">';
      for(const c of allCols){
        let v=row[c]!==undefined?row[c]:'';
        let cls='',style='';
        const isDetail=detailCols.some(d=>d.k===c.k);
        if(isDetail)style='display:none';

        // Color coding
        if(c.k==='roi'){
          if(typeof v==='number')cls=v>=3?'text-good':(v<1&&v>0?'text-bad':'');
        }
        if(c.k==='sim_drift'&&typeof v==='number')cls=Math.abs(v)<1?'text-good':(Math.abs(v)>10?'text-bad':'');
        if(c.k==='winner'){
          if(v==='Yes')cls='badge badge-win';
          else if(v==='Surrender')cls='badge badge-surr';
          else if(v==='No')cls='badge badge-lose';
        }

        // Format
        const t=c.t||'';
        if(typeof v==='number'&&v.toFixed){
          if(t==='int')v=v.toFixed(0);
          else if(t==='f1')v=v.toFixed(1);
          else if(t==='f2')v=v.toFixed(2);
          else if(t==='pct')v=v.toFixed(1);
        }

        const align=c.align==='left'?'text-start':'text-end';
        h+=`<td class="${align} ${cls} col-detail-${c.k}" style="${style}">${esc(String(v))}</td>`;
      }
      h+='</tr>';
    }
    h+='</tbody></table></div></div>';
    gidx++;
  }

  if(d.team_summary){
    h+='<div class="mt-3 mb-2 fw-bold text-info">队伍汇总</div>';
    h+='<pre class="pre-report">'+esc(d.team_summary)+'</pre>';
  }

  document.getElementById('output').innerHTML=h;
}

function toggleDetail(){
  showDetail=!showDetail;
  document.querySelectorAll('[class*="col-detail-"]').forEach(el=>{
    // Only toggle detail columns
    for(const c of['surplus','survival','econ_pct','tech_pct','mil_pct','exchange','army_value','building_value','total_value','gaps','first_extractor','first_factory','sim_drift']){
      if(el.classList.contains('col-detail-'+c)){
        el.style.display=showDetail?'':'none';
        break;
      }
    }
  });
  const lbl=document.querySelector('.toggle-detail');
  if(lbl)lbl.textContent='📋 '+(showDetail?'隐藏详情':'显示详情列');
}

function filterRows(){
  const q=document.getElementById('search').value.toLowerCase();
  document.querySelectorAll('.replay-group').forEach(g=>{
    const rn=(g.getAttribute('data-replay')||'').toLowerCase();
    let hasMatch=rn.includes(q);
    if(!hasMatch){
      g.querySelectorAll('tr[data-player]').forEach(tr=>{
        const pn=(tr.getAttribute('data-player')||'').toLowerCase();
        if(pn.includes(q))hasMatch=true;
      });
    }
    g.style.display=hasMatch||!q?'':'none';
  });
}

refresh();
</script>
</body>
</html>"""


# ── HTTP 请求处理 ──────────────────────────────────────
class Handler(BaseHTTPRequestHandler):
    def log_message(self, *a):
        pass

    def _send_json(self, data):
        content = json.dumps(data, ensure_ascii=False).encode('utf-8')
        self.send_response(200)
        self.send_header('Content-Type', 'application/json; charset=utf-8')
        self.send_header('Content-Length', str(len(content)))
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Connection', 'close')
        self.end_headers()
        self.wfile.write(content)

    def _send_html(self, content):
        data = content.encode('utf-8') if isinstance(content, str) else content
        self.send_response(200)
        self.send_header('Content-Type', 'text/html; charset=utf-8')
        self.send_header('Content-Length', str(len(data)))
        self.send_header('Connection', 'close')
        self.end_headers()
        self.wfile.write(data)

    def do_GET(self):
        path = urlparse(self.path).path
        qs = parse_qs(urlparse(self.path).query)

        if path == '/' or path == '/index.html':
            self._send_html(HTML)

        elif path == '/api/replays':
            replays = get_replay_paths()
            data = [{
                'name': r.name,
                'size': int(r.stat().st_size / 1024),
                'idx': i,
                'date': datetime.fromtimestamp(r.stat().st_mtime).strftime('%m-%d %H:%M'),
            } for i, r in enumerate(replays)]
            self._send_json(data)

        elif path == '/api/analyze':
            try:
                idx = int(qs.get('idx', ['-1'])[0])
                replays = get_replay_paths()
                if 0 <= idx < len(replays):
                    parser, _, _, analyzer, matched_map = load_and_analyze(str(replays[idx]))
                    # Structured player reports (use unified filter)
                    players = []
                    for pid, r in sorted(analyzer.reports.items(),
                                        key=lambda x: -x[1].total_spent):
                        if r.total_spent <= 0 and r.total_income <= 4000 and r.team not in ('A', 'B'):
                            continue
                        players.append(r.to_dict())

                    # Map info
                    map_info = None
                    if matched_map:
                        map_info = {
                            'name': matched_map.display_name,
                            'file': matched_map.file_name,
                            'width': matched_map.width,
                            'height': matched_map.height,
                            'px_width': matched_map.width * matched_map.tile_width,
                            'px_height': matched_map.height * matched_map.tile_height,
                            'type': matched_map.map_type or '',
                            'players': matched_map.player_count or 0,
                            'layers': list(matched_map.layers.keys()),
                        }

                    # Simulation bridge (engine data) — timeout-limited
                    sim_data = {}
                    sim_winner = None
                    import signal
                    try:
                        sim_summary = run_simulation_bridge(parser)
                        if sim_summary:
                            sim_winner = sim_summary.get('winner_team')
                            for spid, s in sim_summary['players'].items():
                                sim_data[str(spid)] = {
                                    'final_credits': round(s.get('sim_credits', s.get('final_credits', 0)), 0),
                                    'total_income': round(s.get('sim_income', s.get('total_income', 0)), 0),
                                    'total_spent': round(s.get('sim_spent', s.get('total_spent', 0)), 0),
                                    'income_per_second': round(s.get('sim_rate', s.get('income_per_second', 0)), 1),
                                    'economic_drift': round(s.get('sim_drift', s.get('economic_drift', 0)), 2),
                                    'team_kills': s.get('team_kills', 0),
                                    'team_losses': s.get('team_losses', 0),
                                    'is_winner': s.get('is_winner'),
                                    'team_is_winner': s.get('team_is_winner'),
                                    'team': s.get('team', '?'),
                                    'active_units': s.get('team_active_units', 0),
                                }
                    except Exception as e:
                        pass  # Engine sim failed — still show parser data

                    # Raw text report for download/copy
                    text_report = analyzer.generate_report()

                    # Game metadata
                    game_dur = parser.get_game_duration_sec() if parser else 0
                    replay_name = replays[idx].name

                    self._send_json({
                        'replay': replay_name,
                        'game_duration': round(game_dur, 0),
                        'total_events': len(parser.events) if parser else 0,
                        'players': players,
                        'sim': sim_data,
                        'sim_winner': sim_winner,
                        'map': map_info,
                        'text_report': text_report,
                    })
                else:
                    self._send_json({'error': '无效的回放索引'})
            except Exception as e:
                import traceback
                self._send_json({'error': str(e), 'trace': traceback.format_exc()})

        elif path == '/api/batch':
            try:
                all_rows, text = batch_analyze_all()
                team_lines = []
                in_team = False
                for line in text.split('\n'):
                    if '队伍汇总' in line:
                        in_team = True
                        continue
                    if in_team and line.strip():
                        team_lines.append(line)
                self._send_json({
                    'summary': f'共 {len(get_replay_paths())} 个回放, {len(all_rows)} 条玩家记录',
                    'rows': all_rows,
                    'team_summary': '\n'.join(team_lines) if team_lines else '',
                })
            except Exception as e:
                import traceback
                self._send_json({'error': str(e), 'trace': traceback.format_exc()})

        elif path == '/api/verify':
            text = verify_engine_data()
            self._send_json({'text': text})

        else:
            self.send_response(404)
            self.end_headers()


# ── 主入口 ─────────────────────────────────────────────
def main():
    port = 18080
    try:
        server = HTTPServer(('127.0.0.1', port), Handler)
        url = f'http://127.0.0.1:{port}'
        print(f'\n  ╔══════════════════════════════════════╗')
        print(f'  ║  Rusted Warfare 回放分析器 Web GUI v6║')
        print(f'  ╠══════════════════════════════════════╣')
        print(f'  ║  地址: {url}          ║')
        print(f'  ║  浏览器将自动打开...                ║')
        print(f'  ║  按 Ctrl+C 停止服务器               ║')
        print(f'  ╚══════════════════════════════════════╝')
        print()
        webbrowser.open(url)
        server.serve_forever()
    except OSError:
        print(f'  端口 {port} 已被占用 — 请关闭其他实例后重试')
    except KeyboardInterrupt:
        print('\n  服务器已停止')
        server.shutdown()


if __name__ == '__main__':
    main()
