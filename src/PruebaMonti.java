package src.edd;

import java.util.Arrays;
import java.util.Comparator;

import src.edd.Pokemon;

public class PruebaMonti{

    public static void main(String[] args) {

        //Creamos pokemon
        Pokemon poke1 = new Pokemon("Bulbasaur", "Planta", 1, 110);
        Pokemon poke2 = new Pokemon("Charmander", "Fuego", 2, 100);
        Pokemon poke3 = new Pokemon("Pikachu", "Electrico", 3, 100);
        Pokemon poke4 = new Pokemon("Squirtle", "Agua", 4, 100);
        Pokemon poke5 = new Pokemon("Pidgey", "Volador", 5, 100);
        
        //Hacemmos minheap
        MonticuloMinimo<Pokemon> monticulo = new MonticuloMinimo<Pokemon>();

        System.out.println("\n***Prueba de heapsort con pokemon***");

        //Hacemos lista de pokemon
        Lista<Pokemon> lista=new Lista();
        lista.add(poke3);
        lista.add(poke2);
        lista.add(poke1);
        lista.add(poke4);
        lista.add(poke5);
        //mostramos lista original
        System.out.println("Lista original: "+ lista);
        //ordenamos la lista con heapsort
        Lista<Pokemon> l=monticulo.heapSort(lista);
        //Mostramos la lista ordenada
        System.out.println("Lista ordenada: "+ l);

        System.out.println("\n***Prueba de heapsort con enteros***");
        //Hacemos lista de eneteros
        Lista<Integer> enteros=new Lista<Integer>();
        enteros.add(2);
        enteros.add(5);
        enteros.add(8);
        enteros.add(7);
        enteros.add(3);
        //Mostramos lista original
        System.out.println("Lista original: "+enteros);
        //Ordenamos la lista
        Lista<Integer> ordenada=monticulo.heapSort(enteros);
        //Mostramos la lista ordenada
        System.out.println("Lista ordenada: "+ordenada);


        System.out.println("\n***Prueba de esMontMin***");
        Pokemon[] p={poke3,poke2, poke1, poke4, poke5};//arreglo desordenado de pokemon
        Pokemon[] q={poke1,poke3, poke2, poke3, poke4};//arrreglo ordenado como minheap
        Pokemon[] r={poke4,poke3, poke2, poke3, poke1};//arreglo ordenado como maxheap
        
        System.out.println(monticulo.esMontMin(p));//debe dar false
        System.out.println(monticulo.esMontMin(q));//debe dar true


        System.out.println("\n**Añadiendo en Maxheap**");
        MonticuloMaximo<Pokemon> m= new MonticuloMaximo<Pokemon>();
        //Añadimos elementos
        m.add(poke3);
        m.add(poke2);
        m.add(poke1);
        m.add(poke4);
        m.add(poke5);
        //Mostramos el maxheap
        System.out.println("Monticulo: "+m);
        System.out.println("Size "+m.size());
        //Eliminamos en el maxheap
        System.out.println("\n**Eliminando elemento en maxheap**");
        m.delete();
        System.out.println("Monticulo: "+m);
        System.out.println("Size "+m.size());//Mostramos tamaño del heap

        System.out.println("\n**Pruebas de esMontMax**");
        System.out.println(m.esMontMax(p));//debe dar false
        System.out.println(m.esMontMax(r));//debe dar true

        /*System.out.println("**Prueba MontMax_MontMin2");
        Pokemon[] s=monticulo.MontMax_MontMin2(r);
        for(int i=0;i<5;i++){
            System.out.println(s[i]);
        }*/

    }


}
