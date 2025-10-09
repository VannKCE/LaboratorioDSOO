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

            int puntosVida = rand.nextInt(5) + 1;
            
            Soldado s = new Soldado(puntosVida, fila, columna, numero);
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
        ArrayList<Soldado> Soldadosc = new ArrayList<>(soldados);
        for (int i = 0; i < Soldadosc.size() - 1; i++) {
            for (int j = 0; j < Soldadosc.size() - i - 1; j++) {
                if (Soldadosc.get(j).getPuntosVida() < Soldadosc.get(j + 1).getPuntosVida()) {
                    Soldado temp = Soldadosc.get(j);
                    Soldadosc.set(j, Soldadosc.get(j + 1));
                    Soldadosc.set(j + 1, temp);
                }
            }
        }
        System.out.println("EJÉRCITO " + numero);
        for (Soldado s : Soldadosc) {
            System.out.println(s);
        }
    }

    public void ordenarPorSeleccion() {
        ArrayList<Soldado> Soldadosc = new ArrayList<>(soldados);
        for (int i = 0; i < Soldadosc.size() - 1; i++) {
            int indiceMayor = i;
            for (int j = i + 1; j < Soldadosc.size(); j++) {
                if (Soldadosc.get(j).getPuntosVida() > Soldadosc.get(indiceMayor).getPuntosVida()) {
                    indiceMayor = j;
                }
            }
            Soldado temp = Soldadosc.get(i);
            Soldadosc.set(i, Soldadosc.get(indiceMayor));
            Soldadosc.set(indiceMayor, temp);
        }

        System.out.println("EJERCITO " + numero);
        for (Soldado s : Soldadosc) {
            System.out.println(s);
        }
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

        if (total1 > total2) {
            System.out.println("GANADOR: Ejercito 1");
        } else if (total1 < total2) {
            System.out.println("GANADOR: Ejercito 2");
        } else {
            System.out.println("Empate");
        }

        System.out.println("MÉTRICA: Mayor puntos de vida.");
    }

    public ArrayList<Soldado> getSoldados() {
        return soldados;
    }

    public int getNumero() {
        return numero;
    }

}
