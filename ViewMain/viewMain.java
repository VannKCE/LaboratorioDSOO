package ViewMain;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

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

    private String ultimaPartidaRanking = "";

    public void setRanking(String texto) { 
        ultimaPartidaRanking = texto; 
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
        JMenuItem itemAbrirLogs = new JMenuItem("Abrir Logs"); 
        JMenuItem itemAbrirConfiguracion = new JMenuItem("Abrir Configuración");
        JMenuItem itemAbrirRanking = new JMenuItem("Abrir Ranking");
        JMenuItem itemGuardar = new JMenuItem("Guardar");
        JMenuItem itemGuardarLogs = new JMenuItem("Guardar Logs");
        JMenuItem itemGuardarRanking = new JMenuItem("Guardar Ranking");
        JMenuItem itemGuardarConfiguracion = new JMenuItem("Guardar Configuracion");
        JMenuItem itemSalir = new JMenuItem("Salir");
        menuArchivo.add(itemNuevo);
        menuArchivo.add(itemAbrir);
        menuArchivo.add(itemAbrirLogs);
        menuArchivo.add(itemAbrirConfiguracion);
        menuArchivo.add(itemAbrirRanking);
        menuArchivo.add(itemGuardar);
        menuArchivo.add(itemGuardarLogs);
        menuArchivo.add(itemGuardarRanking);
        menuArchivo.add(itemGuardarConfiguracion);
        menuArchivo.add(itemSalir);

        itemNuevo.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea iniciar un nuevo juego?",
                "Nuevo Juego",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                reiniciarJuego();
                Controlador.Videojuego.nuevoJuego(this);
            }
        });

        itemSalir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea salir?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            if (opcion == JOptionPane.YES_OPTION)
                System.exit(0);
        });

        itemGuardarLogs.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea guardar los logs?",
                "Confirmar guardado",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                try (FileWriter fw = new FileWriter("resources/logs.txt", true);
                    PrintWriter pw = new PrintWriter(fw)) {
                    pw.println(consola.getText());
                    JOptionPane.showMessageDialog(this, "Logs guardados correctamente.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al guardar los logs.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        itemGuardarConfiguracion.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea guardar la configuración?",
                "Confirmar guardado",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                String config =
                    "Configuración del juego\n"  +
                    "Territorio: " + territorio + "\n" +
                    "Ejército 1: " + reino1 + "\n" +
                    "Ejército 2: " + reino2 + "\n" +
                    "Tablero: 10x10\n";

                System.out.println("CONFIG →\n" + config); 
                guardarArchivo("resources/configuracion.txt", config);
            }
        });

        itemGuardarRanking.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea guardar la configuración?",
                "Confirmar guardado",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                guardarArchivo("resources/ranking.txt", ultimaPartidaRanking);
            }
        });

        itemAbrirLogs.addActionListener(e -> {
            File f = new File("resources/logs.txt");
            if (f.exists()) {
                new ViewLogs("resources/logs.txt");
            } else {
                JOptionPane.showMessageDialog(this, "No existen logs todavía.");
            }
        });


        itemAbrirConfiguracion.addActionListener(e -> {
            new ViewLogs("resources/configuracion.txt");
        });

        itemAbrirRanking.addActionListener(e -> {
            new ViewLogs("resources/ranking.txt");
        });

        //LABORATORIO 13 - GUARDAR
        itemGuardar.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea guardar el archivo?",
                "Confirmar guardado",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                try {
                    String[][] estado = new String[filas][columnas];

                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            String nombre = celdas[i][j].getNombreImagen();
                            estado[i][j] = (nombre == null) ? "" : nombre;
                        }
                    }

                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("partida.dat"));
                    oos.writeObject(estado);
                    oos.close();

                    JOptionPane.showMessageDialog(this, "Juego guardado correctamente.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al guardar el juego.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //LABORATORIO 13 - ABRIR 
        itemAbrir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea abrir la partida?",
                "Abrir",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("partida.dat"));
                    String[][] estado = (String[][]) ois.readObject();
                    ois.close();

                    limpiarTablero();

                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            if (!estado[i][j].isEmpty()) {

                                String nombre = estado[i][j];
                                Image img = new ImageIcon(nombre).getImage();

                                celdas[i][j].setImagenConNombre(nombre, img);
                            }
                        }
                    }

                    escribirConsola("Partida abierta correctamente.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al abrir la partida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JMenu menuVer = new JMenu("Ver");
        JMenuItem itemConsola = new JMenuItem("Mostrar/Ocultar consola");
        menuVer.add(itemConsola);

        itemConsola.addActionListener(e -> scrollConsola.setVisible(!scrollConsola.isVisible()));

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemSobre = new JMenuItem("Sobre el juego");
        itemSobre.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "Este es un juego de batalla de ejercitos con diferentes tipos de soldados",
                "Sobre el juego",
                JOptionPane.INFORMATION_MESSAGE)
        );
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

    public void colocarSoldado(int fila, int columna, String nombreImagen) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {

            Image img = new ImageIcon(nombreImagen).getImage();

            celdas[fila][columna].setImagenConNombre(nombreImagen, img);
        }
    }

    public void limpiarTablero() {
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                celdas[i][j].setImagen(null);
    }

    public void guardarArchivo(String ruta, String contenido) {
        try {
            FileWriter fw = new FileWriter(ruta);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(contenido);
            pw.close();
            fw.close();
            JOptionPane.showMessageDialog(this,
                    "Archivo guardado en:\n" + ruta,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo guardar el archivo:\n" + ruta,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void reiniciarJuego() {
        consola.setText("");
        limpiarTablero();

        try (PrintWriter pw = new PrintWriter("resources/logs.txt")) {
            pw.print(""); 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        escribirConsola("Nuevo juego.");
    }

}
