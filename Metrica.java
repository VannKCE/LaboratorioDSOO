import java.util.Random;
import javax.swing.JTextArea;

public class Metrica {
    
    //LAB 09
    public void calcularGanador(Ejercito e1, Ejercito e2) {
        Random rand = new Random();
        int total1 = e1.puntosTotales();
        int total2 = e2.puntosTotales();
        int total = total1 + total2;

        double p1 = (total1 * 100.0) / total;
        double p2 = (total2 * 100.0) / total;
        double aleatorio = rand.nextDouble() * 100;

        System.out.println("MÉTRICA DE BATALLA");
        System.out.println("Ejército 1: " + e1.getReino() + ": " + String.format("%.2f", p1) + "% de probabilidad de victoria.");
        System.out.println("Ejército 2: " + e2.getReino() + ": " + String.format("%.2f", p2) + "% de probabilidad de victoria.");
        System.out.println("Número aleatorio generado: " + String.format( "%.2f", aleatorio));


        if (aleatorio <= p1)
            System.out.println("El ganador es el ejército 1 de: " + e1.getReino() + ". Ya que al generar los porcentajes de probabilidad de victoria basada en los niveles de vida de sus soldados y aplicando un experimento aleatorio salió vencedor.");
        else
            System.out.println("El ganador es el ejército 2 de: " + e2.getReino() + ". Ya que al generar los porcentajes de probabilidad de victoria basada en los niveles de vida de sus soldados y aplicando un experimento aleatorio salió vencedor.");
    }

    public void calcularGanador(Ejercito e1, Ejercito e2, JTextArea consola) {
        Random rand = new Random();
        int total1 = e1.puntosTotales();
        int total2 = e2.puntosTotales();
        int total = total1 + total2;

        double p1 = (total1 * 100.0) / total;
        double p2 = (total2 * 100.0) / total;
        double aleatorio = rand.nextDouble() * 100;

        consola.append("MÉTRICA DE BATALLA\n");
        consola.append("Ejército 1: " + e1.getReino() + ": " + String.format("%.2f", p1) + "% de probabilidad de victoria.\n");
        consola.append("Ejército 2: " + e2.getReino() + ": " + String.format("%.2f", p2) + "% de probabilidad de victoria.\n");
        consola.append("Número aleatorio generado: " + String.format("%.2f", aleatorio) + "\n");

        if (aleatorio <= p1) {
            consola.append("El ganador es el ejército 1 de: " + e1.getReino() + ". Ya que al generar los porcentajes de probabilidad de victoria basada en los niveles de vida de sus soldados y aplicando un experimento aleatorio salió vencedor.\n");
        } else {
            consola.append("El ganador es el ejército 2 de: " + e2.getReino() + ". Ya que al generar los porcentajes de probabilidad de victoria basada en los niveles de vida de sus soldados y aplicando un experimento aleatorio salió vencedor.\n");
        }
    }

}