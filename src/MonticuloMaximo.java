package src.edd;

import java.time.Year;
import java.util.Iterator;
import java.util.NoSuchElementException;

import src.edd.Collection;

/** 
 * 
 * Clase para monticulos maximos (Maxheaps)
*/
public class MonticuloMaximo<T extends ComparableIndexable<T>> implements Collection<T>{
    
    
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

    }


    private static class Adaptador<T extends Comparable<T>>
    implements ComparableIndexable<Adaptador<T>>{
        /* El elemento. */
        private T elemento;
        /* El índice. */
        private int indice;

        /* Crea un nuevo comparable indexable. */
        public Adaptador(T elemento) {
            this.elemento = elemento;
            this.indice = -1;
        }

        /* Regresa el índice. */
        @Override
        public int getIndice() {
            return this.indice;
        }

        /* Define el índice. */
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
    }
    /* numero de elementos en el arreglo */
    private int elementos;
    /* Nuestro arbol representado como arreglo */
    private T[] arbol;

    /* Con esto podemos crear arreglos genericos sin que el compilador marque error */
    @SuppressWarnings("unchecked") private T[] nuevoArreglo(int n){
        return (T[])(new ComparableIndexable[n]);
    }

    public MonticuloMaximo(){
        elementos = 0;
        arbol = nuevoArreglo(100);
    }

    public MonticuloMaximo(Collection<T> coleccion){
        this(coleccion,coleccion.size());
    }

    public MonticuloMaximo(Iterable<T> iterable, int n ){
        elementos = n;
        arbol = nuevoArreglo(n);
        int i = 0;
        for (T e : iterable) {
           arbol[i] = e;
           arbol[i].setIndice(i);
           i ++;
        }
        for(int j = (elementos-1) /2; j >= 0; j--){
            monticuloMax(j);
            
        }
    }

    private void monticuloMax(int i){
        int izq = i * 2 +1 ;
        int der = i * 2 + 2;
        int maximo = i;

        if (elementos <= i) {
            return;
        }
        if(izq < elementos && arbol[izq].compareTo(arbol[i]) > 0){
            maximo = izq;
        }
        if(der < elementos && arbol[der].compareTo(arbol[maximo]) > 0){
            maximo = der;
        }
        if(maximo == i){
            return;
        }

        else{
            swap(maximo,i);
        }
    }

    private void swap(int i, int j){
        arbol[i].setIndice(j);
        arbol[j].setIndice(i);
        T e = arbol[i];
        arbol[i] = arbol[j];
        arbol[j] = e;

    }

    @Override public void add(T elemento){
        if (elementos == arbol.length) {
            duplicaSize();
        }
        elemento.setIndice(elementos);
        arbol[elementos] = elemento;
        elementos++;
        recorreArriba(elementos - 1);
    }

    private void duplicaSize(){
        T[] arr = nuevoArreglo(arbol.length * 2);
        elementos = 0;
        for(T e: arbol){
            arr[elementos++] = e;
        }
        this.arbol = arr;
    }

    private void recorreArriba(int i){
        int padre = (i-1) / 2;
        int m = i;
        if(padre >= 0 && arbol[padre].compareTo(arbol[i]) < 0){
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
    }
    
    /**
     * Elimina el elemento maximo del monticulo
     * 
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

    }

    /**
     * Elimina un elmento del monticulo
     * 
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
        swap(i, elementos -1);
        arbol[elementos -1] = null;
        elementos --;
        recorreAbajo(i);
        return true;
    }

    

    private void recorreAbajo(int i){
        if(i < 0){
            return;
        }
        int izq = 2*i +1;
        int der = 2*i +2;
        int max = der;
        //No existen
        //  0, 1
        // [],[]
        if(izq >= elementos && der >= elementos){
            return;
        }
        if(izq < elementos){
            if (der < elementos) {
                if (arbol[izq].compareTo(arbol[der]) >0 ) {
                    max = izq;
                }
            }
            else{
                max = izq;
            }
        }
        if(arbol[max].compareTo(arbol[i])>0){
            swap(i, max);
            //Validar la recursion. 
            recorreAbajo(i);
            
        }
        
        

    }


    @Override public boolean contains(T elemento){
        for(T e: arbol){
            if(elemento.equals(e))
                return true;
        }
        return false;
    }

    @Override public boolean isEmpty(){
        return elementos == 0;
    }
    
    @Override
    public void empty() {
        for (int i = 0; i < elementos; i++) {
            arbol[i] = null;
        }
        elementos = 0;
    }

    @Override
    public int size(){
        return elementos;
    }

    public T get(int i){
        if (i< 0 || i>= elementos) {
            throw new NoSuchElementException("Indice no valido");
        }
        return arbol[i];
    }


    @Override public String toString(){
        String resultado ="";
        for (int i = 0; i <elementos; i++) {
            resultado += arbol[i].toString() + ",";
        }
        return resultado;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || getClass() != obj.getClass()){
            return false;
        }
        @SuppressWarnings("unchecked") MonticuloMaximo<T> monticulo = (MonticuloMaximo<T>)obj;
        if (elementos != monticulo.elementos) {
            return false;
        }
        for (int i = 0; i < elementos; i++) {
            if(!arbol[i].equals(monticulo.arbol[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * Regresa un iterador para iterar el montículo mínimo. El montículo se
     * itera en orden BFS.
     * 
     * @return un iterador para iterar el montículo mínimo.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }


    /**
     * Metodo para saber si un arreglo es monticulo máximo
     * @param <T> El tipo del que puede ser el arreglo
     * @param arreglo El arreglo a evaluar
     * @return boolean true si eel arreglo es montículo maximo, false en otro caso
     */
    public <T extends Comparable<T>>boolean esMontMax(T[] arreglo){
        MonticuloMaximo<Adaptador<T>> m=new MonticuloMaximo<Adaptador<T>>();
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
    }

    /**
     * Metodo para convertir un monticulo minimo a uno maximo
     * @param montMin
     * @return
     */
    public MonticuloMaximo<T> MontMin_MontMax(MonticuloMinimo<T> montMin){
        MonticuloMaximo<T> m=new MonticuloMaximo<T>();
        int n=montMin.size();
        for(int i=0;i<n;i++){
            T e=montMin.delete();
            m.add(e);
        }
        return m;
    }


}
