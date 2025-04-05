package org.cli;

import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{

        List<Transaccion> transactions = getTransaccions();

        Transaccion biggest = transactions.stream().max(Transaccion::compareTo).get();

        Map<String, Long> tipoCount = transactions.stream()
                .collect(Collectors.groupingBy(t -> t.tipo, Collectors.counting()));

        BigDecimal balance = getBalance(transactions);

        print(balance, tipoCount, biggest);

    }

    private static void print(BigDecimal balance, Map<String, Long> tipoCount, Transaccion biggest) {
        System.out.println("Reporte de Transacciones");
        System.out.println("---------------------------------------------");
        System.out.println("Balance Final: " + balance);
        System.out.println("Transacción de Mayor Monto: " + biggest);
        System.out.println("Conteo de Transacciones: " + tipoCount.toString().replaceAll("[^\\p{L}\\p{N}\\s=,]",""));

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

    private static List<Transaccion> getTransaccions() {
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);

        CsvRoutines routines = new CsvRoutines(settings);

        InputStream resource = Main.class.getClassLoader().getResourceAsStream("input.csv");

        return routines.parseAll(Transaccion.class, resource);
    }
}