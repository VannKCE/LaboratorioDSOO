import java.util.Random;

public class Espadachines extends Soldado {
    private int longitudEspada;

    public Espadachines(String nombre, int fila, int columna, int ejercito) {
        super(nombre, fila, columna, ejercito, 10, 8, new Random().nextInt(3) + 8); 
        this.longitudEspada = new Random().nextInt(3) + 1;
    }

    public void crearMuroEscudos() {
        if (estaVivo()) {
            System.out.println(getNombre() + " crea un muro de escudos (defensa +1)");
            defender();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Espadachin | Longitud espada: " + longitudEspada;
    }
}
