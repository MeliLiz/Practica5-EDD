package src.edd;

import java.util.Iterator;

/**
 * Clase de prueba de los metodos de arboles
 */
public class Main {
    public static void main(String[] args) {
        
        System.out.println("PRUEBAS ARBOL ROJINEGRO");

        ArbolRojinegro<Integer> arbol=new ArbolRojinegro<Integer>();
        System.out.println("Agregamos el 21 y el 15");
        arbol.insertar(21);
        System.out.println(arbol);
        arbol.insertar(15);
        System.out.println(arbol);
        System.out.println("Agregamos el 7");
        arbol.insertar(7);
        System.out.println(arbol);
        System.out.println("Agregamos el 80");
        arbol.insertar(80);
        
        System.out.println(arbol);
        System.out.println("Agregamos el 79");
        arbol.insertar(79);
        //arbol.delete(21);
        System.out.println(arbol);
        System.out.println("Agregamos el 8");
        arbol.insertar(8);
        System.out.println(arbol);
        System.out.println("Agregamos el 60");
        arbol.insertar(60);
        System.out.println(arbol);
        System.out.println("Eliminamos el 21");
        arbol.delete(21);
        System.out.println(arbol);
        System.out.println("Eliminamos el 8");
        arbol.delete(8);
        System.out.println(arbol);
        System.out.println("Eliminamos el 15");
        arbol.delete(15);
        System.out.println(arbol);
        System.out.println("Eliminamos el 80");
        arbol.delete(80);
        System.out.println(arbol);
        
        /*System.out.println("Creamos un arbol a partir de una lista");
        Lista<Integer> lista=new Lista<Integer>();
        lista.add(20);
        lista.add(15);
        lista.add(10);
        lista.add(5);
        lista.add(17);
        lista.add(2);
        lista.add(7);
        lista.add(7);
        ArbolRojinegro<Integer> a =new ArbolRojinegro<Integer>(lista,false, false);
        System.out.println(a);*/

        int n=5;
        
        Object o=1;
        Integer i=5;

        System.out.println(i==n);

        if(o.getClass().equals(i.getClass())){
            System.out.println(o.getClass());
        }

        
        
    }
}
