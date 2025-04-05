package org.cli.application.domain.model;

public interface ComparableTransaccion extends Comparable<Transaccion> {
    @Override
    int compareTo(Transaccion t);
}
