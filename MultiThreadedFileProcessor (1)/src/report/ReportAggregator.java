package report;

import model.SalesRecord;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReportAggregator {
    private final List<SalesRecord> records = new ArrayList<>();

    public void addRecords(List<SalesRecord> newRecords) {
        records.addAll(newRecords);
    }

    public void generateReport(String outputFile) throws IOException {
        Path path = Path.of(outputFile);
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        DecimalFormat money = new DecimalFormat("#,##0.00");
        int totalUnits = records.stream().mapToInt(SalesRecord::getQuantity).sum();
        double totalRevenue = records.stream().mapToDouble(SalesRecord::getTotal).sum();
        Map<String, Integer> quantityByProduct = new TreeMap<>();
        Map<String, Double> revenueByProduct = new TreeMap<>();

        for (SalesRecord record : records) {
            quantityByProduct.merge(record.getProduct(), record.getQuantity(), Integer::sum);
            revenueByProduct.merge(record.getProduct(), record.getTotal(), Double::sum);
        }

        List<Map.Entry<String, Double>> rankedProducts = new ArrayList<>(revenueByProduct.entrySet());
        rankedProducts.sort(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()));

        StringBuilder report = new StringBuilder();
        report.append("MULTI-THREADED CSV FILE PROCESSOR REPORT\n");
        report.append("========================================\n\n");
        report.append("Valid records processed: ").append(records.size()).append('\n');
        report.append("Total units sold: ").append(totalUnits).append('\n');
        report.append("Total revenue: ").append(money.format(totalRevenue)).append("\n\n");
        report.append("PRODUCT SUMMARY\n");
        report.append("---------------\n");
        report.append(String.format("%-24s %12s %16s%n", "Product", "Units sold", "Revenue"));

        for (Map.Entry<String, Integer> entry : quantityByProduct.entrySet()) {
            report.append(String.format("%-24s %12d %16s%n",
                    entry.getKey(), entry.getValue(),
                    money.format(revenueByProduct.getOrDefault(entry.getKey(), 0.0))));
        }

        report.append("\nTOP PRODUCTS BY REVENUE\n");
        report.append("-----------------------\n");
        int limit = Math.min(5, rankedProducts.size());
        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Double> entry = rankedProducts.get(i);
            report.append(i + 1).append(". ").append(entry.getKey())
                    .append(" — ").append(money.format(entry.getValue())).append('\n');
        }

        Files.writeString(path, report.toString(), StandardCharsets.UTF_8);
        System.out.println("Processed " + records.size() + " valid record(s).");
    }
}
