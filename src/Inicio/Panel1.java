package Inicio;

import usuarios.UsuarioPri;
import usuarios.UsuarioPubli;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // se inicia desde el main o panel1

public class Panel1 extends JFrame {
    private JButton privadoButton;
    private JPanel panel1;
    private JButton publicoButton;
    private JLabel label1;
    Fondopanel fondo =  new Fondopanel();
    public Panel1() {
        this.setContentPane(fondo);
        fondo.add(panel1);
        fondo.setLayout(null);
        panel1.setOpaque(false);
        panel1.setBounds(40, 10, 300, 300);

        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        privadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UsuarioPri().setVisible(true);
                dispose();
            }
        });
        publicoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UsuarioPubli().setVisible(true);
                dispose();
            }
        });
    }

    class Fondopanel extends JPanel {
        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new  ImageIcon(getClass().getResource("/imagenes/img.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
    public static void main(String[] args) {
        new Panel1().setVisible(true);
    }

}
