REM  Updating project

REM create variable that stores the project folder path. This variable will used in the subsequent statements.
set javaTestProjectPath=%~dp0
set TESTNGFILE=RunTests.xml
REM move to the project folder
cd /d %javaTestProjectPath%
REM set path to dir that contains javac.exe and java.exe
@echo off
set /p n= What server do you want to test? DEV or TEST:
set /p s= Where do you want run test? LOCAL or REMOTE:
echo Select suite for execution:
echo Run_AcceptanceTests.xml type 1
echo Run_Donations_Suite_Completed.xml type 2
echo Run_EmailKPI_Suite_Completed.xml type 3
set /p t= Enter number of suite and press Enter:
IF %t% =="1" set suite="Run_AcceptanceTests.xml"
IF %t% == 2 set suite="Run_Donations_Suite_Completed.xml"
IF %t% =="3" set suite="Run_EmailKPI_Suite_Completed.xml"
mvn -f pom.xml install -Denvironment=%n% -Dserver=%s% -DtestSuite=%suite%