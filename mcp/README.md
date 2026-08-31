# mcp/ — 游戏 MCP 服务器 (独立自包含目录)

> 版本: v19.133f98 | 日期: 2026-09-04 | Rusted Warfare 调试协议 → 标准 MCP 工具桥接 (297 个)
> 依赖: `pip install mcp` (mcp SDK 2.x, FastMCP 已改名 MCPServer)
> **自包含**: 本目录与源码逆向项目完全隔离 — 不引用项目任何文件 (协议内联 + script_api.json 自带副本), 可整体复制到任何位置独立运行。

## 目录内容

```
mcp/
├── mcp_game_server.py   — MCP 服务器 (mcp SDK 2.x MCPServer, stdio; 协议逻辑内联, 无项目依赖)
├── script_api.json      — 222 方法签名白名单 (自带的独立副本, 不依赖 tools/capture/)
├── unit_type_names.json — 52 个内置单位类型名 (原版 UnitType 枚举, 字节码实证提取)
├── unit_names.json      — 125 个 ini 注册名 → ini 路径映射 (assets/units 的 name: 字段)
└── README.md            — 本文件
```

## 游戏根定位

默认推断: 本目录上级的上级 (项目内顶层 mcp/ → 游戏根)。
可用环境变量覆盖: `RW_GAME_ROOT=C:\path\to\game python mcp/mcp_game_server.py`
(复制到项目外运行时必须设置 RW_GAME_ROOT)。

## 用途

让 AI 客户端 (Claude Code / 本环境等) 通过标准 MCP 工具直接操作游戏:
启动/停止游戏 (GUI/headless)、加载回放、驱动对局 (开局/资金/单位/移动)、读取游戏日志。
底层桥接游戏调试服务器 (TCP 127.0.0.1:5677, `script <expr>` 命令; 协议与 tools/capture/debug_client.py 同源)。

## 快速开始

```bash
pip install mcp                          # 依赖 (mcp 2.x)
python mcp/mcp_game_server.py            # stdio MCP 服务器 (客户端注册用)
python mcp/mcp_game_server.py --list-tools    # 列出 297 个工具
python mcp/mcp_game_server.py --launch-game   # 仅启动游戏 (GUI) 并等待端口
python mcp/mcp_game_server.py --stop-game     # 停止游戏
python mcp/mcp_game_server.py --self-test     # 自测 (需游戏运行, 加 --launch 自动启动)
```

## 客户端注册 (Claude Code)

项目根 `.mcp.json` 已配置:

```json
{
  "mcpServers": {
    "rusted-warfare": {
      "command": "python",
      "args": ["mcp/mcp_game_server.py"],
      "env": { "PYTHONIOENCODING": "utf-8" }
    }
  }
}
```

## 工具清单 (297)

| 类别 | 数量 | 说明 |
|------|------|------|
| 实时数据层 | 4 | game_window_info (窗口识别) / game_log_stream (增量日志流+结构化事件 9 类) / game_realtime_stats (实时聚合) / game_highfreq_monitor (近帧级毫秒采样: 进程 CPU 时钟+日志增量) |
| 扩展工具 | 11 | game_ping / game_status (附窗口信息) / game_reset / game_force_state / game_launch (GUI 等待窗口+返回窗口信息) / game_stop / game_load_replay / game_read_log / game_read_log_file (日志文件验证通道) / game_window_info (窗口识别: 标题/句柄/尺寸/前台, Windows) / game_script |
| 流程聚合工具 | 8 | game_skirmish_start (开局) / game_skirmish_prep (开局+资金+单位) / game_load_save (存档) / game_replay_flow (回放流程) / game_unit_army (部队) / game_view (视角) / game_state (安全状态) / game_safe_query (安全查询) |
| 单位数据层 | 4 | game_unit_names (全单位真实名字查询) / game_unit_info (单位 ini 真实属性) / game_unit_dump (存档单位真实数据) / game_victory_check (胜利判定, 按游戏标准) |
| debug_* | 67 | 对局操作 (createUnit/createManyUnits/setTeamCredits/moveAllUnitsOnTeam/lookAt/setZoom 等) |
| root_* | 155 | UI/流程 (startNew/loadReplay/showMaps/exit 等) |
| mods_* | 16 | Mod 管理 (loadMods/refreshModList/disableAll 等, 实测安全) |
| multiplayer_* | 33 | 联机大厅 (⚠️ 联机专用, 主菜单/单机调用可能 NPE 崩游戏, 已标注) |

参数类型自动映射: String→str / boolean→bool / int→int / float→float / long→int。
script_api.json 已扩展为 4 对象 271 方法 (debug+root+mods+multiplayer, 02b 源码提取)。
方法签名含对象参数 (Element/n/ArrayList) 的不可脚本化, 用 game_script 高级调用。

## 📊 单位数据层 (真实数据通道)

