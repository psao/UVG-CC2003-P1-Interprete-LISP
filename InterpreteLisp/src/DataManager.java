
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 *
 * @author pablo
 * @version 01/03/2019
 */
public class DataManager {
    
    String PATH_FILE = "";
    
    
    
    public DataManager(){
        
    }
    
    /***
     * Constructor utilizado para setear el path del file
     * @param path path de la ruta del archivo
     */
    public void setPathFile(String path){
        PATH_FILE = path;
    }
    
    /***
     * Metodo para la obtención del contenido de un archivo
     * @param path ruta fisica del archivo
     * @return string con los datos del archivo
     */
    public String getDataFile(){
        BufferedReader reader;
        String linea,datos = "";
        
        try{
            reader = new BufferedReader(new FileReader(PATH_FILE));
                
            while((linea = reader.readLine()) != null){
                //concatenamos con un tabular la lectura de la linea,
                //el tabular se eliminara al separar las expresiones.
                datos += linea + "\n";
            }
            
            // Cerramos la conexion
            reader.close();
            
        }
        //Tomaremos todo tipo de error en la ejecución del bloque de codigo dentro del catch
        catch(Exception e){
            e.printStackTrace();
        }
        
        return datos;
    }
    
    /***
     * Metodo para verificar la existencia del archivo ingresado
     * @return true si el archivo existe, false si el archivo no existe
     */
    public boolean getExists(){
        return (new File(PATH_FILE)).exists();
    }
    
    
    /**
     * Retorna un arreglo con cada caracter del string enviado
     * @param datos String para separar en caracteres
     * @return array de caracteres
     */
    public char[] getCaracterDataFile(String datos){
        String tempData = getDataFile();
        return tempData.toCharArray();
    }
    
    /**
     * Retorna ArrayList con los tokens creados
     * @param delimitador delimitador para descomposición de tokens
     * @return Array con los tokens crados, según el delimitador
     */
    public List getTokens(String delimitador){
        
        return Collections.list(new StringTokenizer(getDataFile().replace("(", " ( ").replace(")", " ) ") , delimitador)).stream()
        .map(token -> (String) token)
        .collect(Collectors.toList());
        
    }
    
    
    public boolean contieneChar(String datos, char caracter) {
        //evaluamos si la cadena no tiene caracteres
        if (datos.length() == 0){
            //Retornamos falso si esta no contiene caracteres
            return false;
        }
        else{
            //Evaluamos si la cadena contiene el caracter buscado, de lo contrario se hace 
            //recursión para seguir buscando en toda la cadena.
            return datos.charAt(0) == caracter || contieneChar(datos.substring(1), caracter);
        }
    }
    
}