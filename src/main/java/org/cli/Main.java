package org.cli;

import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;
import org.cli.adapter.out.FileAdapter;
import org.cli.application.domain.model.Transaccion;
import org.cli.application.domain.service.LoadTransaccionService;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{

        LoadTransaccionService service = new LoadTransaccionService(new FileAdapter());

        List<Transaccion> transactions = service.loadCsv("input.csv");

        Transaccion biggest = transactions.stream().max(Transaccion::compareTo).orElse(null);

        Map<String, Long> kindCount = transactions.stream()
                .collect(Collectors.groupingBy(t -> t.tipo, Collectors.counting()));

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
}