脚本引擎无法取返回值, 单位/胜利的**真实数据**通过三个独立通道获取 (全部实测验证):

| 工具 | 数据来源 | 内容 |
|------|----------|------|
| game_unit_names | 两份静态名字表 | 52 个内置单位类型名 (UnitType 枚举, 如 tank/heavyTank/mammothTank/battleShip) + 125 个 ini 注册名 (c_mammothTank/c_tank/bomber 等); prefix 可过滤 |
| game_unit_info | assets/units/*.ini | 单位真实静态属性: maxHp/price/mass/movespeed/maxattackrange/directdamage/techlevel 等 (按段输出, 如 core.maxhp) |
| game_unit_dump | 纯文本存档 (saves/*.rwsave) | 每个单位的 id/类型/队伍/血量/位置/存活 — 存档真实快照 (hp=-1 表示满血哨兵值) |
| game_victory_check | 游戏日志文件 | 胜利判定: 日志中 'wiped out'/'defeated'/'eliminated' 广播 (游戏真实消息, 如 `AI - Hard has been wiped out (Team: A)`); 对局中附加 debug.isTeamInVictory 调用 (仅存在性辅助) |

**单位命名空间说明** (重要):
- **内置枚举名** (52): 游戏硬编码单位 (commandCenter/landFactory/tank/heavyTank/mammothTank...), 无 ini; 存档类型名用此命名空间
- **ini 注册名** (125): assets/units 的 `name: xxx` 字段 (c_mammothTank/c_tank...), 有 ini 属性可查; 同名覆盖内置 (如 tank.ini → c_tank)
- 两类名字均可直接用于 debug.createManyUnits (实测有效)

**存档 dump 前提**: 需要纯文本存档 — 对局内调用 `debug.plainTextDebugSave(true)` + `root.saveGame('名字')` 生成 (主菜单调用 plainTextDebugSave 会崩, 仅对局内安全)。

**胜利判定注意**: 日志中队伍显示为字母 (Team: A) 且与内部 team 索引不一致 — 建议不传 team 全量检查, 或依赖 wiped_out_team_letters 字段判断。

## 🛡️ 崩溃防护层 (v19.133f98 优化)

调用自动防护, 防止 MCP 调用导致游戏崩溃:

1. **危险方法硬拦截**: DANGEROUS 表 + multiplayer.* → 工具直接返回 blocked (不发送命令, 游戏零风险)
2. **谨慎方法描述标注**: WARN_NOTES 表 (createUnit/plainTextDebugSave/removeAllUnits/killAllUnits 等) → 工具描述 ⚠️ 警告, AI 可见 (不拦截, 状态过滤已保证对局内才可调)
3. **单位类型预检**: game_unit_army/game_skirmish_prep 刷单位前查名字表 (52 内置 + 125 ini), 无效类型直接报错 (消灭"调用成功但没刷出单位")
4. **状态机过滤**: UNKNOWN/MAIN_MENU/STARTING 状态拦截 debug.* (实测主菜单调用会崩); 开局命令 (root_startNew) 成功 → 确定性 IN_GAME; loadReplay 成功 → REPLAY
5. **崩溃检测**: onGameCrash/连接重置 → CRASHED 状态 → 所有游戏方法拦截 + 提示 game_reset + game_launch
6. **崩溃恢复**: game_reset (清理进程+重置状态机) → game_launch 重启
7. **专家纠正**: game_force_state 手动声明状态 (状态机推断失误时)

状态机: UNKNOWN/STARTING/MAIN_MENU/IN_GAME/REPLAY/CRASHED (game_status 可查)。

## ⚠️ 危险方法 (原版固有, 对局中调用会崩游戏)

| 方法 | 说明 |
|------|------|
| debug.getLocalPlayerId | 原版固有 NPE (对照验证), 勿用 |
| debug.getPlayerName | 原版固有 NPE (对照验证), 勿用 |
| debug.setNetworkaiDifficulty | 开局前设置方法, 对局中调用 NPE 崩游戏 (MCP 实测) |
| debug.setNetworkStartingUnits | 开局前设置方法, 对局中慎用 |
| debug.enableFastSync / checkDesync | 网络同步方法, 慎用 |
| multiplayer.* (全部 33) | 联机大厅专用: 主菜单/单机调用可能 NPE 崩游戏 (MCP 实测 loadUsername) |
| debug.createUnit (对局中偶发) | 偶发连接重置 (createManyUnits 稳定, 优先用) |

工具描述中带 ⚠️ 标注。调用危险方法后游戏会崩溃 — 容错层返回 ok=False + 连接重置报告。

## 文件

- `mcp_game_server.py` — MCP 服务器 (mcp SDK 2.x MCPServer, stdio)
- `unit_type_names.json` / `unit_names.json` — 单位真实名字表 (game_unit_names 数据源)
- 协议依赖: `tools/capture/debug_client.py` (同协议) + `tools/capture/script_api.json` (222 方法签名白名单)
