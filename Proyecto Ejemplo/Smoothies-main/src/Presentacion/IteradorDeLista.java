package Presentacion;

import java.util.Iterator;
import java.util.List;

public class IteradorDeLista<T> implements Iterator<T> {
    private List<T> lista; 
    private int indice;

    public IteradorDeLista(List<T> lista) {
        this.lista = lista;
        this.indice = 0;
    }

    @Override
    public boolean hasNext() {
        return indice < lista.size();
    }

    @Override
    public T next() {
        if (hasNext()) {
            T elemento = lista.get(indice);
            indice++;
            return elemento;
        } else {
            throw new IndexOutOfBoundsException("No hay más elementos en la lista");
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove no está soportado en este iterador");
    }
}
