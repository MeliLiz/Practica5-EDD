package src.edd;

import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.Iterator;

/**
 * <p>
 * Clase abstracta para arboles binarios genericos.
 * </p>
 *
 * <p>
 * La clase proporciona las operaciones basicas para arboles binarios, pero
 * deja la implementacion de varias en manos de las subclases concretas.
 * </p>
 */
public abstract class ArbolBinario<T> implements Collection<T> {
    /**
     * Clase interna protegida para vertices.
     */
    protected class Vertice implements VerticeArbolBinario<T> {

        /** El elemento del vertice. */
        public T elemento;
        /** El padre del vertice. */
        public Vertice padre;
        /** El izquierdo del vertice. */
        public Vertice izquierdo;
        /** El derecho del vertice. */
        public Vertice derecho;

        /**
         * Constructor unico que recibe un elemento.
         * @param elemento el elemento del vertice.
         */
        public Vertice(T elemento) {
          if(elemento!=null)
             this.elemento=elemento;
        }

        /**
         * Nos dice si el vertice tiene vertice padre.
         * @return <tt>true</tt> si el vertice tiene vertice padre, <tt>false</tt>
         *         en otro caso.
         */
        @Override public boolean hayPadre() {
            return padre != null;
        }

        /**
         * Nos dice si el vertice tiene vertice izquierdo.
         * @return <tt>true</tt> si el vertice tiene vertice izquierdo,
         *         <tt>false</tt> en otro caso.
         */
        @Override public boolean hayIzquierdo() {
            return izquierdo != null;
        }

        /**
         * Nos dice si el vertice tiene vertice derecho.
         * @return <tt>true</tt> si el vertice tiene vertice derecho, <tt>false</tt>
         *         en otro caso.
         */
        @Override public boolean hayDerecho() {
            return derecho != null;
        }

        /**
         * Regresa el vertice padre del vertice.
         * @return el vertice padre del vertice.
         * @throws NoSuchElementException si el vertice no tiene padre.
         */
        @Override public VerticeArbolBinario<T> padre() {
            if (padre == null){
                throw new NoSuchElementException("El vertice no tiene padre.");
            }
            return padre;
        }

        /**
         * Regresa el vertice izquierdo del vertice.
         * @return el vertice izquierdo del vertice.
         * @throws NoSuchElementException si el vertice no tiene izquierdo.
         */
        @Override public VerticeArbolBinario<T> izquierdo() {
            if (izquierdo == null){
                throw new NoSuchElementException("El vertice no tiene izquierdo.");
            }
            return izquierdo;
        }

        /**
         * Regresa el vertice derecho del vertice.
         * @return el vertice derecho del vertice.
         * @throws NoSuchElementException si el vertice no tiene derecho.
         */
        @Override public VerticeArbolBinario<T> derecho() {
            if (derecho == null){
                throw new NoSuchElementException("El vertice no tiene derecho.");
            }
            return derecho;
        }

        /**
         * Regresa el elemento del vertice.
         * @return T elemento
         */
        @Override public T get() {
            return elemento;
        }

        /**
         * Regresa la altura del vertice.
         * 
         * @return la altura del vertice.
         * 
         * Hay que probar. 
         */
        @Override public int altura() {
            
            int alturaIzquierdo = 0;
            int alturaDerecho = 0;
            
            if (hayIzquierdo()){
                alturaIzquierdo = izquierdo.altura();
            }
            if (hayDerecho()){
                alturaDerecho = derecho.altura();
            }
            /*if(this==null){
                alturaDerecho=-1;
                return alturaDerecho;
            }*/
            return 1 + Math.max(alturaIzquierdo, alturaDerecho);
        }

        /**
         * Regresa la profundidad del vertice.
         * 
         * @return la profundidad del vertice.
        */
        @Override public int profundidad() {
            int profundidadPadre = 0;
            if (hayPadre()){
                profundidadPadre = padre.profundidad();
            }
            return 1 + profundidadPadre;
        }

