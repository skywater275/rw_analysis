@echo off
chcp 65001 >nul
rem ============================================================
rem  测试台.bat [命令] — 游戏完全体·测试台 维护入口
rem
rem    verify（默认）   现场校验 35 项判据（只读，退出码 0=PASS）
rem    list             列出 jar 形态与当前部署
rem    switch 形态      切换 jar：stock / d3-era / current
rem    manifest         重新生成 MANIFEST.json
rem    smoke            真实 GUI 启动冒烟测试（不是回放回归）
rem    live             实时对局数据面板（需游戏以 -debug 端口运行）
rem    help             本帮助
rem ============================================================
setlocal
cd /d "%~dp0"
set "PYTHONIOENCODING=utf-8"
title Rusted Warfare - 测试台

set "RWREV=%~dp0rw-reverse"
if not exist "%RWREV%\tools\rig\verify_rig.py" set "RWREV=%~dp0rw源码逆向"
if not exist "%RWREV%\tools\rig\verify_rig.py" goto :norw

set "CMD=%~1"
if "%CMD%"=="" set "CMD=verify"
if /i "%CMD%"=="verify"   goto :verify
if /i "%CMD%"=="list"     goto :list
if /i "%CMD%"=="switch"   goto :switch
if /i "%CMD%"=="manifest" goto :manifest
if /i "%CMD%"=="smoke"    goto :smoke
if /i "%CMD%"=="live"     goto :live
if /i "%CMD%"=="help"     goto :help
echo [错误] 未知命令: %CMD%
goto :help

:verify
python "%RWREV%\tools\rig\verify_rig.py"
goto :pause_end

:list
python "%RWREV%\tools\rig\switch_jar.py" --list
goto :pause_end

:switch
if "%~2"=="" goto :switch_usage
python "%RWREV%\tools\rig\switch_jar.py" --to %~2 --apply
goto :pause_end

:manifest
python "%RWREV%\tools\rig\make_manifest.py"
goto :pause_end

:smoke
python "%RWREV%\tools\rig\smoke_test.py" --wait 40
goto :pause_end

:live
python "%RWREV%\tools\rig\live_stats.py" --unlock --append --interval 1 --teams 6
goto :pause_end

:help
python "%RWREV%\tools\rig\rig_help.py" tool
goto :pause_end

:switch_usage
echo [错误] 需要形态参数，例如: 测试台.bat switch stock
goto :pause_end

:pause_end
echo.
pause
goto :eof

:norw
echo [错误] 未找到逆向仓 — 需含 tools\rig\verify_rig.py
pause
exit /b 1
