REM  Updating project
git reset --hard
git pull origin HEAD
REM create variable that stores the project folder path. This variable will used in the subsequent statements.
set javaTestProjectPath=%~dp0
set TESTNGFILE=RunTests.xml
REM move to the project folder
cd /d %javaTestProjectPath%
REM set path to dir that contains javac.exe and java.exe
@echo off
set /p n= What server do you want to test? DEV or TEST:
set /p s= Where do you want run test? LOCAL or REMOTE:
mvn -f pom.xml test -Denvironment=%n% -Dserver=%s%