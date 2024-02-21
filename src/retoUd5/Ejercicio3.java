package retoUd5;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {

	public static void main(String[] args) {
		
		String archivo= "tres.dat";
		
		creaArchivo(archivo);
		escribirEnArchivo(archivo);
		

	}


	static void creaArchivo(String nombreArchivo) {
		if (comprobarExiste(nombreArchivo)==true) {
			try {
				File archivo = new File(nombreArchivo);
				if (archivo.createNewFile()) {
	                System.out.println("Archivo creado: " + archivo.getAbsolutePath());
	                
	            } else {
	                System.out.println("Se sobrescribe, el archivo ya existe: " + archivo.getAbsolutePath());
	            }
			}catch (IOException e) {
				
				System.out.println("Error al crear el archivo '"+nombreArchivo);
				e.printStackTrace();
			}
		}
	}
	
	static void escribirEnArchivo(String Archivo) {
		
		try (Scanner sc = new Scanner(System.in)){
			
			FileOutputStream fos = new FileOutputStream(Archivo);
			
			System.out.println("Introduce numeros enteros positivos para guardar en el fichero "+Archivo );
			System.out.println("Escribe un entero negativo para terminar.");
			
			int entrada;
			while((entrada = sc.nextInt())>0) {
				fos.write(entrada);
			}
			System.out.println("Escritura completada");
			fos.close();
		} catch (Exception e) {
			
			System.out.println("Ocurrió un error al escribir en el fichero");
			e.printStackTrace();
		}	
	}	
	
	static boolean comprobarExiste(String nombreArchivo) {
		
		String rutaFicheroAEscribir = System.getProperty("user.dir") + "\\" + nombreArchivo;
		File ef = new File(rutaFicheroAEscribir);
		
		if(ef.exists()) {
			System.out.println("El fichero existe");
			return false;
		}else {
						
		}	return true;
	}
}
