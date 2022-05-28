package src.edd;

import java.util.Iterator;
import src.edd.ArbolBinarioBusqueda;

/**
 * Clase que moldea el comportamiento de los arboles AVL, los cuales cumplen que
 * la diferencia de
 * la altura de sus subarboles izquierdo y derecho es igual o en un rango de uno
 * a dos
 */

public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {

    /**
     * Clase interna protegida para vertices de arboles AVL. La unica
     * diferencia con los vertices tienen altura
     */
    protected class VerticeAVL extends Vertice {

        /**
         * Altura del vertice
         */
        public int altura;

        /**
         * Constructor de un vertice AVL
         * 
         * @param elemento dato del vertice
         */
        public VerticeAVL(T elemento) {
            super(elemento);
            altura = 0;
        }

        /**
         * La altura del vertice
         * 
         * @return
         */
        public int getAltura() {
            return altura;
        }

        /**
         * Representacion en cadena del vertice AVL que devuelve el elemento y su altura
         */
        public String toString() {
            return this.elemento.toString() + "(" + this.altura + ")";
        }

        /**
         * Metodo que devuelve el hijo izquierdo del vertice v
         * 
         * @param v
         * @return
         */
        public VerticeAVL getIzquierdo(VerticeAVL v) {
            if (v == null) {
                return null;
            } else {
                return convertirVertice(v.izquierdo);
            }
        }

        /**
         * Mpetodo que devuelve el hijo derecho del vertice v
         * 
         * @param v
         * @return
         */
        public VerticeAVL getDerecho(VerticeAVL v) {
            if (v == null) {
                return null;
            } else {
                return convertirVertice(v.derecho);
            }
        }

        /**
         * Compara el vertice con otro objeto. La comparacion es
         * <em>recursiva</em>.
         * 
         * @param o el objeto con el cual se comparara el vertice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeRojinegro}, su elemento es igual al elemento de
         *         este vertice, los descendientes de ambos son recursivamente
         *         iguales, y los colores son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked")
            VerticeAVL vertice = (VerticeAVL) o;
            return altura == vertice.altura && super.equals(o);
        }
    }

    /**
     * Constructor de un arbol AVL
     */
    public ArbolAVL() {
        super();
    }

    /**
     * Constructor de un arbol AVL que recibe una coleccion
     * 
     * @param coleccion
     */
    public ArbolAVL(Collection<T> coleccion) {
        super(coleccion);
    }

    /**
     * Constructor que recibe una lista
     * @param lista la lista de elementos
     * @param ordenada si la lista esta ordenada (parmetro solo para sobreescribir el constructor del padre)
     * @param menorAmayor si la lista esta ordenada de menor a mayor (parmetro solo para sobreescribir el constructor del padre)
     */
    public ArbolAVL(Lista<T> lista, boolean ordenada, boolean menorAmayor){
        super();
        ArbolAVL<T> arbol=new ArbolAVL<T>();
        Iterator<T> iterador=lista.iterator();
        for(int i=0;i<lista.size();i++){
            arbol.insertar(iterador.next());
        }
        this.raiz=arbol.raiz;
    }

    /**
     * Metodo que balancea el arbol AVL luego de insertar o eliminar y se asegura
     * que se cumpla
     * que la diferencia de altura de sus subarboles izquierdo y derecho es igual o
     * en un rango de uno a dos
     * 
     * @param v
     * @return
     */
    private VerticeAVL balancear(VerticeAVL v) {
        // si el vertice es distinto de null
        if (v != null) {
            // creamos un nuevo vertice y lo inicializamos en null
            VerticeAVL a = null;
            // si la altura del hijo derecho del vertoce es igual a la altura del hijo
            // izquierdo del vertice mas 2
            if (altura(convertirVertice(v.derecho)) == (altura(convertirVertice(v.izquierdo)) + 2)) {
                // si el hijo derecho del vertice es distinto de null
                if (v.hayDerecho()) {
                    // si la altura del nieto derecho del vertice es igual a la altura del hijo
                    // izquierdo del vertice mas 1
                    if (altura(convertirVertice(v.derecho.derecho)) == (altura(convertirVertice(v.izquierdo)) + 1)) {
                        // usamos el vertice que creamos para rotar el vertice
                        a = rotar(v, true);
                        // si la altura del nieto derecho del vertice es igual a la altura del hijo
                        // izquierdo del vertice
                    } else if (altura(convertirVertice(v.derecho.derecho)) == (altura(convertirVertice(v.izquierdo)))) {
                        // rotamos el hijo derecho del vertice.
                        rotar(convertirVertice(v.derecho), false);
                        a = rotar(convertirVertice(v), true);
                        // a=rotar2(v, false);
                    }
                }
                // si la altura del hijo izquierdo del vertice es igual a la altura del hijo
                // derecho del vertice mas 2
            } else if (altura(convertirVertice(v.izquierdo)) == (altura(convertirVertice(v.derecho)) + 2)) {
                // si el hijo izquierdo del vertice es distinto de null
                if (v.hayIzquierdo()) {
                    // si la altura del nieto izquierdo del vertice es igual a la altura del hijo
                    // derecho del vertice mas 1
                    if (altura(convertirVertice(v.izquierdo.izquierdo)) == (altura(convertirVertice(v.derecho)) + 1)) {
                        // rotamos el vertice
                        a = rotar(v, false);
                        // si la altura del nieto izquierdo del vertice es igual a la altura del hijo
                        // derecho del vertice
                    } else if (altura(convertirVertice(v.izquierdo.izquierdo)) == altura(convertirVertice(v.derecho))) {
                        // rotamos el hijo izquierdo del vertice
                        rotar(convertirVertice(v.izquierdo), true);
                        a = rotar(v, false);
                        // a=rotar2(v, true);
                    }
                }
            } else {
                a = v;
            }
            return a;
        }
        return null;
    }

    /**
     * Metodo que actualiza la altura del vertice sumando 1
     * 
     * @param v
     */
    private void actualizarAltura(VerticeAVL v) {
        if (v != null) {
            v.altura = 1 + Math.max(altura(convertirVertice(v.derecho)), altura(convertirVertice(v.izquierdo)));
        }
    }

    /**
     * Metodo privado que devuelve la altura del vertice
     * 
     * @param v
     * @return
     */
    private int altura(VerticeAVL v) {
        if (v == null) {
            return -1;
        } else {
            return v.altura;
        }
    }

    /**
     * Metodo que rota el vertice para rebalancear el arbol
     * 
     * @param vertice
     * @param izquierda
     * @return
     */
    public VerticeAVL rotar(VerticeAVL vertice, boolean izquierda) {
        // creamos un nuevo verticeAVL auxiliar
        VerticeAVL aux;
        // si el vertice es distinto de null
        if (vertice != null) {
            // y la variable izquierda se inicializa en true
            if (izquierda) {
                // creamos un nuevo vertice padre distinto de null
                VerticeAVL padre = null;
                // si el vertice tiene padre
                if (vertice.hayPadre()) {
                    padre = convertirVertice(vertice.padre);
                }
                // hacemos que aux sea el hijo derecho del vertice
                aux = derecho(vertice);
                // hacemos que el hijo derecho del vertice sea el hijo izquierdo de aux
                vertice.derecho = izquierdo(aux);
                // si el hijo derecho del vertice es distinto de null
                if (vertice.derecho != null) {
                    // hacemos que el padre del hijo derecho del vertice sea el vertice
                    vertice.derecho.padre = vertice;
                }
                // si aux es distinto de null
                if (aux != null) {
                    // hacemos que el hijo izquierdo de aux sea el vertice
                    aux.izquierdo = vertice;
                    // hacemos que el padre del vertice sea aux
                    vertice.padre = aux;
                }
                // si el vertice aux es distinto de null
                if (aux != null) {
                    // hacemos que el padre de aux sea el padre del vertice
                    aux.padre = padre;
                }
                // si el vertice es distinto de null
                if (padre != null) {
                    // si el padre tiene hijo derecho y es igual que el vertice
                    if (padre.hayDerecho() && padre.derecho.elemento.equals(vertice.elemento)) {
                        // el hijo derecho del padre sera aux
                        padre.derecho = aux;
                    } else {
                        // el hijo izquierdo del padre sera aux
                        padre.izquierdo = aux;
                    }
                }

            } // la variable izquierda se inicializa en false
            else {
                // creamos un nuevo verticeAVL padre que inicializa en null
                VerticeAVL padre = null;
                // si el vertice tiene padre
                if (vertice.hayPadre()) {
                    // creamos un nuveo vertice que sea padre del vertice original
                    padre = convertirVertice(vertice.padre);
                }
                // hacemos que aux sea el hijo izquierdo del vertice
                aux = izquierdo(vertice);
                // hacemos que el hijo izquierdo del vertice sea el hijo derecho de aux
                vertice.izquierdo = derecho(aux);
                // si el hijo izquierdo del vertice es distinto de null
                if (vertice.izquierdo != null) {
                    // hacemos que el padre del hijo izquierdo del vertice sea el vertice
                    vertice.izquierdo.padre = vertice;
                }
                // si aux es distinto de null
                if (aux != null) {
                    // hacemos que el hijo derecho de aux sea el vertice
                    aux.derecho = vertice;
                    // hacemos que el padre del vertice sea aux
                    vertice.padre = aux;
                }
                // si el vertice aux es distinto de null
                if (aux != null) {
                    // hacemos que el padre de aux sea el padre del vertice
                    aux.padre = padre;
                }
                // si el vertice padre es distinto de null
                if (padre != null) {
                    // si el padre tiene hijo derecho y es igual que el vertice
                    if (padre.hayDerecho() && padre.derecho.elemento.equals(vertice.elemento)) {
                        // el hijo derecho del padre sera aux
                        padre.derecho = aux;
                    } else {
                        // el hijo izquierdo del padre sera aux
                        padre.izquierdo = aux;
                    }
                }

            }
            // actualizamos la altura del vertice y de aux
            actualizarAltura(vertice);
            actualizarAltura(aux);
            return aux;
        }
        return null;

    }


    /**
     * Metodo que genera un nodo de tipo VerticeAVL para devolver el hijo derecho de
     * v
     * 
     * @param v
     * @return
     */
    public VerticeAVL derecho(VerticeAVL v) {
        if (v != null) {
            return convertirVertice(v.derecho);
        } else {
            return null;
        }
    }

    /**
     * Metodo que genera un nodo de tipo VerticeAVL para devolver el hijo izquierdo
     * de v
     * 
     * @param v
     * @return
     */
    public VerticeAVL izquierdo(VerticeAVL v) {
        if (v != null) {
            return convertirVertice(v.izquierdo);
        } else {
            return null;
        }
    }

    /**
     * Metodo que genera convierte un Vertice tradicional a un vertice AVL
     * 
     * @param v
     * @return
     */
    public VerticeAVL convertirVertice(Vertice v) {
        if (v == null) {
            return null;
        }
        VerticeAVL vertice = (VerticeAVL) v;
        return vertice;
    }

    /**
     * Metodo que inserta un elemento en el arbol AVL
     * 
     * @param elemento
     */
    public void insertar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se puede insertar un elemento vacio");
        }
        // si el arbol no tiene elementos, el elemento a insertar sera la raiz.
        if (isEmpty()) {
            raiz = new VerticeAVL(elemento);
            // aumentamos el contador de los elementos
            elementos++;
        } else {
            // creamos un nuevo vertice que inserta el elemento
            VerticeAVL b = insertarAux(convertirVertice(raiz), elemento);
            // si b es diferente de null
            if (b != null) {
                // la raiz sera b
                this.raiz = b;
            }
        }
    }

    /**
     * Metodo auxiliar de insertar
     * 
     * @param vertice
     * @param elemento
     * @return
     */
    private VerticeAVL insertarAux(VerticeAVL vertice, T elemento) {
        // si el elemento a insertar es distinto del de la raiz
        if (!elemento.equals(vertice.elemento)) {
            // si el elemento es menor que el elemento del vertice
            if (elemento.compareTo(vertice.elemento) < 0) {
                // si el vertice tiene hijo izquierdo
                if (vertice.hayIzquierdo()) {
                    // llamamos al metodo insertarAux con el hijo izquierdo del vertice para crear
                    // el nuevo vertice
                    insertarAux(convertirVertice(vertice.izquierdo), elemento);
                } // si no tiene hijo izquierdo
                else {
                    // hacemos un nuevo vertice AVL con el elemento
                    VerticeAVL nuevo = new VerticeAVL(elemento);
                    // el hijo izquierdo del vertice sera el nuevo vertice
                    vertice.izquierdo = nuevo;
                    // el padre del vertice insertado sera el vertice original
                    nuevo.padre = vertice;
                    // aumentamos el contador de los elementos
                    elementos++;
                    // System.out.println(nuevo);
                    // System.out.println(nuevo.padre);
                }

            } // si el elemento es mayor que el elemento del vertice
            else {
                // si el vertice tiene hijo derecho
                if (vertice.hayDerecho()) {
                    // llamamos al metodo insertarAux con el hijo derecho del vertice para crear el
                    // nuevo vertice
                    insertarAux(convertirVertice(vertice.derecho), elemento);
                } else {
                    // hacemos un nuevo vertice AVL con el elemento
                    VerticeAVL nuevo = new VerticeAVL(elemento);
                    // el hijo derecho del vertice sera el nuevo vertice
                    vertice.derecho = nuevo;
                    // el padre del vertice insertado sera el vertice original
                    nuevo.padre = vertice;
                    // aumentamos el contador de los elementos
                    elementos++;
                }

            }
            // generamos un vertice padre para balancear el arbol a partir del vertice que
            // ha sido insertado
            VerticeAVL padre = balancear(vertice);
            // System.out.println("Vertice: "+vertice+" Padre: "+padre);
            // System.out.println(vertice.izquierdo);
            // actualizamos la altura del vertice original
            actualizarAltura(vertice);
            return padre;
        }
        return null;
    }

    /**
     * Metodo para eliminar elementos del arbol AVL
     * 
     * @param elemento
     */
    public void delete(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se puede eliminar un elemento vacio");
        } else {
            // llamamos el metodo eliminar para aplicarlo sobre la raiz y el elemento
            raiz = eliminar(convertirVertice(raiz), elemento);
            // decrecemos el contador de los elementos
            elementos--;
            // System.out.println(raiz);
        }
    }

    /**
     * Metodo auxiliar para eliminar elementos del arbol AVL
     * 
     * @param vertice
     * @param elemento
     * @return
     */
    private VerticeAVL eliminar(VerticeAVL vertice, T elemento) {
        if (vertice == null) {// si el vertice es vacio no podemos aplicar el metodo
            throw new IllegalArgumentException("El vertice es vacio");
        }
        // Si el elemento no esta en el arbol no podemos eliminarlo
        Vertice a = search2(raiz, elemento);
        if (a == null) {
            throw new IllegalArgumentException("El elemento a eliminar no se encuentra en el arbol");
        }
        if (elemento.compareTo(vertice.elemento) < 0) {// si el elemento buscado es menor que el del vertice nos vamos a
                                                       // la izquierda
            eliminar(convertirVertice(vertice.izquierdo), elemento);
        } else if (elemento.compareTo(vertice.elemento) > 0) {// si es mayor nos vamos a la derecha
            eliminar(convertirVertice(vertice.derecho), elemento);
        } else {
            // si el vertice no tiene hijo derecho ni hijo izquierdo
            if (!vertice.hayDerecho() && !vertice.hayIzquierdo()) {
                // pero si tiene padre
                if (vertice.hayPadre()) {
                    // System.out.println("Vertice: "+vertice);
                    // System.out.println("Padre: "+vertice.padre);
                    // si padre el elemento tiene hijo izquierdo y es el mismo que el vertice
                    if (vertice.padre.hayIzquierdo() && vertice.padre.izquierdo.elemento.equals(vertice.elemento)) {
                        vertice.padre.izquierdo = null;
                    } else {
                        vertice.padre.derecho = null;
                    }
                } else {
                    raiz = null;
                }
                // System.out.println(vertice);
                // System.out.println(vertice.padre);
                // System.out.println(vertice.padre.derecho);
                vertice = null;

                // si el vertice no tiene hijo izquierdo
            } else if (!vertice.hayIzquierdo()) {
                // pero el vertice tiene padre
                if (vertice.hayPadre()) {
                    // si el padre tiene hijo izquierdo y es el mismo que el vertice
                    if (vertice.padre.hayIzquierdo() && vertice.padre.izquierdo.elemento.equals(vertice.elemento)) {
                        vertice.padre.izquierdo = vertice.derecho;
                    } else {
                        vertice.padre.derecho = vertice.derecho;
                    }
                    vertice.derecho.padre = vertice.padre;
                } else {
                    raiz = vertice.derecho;
                }
                // aux=vertice;
                // vertice=convertirVertice(vertice.derecho);
                // si el vertice no tiene hijo derecho
            } else if (!vertice.hayDerecho()) {
                // pero si tiene padre
                if (vertice.hayPadre()) {
                    if (vertice.padre.izquierdo.elemento.equals(vertice.elemento)) {
                        vertice.padre.izquierdo = vertice.izquierdo;
                    } else {
                        vertice.padre.derecho = vertice.izquierdo;
                    }
                    vertice.izquierdo.padre = vertice.padre;
                } else {
                    raiz = vertice.izquierdo;
                }
                // aux=vertice;
                // vertice=convertirVertice(vertice.izquierdo);
            } else {
                // llamamos al metodo encontrar minimo para encontrar el elemento minimo del
                // subarbol derecho
                T b = encontrarMin(convertirVertice(vertice.derecho));
                // eliminamos a b del arbol
                eliminar(convertirVertice(raiz), b);
                // intercambiamos
                vertice.elemento = b;
            }
        }
        // balanceamos el arbol
        VerticeAVL padre = balancear(vertice);
        // System.out.println(padre);
        // actualizamos alturas
        actualizarAltura(vertice);
        return padre;
    }

    /**
     * Metodo auxiliar para encontrar el elemento minimo del subarbol derecho
     * 
     * @param vertice
     * @return
     */
    private T encontrarMin(VerticeAVL vertice) {
        if (vertice != null) {
            // si el vertice tiene hijo izquierdo
            if (vertice.izquierdo != null) {
                // llamamos al metodo encontrar minimo para encontrar el elemento minimo del
                // subarbol izquierdo
                T b = encontrarMin(convertirVertice(vertice.izquierdo));
                return b;
            } // si no tiene hijo izquierdo
            else {
                // retornamos el elemento del vertice
                T b = vertice.elemento;
                return b;
            }
        } else {
            throw new IllegalCallerException("No se puede usar este metodo con un vertice vacio");
        }
    }

}
