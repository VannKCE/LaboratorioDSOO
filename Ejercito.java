import java.util.ArrayList;
import java.util.Random;

public class Ejercito {
    private ArrayList<Soldado> soldados; 
    private int numero; 
    private Random rand = new Random();

    public Ejercito(int numero) {
        this.numero = numero;
        soldados = new ArrayList<>();
    }

    public void generarSoldados(Tablero t) {
        int filas = 10, columnas = 10;
        int cantidad = rand.nextInt(10) + 1; 

        for (int i = 0; i < cantidad; i++) {
            int fila, columna;

            do {
                fila = rand.nextInt(filas);
                columna = rand.nextInt(columnas);
            } while (t.obtenerSoldado(fila, columna) != null);

            int tipo = rand.nextInt(4);
            Soldado s;

            switch (tipo) {
                case 0:
                    s = new Espadachines("Espadachin" + i + "X" + numero,
                            rand.nextInt(2) + 3, fila, columna, numero, rand.nextInt(2) + 1);
                    break;
                case 1:
                    s = new Arquero("Arquero" + i + "X" + numero,
                            rand.nextInt(3) + 1, fila, columna, numero, rand.nextInt(5) + 5);
                    break;
                case 2:
                    s = new Caballeros("Caballero" + i + "X" + numero, rand.nextInt(3) + 3, fila, columna, numero, true);
                    break;
                default:
                    s = new Lancero("Lancero" + i + "X" + numero, rand.nextInt(2) + 1, fila, columna, numero, rand.nextInt(3) + 2);
                    break;
            }

            soldados.add(s);
            t.colocarSoldado(s, fila, columna);
        }
    }

    public void mostrarEjercito() {
        System.out.println("EJÉRCITO " + numero);
        for (Soldado s : soldados) {
            System.out.println(s);
        }
        System.out.println();
    }

    public Soldado obtenerMayorVida() {
        Soldado mayor = soldados.get(0);
        for (Soldado s : soldados) {
            if (s.getPuntosVida() > mayor.getPuntosVida()) {
                mayor = s;
            }
        }
        return mayor;
    }

    public double promedioVida() {
        int total = 0;
        for (Soldado s : soldados) {
            total += s.getPuntosVida();
        }
        return (double) total / soldados.size();
    }

    public void ordenarPorBurbuja() {
        ArrayList<Soldado> copia = new ArrayList<>(soldados);
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - i - 1; j++) {
                if (copia.get(j).getPuntosVida() < copia.get(j + 1).getPuntosVida()) {
                    Soldado temp = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temp);
                }
            }
        }
        System.out.println("EJÉRCITO " + numero);
        for (Soldado s : copia) {
            System.out.println(s);
        }
        System.out.println();
    }

    public void ordenarPorSeleccion() {
        ArrayList<Soldado> copia = new ArrayList<>(soldados);
        for (int i = 0; i < copia.size() - 1; i++) {
            int indiceMayor = i;
            for (int j = i + 1; j < copia.size(); j++) {
                if (copia.get(j).getPuntosVida() > copia.get(indiceMayor).getPuntosVida()) {
                    indiceMayor = j;
                }
            }
            Soldado temp = copia.get(i);
            copia.set(i, copia.get(indiceMayor));
            copia.set(indiceMayor, temp);
        }
        System.out.println("EJÉRCITO " + numero );
        for (Soldado s : copia) {
            System.out.println(s);
        }
        System.out.println();
    }

    public int puntosTotales() {
        int total = 0;
        for (Soldado s : soldados) {
            total += s.getPuntosVida();
        }
        return total;
    }

    public void comparar(Ejercito otro) {
        int total1 = this.puntosTotales();
        int total2 = otro.puntosTotales();

        System.out.println("Comparando Ejércitos...");
        System.out.println("Ejército 1 total vida: " + total1);
        System.out.println("Ejército 2 total vida: " + total2);

        if (total1 > total2) {
            System.out.println("GANADOR: Ejército 1");
        } else if (total1 < total2) {
            System.out.println("GANADOR: Ejército 2");
        } else {
            System.out.println("Empate");
        }
        System.out.println("MÉTRICA: Mayor suma de puntos de vida.");
        System.out.println();
    }

    public ArrayList<Soldado> getSoldados() {
        return soldados;
    }

    public int getNumero() {
        return numero;
    }
}
