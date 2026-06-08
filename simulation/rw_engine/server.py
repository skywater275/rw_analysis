# -*- coding: utf-8 -*-
"""Web game server — browser-based UI for the simulation engine."""
import sys, os, json, math, time
from http.server import HTTPServer, BaseHTTPRequestHandler
from urllib.parse import urlparse, parse_qs

from engine import Engine, FRAME_RATE, DEFAULT_SPEED, STARTING_CREDITS, get_cost, is_building

GAME = None

HTML = r'''<!DOCTYPE html><html lang="zh"><head><meta charset="UTF-8">
<title>Rusted Warfare - Simulation Engine</title>
<style>
*{margin:0;padding:0;box-sizing:border-box}
body{background:#1a1a2e;color:#e0e0e0;font-family:monospace;overflow:hidden}
#bar{background:#16213e;padding:8px 16px;display:flex;gap:16px;align-items:center;border-bottom:2px solid #0f3460}
#bar button{background:#0f3460;color:#e0e0e0;border:1px solid #533483;padding:6px 14px;cursor:pointer;border-radius:3px;font-size:12px}
#bar button:hover{background:#533483}
#bar .s{font-size:13px;color:#aaa}
#main{display:flex;height:calc(100vh - 45px)}
#map{flex:1;position:relative;background:#0a0a1a;overflow:hidden}
#map canvas{position:absolute;top:0;left:0}
#side{width:300px;background:#16213e;border-left:2px solid #0f3460;padding:10px;overflow-y:auto;font-size:12px}
.tp{margin-bottom:12px;background:#0f3460;border-radius:4px;padding:8px}
.tp h3{color:#e94560;margin-bottom:4px}
.tp .v{color:#4ecca3}
.ctls{display:flex;gap:4px;flex-wrap:wrap;margin:8px 0}
.ctls input,.ctls select{width:70px;background:#1a1a2e;color:#e0e0e0;border:1px solid #533483;padding:3px 4px;border-radius:2px;font-size:11px}
.ctls button{background:#0f3460;color:#e0e0e0;border:1px solid #533483;padding:3px 8px;cursor:pointer;border-radius:2px;font-size:11px}
#log{font-size:10px;color:#666;max-height:150px;overflow-y:auto}
</style></head><body>
<div id="bar">
<button onclick="x('start')">New</button><button onclick="x('pause')">||</button>
<button onclick="x('spd',1)">1x</button><button onclick="x('spd',2.5)">2.5x</button><button onclick="x('spd',10)">10x</button>
<span class="s" id="tm">0s</span><span class="s" id="tk">T0</span><span class="s" id="fp">-</span>
</div><div id="main"><div id="map"><canvas id="cv"></canvas></div><div id="side">
<div id="ts"></div><div class="ctls">
<select id="sel"><option value="0">Team0</option><option value="1">Team1</option></select>
<input id="un" value="mechGun"><button onclick="x('build')">Build</button>
<input id="mx" value="800"><input id="my" value="500"><button onclick="x('move')">Move</button>
</div><div id="log"></div></div></div>
<script>
var S=null,CV,CT,fc=0,lt=0;
function init(){CV=document.getElementById('cv');CT=CV.getContext('2d');rCV();window.addEventListener('resize',rCV);x('start');setInterval(poll,200);requestAnimationFrame(loop)}
function rCV(){CV.width=CV.parentElement.clientWidth;CV.height=CV.parentElement.clientHeight}
function x(c,v){
 var p='cmd='+c;
 if(c=='build'){p+='&t='+document.getElementById('sel').value+'&u='+document.getElementById('un').value}
 else if(c=='move'){p+='&t='+document.getElementById('sel').value+'&x='+document.getElementById('mx').value+'&y='+document.getElementById('my').value}
 else if(c=='start'){p+='&n=2&cr=4000&sp=2.5'}
 else if(c=='spd'){p+='&v='+v}
 fetch('/a?'+p).then(r=>r.json()).then(d=>{S=d;uS()})}
function poll(){fetch('/a?cmd=tick&n=3').then(r=>r.json()).then(d=>{S=d;uS()})}
function uS(){if(!S)return
 document.getElementById('tm').textContent=(S.time||0).toFixed(1)+'s'
 document.getElementById('tk').textContent='T'+S.tick
 var h=''
 for(var t in S.teams){var d=S.teams[t]
  h+='<div class=tp><h3>Team '+t+'</h3>'
  h+='<div>Credits: <span class=v>'+d.credits.toFixed(0)+'</span> | Income: <span class=v>'+d.income_ps.toFixed(1)+'/s</span></div>'
  h+='<div>Units: '+d.active_units+' | K:'+d.kills+' | L:'+d.losses+'</div></div>'}
 document.getElementById('ts').innerHTML=h}
function loop(ts){fc++;if(ts-lt>=1000){document.getElementById('fp').textContent=fc+'fps';fc=0;lt=ts}
 if(!S||!CT)return requestAnimationFrame(loop)
 CT.clearRect(0,0,CV.width,CV.height)
 CT.strokeStyle='#1a1a3e';CT.lineWidth=0.5
 for(var i=0;i<CV.width;i+=50){CT.beginPath();CT.moveTo(i,0);CT.lineTo(i,CV.height);CT.stroke()}
 for(var j=0;j<CV.height;j+=50){CT.beginPath();CT.moveTo(0,j);CT.lineTo(CV.width,j);CT.stroke()}
 var cs=['#4ecca3','#e94560','#f0c040','#40a0f0']
 for(var t in S.units){var us=S.units[t],c=cs[parseInt(t)%4]
  for(var i=0;i<us.length;i++){var u=us[i],px=u.x/4,py=u.y/4
   if(u.is_building){CT.fillStyle=c;CT.globalAlpha=0.5;CT.fillRect(px-u.radius/4,py-u.radius/4,u.radius/2,u.radius/2);CT.globalAlpha=1}
   else{CT.fillStyle=c;CT.beginPath();CT.arc(px,py,Math.max(4,u.radius/4),0,6.28);CT.fill()}
   var hp=u.hp/u.max_hp;CT.fillStyle='#333';CT.fillRect(px-10,py-u.radius/4-8,20,3)
   CT.fillStyle=hp>0.5?'#4ecca3':hp>0.25?'#f0c040':'#e94560';CT.fillRect(px-10,py-u.radius/4-8,20*hp,3)}}}
 requestAnimationFrame(loop)}
window.onload=init
</script></body></html>'''


