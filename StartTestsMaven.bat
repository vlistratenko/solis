REM create variable that stores the project folder path. This variable will used in the subsequent statements.
set javaTestProjectPath=%~dp0
set TESTNGFILE=RunTests.xml
REM move to the project folder
cd /d %javaTestProjectPath%
REM set path to dir that contains javac.exe and java.exe
REM set path=C:\Program Files\Java\jdk1.7.0_11\bin
@echo off
set /p n= What type of content, do you wont to post? 1 - Link or 2 - Photo:
mvn -f pom%n%.xml test