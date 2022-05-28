package src.edd;

import java.time.Year;
import java.util.Iterator;
import java.util.NoSuchElementException;

import src.edd.Collection;
import src.edd.MonticuloMaximo;

/** 
 * 
 * Clase para monticulos minimos (Minheaps)
*/
public class MonticuloMinimo<T extends ComparableIndexable<T>> implements Collection<T>{
    
    /**
     * Clase para iteradores de monticulos
     */
    private class Iterador implements Iterator<T>{
        
        private int indice;

        @Override public boolean hasNext(){
            return indice < elementos;
        }

        @Override public T next(){
            if (hasNext()) {
                return arbol[indice++];
            }
            throw new NoSuchElementException("No hay, no existe");
        }
    }//FIN DE cLASE ITERADOR

    /**
     * Clase para Adaptadores
     */
    private static class Adaptador<T extends Comparable<T>> implements ComparableIndexable<Adaptador<T>>{
        /* El elemento. */
        private T elemento;
        /* El indice. */
        private int indice;

        /* Crea un nuevo comparable indexable. */
        public Adaptador(T elemento) {
            this.elemento = elemento;
            this.indice = -1;
        }

        /* Regresa el indice. */
        @Override
        public int getIndice() {
            return this.indice;
        }

        /* Define el indice. */
        @Override
        public void setIndice(int indice) {
            this.indice = indice;
        }

        /* Compara un indexable con otro. */
        @Override
        public int compareTo(Adaptador<T> adaptador) {
            return this.elemento.compareTo(adaptador.elemento);
        }

        public String toString(){
            return "A{"+elemento+" "+indice+"}";
        }

        public T getElemento(){
            return elemento;
        }
    }//FIN DE CLASE ADAPTADOR


    /* numero de elementos en el arreglo */
    private int elementos;
    /* Nuestro arbol representado como arreglo */
    private T[] arbol;


    /* Con esto podemos crear arreglos genericos sin que el compilador marque error */
    @SuppressWarnings("unchecked") 
    private T[] nuevoArreglo(int n){
        return (T[])(new ComparableIndexable[n]);
    }//FIN DE NUEVOARREGLO

    /**
     * Constructor sin parametros;
     */
    public MonticuloMinimo(){
        elementos = 0;
        arbol = nuevoArreglo(100);
    }//FIN DE CONSTRUCTOR 1

    /**
     * Constructor que recibe una coleccion
     * @param coleccion
     */
    public MonticuloMinimo(Collection<T> coleccion){
        this(coleccion,coleccion.size());
    }//FIN DE CONSTRUCTOR 2

    /**
     * Constructor que recibe un iterable
     * @param iterable
     * @param n
     */
    public MonticuloMinimo(Iterable<T> iterable, int n ){
        elementos = n;
        arbol = nuevoArreglo(n);
        int i = 0;
        for (T e : iterable) {
           arbol[i] = e;
           arbol[i].setIndice(i);
           i ++;
        }
        for(int j = (elementos-1) /2; j >= 0; j--){
            monticuloMin(j);
            
        }
    }//FIN DE CONSTRUCTOR 3

    /**
     * Constructor que recibe un monticulo maximo
     * @param max
     */
    public MonticuloMinimo(MonticuloMaximo<T> max){
        MonticuloMinimo<T> nuevo=new MonticuloMinimo<>();
        nuevo=nuevo.MontMax_MontMin(max);
        this.arbol=nuevo.arbol;
        this.elementos=nuevo.elementos;
    }//FIN DE CONSTRUCTOR 4

    private void monticuloMin(int i){
        int izq = i * 2 +1 ;
        int der = i * 2 + 2;
        int minimo = i;

        if (elementos <= i) {
            return;
        }
        if(izq < elementos && arbol[izq].compareTo(arbol[i]) < 0){
            minimo = izq;
        }
        if(der < elementos && arbol[der].compareTo(arbol[minimo]) < 0){
            minimo = der;
        }
        if(minimo == i){
            return;
        }

        else{
            swap(arbol[minimo],arbol[i]);
        }
    }//FIN DE MONTICULOMIN

