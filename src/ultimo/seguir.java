package ultimo;

import Inicio.Panel1;
import factura.Factura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class seguir extends JFrame{
    private JButton siButton;
    private JButton noButton;
    private JPanel panel1;
    Fondopanel fondo = new Fondopanel();
    public seguir() {
        this.setContentPane(fondo);
        fondo.add(panel1);

        fondo.setLayout(null);
        panel1.setOpaque(false);

        panel1.setBounds(40, 20, 300, 300);

        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        siButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Panel1().setVisible(true);
                dispose();
            }
        });
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Factura().setVisible(true);
                dispose();
            }
        });
    }
    class Fondopanel extends JPanel {
        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/img_1.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
    public static void main(String[] args) {
        new seguir();
    }
}
