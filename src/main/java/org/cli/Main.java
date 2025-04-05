package org.cli;

import org.cli.adapter.out.FileAdapter;
import org.cli.application.domain.model.Transaccion;
import org.cli.application.domain.service.LoadTransactionService;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        LoadTransactionService service = new LoadTransactionService(new FileAdapter());
        Optional<String> filePath = getFilePath(args);

        List<Transaccion> transactions = service.loadCsv(filePath.orElse(null));

        Transaccion biggest = transactions.stream().max(Transaccion::compareTo).orElse(null);
        Map<String, Long> kindCount = transactions.stream().collect(
                Collectors.groupingBy(t -> t.tipo, Collectors.counting()));
        BigDecimal balance = getBalance(transactions);

        print(balance, kindCount, biggest);

    }

    private static void print(BigDecimal balance, Map<String, Long> kindCount, Transaccion biggest) {
        System.out.println("Reporte de Transacciones");
        System.out.println("---------------------------------------------");
        System.out.println("Balance Final: " + balance);
        System.out.println("Transacción de Mayor Monto: " + biggest);
        System.out.println("Conteo de Transacciones: " + kindCount.toString().replaceAll("[^\\p{L}\\p{N}\\s=,]",""));

    }

    private static BigDecimal getBalance(List<Transaccion> transactions) {
        return transactions.stream()
                .map(t ->
                    switch (t.tipo){
                        case "Débito"  -> t.monto.negate();
                        case "Crédito" -> t.monto;
                        default -> throw new IllegalStateException("Unexpected value: " + t.tipo);
                    })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static Optional<String> getFilePath(String[] args) {
        return args.length > 0 ? Optional.of(args[0]) : Optional.empty();
    }
}