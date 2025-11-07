import java.util.*
;
public class Lancero extends Soldado {
    private int longitudLanza;

    public Lancero(String nombre, int fila, int columna, int ejercito) {
        super(nombre, fila, columna, ejercito, 5, 10, new Random().nextInt(4) + 5);
        this.longitudLanza = new Random().nextInt(3) + 1;
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
