package ViewMain;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class ViewLogs extends JFrame {

    private JPanel contentPane;
    private JTextArea textArea;

    public ViewLogs(String filetxt) {

        setTitle("Visualizador de Logs");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        cargarArchivo(filetxt);

        setVisible(true);
    }

    private void cargarArchivo(String ruta) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            StringBuilder sb = new StringBuilder();

            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }

            br.close();
            textArea.setText(sb.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo abrir el archivo:\n" + ruta,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
