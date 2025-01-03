package factura;
import ultimo.Final1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

import static main.Conexion.conectar;

public class Factura extends JFrame {
    private JList<String> list1;
    private JPanel panel1;
    private JButton guardarButton;
    private DefaultListModel<String> listModel;
    private int nuevoId;
    private String nombre;
    private String pasaporte;
    private String nacionalidad;
    Fondopanel fondo = new Fondopanel();

    public Factura() {
        listModel = new DefaultListModel<>();
        list1.setModel(listModel);

        leerdato();
        leerdato2();
        leerdato3();
        leerdato5();
        leerdato4();


        this.setContentPane(fondo);
        fondo.add(panel1);
        fondo.setLayout(null);
        panel1.setOpaque(false);
        panel1.setBounds(40, 70, 300, 300);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Final1().setVisible(true);
                dispose();
            }
        });
    }

    public void leerdato() {
        String query = "SELECT idPasajero, nombre, pasaporte, nacionalidad FROM pasajeros ORDER BY idPasajero DESC LIMIT 1";

        try (Connection con = conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                int idPasajero = rs.getInt("idPasajero");
                nombre = rs.getString("nombre");
                pasaporte = rs.getString("pasaporte");
                nacionalidad = rs.getString("nacionalidad");

                listModel.addElement("Nombre: " + nombre);
                listModel.addElement("Pasaporte: " + pasaporte);
                listModel.addElement("Nacionalidad: " + nacionalidad);
            } else {
                listModel.addElement("No hay pasajeros en la tabla.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void leerdato2() {
        String nombreAeropuerto = Datos.getNombre();

        if (nombreAeropuerto != null) {
            listModel.addElement("Aeropuerto: " + nombreAeropuerto);
        } else {
            listModel.addElement("Error.");
        }
    }
    public void leerdato5() {
        String sudvencion = Datos.getSubvencion();

        if (sudvencion != null) {
            listModel.addElement("sudvencion: " + sudvencion);
        } else {
            listModel.addElement("no tiene sudvencion");
        }
    }

    public void leerdato3() {
        String nombreCompania = Datos2.getNombre();

        if (nombreCompania != null) {
            listModel.addElement("Compañía: " + nombreCompania);
        } else {
            listModel.addElement("Error.");
        }
    }


    public void leerdato4() {
        String query = "SELECT idFactura FROM Factura ORDER BY idFactura DESC LIMIT 1";

        try (Connection con = conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                int suma = rs.getInt("idFactura");
                nuevoId = suma + 1;
            } else {
                nuevoId = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        new Factura();
    }
}
