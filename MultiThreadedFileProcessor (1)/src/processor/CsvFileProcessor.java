package processor;

import config.ProcessorConfig;
import model.SalesRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CsvFileProcessor implements Callable<List<SalesRecord>> {
    private final Path filePath;
    private final ProcessorConfig config;

    public CsvFileProcessor(Path filePath, ProcessorConfig config) {
        this.filePath = filePath;
        this.config = config;
    }

    @Override
    public List<SalesRecord> call() throws IOException {
        List<SalesRecord> records = new ArrayList<>();
        System.out.println("[" + Thread.currentThread().getName() + "] Reading " + filePath.getFileName());

        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) continue;
                if (lineNumber == 1 && config.isSkipHeader()) continue;

                String[] columns = line.split(
                        java.util.regex.Pattern.quote(String.valueOf(config.getDelimiter())), -1
                );
                if (columns.length != 3) {
                    System.err.println("Skipping " + filePath.getFileName() + " line "
                            + lineNumber + ": expected 3 columns (product,quantity,unitPrice).");
                    continue;
                }

                try {
                    String product = columns[0].trim();
                    int quantity = Integer.parseInt(columns[1].trim());
                    double unitPrice = Double.parseDouble(columns[2].trim());
                    records.add(new SalesRecord(product, quantity, unitPrice));
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping " + filePath.getFileName() + " line "
                            + lineNumber + ": " + e.getMessage());
                }
            }
        }

        System.out.println("[" + Thread.currentThread().getName() + "] Finished "
                + filePath.getFileName() + " (" + records.size() + " valid records)");
        return records;
    }
}
