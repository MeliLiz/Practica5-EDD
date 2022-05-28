package src.edd;

//import java.util.Comparator;

public interface Collection<T> extends Iterable<T>{

    /**
     * Agrega un elemento a la coleccion.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
    */
    public void add(T elemento);

    /**
     * Elimina un elemento de la coleccion.
     * 
     * @param elemento el elemento a eliminar.
     */
    //public boolean delete(T elemento);

    /**
     * Regresa un elemento de la coleccion.
     * y lo elimina.
     * 
     * @return El elemento a sacar.
     */
    //public T pop();

    /**
     * Regresa el numero de elementos en la coleccion.
     * 
     * @return el numero de elementos en la coleccion.
     */
    public int size();

    /**
     * Nos dice si un elemento esta contenido en la coleccion.
     * 
     * @param elemento el elemento que queremos verificar si esta contenido en
     *                 la coleccion.
     * @return <code>true</code> si el elemento esta contenido en la coleccion,
     *         <code>false</code> en otro caso.
     */
    public boolean contains(T elemento);

    /**
     * Vacia la coleccion.
     * 
     */
    public void empty();

    /**
     * Nos dice si la coleccion es vacia.
     * 
     * @return <code>true</code> si la coleccion es vacia, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty();
    
    /**
     * Nos dice si la coleccion es igual a otra coleccion recibida.
     * 
     * @param coleccion la coleccion con el que hay que comparar.
     * @return <tt>true</tt> si la coleccion es igual a la coleccion recibido;
     *         <tt>false</tt> en otro caso.
     */
    public boolean equals(Object o);

    /**
     * Regresa una representacion en cadena de la coleccion.
     * 
     * @return una representacion en cadena de la coleccion.
     */
    public String toString();
    
    /**
     * Metodo que invierte el orden de la coleccion .
     * 
     */
    //public void reverse();

    /**
     * Regresa una copia de la coleccion.
     * 
     * @return una copia de la coleccion.
     */
    //public Collection<T> clone();


}
