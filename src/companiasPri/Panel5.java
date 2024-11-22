package companiasPri;

import factura.Datos2;
import vuelosPri.Panel11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import static main.Conexion.conectar;

public class Panel5 extends JFrame {
    private JPanel panel5;
    private int nuevoId;
    private JList<String> list1;
    private DefaultListModel<String> listModel;
    private int idAeropuerto;
    Fondopanel fondo = new Fondopanel();

    public Panel5(int idAeropuerto) {
        this.idAeropuerto = idAeropuerto;
        listModel = new DefaultListModel<>();
        list1.setModel(listModel);
        leerDato();
        leerDato3();
        this.setContentPane(fondo);
        fondo.add(panel5);

        fondo.setLayout(null);
        panel5.setOpaque(false);
        list1.setOpaque(false);
        panel5.setBounds(40, 18, 300, 300);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int seleccion = list1.getSelectedIndex();
                String selectedValue = listModel.getElementAt(seleccion);
                int idCompania = Integer.parseInt(selectedValue.split("\\.")[0].trim());

                String nombre = selectedValue.split("\\.")[1].trim();
                Datos2.setNombre(nombre);

                guardarDato(nuevoId, idCompania);
                new Panel11(idCompania).setVisible(true);
                dispose();
            }
        });
    }

    public void leerDato3() {
        String query = "SELECT id FROM aeropuertos_companias ORDER BY id DESC LIMIT 1";

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

    public void leerDato() {
        String query = "SELECT idCompania, nombre FROM companias ORDER BY idCompania LIMIT 2 OFFSET 2";
        try {
            Connection con = conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String row = rs.getString("idCompania") + ". compañía " + rs.getString("nombre");
                listModel.addElement(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarDato(int nuevoId, int idCompania) {
        String query = "INSERT INTO aeropuertos_companias (id, idAeropuerto, idCompania) VALUES (?,?,?)";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, nuevoId);
            pst.setInt(2, idAeropuerto);
            pst.setInt(3, idCompania);
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
            imagen = new ImageIcon(getClass().getResource("/imagenes/img_2.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
}
