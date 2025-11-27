package Modelo;

import ViewMain.viewMain;

import java.util.ArrayList;
import java.util.Random;

public class Ejercito {
    private ArrayList<Soldado> soldados; 
    private int numero; 
    //LAB 09
    private String reino;
    private Random rand = new Random();

    public Ejercito(int numero) {
        this.numero = numero;
        soldados = new ArrayList<>();
    }


    public Ejercito(int numero, String reino) {
        this.numero = numero;
        this.reino = reino;
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

            int tipo = rand.nextInt(4); // 0=Espadachín, 1=Arquero, 2=Caballero, 3=Lancero
            Soldado s;

            switch (tipo) {
                case 0:
                    s = new Espadachines("Espadachin" + i + "X" + numero, fila, columna, numero);
                    break;
                case 1:
                    s = new Arquero("Arquero" + i + "X" + numero, fila, columna, numero);
                    break;
                case 2:
                    s = new Caballeros("Caballero" + i + "X" + numero, fila, columna, numero);
                    break;
                default:
                    s = new Lancero("Lancero" + i + "X" + numero, fila, columna, numero);
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

    //LAB 09
    public void mostrarResumen() {
        int cab = 0, arq = 0, esp = 0, lan = 0;
        for (Soldado s : soldados) {
            if (s instanceof Caballeros) cab++;
            else if (s instanceof Arquero) arq++;
            else if (s instanceof Espadachines) esp++;
            else if (s instanceof Lancero) lan++;
        }
        System.out.println("Ejército " + numero + ": " + reino);
        System.out.println("Total de soldados: " + soldados.size());
        System.out.println("Espadachines: " + esp);
        System.out.println("Arqueros: " + arq);
        System.out.println("Caballeros: " + cab);
        System.out.println("Lanceros: " + lan);
        System.out.println();
    }

    public String getReino() {
        return reino;
    }

    public void aplicarBonus() {
        for (Soldado s : soldados) {
            s.setVidaActual(s.getVidaActual() + 1);
        }
    }

    public void mostrarResumenConsola(viewMain ventana) {
        int cab = 0, arq = 0, esp = 0, lan = 0;
        for (Soldado s : soldados) {
            if (s instanceof Caballeros) cab++;
            else if (s instanceof Arquero) arq++;
            else if (s instanceof Espadachines) esp++;
            else if (s instanceof Lancero) lan++;
        }
        ventana.escribirConsola("EJÉRCITO " + numero + ": " + reino); 
        for (Soldado s : soldados) {
            ventana.escribirConsola(s.toString());
        }
        ventana.escribirConsola("Total de soldados: " + soldados.size());
        ventana.escribirConsola("Espadachines: " + esp);
        ventana.escribirConsola("Arqueros: " + arq);
        ventana.escribirConsola("Caballeros: " + cab);
        ventana.escribirConsola("Lanceros: " + lan);
    }

    public int contarVivos() {
        int c = 0;
        for (Soldado s : soldados)
            if (s.estaVivo()) c++;
        return c;
    }

}
