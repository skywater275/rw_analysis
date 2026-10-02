@echo off
chcp 65001 >nul
rem ============================================================
rem  启动游戏.bat [模式] — 游戏完全体·测试台 启动入口（默认带实时日志窗口）
rem
rem  编码约定：UTF-8(BOM) + CRLF；开头 chcp 65001；Python 用 PYTHONIOENCODING=utf-8；
rem            Java 用 -Dsun.stdout.encoding=UTF-8（游戏 stdout 与 -log 文件均为 UTF-8）
rem
rem    无参数 / console   实时日志窗口（直连 java，最"原味"的实时输出）
rem    verbose            ★ 日志监管器：分类上色 + 全量归档 + 每 15s 数据摘要 + 崩溃判据
rem                         （= 调试端口 5677 + -logcolor + -replay_debug + -debugscript）
rem    debug              实时日志窗口 + 调试端口 5677（供 MCP 桥接）
rem    file               写文件日志 lastrun-bat.log（控制台仅少量早期输出）
rem    overlay            实验：classpath 前置 patch 目录（带实时日志）
rem    stock / d3-era / current   先切换 jar 形态，再带日志启动
rem    exe                用官方启动器（无日志窗口：其 java 子进程是 javaw）
rem    help               中文帮助
rem ============================================================
setlocal
cd /d "%~dp0"
set "PYTHONIOENCODING=utf-8"
title Rusted Warfare - 游戏实时日志

set "RWREV=%~dp0rw-reverse"
if not exist "%RWREV%\tools\rig\switch_jar.py" set "RWREV=%~dp0rw源码逆向"
if not exist "%RWREV%\tools\rig\switch_jar.py" goto :norw

python "%RWREV%\tools\rig\rig_help.py" banner

set "MODE=%~1"
if "%MODE%"=="" goto :console
if /i "%MODE%"=="console" goto :console
if /i "%MODE%"=="verbose" goto :verbose
if /i "%MODE%"=="debug"   goto :debug
if /i "%MODE%"=="file"    goto :file
if /i "%MODE%"=="overlay" goto :overlay
if /i "%MODE%"=="exe"     goto :exe
if /i "%MODE%"=="stock"   goto :switch
if /i "%MODE%"=="d3-era"  goto :switch
if /i "%MODE%"=="current" goto :switch
if /i "%MODE%"=="help"    goto :help
echo [错误] 未知模式: %MODE%
goto :help

:switch
echo [测试台] 切换 jar 形态: %MODE%
python "%RWREV%\tools\rig\switch_jar.py" --to %MODE% --apply
if errorlevel 1 goto :failed
python "%RWREV%\tools\rig\rig_help.py" banner
goto :console

:console
if not exist "game-lib.jar" goto :nojar
set "CP=game-lib.jar;libs/*"
echo [测试台] 启动：实时日志窗口（关闭本窗口 = 结束游戏）
jvm64\bin\java.exe -Xmx1000M -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -Djava.library.path=. -cp "%CP%" com.corrodinggames.rts.java.Main -logcolor
goto :after

:verbose
if not exist "game-lib.jar" goto :nojar
echo [测试台] 启动：日志监管器（分类上色 + 全量归档 + 数据侧栏 + 崩溃判据）
python "%RWREV%\tools\rig\run_game.py" --debug-port 5677 --sidebar 15 --replay-debug --debugscript "%RWREV%\tools\rig\verbose-script.txt"
goto :after

:debug
if not exist "game-lib.jar" goto :nojar
set "CP=game-lib.jar;libs/*"
echo [测试台] 启动：实时日志窗口 + 调试端口 5677
jvm64\bin\java.exe -Xmx1000M -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -Djava.library.path=. -cp "%CP%" com.corrodinggames.rts.java.Main -logcolor -debug 5677:local
goto :after

:file
if not exist "game-lib.jar" goto :nojar
set "CP=game-lib.jar;libs/*"
echo [测试台] 启动：文件日志 lastrun-bat.log（控制台仅少量早期输出）
jvm64\bin\java.exe -Xmx1000M -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -Djava.library.path=. -cp "%CP%" com.corrodinggames.rts.java.Main -logcolor -log lastrun-bat.log
goto :after

:overlay
if not exist "patch" goto :nopatch
if not exist "game-lib.jar" goto :nojar
set "CP=patch;game-lib.jar;libs/*"
echo [测试台·实验] 启动：classpath 前置 patch 目录（带实时日志）
jvm64\bin\java.exe -Xmx1000M -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -Djava.library.path=. -cp "%CP%" com.corrodinggames.rts.java.Main -logcolor
goto :after

:exe
if not exist "game-lib.jar" goto :nojar
echo [测试台] 用官方启动器启动（此模式没有日志窗口）
start "" "Rusted Warfare - 64.exe"
goto :eof

:after
echo.
echo [测试台] 游戏进程已退出。窗口保留以便查看日志；按任意键关闭。
pause >nul
goto :eof

:help
python "%RWREV%\tools\rig\rig_help.py" start
echo.
pause
goto :eof

:norw
echo [错误] 未找到逆向仓 — 需含 tools\rig\switch_jar.py
pause
exit /b 1
:nojar
echo [错误] 缺少 game-lib.jar — 请先执行: 测试台.bat switch current
pause
exit /b 1
:nopatch
echo [错误] 缺少 patch 目录
pause
exit /b 1
:failed
echo [错误] jar 切换失败（详见上方输出）
pause
exit /b 1
