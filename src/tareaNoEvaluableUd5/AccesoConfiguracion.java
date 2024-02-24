

/*Tarea no evaluable UD5 - Jose Daniel Romero Perez
 * Con el siguiente codigo se realizan operaciones de lectura y modificacion del contenido 
 * del archivo configuracion.properties localizado en el mismo package que la clase main.
 */

package tareaNoEvaluableUd5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

public class AccesoConfiguracion {
	
	/*Se instancia objeto de la clase Properties como estático para que pertenezca a 
	 * la clase AccesoConfiguracion y sea accesible por el resto de métodos.
	 * Se instancia un objeto de la clase File también de tipo static que apunta al archivo objeto de la actividad
	 */
	
	static Properties properties = new Properties();
	static File myfile = new File(System.getProperty("user.dir") +"\\src\\tareaNoEvaluableUd5\\configuracion.properties");
	
	/*
	 * He intentado que el metodo main esté lo más limpio posible, para ello he creado un metodo llamado
	 * primeraParte() en el que se incluyen las primeras operaciones solicitadas en el enunciado.
	 */
	
	public static void main(String[] args) {
		
		primeraParte();
		Conexion nuevaConfiguracion = solicitarNuevaConf();
		insertarNuevaConfiguracion(nuevaConfiguracion,properties);
		escribirFichero(myfile, properties);
		leerFichero(myfile);			
	}
	
	public static void primeraParte() {
		try {
			
			properties.load(new FileInputStream(myfile));
			 String jdbc = properties.getProperty("jdbc");
	         String usuario = properties.getProperty("usuario");
	         String clave = properties.getProperty("clave");
	         
	         System.out.println("**Visualizando System.out y get()**");
	         System.out.println(jdbc+"\n"+usuario+"\n"+clave);
	         
	         System.out.println("\n**Visualizando con metodo list()**");
	         properties.list(System.out);
	         
	         Conexion conexionBD = new Conexion(jdbc, usuario, clave);
	         System.out.println("\n**Visualizando con metodo toString**");
	         System.out.println(conexionBD.toString());
			
	         
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
	/*
	 * Se crea metodo de tipo void leerFichero() que recibe como parametro el objeto File myfile. Se hace uso del metodo
	 * FileReader envuelto en el metodo BufferedREader instanciando un objeto bReader. Estos datos se leen y se almacenan
	 * en el objeto properties a traves del metodo load(), se muestran en consola traves del metodo list()
	 * 
	 */
	
	public static void leerFichero(File myfile) {
		
			System.out.println("\n**Leyendo del fichero de configuracion**");
		
			try (BufferedReader bReader = new BufferedReader(new FileReader(myfile))){
				properties.load(bReader);
				properties.list(System.out);															
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/*
	 * Se creal metodo de tipo void escribirFichero() que recibe como parametros un objeto file y un objeto propiedad, 
	 * se muestra en pantalla el valor de las claves solicitadas a través del metodo getProperty()
	 * Se crea un Stream instanciando un objeto de la clase FileOutputStream apuntando al objeto de tipo File que se pasa
	 * por parametro. Se hace uso del metodo store para sobre escibir los nuevos valores establecidos en el objeto properties.
	 *   
	 */
	
	public static void escribirFichero (File myFile, Properties properties) {
		System.out.println("**Nueva configuracion introducida por el usuario**");
		                
         System.out.println("Url-> "+properties.getProperty("jdbc"));
         System.out.println("Usuario-> "+properties.getProperty("usuario"));
         System.out.println("Clave-> "+properties.getProperty("clave"));
         
         try (FileOutputStream fos = new FileOutputStream(myFile)){
        	 
        	 properties.store(fos,"Nueva configuración");
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Se crea metodo de tipo Conexion, el metodo devuelve una instancia de la clase Conexion.
	 * Se solicita al usuario por consola que se introduzcan 3 nuevos datos, por lo que hay un dato del 
	 * arhivo configuracion.properties que no será modificado. 
	 * Se instancia un objeto de la clase Scanner.
	 */
	
	public static Conexion solicitarNuevaConf() {
		
		Scanner sc = new Scanner(System.in);	
		String jdbc;
		String usuario;
		String clave;
		System.out.println("\n**Introduzca nueva configuracion**");
		System.out.print("Url de jdbc: ");
		jdbc= sc.nextLine();
		System.out.print("Usuario: ");
		usuario= sc.nextLine();
		System.out.print("Clave: ");
		clave= sc.nextLine();
		sc.close();
		return new Conexion(jdbc, usuario, clave);
	}
	
	/*
	 * Se crea metodo para actualizar los valores del objeto properties. El metodo recibe como 
	 * parametro un objeto de la clase conexion y un objeto de la clase properties.
	 * Toma los valores a modificar a traves de los getters de la clase conexion
	 * y cambia los valores del objeto properties a traves del metodo setProperty(). 
	 */
	
	public static void insertarNuevaConfiguracion(Conexion conf, Properties properties) {
				              
        properties.setProperty("jdbc", conf.getJdbc());
        properties.setProperty("usuario", conf.getUsuario());
        properties.setProperty("clave", conf.getClave());
		
	}
	
	
}	
	

