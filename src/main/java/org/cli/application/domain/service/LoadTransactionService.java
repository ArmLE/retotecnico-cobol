package org.cli.application.domain.service;

import org.cli.application.domain.model.Transaccion;
import org.cli.application.port.out.LoadTransaccionPort;

import java.io.FileNotFoundException;
import java.util.List;

public class LoadTransactionService {
    private final LoadTransaccionPort loadTransaccionPort;

    public LoadTransactionService(LoadTransaccionPort loadTransaccionPort) {
        this.loadTransaccionPort = loadTransaccionPort;
    }
    public List<Transaccion> loadCsv(String filename) {
        return loadTransaccionPort.loadFromLocal(filename);
    }
}
