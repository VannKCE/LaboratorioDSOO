package Modelo;
import java.util.*;
public class Caballeros extends Soldado {
    private boolean montando;
    private String arma; // "espada" o "lanza"

    public Caballeros(String nombre, int fila, int columna, int ejercito) {
        super(nombre, fila, columna, ejercito, 13, 7, new Random().nextInt(3) + 10);
        this.montando = new Random().nextBoolean();
        this.arma = montando ? "lanza" : "espada";
    }

    public void montar() {
        if (!montando) {
            montando = true;
            arma = "lanza";
            envestir();
        }
    }

    public void desmontar() {
        if (montando) {
            montando = false;
            arma = "espada";
            defender();
        }
    }

    public void envestir() {
        if (montando) {
            System.out.println(getNombre() + " realiza una envestida montado (3 ataques).");
        } else {
            System.out.println(getNombre() + " realiza una envestida desmontado (2 ataques).");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Caballero | Arma: " + arma + " | Montando: " + montando;
    }
}
