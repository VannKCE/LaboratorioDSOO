import java.awt.*;
import javax.swing.*;

public class viewMain extends JFrame {

    public static final long serialVersionUID = 1L;
    private JButton[][] tablero = new JButton[10][10];
    private JButton btnIniciar, btnPausar, btnReanudar, btnSalir;
    private JButton btnN, btnS, btnE, btnO, btnNE, btnNO, btnSE, btnSO;

    private JTextArea areaInfo;

    public viewMain() {
        setTitle("Batalla de Reinos - GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel panelTablero = new JPanel(new GridLayout(10, 10, 2, 2));
        panelTablero.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tablero[i][j] = new JButton();
                panelTablero.add(tablero[i][j]);
            }
        }
        add(panelTablero, BorderLayout.CENTER);

        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(new BorderLayout());
        panelLateral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelControl = new JPanel(new GridLayout(4, 1, 8, 8));
        panelControl.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        btnIniciar = new JButton("Iniciar");
        btnPausar = new JButton("Pausar");
        btnReanudar = new JButton("Reanudar");
        btnSalir = new JButton("Salir");

        panelControl.add(btnIniciar);
        panelControl.add(btnPausar);
        panelControl.add(btnReanudar);
        panelControl.add(btnSalir);

        panelLateral.add(panelControl, BorderLayout.NORTH);

        JPanel panelMovimiento = new JPanel(new GridLayout(3, 3, 8, 8));
        panelMovimiento.setBorder(BorderFactory.createTitledBorder("Movimientos"));
        panelMovimiento.setPreferredSize(new Dimension(150, 150));

        btnNO = new JButton("NO");
        btnN  = new JButton("N");
        btnNE = new JButton("NE");
        btnO  = new JButton("O");
        JButton centro = new JButton("");
        centro.setEnabled(false);
        btnE  = new JButton("E");
        btnSO = new JButton("SO");
        btnS  = new JButton("S");
        btnSE = new JButton("SE");

        panelMovimiento.add(btnNO);
        panelMovimiento.add(btnN);
        panelMovimiento.add(btnNE);
        panelMovimiento.add(btnO);
        panelMovimiento.add(centro);
        panelMovimiento.add(btnE);
        panelMovimiento.add(btnSO);
        panelMovimiento.add(btnS);
        panelMovimiento.add(btnSE);

        panelLateral.add(panelMovimiento, BorderLayout.CENTER);

        areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        areaInfo.setLineWrap(true);
        areaInfo.setWrapStyleWord(true);
        areaInfo.setText("Información del juego...\nAquí aparecerán datos, soldados, logs, movimientos, ataques, etc.");

        JScrollPane scrollInfo = new JScrollPane(areaInfo);
        scrollInfo.setPreferredSize(new Dimension(250, 250));

        panelLateral.add(scrollInfo, BorderLayout.SOUTH);

        add(panelLateral, BorderLayout.EAST);

        setVisible(true);
    }

    public static void main(String[] args) {
        new viewMain();
    }
}
