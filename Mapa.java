import java.util.Random;

public class Mapa {
    private Tablero tablero;
    private Ejercito ejercito1;
    private Ejercito ejercito2;
    private String tipoTerritorio;

    public Mapa() {
        tablero = new Tablero(10, 10);
        tipoTerritorio = generarTerritorio();
        System.out.println("Territorio: " + tipoTerritorio);

        // Crear dos reinos distintos (sin guerra civil)
        String[] reinos = {"Inglaterra", "Francia", "Castilla-Aragón", "Moros", "Sacro Imperio Romano-Germánico"};
        Random rand = new Random();
        int r1 = rand.nextInt(reinos.length);
        int r2;
        do {
            r2 = rand.nextInt(reinos.length);
        } while (r2 == r1);

        ejercito1 = new Ejercito(1, reinos[r1]);
        ejercito2 = new Ejercito(2, reinos[r2]);

        ejercito1.generarSoldados(tablero);
        ejercito2.generarSoldados(tablero);

        aplicarBonusTerritorial();

        System.out.println("Ejércitos generados:");
        System.out.println("Ejército 1 -> " + reinos[r1]);
        System.out.println("Ejército 2 -> " + reinos[r2]);
    }

    private String generarTerritorio() {
        String[] territorios = {"bosque", "campo abierto", "montaña", "desierto", "playa"};
        return territorios[new Random().nextInt(territorios.length)];
    }

    private void aplicarBonusTerritorial() {
        bonusPorTerreno(ejercito1);
        bonusPorTerreno(ejercito2);
    }

    private void bonusPorTerreno(Ejercito e) {
        String reino = e.getReino();
        if ((reino.equals("Inglaterra") && tipoTerritorio.equals("bosque")) ||
            (reino.equals("Francia") && tipoTerritorio.equals("campo abierto")) ||
            (reino.equals("Castilla-Aragón") && tipoTerritorio.equals("montaña")) ||
            (reino.equals("Moros") && tipoTerritorio.equals("desierto")) ||
            (reino.equals("Sacro Imperio Romano-Germánico") &&
                (tipoTerritorio.equals("bosque") || tipoTerritorio.equals("playa") || tipoTerritorio.equals("campo abierto")))) {
            e.aplicarBonus();
            System.out.println("Bonus aplicado al reino " + reino + " (+1 vida a todos sus soldados)");
        }
    }

    public void mostrarMapa() {
        tablero.mostrarTablero();
    }

    public Ejercito getEjercito1() {
        return ejercito1;
    }

    public Ejercito getEjercito2() {
        return ejercito2;
    }
}
