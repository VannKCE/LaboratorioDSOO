public class Videojuego {
    public static void main(String[] args) {
        Tablero t = new Tablero(10, 10);

        Ejercito e1 = new Ejercito(1);
        Ejercito e2 = new Ejercito(2);

        e1.generarSoldados(t);
        e2.generarSoldados(t);

        System.out.println("TABLERO");
        t.mostrarTablero();

        System.out.println("SOLDADOS CON MAYOR VIDA");
        System.out.println("EJERCITO 1: " + e1.obtenerMayorVida());
        System.out.println("EJERCITO 2: " + e2.obtenerMayorVida());

        System.out.println();

        System.out.println("PROMEDIO DE VIDA");
        System.out.println("EJERCITO 1: " + e1.promedioVida());
        System.out.println("EJERCITO 2: " + e2.promedioVida());

        System.out.println();

        System.out.println("CREACION DE SOLDADOS");
        e1.mostrarEjercito();
        e2.mostrarEjercito();

        System.out.println("RANKING DE SOLDADOS");
        e1.ordenarPorBurbuja();
        e2.ordenarPorSeleccion();

        System.out.println();

        e1.comparar(e2);

        System.out.println();
        
        System.out.println("JUEGO");
        Juego juego = new Juego();
        juego.iniciarJuego(); 

        System.out.println("TABLERO TIPOS SOLDADOS");
        t.mostrarTablero();
    }
}
