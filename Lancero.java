public class Lancero extends Soldado {
    private int longitudLanza;

    public Lancero(String nombre, int puntosVida, int fila, int columna, int ejercito, int longitudLanza) {
        super(nombre, puntosVida, fila, columna, ejercito);
        this.longitudLanza = longitudLanza;
    }

    public void schiltrom() {
        if (estaVivo()) {
            System.out.println(getNombre() + " forma un schiltrom (+defensa).");
            defender();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Lancero | Longitud lanza: " + longitudLanza;
    }
}
