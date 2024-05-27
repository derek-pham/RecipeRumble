@echo off
setlocal

REM Navigate to the source directory
cd src\main\java

REM Compile all Java files
echo Compiling Java files...
javac -d ..\..\..\..\out com\recipeRumble\*.java com\recipeRumble\game\*.java com\recipeRumble\game\foods\ingredients\*.java com\recipeRumble\game\foods\dishes\*.java com\recipeRumble\game\foods\techniques\*.java com\recipeRumble\game\locations\*.java com\recipeRumble\game\utils\*.java

REM Check if compilation was successful
if %ERRORLEVEL% neq 0 (
    echo Compilation failed.
    exit /b %ERRORLEVEL%
)

REM Navigate to the output directory
cd ..\..\..\..\out

REM Run the Main class
echo Running Main class...
java com.recipeRumble.Main

endlocal