    //Metodo para actualizar posiciones e indices
    private void swap(T i, T j) {
        int aux = j.getIndice();
        arbol[i.getIndice()] = j;
        arbol[j.getIndice()] = i;
        j.setIndice(i.getIndice());
        i.setIndice(aux);
    }//FIN DE SWAP

    /**
     * Metodo para awadir un elemento al heap
     * @param elemento El elemento a awadir
     */
    @Override public void add(T elemento){
        if (elementos == arbol.length) {
            duplicaSize();
        }
        elemento.setIndice(elementos);
        arbol[elementos] = elemento;
        elementos++;
        recorreArriba(elementos - 1);
    }//FIN DE ADD

    //Metodo para duplicar el tamawo del arreglo de nuestro arbol
    private void duplicaSize(){
        T[] arr = nuevoArreglo(arbol.length * 2);
        elementos = 0;
        for(T e: arbol){
            arr[elementos++] = e;
        }
        this.arbol = arr;
    }//FIN DE DUPLICASIZE

    //Metodo para recorrer el arbol hacia arriba y verificar que se cumpla condicion de minheap
    private void recorreArriba(int i){
        int padre = (i-1) / 2;
        int m = i;
        if(padre >= 0 && arbol[padre].compareTo(arbol[i]) > 0){
            m = padre;
        }
        if (m!= i) {
            T a = arbol[i];
            arbol[i] = arbol[padre];
            arbol[i].setIndice(i);
            arbol[padre] = a;
            arbol[padre].setIndice(padre);
            recorreArriba(m);
        }
    }//FIN DE RECORREARRIBA
    
    /**
     * Elimina el elemento minimo del monticulo
     */
    public T delete(){
        if(elementos == 0){
            throw new IllegalStateException("Monticulo vacio");
        }
        T e = arbol[0];
        boolean bool = delete(e);
        if(bool){
            return e;
        }
        else{
            return null;
        }
    }//FIN DE DELETE()

    /**
     * Elimina un elmento del monticulo
     */
    public boolean delete(T elemento){
        if(elemento ==null || isEmpty() ){
            return false;
        }
        if(!contains(elemento)){
            return false;
        }
        int i = elemento.getIndice();
        if(i <0 || elementos <=i )
            return false;
        swap(arbol[i], arbol[elementos -1]);
        arbol[elementos -1] = null;
        elementos --;
        recorreAbajo(i);
        return true;
    }//FIN DE DELETE(ELEM)

    
    //Metodo para recorrer el arbol hacia abajo y verificar que se cumpla condicion de minheap
    private void recorreAbajo(int i){
        if(i < 0){
            return;
        }
        int izq = 2*i +1;
        int der = 2*i +2;
        int min = der;
        if(izq >= elementos && der >= elementos){
            return;
        }
        if(izq < elementos){
            if (der < elementos) {
                if (arbol[izq].compareTo(arbol[der]) <0 ) {
                    min = izq;
                }
            }
            else{
                min = izq;
            }
        }
        if(arbol[min].compareTo(arbol[i])<0){
            swap(arbol[i], arbol[min]);
            //Validar la recursion. 
            recorreAbajo(i);
        }
    }//FIN DE RECORREABAJO

    @Override public boolean contains(T elemento){
        for(T e: arbol){
            if(elemento.equals(e))
                return true;
        }
        return false;
    }//FIN DE CONTAINS

    @Override public boolean isEmpty(){
        return elementos == 0;
    }//FIN DE ISEMPTY
    
    @Override
    public void empty() {
        for (int i = 0; i < elementos; i++) {
            arbol[i] = null;
        }
        elementos = 0;
    }//FIN DE EMPTY

    @Override
    public int size(){
        return elementos;
    }//FIN DE SIZE

    /**
     * Metodo para obtener el elemento en el indice i del heap
     * @param i
     * @return
     */
    public T get(int i){
        if (i< 0 || i>= elementos) {
            throw new NoSuchElementException("Indice no valido");
        }
        return arbol[i];
    }//FIN DE GET


