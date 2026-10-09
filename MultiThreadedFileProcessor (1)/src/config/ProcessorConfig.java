package config;

public final class ProcessorConfig {
    private final int threadCount;
    private final String inputFolder;
    private final String outputFile;
    private final boolean skipHeader;
    private final char delimiter;

    private ProcessorConfig(Builder builder) {
        if (builder.threadCount < 1) {
            throw new IllegalArgumentException("threadCount must be at least 1");
        }
        this.threadCount = builder.threadCount;
        this.inputFolder = builder.inputFolder;
        this.outputFile = builder.outputFile;
        this.skipHeader = builder.skipHeader;
        this.delimiter = builder.delimiter;
    }

    public int getThreadCount() { return threadCount; }
    public String getInputFolder() { return inputFolder; }
    public String getOutputFile() { return outputFile; }
    public boolean isSkipHeader() { return skipHeader; }
    public char getDelimiter() { return delimiter; }

    @Override
    public String toString() {
        return "ProcessorConfig{threadCount=" + threadCount
                + ", inputFolder='" + inputFolder + '\''
                + ", outputFile='" + outputFile + '\''
                + ", skipHeader=" + skipHeader
                + ", delimiter='" + delimiter + "'}";
    }

    public static class Builder {
        private int threadCount = 3;
        private String inputFolder = "data";
        private String outputFile = "output/report.txt";
        private boolean skipHeader = true;
        private char delimiter = ',';

        public Builder threadCount(int value) { this.threadCount = value; return this; }
        public Builder inputFolder(String value) { this.inputFolder = value; return this; }
        public Builder outputFile(String value) { this.outputFile = value; return this; }
        public Builder skipHeader(boolean value) { this.skipHeader = value; return this; }
        public Builder delimiter(char value) { this.delimiter = value; return this; }

        public ProcessorConfig build() {
            return new ProcessorConfig(this);
        }
    }
}
