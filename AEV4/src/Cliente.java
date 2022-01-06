import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	/*
	 * Arrancamos la conexión de l cliente, fijamos puerto y host
	 * Establecemos conexión y enviamos el socket
	 * Llama a los metodos de la contraseña
	 * parseamos a contraseña
	 * entrada: args
	 * salida: 0
	 */
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {		
		String lhost = "localhost";
		int puerto = 1234;
		
		System.out.println("CLIENTE >>>> Arranca cliente");
		Socket sckCliente = new Socket(lhost, puerto); //socket cliente
		ObjectInputStream InObjeto = new ObjectInputStream(sckCliente.getInputStream()); // entrada obj
		Contrasenya pass = (Contrasenya) InObjeto.readObject(); // Convertimos en contraseña
		
		System.out.println("CLIENTE >>>> Recibe de SERVIDOR objeto Contrasenya con atributos: contraseÃ±a plana: "+pass.getPassNormal() +" --- contraseÃ±a encriptada: "+pass.getPassEncriptado()+" --- tipo de encriptación: "+pass.getTipoEncriptacion());
		
		Scanner sc = new Scanner(System.in);
		pass.pedirContrasenyaNormal(sc);
		pass.pedirTipoEncriptacion(sc);
		sc.close();
		
		ObjectOutputStream OutObjeto = new ObjectOutputStream(sckCliente.getOutputStream()); //salida obj
		OutObjeto.writeObject(pass); // enviar obj
		
		System.out.println("CLIENTE >>>> Envía al SERVIDOR la contrasenya con contraseña: "+ pass.getPassNormal() +" --- contraseña encriptada: "+pass.getPassEncriptado()+" --- tipo: "+ pass.getTipoEncriptacion());
		
		System.out.println("CLIENTE >>>> Recibe al SERVIDOR la contrasenya con contraseña: "+ pass.getPassNormal() +" --- contraseña encriptada: "+ pass.getPassEncriptado()+" --- tipo: "+ pass.getTipoEncriptacion());
		// cerramos todo
		InObjeto.close();
		OutObjeto.close();
		sckCliente.close();	
	}

}
