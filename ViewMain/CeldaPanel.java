package ViewMain;

import javax.swing.*;
import java.awt.*;

public class CeldaPanel extends JPanel {

    private Image imagen;
    private Color fondo = Color.WHITE;

    public void setImagen(Image imagen) {
        this.imagen = imagen;
        repaint();
    }

    public Image getImagen() {
        return imagen;
    }

    public void setColorFondo(Color c) {
        this.fondo = c;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(fondo);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
