@echo off
echo ========================================
echo Starting Internship Management System
echo ========================================
echo.

REM Set JAVA_HOME
set JAVA_HOME=C:\Program Files\Java\jdk-21
echo JAVA_HOME set to: %JAVA_HOME%
echo.

REM Check if MySQL is running
netstat -an | findstr ":3306" >nul
if %errorlevel% neq 0 (
    echo WARNING: MySQL does not appear to be running on port 3306!
    echo Please start MySQL (WAMP) before continuing.
    echo.
    pause
)

echo MySQL check: OK
echo.
echo Starting application...
echo This may take 1-2 minutes on first run...
echo.
call .\maven\apache-maven-3.9.6\bin\mvn.cmd spring-boot:run

pause

