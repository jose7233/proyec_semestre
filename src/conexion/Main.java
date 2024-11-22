package conexion;

import Inicio.*;

public class Main {
    public static void main(String[] args) {
        Conexion pbc = new Conexion();
        pbc.conectar();

        Panel1 p1 = new Panel1();
        p1.setVisible(true);
    }
}
