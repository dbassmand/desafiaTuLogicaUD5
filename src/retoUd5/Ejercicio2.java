package retoUd5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Ejercicio2 {

	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String archivo1="uno.txt";
		String archivo2="dos.txt";
		String carpeta="dirEjer2";
		String rutaAFichero1 =System.getProperty("user.dir")+"\\"+carpeta+"\\"+archivo1;
				
		System.out.println("El directorio de trabajo actual es "+System.getProperty("user.dir"));
		
		creaDirectorio(carpeta);
		creaArchivo(carpeta, archivo1);
		creaArchivo(carpeta, archivo2);
		escribirEnArchivo(rutaAFichero1);
		duplicarFicheros(archivo1, archivo2);
		leerDeFichero(archivo2);
		
			
	}
	
	static void creaDirectorio(String nombreCarpeta) {
		
		String rutaCarpetaAEscribir=System.getProperty("user.dir")+"\\"+nombreCarpeta;
		Path path = Paths.get(rutaCarpetaAEscribir);
		
		if(comprobarExiste(rutaCarpetaAEscribir)==true) {
			try {
				Files.createDirectory(path);
				System.out.println("Carpeta dirEjer2 creada correctamente");
			} catch (IOException e) {
				
				System.out.println("El directorio ya existe");
			}
		}
	}
	
	static void creaArchivo(String nombreCarpeta, String nombreArchivo) {
		if (comprobarExiste(nombreArchivo)==true) {
			try {
				File archivo = new File(nombreCarpeta, nombreArchivo);
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
	
	
	static boolean comprobarExiste(String nombreArchivo) {
		
		String rutaFicheroAEscribir = System.getProperty("user.dir") + "\\" + nombreArchivo;
		File ef = new File(rutaFicheroAEscribir);
		
		if(ef.exists()) {
			System.out.println("El fichero existe");
			return false;
		}else {
						
		}	return true;
				
	}
	
	static void escribirEnArchivo(String rutaArchivo) {
		
		try (Scanner sc = new Scanner(System.in)){
			
			BufferedWriter bf = new BufferedWriter(new FileWriter(rutaArchivo));
			
			System.out.println("Introduce nombres para guardar en el fichero uno.txt");
			System.out.println("Pulsa guion medio '-' para terminar.");
			
			String entrada;
			while(!(entrada = sc.nextLine()).equals("-")) {
				bf.write(entrada);
				bf.newLine();
			}
			System.out.println("Escritura completada");
			bf.close();
		} catch (Exception e) {
			
			System.out.println("Ocurrió un error al escribir en el fichero");
			e.printStackTrace();
		}	
		
		
	}
	
	static boolean duplicarFicheros(String archivoOrigen, String archivoDestino) {
		
		FileReader in=null;
		FileWriter out=null;
		
		String rutaFicheroOrigen = System.getProperty("user.dir") + "\\dirEjer2\\" + archivoOrigen ;
		String rutaFicheroDestino = System.getProperty("user.dir") + "\\dirEjer2\\" + archivoDestino ;
		System.out.println("\n\nSe copia el contenido del archivo "+archivoOrigen+" a "+archivoDestino);
		
		try {
			
			in = new FileReader(rutaFicheroOrigen);
			out = new FileWriter(rutaFicheroDestino);
			
			int c;
			
			while ((c=in.read())!=-1) {
				out.write(c);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			System.out.println("No se encuentra el archivo de origen o destino");
			
		}
	
		return true;
		
	}
	static boolean leerDeFichero(String nombreArchivo) {
		
		String rutaFicheroAEscribir = System.getProperty("user.dir") + "\\dirEjer2\\" + nombreArchivo ;
		
		try (BufferedReader br = new BufferedReader(new FileReader(rutaFicheroAEscribir))){
			int caracter;
			System.out.println("\nEl contenido del fichero "+nombreArchivo+".txt es:");
			while((caracter=br.read())!=-1) {
				System.out.print((char)caracter);
			}
			System.out.println("\nLectura del archivo "+ nombreArchivo+".txt completada");
			return true;
		}catch (IOException e) {
			
			System.out.println("\nError al leer el archivo: "+e.getMessage());
			return false;
		}
	
	}
	
}
