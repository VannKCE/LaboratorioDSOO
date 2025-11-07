public class Soldado {
    //LAB 03
    private String nombre;
    private int puntosVida;
    private int fila;
    private int columna;
    private int ejercito;
    private static int contador1=0;
    private static int contador2=0;
    //LAB 04
    private int nivelAtaque;
    private int nivelDefensa;
    private int nivelVida;
    private int vidaActual;
    private int velocidad;
    private String actitud;
    private boolean vive;

    //LAB 04 - 3 CONSTRUCTORES//
    public Soldado() {
        this.puntosVida = (int)(Math.random() * 5) + 1;
        this.fila = 0;
        this.columna = 0;
        this.ejercito = 1;
        this.nivelAtaque = (int)(Math.random() * 5) + 1;
        this.nivelDefensa = (int)(Math.random() * 5) + 1;
        this.nivelVida = puntosVida;
        this.vidaActual = puntosVida;
        this.velocidad = 0;
        this.actitud = "defensiva";
        this.vive = true;

        this.nombre = "Soldado" + contador1 + "X1";
        contador1++;
    }

    public Soldado(String nombre, int puntosVida, int fila, int columna, int ejercito) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.fila = fila;
        this.columna = columna;
        this.ejercito = ejercito;
    }
    
    public Soldado(int puntosVida, int fila, int columna, int ejercito) {
        this.puntosVida = puntosVida;
        this.fila = fila;
        this.columna = columna;
        this.ejercito = ejercito;
        if (ejercito == 1) {
            this.nombre = "Soldado" + contador1 + "X1";
            contador1++;
        } else {
            this.nombre = "Soldado" + contador2 + "X2";
            contador2++;
        }

        this.nivelAtaque = (int)(Math.random() * 5) + 1;
        this.nivelDefensa = (int)(Math.random() * 5) + 1;
        this.nivelVida = puntosVida;
        this.vidaActual = puntosVida;
        this.velocidad = 0;
        this.actitud = "defensiva";
        this.vive = true;
    }

    public Soldado(String nombre, int fila, int columna, int ejercito, int ataque, int defensa, int puntosVida) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;
        this.ejercito = ejercito;
        this.nivelAtaque = ataque;
        this.nivelDefensa = defensa;
        this.puntosVida = puntosVida;
        this.vidaActual = puntosVida;
        this.vive = true;
    }

    public Soldado(int puntosVida, int fila, int columna, int ejercito, int nivelAtaque, int nivelVida, int vidaActual, int nivelDefensa, int velocidad, String actitud, boolean vive) {
        this.puntosVida = puntosVida;
        this.fila = fila;
        this.columna = columna;
        this.ejercito = ejercito;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.nivelVida = nivelVida;
        this.vidaActual = vidaActual;
        this.velocidad = velocidad;
        this.actitud = actitud;
        this.vive = vive;
    }


    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPuntosVida() { 
        return puntosVida; 
    }
    public int getFila() { 
        return fila; 
    }
    public int getColumna() { 
        return columna; 
    }
    public int getEjercito() { 
        return ejercito; 
    }

    public void setPuntosVida(int puntosVida){
        this.puntosVida= puntosVida;
    }

    //LAB  04 - MÉTODOS//
    public void atacar() {
        if(vive) {
            avanzar();
        }
    }

    public void defender() {
        if (vive) {
            velocidad = 0;
            actitud = "defensiva";
        }
    }

    public void avanzar() {
        if (vive) {
            velocidad++;
            actitud = "ofensiva";
        }
    }

    public void retroceder() {
        if(vive){
            if (velocidad > 0) {
                velocidad = 0;
            } else {
                velocidad--;
            }
            actitud = "defensiva";
        }
    }

    public void serAtacado() {
        if(vive) {
            vidaActual--;
            if(vidaActual <= 0){
                morir();
            }
        }
    }

    public void huir() {
        if (vive){
            velocidad += 2;
            actitud = "fuga";
        }

    }

    public void morir() {
        vidaActual = 0;
        vive = false;
    }

    public int getVidaActual() { 
        return vidaActual; 
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }

    //LAB 05
    public boolean estaVivo() {
        return vive;
    }

    public int[] getPosicion() {
        return new int[] {fila, columna};
    }

    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
    
    public int getNivelDefensa() {
        return nivelDefensa;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " | PV: " + puntosVida + " | Vida actual: " + vidaActual + " | Posición: (" + (fila + 1) + "," + (columna + 1) + ")" + " | Ejército: " + ejercito + " | Actitud: " + actitud;
    }
}