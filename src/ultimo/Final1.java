package ultimo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Final1 extends JFrame{
    private JPanel panel1;
    private JButton salirButton;
    Fondopanel fondo = new Fondopanel();
    public Final1(){
        this.setContentPane(fondo);
        fondo.add(panel1);
        fondo.setLayout(null);
        panel1.setOpaque(false);
        panel1.setBounds(40, 20, 300, 300);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    class Fondopanel extends JPanel {
        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/img_3.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
    public static void main(String[] args) {
        new Final1();
    }
}