class Handler(BaseHTTPRequestHandler):
    def do_GET(self):
        global GAME
        p=urlparse(self.path)
        if p.path=='/': self._html()
        elif p.path=='/a': self._api(parse_qs(p.query))
        else: self.send_response(404); self.end_headers()

    def _html(self):
        self.send_response(200); self.send_header('Content-Type','text/html; charset=utf-8')
        self.send_header('Cache-Control','no-cache'); self.end_headers()
        self.wfile.write(HTML.encode())

    def _api(self, q):
        global GAME
        c=q.get('cmd',[''])[0]
        if c=='start':
            n=int(q.get('n',[2])[0]); cr=int(q.get('cr',[4000])[0])
            sp=float(q.get('sp',[2.5])[0])
            GAME=Engine(); GAME.speed_mult=sp
            for tid in range(n):
                w=GAME.create_world(tid); w.credits=cr
                w.spawn_unit('commandCenter',500+tid*800,500)
            for tid,w in GAME.worlds.items():
                es=[ww for ot,ww in GAME.worlds.items() if ot!=tid]
                w.enemy_world=es[0] if es else None
        elif c=='pause' and GAME:
            GAME.paused=not getattr(GAME,'paused',False)
        elif c=='spd' and GAME:
            GAME.speed_mult=float(q.get('v',[2.5])[0])
        elif c=='build' and GAME:
            tid=int(q.get('t',[0])[0]); name=q.get('u',['mechGun'])[0]
            w=GAME.worlds.get(tid)
            if w:
                cost=get_cost(name)
                if cost and w.credits>=cost:
                    x=float(q.get('x',[500+tid*800])[0])
                    y=float(q.get('y',[600])[0])
                    if is_building(name): w.start_build(name,x,y,cost)
                    else: w.spend_credits(cost); w.spawn_unit(name,x,y)
        elif c=='move' and GAME:
            tid=int(q.get('t',[0])[0]); tx=float(q.get('x',[800])[0]); ty=float(q.get('y',[500])[0])
            w=GAME.worlds.get(tid)
            if w:
                for u in w.units.values():
                    if u.is_alive and u.is_mobile: w.move_unit_to(u,tx,ty)
        elif c=='tick' and GAME:
            for _ in range(int(q.get('n',[3])[0])): GAME.tick()

        state={'time':GAME.game_time if GAME else 0,'tick':GAME.global_tick if GAME else 0,
               'teams':{},'units':{}}
        if GAME:
            for tid,w in GAME.worlds.items():
                s=w.get_state(); state['teams'][str(tid)]=s
                us=[]
                for uid,u in w.units.items():
                    if u.is_alive:
                        us.append({'uid':uid,'type':u.unit_type,'x':u.x,'y':u.y,
                            'hp':u.hp,'max_hp':u.max_hp,'radius':u.radius,
                            'is_building':u.is_building,'weapon_range':u.weapon_range,
                            'build_progress':u.build_progress})
                state['units'][str(tid)]=us
        self.send_response(200); self.send_header('Content-Type','application/json')
        self.send_header('Access-Control-Allow-Origin','*'); self.end_headers()
        self.wfile.write(json.dumps(state,default=str).encode())

    def log_message(self,*a): pass


def main(port=8080):
    print('Rusted Warfare Simulation Engine — Web Server')
    print('Open: http://localhost:%d'%port)
    HTTPServer(('0.0.0.0',port),Handler).serve_forever()

if __name__=='__main__':
    port = int(sys.argv[1]) if len(sys.argv) > 1 else 8080
    main(port)
