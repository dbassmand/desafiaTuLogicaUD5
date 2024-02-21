package retoUd5;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;



public class Ejercicio1 {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean bandera1 = true;
		boolean bandera2 = true;
		String archivo1, archivo2;
		String carpeta= "dirEjer1";
		
		System.out.println("Hola, vamos a crear 2 archivos.");
		
		//Bucle do-while para comprobar que el nombre del archivo tiene un minimo de 3 caracteres.
		do {
			System.out.println("\nIntroduce el nombre del archivo 1: ");
			 archivo1 = sc.nextLine();
			 //boolean existe = comprobarExiste(archivo1); //se crea variable booleana para el segund condicional
			if (archivo1.length()<3) {
				System.out.println("Nombre de archivo incorrecto, \ndebe tener al menos 3 caracteres.");
			}else if(comprobarExiste(archivo1)==true){
					comprobarExiste(archivo1);
					System.out.println("El nombre de fichero "+archivo1+ ".txt es valido");
					bandera1= false;                  //se pasa la bandera a falso para parar el bucle while
					crearArchivo(archivo1);
			}
		}
			while (bandera1);
		
		//Se repite el mismo procedimiento anterior, quizás haber hecho un bucle for para repetirlo 2 veces habría sido más elegante
		
		do {
			System.out.println("\nIntroduce el nombre del archivo 2: ");
			archivo2 = sc.nextLine();
			boolean existe = comprobarExiste(archivo2);
			if (archivo2.length()<3) {
				System.out.println("Nombre de archivo incorrecto, \ndebe tener al menos 3 caracteres.");
			}else if (existe){
					comprobarExiste(archivo2);
					System.out.println("El nombre de fichero "+archivo2+ ".txt es valido");
					bandera2=false;
					crearArchivo(archivo2);
			}
		} 	while (bandera2);
		
		//Se ejecutan los métodos solicitados en el enunciado del ejercicio segun su orden.
		
		escribirEnFichero(archivo1);
		
		leerDeFichero(archivo1);
		
		dameAtributos(archivo1);
		
		duplicarFicheros(archivo1, archivo2);
		
		borrarFichero(archivo1);
		
		leerDeFichero(archivo2);
			
		creaDirectorio(carpeta);
				
