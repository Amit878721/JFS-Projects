# Multi-Threaded CSV File Processor

A beginner-friendly Java project that processes multiple sales CSV files concurrently and generates a combined text report.

## Features

- Reads every `.csv` file from the `data/` folder.
- Processes files concurrently with a fixed thread pool.
- Uses `Callable` and `Future` to return each task's records.
- Validates rows and skips malformed or invalid records.
- Aggregates quantities and revenue by product.
- Writes a report to `output/report.txt`.
- Demonstrates OOP, packages, file handling, the Builder pattern, collections, and exception handling.

## Requirements

- Java 11 or newer (Java 17+ recommended)
- No third-party libraries required

## Run from the project root

### Windows PowerShell

```powershell
javac -d out src\Main.java src\config\ProcessorConfig.java src\model\SalesRecord.java src\processor\CsvFileProcessor.java src\report\ReportAggregator.java
java -cp out Main
```

### macOS / Linux

```bash
mkdir -p out
javac -d out src/Main.java src/config/ProcessorConfig.java src/model/SalesRecord.java src/processor/CsvFileProcessor.java src/report/ReportAggregator.java
java -cp out Main
```

After a successful run, open `output/report.txt`.

## CSV format

Each file should have a header and three columns:

```csv
product,quantity,unitPrice
Notebook,25,2.50
Pen,50,0.80
```

Place additional CSV files in `data/`. The default configuration skips the first line of each CSV file.

## Configuration

Edit the `ProcessorConfig.Builder` chain in `src/Main.java` to change the number of threads, input folder, output path, or delimiter.

## Notes

- This sample parser intentionally supports simple comma-separated rows; quoted fields containing commas are not handled.
- Output totals are formatted as numbers without a currency symbol because no currency is assumed.
