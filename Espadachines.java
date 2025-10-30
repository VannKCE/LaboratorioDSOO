public class Espadachines extends Soldado {
    private int longitudEspada;

    public Espadachines(String nombre, int puntosVida, int fila, int columna, int ejercito, int longitudEspada) {
        super(nombre, puntosVida, fila, columna, ejercito);
        this.longitudEspada = longitudEspada;
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
