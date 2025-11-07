public class lab09Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero(10, 10);

        Ejercito e1 = new Ejercito(1, "Inglaterra");
        Ejercito e2 = new Ejercito(2, "Francia");

        e1.generarSoldados(tablero);
        e2.generarSoldados(tablero);

        
        System.out.println("TABLERO");
        tablero.mostrarTablero();

        e1.mostrarResumen();
        e2.mostrarResumen();

        Metrica metrica = new Metrica();
        metrica.calcularGanador(e1, e2);
        
    }
}
