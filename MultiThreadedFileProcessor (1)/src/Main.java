import config.ProcessorConfig;
import model.SalesRecord;
import processor.CsvFileProcessor;
import report.ReportAggregator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("     Multi-Threaded CSV File Processor");
        System.out.println("============================================");

        ProcessorConfig config = new ProcessorConfig.Builder()
                .threadCount(3)
                .inputFolder("data")
                .outputFile("output/report.txt")
                .skipHeader(true)
                .delimiter(',')
                .build();

        System.out.println("\nConfiguration: " + config);

        File folder = new File(config.getInputFolder());
        File[] csvFiles = folder.listFiles(
                (dir, name) -> name.toLowerCase().endsWith(".csv")
        );

        if (csvFiles == null || csvFiles.length == 0) {
            System.err.println("No CSV files found in: " + folder.getAbsolutePath());
            System.err.println("Add one or more .csv files to the data folder and try again.");
            return;
        }

        System.out.println("\nFound " + csvFiles.length + " CSV file(s):");
        for (File file : csvFiles) {
            System.out.println("  - " + file.getName());
        }

        ExecutorService executor = Executors.newFixedThreadPool(config.getThreadCount());
        List<Future<List<SalesRecord>>> futures = new ArrayList<>();
        ReportAggregator aggregator = new ReportAggregator();

        try {
            for (File csvFile : csvFiles) {
                futures.add(executor.submit(new CsvFileProcessor(csvFile.toPath(), config)));
            }

            System.out.println("\nProcessing files concurrently...");
            for (Future<List<SalesRecord>> future : futures) {
                try {
                    aggregator.addRecords(future.get());
                } catch (ExecutionException e) {
                    System.err.println("A file-processing task failed: " + e.getCause().getMessage());
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Processing was interrupted.");
        } finally {
            executor.shutdown();
        }

        System.out.println("\nGenerating report...");
        try {
            aggregator.generateReport(config.getOutputFile());
            System.out.println("Done! Report saved to: " + config.getOutputFile());
        } catch (Exception e) {
            System.err.println("Could not write report: " + e.getMessage());
        }
    }
}
