package src.edd;

public class Pokemon implements ComparableIndexable<Pokemon>{
    String nombre;
    String tipo;
    int nivel;
    int indice;
    int hp;
    

    public Pokemon(String nombre, String tipo, int nivel, int hp) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.hp = hp;
    }
    public Pokemon(){
        this.nombre = "Pikachu";
        this.tipo = "Electrico";
        this.nivel = 1;
        this.hp = 100;
    }

    public String toString() {
        return "" + nombre ;
    }
    
    @Override
    public int compareTo(Pokemon otro) {
        return this.nombre.compareTo(otro.nombre);
    }
    
    @Override
    public int getIndice() {
        return this.indice;
    }

    @Override
    public void setIndice(int indice) {
        this.indice = indice;
    }


}
