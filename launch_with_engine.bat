@echo off
setlocal

set "GAME_DIR=%~dp0"
set "GAME_DIR=%GAME_DIR:~0,-1%"

echo ============================================
echo Rusted Warfare v1.15 - Simulation Engine
echo ============================================
echo.

REM Step 1: Start simulation engine
echo [1/2] Starting simulation engine on port 8090...
cd /d "%GAME_DIR%\rw_engine"
if errorlevel 1 (
    echo ERROR: Cannot find rw_engine directory
    echo Current: %CD%
    pause
    exit /b 1
)
start "RW-Engine" /MIN python server.py 8090
cd /d "%GAME_DIR%"

REM Wait for engine
echo Waiting for engine to start...
ping -n 4 127.0.0.1 >nul

REM Step 2: Launch game
echo [2/2] Launching game...
echo.

set "JAVA=%GAME_DIR%\jdk\jdk-17.0.2\bin\java.exe"
if not exist "%JAVA%" set "JAVA=%GAME_DIR%\jvm64\bin\java.exe"
if not exist "%JAVA%" (
    echo WARNING: No JDK found, trying system java...
    set "JAVA=java"
)

set "CP=%GAME_DIR%\game-lib.jar;%GAME_DIR%\libs\*"
"%JAVA%" -Xmx1000M -Dfile.encoding=UTF-8 -Djava.library.path="%GAME_DIR%" -cp "%CP%" com.corrodinggames.rts.java.Main -width 1024 -height 768

echo.
echo Game closed.
pause
