package Modelo;
import java.util.Random;

public class Arquero extends Soldado {
    private int flechas;

    public Arquero(String nombre, int fila, int columna, int ejercito) {
        super(nombre, fila, columna, ejercito, 7, 3, new Random().nextInt(3) + 3);
        this.flechas = new Random().nextInt(5) + 1;
    }

    public void dispararFlecha() {
        if (estaVivo() && flechas > 0) {
            flechas--;
            System.out.println(getNombre() + " dispara una flecha");
        } else if (flechas == 0) {
            System.out.println("No tiene más flechas.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Arquero | Flechas: " + flechas;
    }
}
