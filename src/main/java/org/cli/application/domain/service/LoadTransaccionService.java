package org.cli.application.domain.service;

import org.cli.application.domain.model.Transaccion;
import org.cli.application.port.out.LoadTransaccionPort;

import java.util.List;

public class LoadTransaccionService {
    private final LoadTransaccionPort loadTransaccionPort;

    public LoadTransaccionService(LoadTransaccionPort loadTransaccionPort) {
        this.loadTransaccionPort = loadTransaccionPort;
    }
    public List<Transaccion> loadCsv(String filename) {
        return loadTransaccionPort.loadFromLocal(filename);
    }
}
