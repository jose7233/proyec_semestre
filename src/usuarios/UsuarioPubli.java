package usuarios;

import Inicio.Panel1;
import Inicio.Panel3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static conexion.Conexion.conectar;

public class UsuarioPubli extends JFrame {
    private JPanel panel2;
    private JList<String> list1;
    private JButton aceptarButton;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField2;
    private DefaultListModel<String> listModel;
    private int nuevoId;


    Fondopanel fondo = new Fondopanel();

    public UsuarioPubli() {
        listModel = new DefaultListModel<>();
        list1.setModel(listModel);

        leerDato();
        setJMenuBar(createMenuBar());
        this.setContentPane(fondo);
        fondo.add(panel2);

        fondo.setLayout(null);
        panel2.setOpaque(false);
        list1.setOpaque(false);
        panel2.setBounds(40, 20, 300, 300);

        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDato(nuevoId);
                new Panel3().setVisible(true);
                dispose();
            }
        });
    }
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem salirItem = new JMenuItem("Salir");
        salirItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JMenuItem regresarItem = new JMenuItem("Regresar");
        regresarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Panel1().setVisible(true);
                dispose();
            }
        });

        menu.add(salirItem);
        menu.add(regresarItem);
        menuBar.add(menu);

        return menuBar;
    }
    public void leerDato() {
        String query = "SELECT idPasajero FROM pasajeros ORDER BY idPasajero DESC LIMIT 1";

        try (Connection con = conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                int suma = rs.getInt("idPasajero");
                nuevoId = suma + 1;
                listModel.addElement("id: " + nuevoId);
            } else {
                nuevoId = 1;
                listModel.addElement("id: " + nuevoId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarDato(int nuevoId) {
        int id = nuevoId;
        String nombre = textField1.getText();
        String pasaporte = textField2.getText();
        String nacionalidad = textField3.getText();

        String query = "INSERT INTO pasajeros (idPasajero, nombre, pasaporte, nacionalidad) VALUES (?, ?, ?, ?)";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id);
            pst.setString(2, nombre);
            pst.setString(3, pasaporte);
            pst.setString(4, nacionalidad);
            pst.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + e.getMessage());
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
        new UsuarioPubli();
}
}
