package src.edd;

import java.util.NoSuchElementException;
/**
 * Interfaz para vertices de arboles binarios.
 */
public interface VerticeArbolBinario<T> {

    /**
     * Nos dice si el vertice tiene vertice padre.
     * 
     * @return <tt>true</tt> si el vertice tiene vertice padre, <tt>false</tt>
     *         en otro caso.
     */
    public boolean hayPadre();

    /**
     * Nos dice si el vertice tiene vertice izquierdo.
     * 
     * @return <tt>true</tt> si el vertice tiene vertice izquierdo,
     *         <tt>false</tt> en otro caso.
     */
    public boolean hayIzquierdo();

    /**
     * Nos dice si el vertice tiene vertice derecho.
     * 
     * @return <tt>true</tt> si el vertice tiene vertice derecho, <tt>false</tt>
     *         en otro caso.
     */
    public boolean hayDerecho();

    /**
     * Regresa el vertice padre del vertice.
     * 
     * @return el vertice padre del vertice.
     * @throws NoSuchElementException si el vertice no tiene padre.
     */
    public VerticeArbolBinario<T> padre();

    /**
     * Regresa el vertice izquierdo del vertice.
     * 
     * @return el vertice izquierdo del vertice.
     * @throws NoSuchElementException si el vertice no tiene izquierdo.
     */
    public VerticeArbolBinario<T> izquierdo();

    /**
     * Regresa el vertice derecho del vertice.
     * 
     * @return el vertice derecho del vertice.
     * @throws NoSuchElementException si el vertice no tiene derecho.
     */
    public VerticeArbolBinario<T> derecho();

    /**
     * Regresa la altura del vertice.
     * 
     * @return la altura del vertice.
     */
    public int altura();

    /**
     * Regresa la profundidad del vertice.
     * 
     * @return la profundidad del vertice.
     */
    public int profundidad();

    /**
     * Regresa el elemento que contiene el vertice.
     * 
     * @return el elemento que contiene el vertice.
     */
    public T get();
}