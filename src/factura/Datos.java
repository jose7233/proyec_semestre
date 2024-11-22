package factura;

public class Datos {
    private static String nombre;
    private static String subvencion;

    public static void setNombre(String nombre) {
        Datos.nombre = nombre;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setSubvencion(String subvencion) {
        Datos.subvencion = subvencion;
    }

    public static String getSubvencion() {
        return subvencion;
    }
}

