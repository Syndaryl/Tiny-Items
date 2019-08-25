@echo off
echo.
echo Remote Desktop will be temporarily disconnected . . . please reconnect after a few seconds.
echo.
pause
@echo on
REM The active session has an arrow as the first character
setlocal EnableDelayedExpansion
FOR /F %%A in ('qwinsta') do (
	set tempSessionName=%%A
	if "!tempSessionName:~0,1!"==">"  ( 
		@echo on
		tscon.exe !tempSessionName:~1! /v /dest:console 
		@echo off
	)	
)
@echo off
echo.
echo Starting modded minecraft . . .
@echo on
echo S!nd4r1l | runas /user:owner /savecred "D:\Git\Minecraft-MugItem\gradlew.bat runClient"
@echo off
echo.
pause