        /**
         * Compara el vertice con otro objeto. La comparacion es
         * <em>recursiva</em>. Las clases que extiendan {@link Vertice} deben
         * sobrecargar el metodo {@link Vertice#equals}.
         * 
         * @param o el objeto con el cual se comparara el vertice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link Vertice}, su elemento es igual al elemento de este
         *         vertice, y los descendientes de ambos son recursivamente
         *         iguales; <code>false</code> en otro caso.
         */

         @Override public boolean equals(Object o){
            if (o == null || getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked")
            Vertice vertice = (Vertice) o;
            if (!this.elemento.equals(vertice.elemento)) {
                return false;
            }
            return equalsAuxDer(this,vertice) && equalsAuxIzq(this, vertice);
        }

        private boolean equalsAuxDer(Vertice a,Vertice b){
            if(a.hayDerecho() && b.hayDerecho() && b.elemento.equals(a.elemento)){
                return a.derecho.equals(b.derecho);
            }
            if(!a.hayDerecho() && !b.hayDerecho() && b.elemento.equals(a.elemento)){
                return true;
            }
            if(a.hayDerecho() && !b.hayDerecho()){
                return false;
            }
            if (!a.hayDerecho() && b.hayDerecho()) {
                return false;
            }
            return false;
        }

        private boolean equalsAuxIzq(Vertice a, Vertice b) {
            if (a.hayIzquierdo() && b.hayIzquierdo() && b.elemento.equals(a.elemento)) {
                return a.izquierdo.equals(b.izquierdo);
            }
            if (!a.hayIzquierdo() && !b.hayIzquierdo() && b.elemento.equals(a.elemento)) {
                return true;
            }
            if (a.hayIzquierdo() && !b.hayIzquierdo()) {
                return false;
            }
            if (!a.hayIzquierdo() && b.hayIzquierdo()) {
                return false;
            }
            return false;
        }

        public String toString(){
            return this.elemento.toString();
        }

    }

    /** La raiz del arbol. */
    protected Vertice raiz;
    /** El contador del numero de elementos */
    protected int elementos;

    /**
     * Constructor sin parametros 
     * 
    */
    public ArbolBinario() {

    }

    // Revisar forEach
    /**
     * Constructor que recibe una coleccion de elementos
     */
    public ArbolBinario(Collection<T> coleccion) {
        for (T elemento : coleccion){
            this.add(elemento);
        }
    }
    
    /**
     * Construye un nuevo vertice, usando una instancia de {@link Vertice}. Para
     * crear vertices se debe utilizar este metodo en lugar del operador
     * <code>new</code>, para que las clases herederas de esta puedan
     * sobrecargarlo y permitir que cada estructura de arbol binario utilice
     * distintos tipos de vertices.
     * 
     * @param elemento el elemento dentro del vertice.
     * @return un nuevo vertice con el elemento recibido dentro del mismo.
     */
    protected Vertice nuevoVertice(T elemento) {
        return new Vertice(elemento);
    }
    
    /**
     * Regresa la altura del vertice.
     * 
     * @return la altura del vertice.
     */
    public int altura() {
        if (isEmpty()){
            return -1;
        }
        return raiz.altura();
    }

    /**
     * Nos dice si el arbol es vacio.
     * 
     * @return <code>true</code> si el arbol es vacio, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean isEmpty() {
        return raiz == null;
    }
    
    /**
     * Regresa el numero de elementos que se han agregado al arbol.
     * 
     * @return el numero de elementos en el arbol.
     */
    @Override public int size() {
        return elementos;
    }

    /**
     * Nos dice si un elemento esta en el arbol binario.
     * 
     * @param elemento el elemento que queremos comprobar si esta en el arbol.
     * @return <code>true</code> si el elemento esta en el arbol;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contains(T elemento){
        return busca(elemento) != null;
    }

    /**
     * Busca el vertice de un elemento en el arbol. Si no lo encuentra regresa
     * <tt>null</tt>.
     * 
     * @param elemento el elemento para buscar el vertice.
     * @return un vertice que contiene el elemento buscado si lo encuentra;
     *         <tt>null</tt> en otro caso.
     */

