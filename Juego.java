import java.util.Scanner;

public class Juego {

    private Tablero tablero;
    private Ejercito ejercito1;
    private Ejercito ejercito2;
    private Batalla batalla;
    private Scanner sc;

    public Juego() {
        tablero = new Tablero(10, 10);
        ejercito1 = new Ejercito(1);
        ejercito2 = new Ejercito(2);

        ejercito1.generarSoldados(tablero);
        ejercito2.generarSoldados(tablero);

        batalla = new Batalla(tablero);
        sc = new Scanner(System.in);
    }

    public void iniciar() {
        boolean turnoEjercito1 = true; 

        while (!ejercito1Vacio() && !ejercito2Vacio()) {

            tablero.mostrarTablero();

            if (turnoEjercito1) {
                System.out.println("Turno del Ejército 1");
            } else {
                System.out.println("Turno del Ejército 2");
            }

            Ejercito actual;
            if (turnoEjercito1) {
                actual = ejercito1;
            } else {
                actual = ejercito2;
            }

            Soldado soldado = seleccionarSoldado(actual);

            boolean movido = false;
            while (!movido) {
                System.out.print("Ingrese dirección (e/o/n/s/w/x/y/z): ");
                String dir = sc.next();
                movido = batalla.moverSoldado(soldado, dir);
            }

            if (turnoEjercito1) {
                turnoEjercito1 = false;
            } else {
                turnoEjercito1 = true;
            }
        }

        if (ejercito1Vacio()) {
            System.out.println("Ganó el Ejército 2");
        } else {
            System.out.println("Ganó el Ejército 1");
        }
    }


    private Soldado seleccionarSoldado(Ejercito ejercito) {
        while (true) {
            System.out.print("Ingrese fila del soldado a mover: ");
            int fila = sc.nextInt();
            fila = fila-1;

            System.out.print("Ingrese columna del soldado a mover: ");
            int col = sc.nextInt();
            col=col-1;

            Soldado s = tablero.obtenerSoldado(fila, col);
            if (s != null && s.getEjercito() == ejercito.getNumero() && s.estaVivo()) {
                return s;
            } else {
                System.out.println("Soldado inválido. Intente de nuevo.");
            }
        }
    }

    private boolean ejercito1Vacio() {
        for (Soldado s : ejercito1.getSoldados()) {
            if (s.estaVivo()) 
            return false;
        }
        return true;
    }

    private boolean ejercito2Vacio() {
        for (Soldado s : ejercito2.getSoldados()) {
            if (s.estaVivo()) return false;
        }
        return true;
    }
}
