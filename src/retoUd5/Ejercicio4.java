package retoUd5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Ejercicio4 {
	
	
	/*Se crea clase persona con 4 atributos, así como un constructor con
	 * parametros. No se implementan getters
	 * & setter al no ser necesario en este ejercicio.
	 */
	
	public static class Persona implements Serializable{
		private static final long serialVersionUID = 1L; // Identificador único de versión

		public String nombre;
		public int edad;
		public double altura;
		public double peso;
		
		public Persona (String nombre, int edad, double altura, double peso) {
			
			this.nombre=nombre;
			this.edad=edad;
			this.altura=altura;
			this.peso=peso;
			
		}
	}
	
	public static void main(String[] args) {
		
		 
		String archivo= "persona.dat";
				 
		 Persona persona1 = new Persona("Jose", 27, 1.76, 74.2);
		 Persona persona2 = new Persona("Laura", 56, 1.66, 57.1);
		 Persona persona3 = new Persona("Felipe", 65, 1.84, 92.6);
		
		 creaArchivo(archivo);
		 
		 guardarObjeto(persona1, archivo);
		 guardarObjeto(persona2, archivo);
		 guardarObjeto(persona3, archivo);
		 System.out.println("");
		 leerObjeto(persona1, archivo);
		 leerObjeto(persona2, archivo);
		 leerObjeto(persona3, archivo);

	}

	static void creaArchivo(String nombreArchivo) {
		File archivo = new File(nombreArchivo);
		if (!archivo.exists()) {
			try {
					if (archivo.createNewFile()) {
	                System.out.println("Archivo creado: " + archivo.getAbsolutePath());
	            } 
			}catch (IOException e) {
				
				System.out.println("Error al crear el archivo '"+nombreArchivo);
				e.printStackTrace();
			}
		}
	}
	
		
	static void guardarObjeto(Persona persona, String nombreArchivo) {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))){
			oos.writeObject(persona);
			System.out.println("Datos de objeto "+persona.nombre+" guardado en archivo "+nombreArchivo);
		} catch (IOException e) {
			System.out.println("Error al guardar datos en el archivo '"+nombreArchivo+"'");
			e.printStackTrace();
		}
	}	
	
	/*el metodo lee el archivo dado a traves del metodo readObjet()
	 * guardando los datos en el objeto persona de la clase Persona
	 */
	static void leerObjeto (Persona persona, String nombreArchivo) {
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))){
			persona = (Persona) ois.readObject();
			System.out.println("Datos de objeto "+persona.nombre+" leidos del archivo "+nombreArchivo);
		} catch (FileNotFoundException e) {
			System.out.println("El archivo " + nombreArchivo + " no se encontró.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida al leer el archivo " + nombreArchivo);
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
            System.out.println("No se pudo encontrar la clase Persona.");
            e.printStackTrace();
		
		}
	}
	
}
