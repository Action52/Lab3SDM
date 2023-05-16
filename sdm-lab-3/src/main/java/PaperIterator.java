import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class PaperIterator implements Iterator<PaperRow> {

    private Iterator<CSVRecord> iterator;

    public PaperIterator(String csvFilePath) throws IOException {
        Reader reader = new FileReader(csvFilePath);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withIgnoreEmptyLines());
        iterator = csvParser.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public PaperRow next() {
        CSVRecord record = iterator.next();
        PaperRow paper = new PaperRow(record);
        return paper;
    }
}