import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {
    private String archivo;
    private String separador;
    private String directorioSalida;
    public Configuracion(String[] args) {

        Properties propiedades = cargarPropiedades();
        archivo = propiedades.getProperty("archivo","datos/datos.csv");
        separador = propiedades.getProperty("separador",",");
        directorioSalida = propiedades.getProperty("directorioSalida","salida");
        directorioSalida = System.getenv().getOrDefault("ANALIZADOR_OUTPUT", directorioSalida);
        
        if (args.length >= 1) {
            archivo = args[0];
        }
        if (args.length >= 2) {
            separador = args[1];
        }

    }
    
    private Properties cargarPropiedades() {    
        Properties propiedades = new Properties();
        try (FileInputStream entrada = new FileInputStream("config/application.properties")) {
            propiedades.load(entrada);
        } catch (IOException e) {
            System.out.println("Archivo de configuración no disponible.");
        }
        return propiedades;
    }
    
    public String getArchivo() {
        return archivo;
    }
    
    public String getSeparador() {
        return separador;
    }
    
    public String getDirectorioSalida() {
        return directorioSalida;
    }
    
}