    @Override public String toString(){
        String resultado ="";
        for (int i = 0; i <elementos; i++) {
            resultado += arbol[i].toString() + ",";
        }
        return resultado;
    }//FIN DE TOSTRING

    @Override
    public boolean equals(Object obj) {
        if(obj==null || getClass() != obj.getClass()){
            return false;
        }
        @SuppressWarnings("unchecked") MonticuloMinimo<T> monticulo = (MonticuloMinimo<T>)obj;
        if (elementos != monticulo.elementos) {
            return false;
        }
        for (int i = 0; i < elementos; i++) {
            if(!arbol[i].equals(monticulo.arbol[i])){
                return false;
            }
        }
        return true;
    }//FIN DE EQUALS

    /**
     * Regresa un iterador para iterar el monticulo minimo. El monticulo se
     * itera en orden BFS.
     * 
     * @return un iterador para iterar el monticulo minimo.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }//FIN DE ITERATOR

    /**
    * Ordena la coleccion usando HeapSort.
    * @param <T> tipo del que puede ser el arreglo.
    * @param coleccion la coleccion a ordenar.
    * @return una lista ordenada con los elementos de la coleccion.
    */
    public static <T extends Comparable<T>> Lista<T> heapSort(Collection<T> coleccion) {
        Iterator<T> iterador=coleccion.iterator();
        MonticuloMinimo<Adaptador<T>> monticulo=new MonticuloMinimo<Adaptador<T>>();
        for(int i=0;i<coleccion.size();i++){
            Adaptador<T> actual=new Adaptador<T>(iterador.next());
            monticulo.add(actual);
        }
        Lista<T> l = new Lista<T>();
        int n=monticulo.size();
        for(int i=0;i<n;i++){
            Adaptador<T> e=monticulo.delete();
            T el=e.elemento;
            l.add(el);
        }
        return l;
    }//FIN DE HEAPSORT

    /**
     * Metodo para saber si un arreglo es monticulo minimo
     * @param <T> El tipo del que puede ser el arreglo
     * @param arreglo El arreglo a evaluar
     * @return boolean true si eel arreglo es monticulo minimo, false en otro caso
     */
    public <T extends Comparable<T>>boolean esMontMin(T[] arreglo){
        MonticuloMinimo<Adaptador<T>> m=new MonticuloMinimo<Adaptador<T>>();
        Lista<Adaptador<T>> lista=new Lista<Adaptador<T>>();
        for(int i=0;i<arreglo.length;i++){
            Adaptador<T> actual=new Adaptador<T>(arreglo[i]);
            m.add(actual);
            lista.add(actual);
        }
        Iterator<Adaptador<T>> iterador=lista.iterator();
        Iterator<Adaptador<T>> it=m.iterator();
        for(int i=0;i<arreglo.length;i++){
            if(!iterador.next().equals(it.next())){
                return false;
            }
        }
        return true;
    }//FIN DE ESMONTMIN

    //Mismo metodo que el anterior pero este solo funciona para T comparable indexables
    public boolean esMontMin2(T[] arreglo){
        MonticuloMinimo<T> m=new MonticuloMinimo<T>();
        for(int i=0;i<arreglo.length;i++){
            T actual=arreglo[i];
            m.add(actual);
        }
        
        //System.out.println(m);
        for(int i=0;i<arreglo.length;i++){
            if(!arreglo[i].equals(m.arbol[i])){
                return false;
            }
        }
        return true;
    }//FIN DE ESMONTMIN2

    /**
     * Metodo para convertir un monticulo maximo a uno minimo
     * @param montMax
     * @return
     */
    public MonticuloMinimo<T> MontMax_MontMin(MonticuloMaximo<T> montMax){
        MonticuloMinimo<T> m=new MonticuloMinimo<T>();
        int n=montMax.size();
        for(int i=0;i<n;i++){
            T e=montMax.delete();
            m.add(e);
        }
        return m;
    }//FIN DE MONTMAX_MONTMIN

}