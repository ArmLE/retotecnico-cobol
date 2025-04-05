package org.cli.adapter.out;

import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;
import org.cli.application.domain.model.Transaccion;
import org.cli.application.port.out.LoadTransaccionPort;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FileAdapter implements LoadTransaccionPort {
    @Override
    public List<Transaccion> loadFromLocal(String filename) {

        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        CsvRoutines routines = new CsvRoutines(settings);

        return routines.parseAll(Transaccion.class, loadFromFileSystem(filename));
    }
    private InputStream loadFromFileSystem(String filename) {
        try {
            return new FileInputStream(filename);
        } catch (IOException | NullPointerException e) {
            System.err.println("File not found in the filesystem: " + filename);
            System.out.println("Using a internal file input.csv of own resources ");
            System.out.println();
            return getClass().getClassLoader().getResourceAsStream("input.csv");
        }
    }
}
