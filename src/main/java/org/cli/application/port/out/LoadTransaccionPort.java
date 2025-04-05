package org.cli.application.port.out;

import org.cli.application.domain.model.Transaccion;

import java.io.FileNotFoundException;
import java.util.List;

public interface LoadTransaccionPort {
    List<Transaccion> loadFromLocal(String filename);
}
