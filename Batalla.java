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
            case "e": colDestino++; break;   
            case "o": colDestino--; break;  
            case "n": filaDestino--; break; 
            case "s": filaDestino++; break; 
            case "w": filaDestino--; colDestino++; break; 
            case "x": filaDestino--; colDestino--; break;  
            case "y": filaDestino++; colDestino++; break;       
            case "z": filaDestino++; colDestino--; break;  
            default: 
                System.out.println("Dirección inválida."); 
                return false;
        }

        if (!batalla.movimientoValido(pos[0], pos[1], filaDestino, colDestino, s.getEjercito())) {
            System.out.println("Movimiento inválido.");
            return false;
        }

        Soldado rival = batalla.obtenerSoldadoRival(filaDestino, colDestino, s.getEjercito());
        if (rival != null) {
            System.out.println("Batalla");
            resolverBatalla(s, rival);
        } else {
            batalla.moverSoldado(pos[0], pos[1], filaDestino, colDestino);
            System.out.println("Soldado movido a [" + filaDestino + "," + colDestino + "]");
        }

        return true;
    }

    private void resolverBatalla(Soldado E1, Soldado E2) {
        if (E1.getVidaActual() >= E2.getVidaActual()) {
            System.out.println("El soldado E" + E1.getEjercito() + " gana la batalla.");
            E2.morir();
            batalla.moverSoldado(E1.getFila(), E1.getColumna(), E2.getFila(), E2.getColumna());
        } else {
            System.out.println("El soldado E" + E2.getEjercito() + " gana la batalla.");
            E1.morir();
        }
    }

}
