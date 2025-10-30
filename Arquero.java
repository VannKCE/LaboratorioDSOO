public class Arquero extends Soldado {
    private int flechas;

    public Arquero(String nombre, int puntosVida, int fila, int columna, int ejercito, int flechas) {
        super(nombre, puntosVida, fila, columna, ejercito);
        this.flechas = flechas;
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
