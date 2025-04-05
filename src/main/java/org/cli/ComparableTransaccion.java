package org.cli;

public interface ComparableTransaccion extends Comparable<Transaccion> {
    @Override
    int compareTo(Transaccion t);
}
