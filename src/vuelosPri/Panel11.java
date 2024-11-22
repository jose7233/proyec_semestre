package vuelosPri;

import ultimo.seguir;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import static conexion.Conexion.conectar;

public class Panel11 extends JFrame {
    private JPanel panel11;
    private JList<String> list1;
    private DefaultListModel<String> listModel;
    private int nuevoId;
    private int idCompania;
    private int idPasajero;
    Fondopanel fondo = new Fondopanel();

    public Panel11(int idCompania) {
        this.idCompania = idCompania;
        listModel = new DefaultListModel<>();
        list1.setModel(listModel);
        leerDato();
        leerDato3();
        leerDato2();
        this.setContentPane(fondo);
        fondo.add(panel11);

        fondo.setLayout(null);
        panel11.setOpaque(false);
        list1.setOpaque(false);
        panel11.setBounds(40, 18, 300, 300);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int seleccion = list1.getSelectedIndex();
                String selectedValue = listModel.getElementAt(seleccion);
                int idVuelo = Integer.parseInt(selectedValue.split("\\.")[0].trim());
                guardarDato(nuevoId, idCompania, idVuelo);
                guardarDato2(nuevoId, idPasajero, idVuelo);
                new seguir().setVisible(true);
                dispose();
            }
        });
    }

    public void leerDato3() {
        String query = "SELECT id FROM compania_vuelos ORDER BY id DESC LIMIT 1";

        try (Connection con = conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                int suma = rs.getInt("id");
                nuevoId = suma + 1;
            } else {
                nuevoId = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void leerDato2() {
        String query = "SELECT idPasajero FROM pasajeros ORDER BY idPasajero DESC LIMIT 1";

        try (Connection con = conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                idPasajero = rs.getInt("idPasajero");

            } else {

                idPasajero = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void leerDato() {
        String query = "SELECT idVuelo, ciudadOrigen, ciudadDestino, precio FROM vuelos ORDER BY idVuelo LIMIT 2 OFFSET 2";
        try {
            Connection con = conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String row = rs.getString("idVuelo") + ". " +
                        rs.getString("ciudadOrigen") + " - " +
                        rs.getString("ciudadDestino") + "  precio: " +
                        rs.getString("precio");
                listModel.addElement(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarDato(int nuevoId, int idCompania, int idVuelo) {
        String query = "INSERT INTO compania_vuelos (id, idCompania, idVuelo) VALUES (?,?,?)";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, nuevoId);
            pst.setInt(2, idCompania);
            pst.setInt(3, idVuelo);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + e.getMessage());
        }
    }

    public void guardarDato2(int nuevoId, int idPasajero, int idVuelo) {
        String query = "INSERT INTO vuelos_pasajeros (id, idVuelo, idPasajero) VALUES (?,?,?)";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, nuevoId);
            pst.setInt(2, idVuelo);
            pst.setInt(3, idPasajero);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: ");
        }
    }

    class Fondopanel extends JPanel {
        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/img_2.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
public static void main(String[] args) {
        new Panel11(0);
}

}