    public VerticeArbolBinario<T> busca(T elemento){
        return buscar(raiz,elemento);
    }

    protected Vertice buscar(Vertice v, T elemento){
        if(v == null || elemento ==null){
            return null;
        }
        if(v.elemento.equals(elemento)){
            return v;
        }
        Vertice aux = buscar(v.izquierdo, elemento);
        if(aux != null){
            return aux;
        }
        return buscar(v.derecho, elemento);
    }

    /**
     * Regresa el vertice que contiene la raiz del arbol.
     * 
     * @return el vertice que contiene la raiz del arbol.
     * @throws NoSuchElementException si el arbol es vacio.
     */
    public VerticeArbolBinario<T> raiz(){
        if (raiz ==null) {
            throw new NoSuchElementException();
        }
        return raiz;
    }

    /**
     * Metodo para vaciar el arbol
     */
    @Override public void empty(){
        raiz = null;
        elementos=0;
    }
    

    /**
     * Metodo auxiliar de toString
     * 
     * @return espacios
     */
    private String dibujaEspacios(int l, int[] a, int n) {
        String s = "";
        for (int i = 0; i < l; i++) {
            if (a[i] == 1) {
                s = s + "│  ";
            } else {
                s = s + "   ";
            }
        }
        return s;
    }

    /**
     * Metodo auxiliar de toString
     *
     */

    private String toString(Vertice v, int l, int[] m) {
        String s = v.toString() + "\n";
        m[l] = 1;

        if (v.izquierdo != null && v.derecho != null) {
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "├─›";
            s = s + toString(v.izquierdo, l + 1, m);
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "└─»";
            m[l] = 0;
            s = s + toString(v.derecho, l + 1, m);
        } else if (v.izquierdo != null) {
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "└─›";
            m[l] = 0;
            s = s + toString(v.izquierdo, l + 1, m);
        } else if (v.derecho != null) {
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "└─»";
            m[l] = 0;
            s = s + toString(v.derecho, l + 1, m);
        }
        return s;
    }
    
    /**
     * Regresa una representacion en cadena del arbol.
     * 
     * @return una representacion en cadena del arbol.
     */
    @Override
    public String toString() {
        if (this.raiz == null) {
            return "";
        }
        int[] a = new int[this.altura() + 1];
        for (int i = 0; i < a.length; i++) {
            a[i] = 0;
        }
        return toString(this.raiz, 0, a);
    }

    /**
     * Compara el arbol con un objeto.
     * 
     * @param o el objeto con el que queremos comparar el arbol.
     * @return <code>true</code> si el objeto recibido es un arbol binario y los
     *         arboles son iguales; <code>false</code> en otro caso.
     */

    @Override public boolean equals(Object o){
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        ArbolBinario<T> arbol = (ArbolBinario<T>)o;
        if(isEmpty()){
            return arbol.isEmpty();
        }
        return this.raiz.equals(arbol.raiz);
    }


    /**
     * Convierte el vertice (visto como instancia de {@link
     * VerticeArbolBinario}) en vertice (visto como instancia de {@link
     * Vertice}). Metodo auxiliar para hacer esta audicion en un unico lugar.
     * 
     * @param vertice el vertice de arbol binario que queremos como vertice.
     * @return el vertice recibido visto como vertice.
     * @throws ClassCastException si el vertice no es instancia de {@link
     *                            Vertice}.
     */
    protected Vertice vertice(VerticeArbolBinario<T> vertice) {
        return (Vertice) vertice;
    }

    /**
     * Metodo que regresa un iterador
     * @return
     */
    public abstract Iterator<T> iterator();
    



    




   



}
