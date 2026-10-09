@echo off
cd /d "%~dp0"
if not exist out mkdir out
javac -d out src\Main.java src\config\ProcessorConfig.java src\model\SalesRecord.java src\processor\CsvFileProcessor.java src\report\ReportAggregator.java
if errorlevel 1 (
  echo Compilation failed. Check the error messages above.
  pause
  exit /b 1
)
java -cp out Main
echo.
echo If successful, open output\report.txt
pause
