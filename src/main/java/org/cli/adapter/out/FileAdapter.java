package org.cli.adapter.out;

import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;
import org.cli.Main;
import org.cli.application.domain.model.Transaccion;
import org.cli.application.port.out.LoadTransaccionPort;

import java.io.InputStream;
import java.util.List;

public class FileAdapter implements LoadTransaccionPort {
    @Override
    public List<Transaccion> loadFromLocal(String filename) {
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);

        CsvRoutines routines = new CsvRoutines(settings);

        InputStream resource = Main.class.getClassLoader().getResourceAsStream(filename);

        return routines.parseAll(Transaccion.class, resource);
    }
}
