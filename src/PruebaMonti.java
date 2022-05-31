package src.edd;

import java.util.Arrays;
import java.util.Comparator;

import src.edd.Pokemon;

public class PruebaMonti{

    public static void main(String[] args) {

        String reset="\u001B[0m";
        
        String azul ="\u001B[34m";
        

        //Creamos pokemon
        Pokemon poke1 = new Pokemon("Bulbasaur", "Planta", 1, 110);
        Pokemon poke2 = new Pokemon("Charmander", "Fuego", 2, 100);
        Pokemon poke3 = new Pokemon("Pikachu", "Electrico", 3, 100);
        Pokemon poke4 = new Pokemon("Squirtle", "Agua", 4, 100);
        Pokemon poke5 = new Pokemon("Pidgey", "Volador", 5, 100);
        
        //Hacemmos minheap
        MonticuloMinimo<Pokemon> monticulo = new MonticuloMinimo<Pokemon>();

        System.out.println("\n"+azul+"***Prueba de heapsort con pokemon***"+reset);

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

        System.out.println("\n"+azul+"***Prueba de heapsort con enteros***"+reset);
        //Hacemos lista de eneteros
        Lista<Integer> enteros=new Lista<Integer>();
        enteros.add(2);
        enteros.add(5);
        enteros.add(8);
        enteros.add(7);
        enteros.add(3);
        enteros.add(30);
        enteros.add(21);
        enteros.add(19);
        //Mostramos lista original
        System.out.println("Lista original: "+enteros);
        //Ordenamos la lista
        Lista<Integer> ordenada=monticulo.heapSort(enteros);
        //Mostramos la lista ordenada
        System.out.println("Lista ordenada: "+ordenada);


        System.out.println("\n"+azul+"***Prueba de esMontMin***"+reset);
        Pokemon[] p={poke3,poke2, poke1, poke4, poke5};//arreglo desordenado de pokemon
        Pokemon[] q={poke1,poke3, poke2, poke3, poke4};//arrreglo ordenado como minheap
        Pokemon[] r={poke4,poke3, poke2, poke3, poke1};//arreglo ordenado como maxheap
        
        System.out.println(monticulo.esMontMin(p));//debe dar false
        System.out.println(monticulo.esMontMin(q));//debe dar true


        System.out.println("\n"+azul+"**Awadiendo en Maxheap**"+reset);
        MonticuloMaximo<Pokemon> m= new MonticuloMaximo<Pokemon>();
        //Awadimos elementos
        m.add(poke3);
        m.add(poke2);
        m.add(poke1);
        m.add(poke4);
        m.add(poke5);
        //Mostramos el maxheap
        System.out.println("Monticulo: "+m);
        System.out.println("Size "+m.size());
        //Eliminamos en el maxheap
        System.out.println("\n"+azul+"**Eliminando elemento en maxheap**"+reset);
        m.delete();
        System.out.println("Monticulo: "+m);
        System.out.println("Size "+m.size());//Mostramos tamawo del heap

        System.out.println("\n"+azul+"**Pruebas de esMontMax**"+reset);
        System.out.println(m.esMontMax(p));//debe dar false
        System.out.println(m.esMontMax(r));//debe dar true

        System.out.println("\n"+azul+"**Prueba MontMax_MontMin**"+reset);
        System.out.println("Monticulo maximo a convertir: "+m);
        MonticuloMinimo<Pokemon> monticulo2 = new MonticuloMinimo<Pokemon>(m);
        System.out.println("Monticulo minimo: "+monticulo2);

        monticulo2.add(poke4);

        System.out.println("\n"+azul+"**Prueba MontMin_MontMax**"+reset);
        System.out.println("Monticulo minimo a convertir: "+monticulo2);
        MonticuloMaximo<Pokemon> monticulo3 = new MonticuloMaximo<Pokemon>(monticulo2);
        System.out.println("Monticulo maximo: "+monticulo3);

    }


}
