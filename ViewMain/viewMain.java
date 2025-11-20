package ViewMain;

import javax.swing.*;
import java.awt.*;

public class viewMain extends JFrame {

    private CeldaPanel[][] celdas;
    private final int filas = 10;
    private final int columnas = 10;
    private JTextArea consola;
    private JScrollPane scrollConsola;

    private MovimientoListener listenerMovimiento;

    public void setMovimientoListener(MovimientoListener l) {
        this.listenerMovimiento = l;
    }

    public viewMain(String reino1, String reino2, String territorio) {
        setTitle("Videojuego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 20));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        // PANEL DE INFORMACIÓN GENERAL
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel labelEjercito1 = new JLabel("Ejército 1: " + reino1, SwingConstants.CENTER);
        JLabel labelTerritorio = new JLabel("Territorio: " + territorio, SwingConstants.CENTER);
        JLabel labelEjercito2 = new JLabel("Ejército 2: " + reino2, SwingConstants.CENTER);

        JPanel panelEjercitos = new JPanel(new GridLayout(1, 2));
        panelEjercitos.add(labelEjercito1);
        panelEjercitos.add(labelEjercito2);

        panelInfo.add(panelEjercitos, BorderLayout.NORTH);
        panelInfo.add(labelTerritorio, BorderLayout.CENTER);

        contentPane.add(panelInfo, BorderLayout.NORTH);

        //TABLERO
        JPanel panelTablero = new JPanel(new GridLayout(filas, columnas));
        panelTablero.setBackground(Color.LIGHT_GRAY);
        celdas = new CeldaPanel[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new CeldaPanel();
                final int fx = i, cx = j;

                celdas[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if (listenerMovimiento != null)
                            listenerMovimiento.onCeldaClick(fx, cx);
                    }
                });

                panelTablero.add(celdas[i][j]);
            }
        }
        contentPane.add(panelTablero, BorderLayout.CENTER);


        // CONSOLA
        consola = new JTextArea();
        consola.setEditable(false);
        consola.setBackground(Color.WHITE);
        consola.setForeground(Color.BLACK);
        scrollConsola = new JScrollPane(consola);
        scrollConsola.setPreferredSize(new Dimension(250, 0));
        contentPane.add(scrollConsola, BorderLayout.EAST);

        // LAB 11- MENUBAR
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemNuevo = new JMenuItem("Nuevo");
        JMenuItem itemAbrir = new JMenuItem("Abrir");
        JMenuItem itemGuardar = new JMenuItem("Guardar");
        JMenuItem itemSalir = new JMenuItem("Salir");
        menuArchivo.add(itemNuevo);
        menuArchivo.add(itemAbrir);
        menuArchivo.add(itemGuardar);
        menuArchivo.add(itemSalir);

        itemSalir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea salir?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (opcion == JOptionPane.YES_OPTION)
                System.exit(0);
        });

        JMenu menuVer = new JMenu("Ver");
        JMenuItem itemConsola = new JMenuItem("Mostrar/Ocultar consola");
        menuVer.add(itemConsola);

        itemConsola.addActionListener(e -> scrollConsola.setVisible(!scrollConsola.isVisible()));

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemSobre = new JMenuItem("Sobre el juego");
        menuAyuda.add(itemSobre);

        menuBar.add(menuArchivo);
        menuBar.add(menuVer);
        menuBar.add(menuAyuda);

        setJMenuBar(menuBar);

        JPanel panelBotones = new JPanel(new GridLayout(3, 3, 5, 5));

        String[][] dirs = {
                {"x", "n", "w"},
                {"o", " ", "e"},
                {"z", "s", "y"}
        };

        for (int i = 0; i < dirs.length; i++) {
            for (int j = 0; j < dirs[i].length; j++) {
                String d = dirs[i][j];
                JButton b;
                if (d.equals(" ")) {
                    b = new JButton();
                    b.setEnabled(false);
                } else {
                    b = new JButton(d);
                    b.addActionListener(e -> {
                        if (listenerMovimiento != null)
                            listenerMovimiento.onMover(d);
                    });
                }
                panelBotones.add(b);
            }
        }

        contentPane.add(panelBotones, BorderLayout.SOUTH);


        setVisible(true);
    }

    public void escribirConsola(String texto) {
        consola.append(texto + "\n");
    }

    public void colocarSoldado(int fila, int columna, Image imagen) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            celdas[fila][columna].setImagen(imagen);
        }
    }

    public void limpiarTablero() {
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                celdas[i][j].setImagen(null);
    }

}
