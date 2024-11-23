package Inicio;

import companiasPri.Panel4;
import companiasPri.Panel5;
import factura.Datos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static main.Conexion.conectar;

public class Panel2 extends JFrame {
    private JPanel panel2;
    private JList<String> list1;
    private DefaultListModel<String> listModel;
    private int idAeropuerto;
    Fondopanel fondo = new Fondopanel();

    public Panel2() {
        listModel = new DefaultListModel<>();
        list1.setModel(listModel);

        leerDato();

        this.setContentPane(fondo);
        fondo.add(panel2);

        fondo.setLayout(null);
        panel2.setOpaque(false);
        list1.setOpaque(false);
        panel2.setBounds(40, 0, 300, 300);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int seleccion = list1.getSelectedIndex();
                String selectedValue = listModel.getElementAt(seleccion);
                idAeropuerto = Integer.parseInt(selectedValue.split("\\.")[0].trim());



                String nombre = selectedValue.split("\\.")[1].split("\\(")[0].trim();
                Datos.setNombre(nombre);




                if (seleccion == 0) {
                    new Panel4(idAeropuerto).setVisible(true);
                } else if (seleccion == 1) {
                    new Panel5(idAeropuerto).setVisible(true);
                }
                dispose();

            }
        });
    }

    public void leerDato() {
        String query = "SELECT idAeropuerto, nombre, ciudad, pais, subvencion FROM aeropuertos WHERE Privado = 1 LIMIT 2";
        try {
            Connection con = conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String row = rs.getString("idAeropuerto") + ". " +
                        rs.getString("nombre") + " " +
                        "(" + rs.getString("ciudad") + " - " +
                        rs.getString("pais") + ") " + "                                                    "
                        + rs.getString("subvencion");
                listModel.addElement(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        new Panel2();
    }
}
