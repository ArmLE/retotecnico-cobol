package org.cli.application.domain.model;

import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.Trim;

import java.math.BigDecimal;

public class Transaccion implements ComparableTransaccion{
    @Parsed(field = "id")
    private BigDecimal id;
    @Trim
    @Parsed(field = "tipo")
    public String tipo;
    @Parsed(field = "monto")
    public BigDecimal monto;

    @Override
    public String toString() {
        return "ID " + id +" - " + monto;
    }

    @Override
    public int compareTo(Transaccion t) {
        return monto.compareTo(t.monto);
    }
}

