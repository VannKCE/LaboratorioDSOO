import java.util.*;
public class Batalla {
    
    private Tablero batalla;
    

    public Batalla(Tablero batalla) {
        this.batalla = batalla;
    }

    public boolean moverSoldado(Soldado s, String direccion) {
        int[] pos = s.getPosicion();
        int filaDestino = pos[0];
        int colDestino = pos[1];

        switch (direccion.toLowerCase()) {
            case "e": 
                colDestino++; 
                break;   
            case "o": 
                colDestino--; 
                break;  
            case "n": 
                filaDestino--; 
                break; 
            case "s": 
                filaDestino++; 
                break; 
            case "w": 
                filaDestino--; 
                colDestino++; 
                break; 
            case "x": 
                filaDestino--; 
                colDestino--; 
                break;  
            case "y": 
                filaDestino++; 
                colDestino++; 
                break;       
            case "z":
                filaDestino++; 
                colDestino--; 
                break;  
            default: 
                System.out.println("Dirección inválida."); 
                return false;
        }

        if (!batalla.movimientoValido(pos[0], pos[1], filaDestino, colDestino, s.getEjercito())) {
            System.out.println("Movimiento inválido.");
            return false;
        }

        Soldado rival = batalla.obtenerSoldado(filaDestino, colDestino, s.getEjercito());
        if (rival == null) {
            batalla.moverSoldado(pos[0], pos[1], filaDestino, colDestino);
            System.out.println("Soldado movido a [" + (filaDestino+1) + "," + (colDestino+1) + "]");
            
        } else {
            System.out.println("BATALLA");
            resolverBatalla(rival, s);  
        }

        return true;
    }

    // LABORATORIO 06
    private void resolverBatalla(Soldado E1, Soldado E2) {
        Random rand = new Random();
        int aleatorio = rand.nextInt(101);
        int total = E1.getVidaActual() + E2.getVidaActual();

        double prob1 = (E1.getVidaActual() * 100.0) / total;
        double prob2 = (E2.getVidaActual() * 100.0) / total;

        System.out.println("Vida Soldado 1: " + E1.getVidaActual());
        System.out.println("Vida Soldado 2: " + E2.getVidaActual());
        System.out.println("Probabilidad de ganar S1: " + prob1);
        System.out.println("Probabilidad de ganar S2: " + prob2);
        System.out.println("Número aleatorio generado: " + aleatorio);

        if (aleatorio < prob1) {
            System.out.println("Gana el soldado del ejército " + E1.getEjercito() + " porque su probabilidad fue mayor que el valor aleatorio.");
            E2.morir();
            E1.setVidaActual(E1.getVidaActual() + 1);
            batalla.moverSoldado(E1.getFila(), E1.getColumna(), E2.getFila(), E2.getColumna());
        } else {
            System.out.println("Gana el soldado del ejército " + E2.getEjercito() + " porque el número aleatorio superó la probabilidad de su oponente.");
            E1.morir();
            E2.setVidaActual(E2.getVidaActual() + 1);
            batalla.moverSoldado(E2.getFila(), E2.getColumna(), E1.getFila(), E1.getColumna());
        }
        
    }

}
