package src.edd;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Interfaz para iteradores de lista. Un iterador de lista se puede visualizar
 * como que esta siempre entre dos elementos de la lista, o antes del primero, o
 * despues del ultimo.
 */
public interface IteradorLista<T> extends Iterator<T> {

    /**
     * Nos dice si hay un elemento anterior. El metodo debe regresar
     * <tt>true</tt>, excepto cuando la lista este vacia, o el iterador este
     * antes del primer elemento.
     * @return <tt>true</tt> si el iterador tiene un elemento a su izquierda,
     *         <tt>false</tt> en otro caso.
     */
    public boolean hasPrevious();

    /**
     * Regresa el elemento anterior al iterador, y lo mueve a la izquierda.
     * @return el elemento anterior al iterador.
     * @throws NoSuchElementException si el iterador no tiene elemento anterior.
     */
    public T previous();

    /**
     * Mueve el iterador a la izquierda del primer elemento. Despues de llamar
     * este metodo, el metodo {@link Iterator#hasNext} siempre regresa
     * <tt>true</tt> si la lista no es vacia.
     */
    public void start();

    /**
     * Mueve el iterador a la derecha del ultimo elemento. Despues de llamar
     * este metodo, el metodo {@link IteradorLista#hasPrevious} siempre regresa
     * <tt>true</tt> si la lista no es vacia.
     */
    public void end();
}
