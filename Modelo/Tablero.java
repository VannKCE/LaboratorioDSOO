package Modelo;

import java.util.ArrayList;
public class Tablero {
    private ArrayList<ArrayList<Soldado>> tablero;
    private int filas;
    private int columnas;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        tablero = new ArrayList<>();

        for (int i = 0; i < filas; i++) {
            ArrayList<Soldado> fila = new ArrayList<>();
            for (int j = 0; j < columnas; j++) {
                fila.add(null); 
            }
            tablero.add(fila);
        }
    }

    public boolean colocarSoldado(Soldado s, int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            if (tablero.get(fila).get(columna) == null) {
                tablero.get(fila).set(columna, s);
                return true;
            }
        }
        return false;
    }

    public Soldado obtenerSoldado(int fila, int columna) {
        return tablero.get(fila).get(columna);
    }

    public void mostrarTablero() {
        System.out.print("   "); 
        for (int j = 0; j < columnas; j++) {
            if (j + 1 < 10)
                System.out.print(" 0" + (j + 1) + " ");
            else
                System.out.print(" " + (j + 1) + " ");
        }
        System.out.println();

        for (int i = 0; i < filas; i++) {
            if (i + 1 < 10) {
                System.out.print("0" + (i + 1) + " ");
            } else {
                System.out.print(i + 1 + " ");
            }

            for (int j = 0; j < columnas; j++) {
                Soldado s = tablero.get(i).get(j);
                if (s == null) {
                    System.out.print("[  ]");
                } else {
                    char inicial = s.getNombre().charAt(0); 
                    System.out.print("[" + inicial + s.getEjercito() + "]");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    public ArrayList<ArrayList<Soldado>> getTablero() {
        return tablero;
    }

    public boolean movimientoValido(int filaOrigen, int colOrigen, int filaDestino, int colDestino, int ejercito) {
        if (filaOrigen < 0 || filaOrigen >= filas || colOrigen < 0 || colOrigen >= columnas)
            return false;

        Soldado s = tablero.get(filaOrigen).get(colOrigen);
        if (s == null || s.getEjercito() != ejercito || !s.estaVivo())
            return false;

        if (filaDestino < 0 || filaDestino >= filas || colDestino < 0 || colDestino >= columnas)
            return false;

        Soldado destino = tablero.get(filaDestino).get(colDestino);
        if (destino != null && destino.getEjercito() == ejercito)
            return false;

        return true;
    }

    public boolean moverSoldado(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        Soldado s = obtenerSoldado(filaOrigen, colOrigen);
        if (s == null)
            return false;

        tablero.get(filaDestino).set(colDestino, s);
        tablero.get(filaOrigen).set(colOrigen, null);

        s.setPosicion(filaDestino, colDestino);

        s.avanzar();

        return true;
    }

    public Soldado obtenerSoldado(int fila, int columna, int ejercito) {
        Soldado s = tablero.get(fila).get(columna);
        if (s != null && s.getEjercito() != ejercito) {
            return s;
        }
        return null;
    }


}
