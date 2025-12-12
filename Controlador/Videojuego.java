package Controlador;

import Modelo.*;
import ViewMain.*;
import javax.swing.*;

public class Videojuego {
    private static Soldado soldadoSeleccionado = null;
    private static int turnoEjercito = 1;

    public static void main(String[] args) {
        Mapa mapa = new Mapa();
        Ejercito e1 = mapa.getEjercito1();
        Ejercito e2 = mapa.getEjercito2();
        viewMain ventana = new viewMain(e1.getReino(), e2.getReino(), mapa.getTipoTerritorio());
        
        ventana.escribirConsola("RESUMEN");
        
        e1.mostrarResumenConsola(ventana);
        e2.mostrarResumenConsola(ventana);

        // COLOCAR SOLDADOS EN EL TABLERO 
        for (Soldado s : e1.getSoldados())
            ventana.colocarSoldado(s.getFila(), s.getColumna(), obtenerNombreImagen(s, true));

        for (Soldado s : e2.getSoldados())
            ventana.colocarSoldado(s.getFila(), s.getColumna(), obtenerNombreImagen(s, false));
        

        Batalla batalla = new Batalla(mapa.getTablero());

        ventana.setMovimientoListener(new MovimientoListener() {
            @Override
            public void onCeldaClick(int fila, int columna) {
                Soldado s = mapa.getTablero().obtenerSoldado(fila, columna);
                if (s != null && s.estaVivo()) {
                    if ((turnoEjercito == 1 && s.getEjercito() != 1) || 
                        (turnoEjercito == 2 && s.getEjercito() != 2)) {
                        ventana.escribirConsola("No es el turno de ese ejército.");
                        return;
                    }
                    soldadoSeleccionado = s;
                    ventana.escribirConsola("Soldado seleccionado en (" + (fila + 1) + "," + (columna + 1) + ")");
                } else {
                    ventana.escribirConsola("No hay soldado en esa celda.");
                }
            }

            @Override
            public void onMover(String direccion) {
                if (soldadoSeleccionado == null) {
                    ventana.escribirConsola("Seleccione un soldado primero.");
                    return;
                }

                boolean movido = batalla.moverSoldado(soldadoSeleccionado, direccion);
                if (movido) {
                    turnoEjercito = (turnoEjercito == 1) ? 2 : 1;
                    ventana.escribirConsola("Turno del ejército " + turnoEjercito);

                    actualizarTablero(ventana, mapa);
                    soldadoSeleccionado = null;

                    verificarGanador(mapa, ventana);
                }
            }
        });

        Metrica metrica = new Metrica();
        metrica.calcularGanador(e1, e2, ventana);

        
        ventana.escribirConsola("Turno del ejército " + turnoEjercito);
        

    }

    private static void actualizarTablero(viewMain ventana, Mapa mapa) {
        ventana.limpiarTablero();

        for (Soldado s : mapa.getEjercito1().getSoldados())
            if (s.estaVivo())
                ventana.colocarSoldado(s.getFila(), s.getColumna(), obtenerNombreImagen(s, true));

        for (Soldado s : mapa.getEjercito2().getSoldados())
            if (s.estaVivo())
                ventana.colocarSoldado(s.getFila(), s.getColumna(), obtenerNombreImagen(s, false));
    }

    private static String obtenerNombreImagen(Soldado s, boolean azul) {
        String color = azul ? "azul" : "rojo";

        switch (s.getClass().getSimpleName()) {
            case "Espadachines":
                return "e_" + color + ".png";
            case "Arquero":
                return "a_" + color + ".png";
            case "Caballeros":
                return "c_" + color + ".png";
            case "Lancero":
                return "l_" + color + ".png";
            default:
                return null;
        }
    }


    private static void verificarGanador(Mapa mapa, viewMain ventana) {
        boolean e1Vivo = mapa.getEjercito1().getSoldados().stream().anyMatch(Soldado::estaVivo);
        boolean e2Vivo = mapa.getEjercito2().getSoldados().stream().anyMatch(Soldado::estaVivo);

        if (e1Vivo && e2Vivo) return;

        String ganadorEj;
        String perdedorEj;
        String ganadorReino;
        String perdedorReino;

        if (!e1Vivo) {
            ganadorEj = "Ejército 2";
            perdedorEj = "Ejército 1";
            ganadorReino = mapa.getEjercito2().getReino();
            perdedorReino = mapa.getEjercito1().getReino();
        } else {
            ganadorEj = "Ejército 1";
            perdedorEj = "Ejército 2";
            ganadorReino = mapa.getEjercito1().getReino();
            perdedorReino = mapa.getEjercito2().getReino();
        }

        ventana.escribirConsola(ganadorEj + ": " + ganadorReino + " ha ganado la batalla!");

        JOptionPane.showMessageDialog(
                ventana,
                "¡" + ganadorEj + " ha ganado!\nReino: " + ganadorReino,
                "Partida terminada",
                JOptionPane.INFORMATION_MESSAGE
        );

        int soldadosVivosGanador = ganadorEj.equals("Ejército 1")
                ? mapa.getEjercito1().contarVivos()
                : mapa.getEjercito2().contarVivos();

        String ranking =
                "RESULTADO DE PARTIDA\n" +
                "Ganador : " + ganadorEj + " (" + ganadorReino + ")\n" +
                "Perdedor: " + perdedorEj + " (" + perdedorReino + ")\n" +
                "Soldados vivos del ganador: " + soldadosVivosGanador + "\n";
        ventana.setRanking(ranking);
        RankingDAO.guardarResultado(ganadorReino, perdedorReino, soldadosVivosGanador);
    }

    public static void nuevoJuego(viewMain ventana) {
        Mapa mapa = new Mapa();
        Ejercito e1 = mapa.getEjercito1();
        Ejercito e2 = mapa.getEjercito2();

        ventana.limpiarTablero();
        ventana.escribirConsola("RESUMEN");
        e1.mostrarResumenConsola(ventana);
        e2.mostrarResumenConsola(ventana);

        for (Soldado s : e1.getSoldados())
            ventana.colocarSoldado(s.getFila(), s.getColumna(), obtenerNombreImagen(s, true));

        for (Soldado s : e2.getSoldados())
            ventana.colocarSoldado(s.getFila(), s.getColumna(), obtenerNombreImagen(s, false));
    }

}