		sc.close();
	}
	
	//Metodo para crear archivo utilizando una instancia de la clase FileWriter, 
	//esta clase obliga a tratar una excepcion controlada a traves de Try-catch
	
	static void crearArchivo(String nombreArchivo) {
		try {
			FileWriter archivo = new FileWriter(nombreArchivo+".txt");
			System.out.println("Se ha creado el archivo '"+nombreArchivo+".txt'");
			System.out.println("La ruta actual es "+ System.getProperty("user.dir"));
			archivo.close();
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("Error al crear el archivo '"+nombreArchivo+".txt'");
			e.printStackTrace();
		}
	}
	
	//Metodo para crear directorio, bastante similar a crearArchivo adaptado a la clase Files
	
	static void creaDirectorio(String nombreCarpeta) {
				
		String rutaCarpetaAEscribir=System.getProperty("user.dir")+"\\"+nombreCarpeta;
		Path path = Paths.get(rutaCarpetaAEscribir);
		
		if(comprobarExiste(rutaCarpetaAEscribir)==true) {
			try {
				Files.createDirectory(path);
				System.out.println("Carpeta dirEjer1 creada correctamente");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("El directorio ya existe");
			}
		}
		
			
	}
	
	//Metodo para escribir en el fichero indicado los numeros del 0 al 10
	//El metodo write solo admite caracteres tipo string por lo que hay que castear 
	//de int a string con el método valueOf()
	
	static void escribirEnFichero(String nombreArchivo) {
		
		String rutaFicheroAEscribir = System.getProperty("user.dir") + "\\" + nombreArchivo + ".txt";
		boolean existe=comprobarExiste(nombreArchivo);
		if (!existe) {
			System.out.println("\nSe comprueba que el archivo existe antes de la ecritura");
			try (FileWriter out = new FileWriter(rutaFicheroAEscribir)) {
                for (int i = 0; i < 11; i++) {
                    out.write(String.valueOf(i) + "\n"); // Convertir el entero a String y añadir un salto de línea
                }
                System.out.println("Se ha escrito en el archivo " + nombreArchivo + ".txt los números del 0 al 10.");
                out.close();
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo " + rutaFicheroAEscribir + ": " + e.getMessage());
            }
			
		}
	       
    }

	//Método leerDeFichero. Aqui se envuelve el metodo FileReader con un buffer de la clase BufferReader.
	//Esto es más comun en la lectura de lineas completas, pero el metodo FileReader se suele implementar así
	
	static boolean leerDeFichero(String nombreArchivo) {
		
		String rutaFicheroAEscribir = System.getProperty("user.dir") + "\\" + nombreArchivo + ".txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(rutaFicheroAEscribir))){
			int caracter;
			System.out.println("\nEl contenido del fichero "+nombreArchivo+".txt es:");
			while((caracter=br.read())!=-1) {
				System.out.print((char)caracter);
			}
			System.out.println("\nLectura del archivo "+ nombreArchivo+".txt completada");
			return true;
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("\nError al leer el archivo: "+e.getMessage());
			return false;
		}
		
	}
	
	//Método borrarFichero a través del método delete() de la clase File, el metodo se define como booleano
	//porque así lo indica el enunciado, pero podría ser un metodo void.
	
	static boolean borrarFichero(String nombreArchivo) {
		
		String rutaFicheroAEscribir = System.getProperty("user.dir") + "\\" + nombreArchivo + ".txt";
		
		File fl = new File(rutaFicheroAEscribir);
		System.out.println("\nSe ha borrado el fichero: "+rutaFicheroAEscribir);
		
		return fl.delete();
		
	}
	
	//Metodo para comprobar la posible preexistencia de archivos o carpetas
	static boolean comprobarExiste(String nombreArchivo) {
		
		String rutaFicheroAEscribir = System.getProperty("user.dir") + "\\" + nombreArchivo + ".txt";
		File ef = new File(rutaFicheroAEscribir);
		
		if(ef.exists()) {
			System.out.println("El fichero existe");
			return false;
		}else {
						
		}	return true;
				
	}

	//Metodo para copiar el contenido de un archivo a otro el cual recibe como parametros los nombres
	//de ambos archivos. Se crea un bucle while, aunque se podría haber utilizado un bucle for al saber la longitud
	//del archivo de origen. De esta forma es totalmente escalable.
	static boolean duplicarFicheros(String archivoOrigen, String archivoDestino) {
		
		FileReader in=null;
		FileWriter out=null;
		
		String rutaFicheroOrigen = System.getProperty("user.dir") + "\\" + archivoOrigen + ".txt";
		String rutaFicheroDestino = System.getProperty("user.dir") + "\\" + archivoDestino + ".txt";
		System.out.println("\n\nSe copia el contenido del archivo "+archivoOrigen+".txt a "+archivoDestino+".txt");
		
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
	//Metodo para leer los atributos de los archivos. En la parte para que se compruebe si tienen 
	static void dameAtributos(String nombreArchivo) {
		
		String rutaFicheroAEscribir = System.getProperty("user.dir") + "\\" + nombreArchivo + ".txt";
		File fl =new File(rutaFicheroAEscribir);
				
		System.out.println("--------------------------------------------------");
		System.out.println("\nEl "+nombreArchivo+" tiene los siguientes atributos:");
		System.out.println("\nNombre del archivo: "+ fl.getName());
		System.out.println("La ruta absoluta es "+ rutaFicheroAEscribir);
		System.out.println("El directorio padre es "+ System.getProperty("user.dir"));
		System.out.println("El tamaño del fichero es de " + fl.length()+" bytes.");
		if (fl.isFile()) {
		    System.out.println("El archivo es un fichero");
		} else if (fl.isDirectory()) {
		    System.out.println("El archivo es un directorio");
		} else {
		    System.out.println("No se pudo determinar el tipo de archivo");
		}
		if (fl.canRead()) {
			System.out.println("El archivo tiene permisos de lectura.");
		} else {
			System.out.println("El archivo no tiene permisos de lectura.");
		}
		if (fl.canWrite()) {
			System.out.println("El archivo tiene permisos de escritura.");
		} else {
			System.out.println("El archivo no tiene permisos de escritura.");
		}
		if (fl.canExecute()) {
			System.out.println("El archivo tiene permisos de ejecución.");
		} else {
			System.out.println("El archivo no tiene permisos de ejecución.");
		}
		if (fl.isHidden()) {
			System.out.println("El archivo esta oculto.");
		} else {
			System.out.println("El archivo no esta oculto");
		}
						
	}
	
}
