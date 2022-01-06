import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	/*
	 * Arrancamos la conexi�n de l cliente, fijamos puerto y host
	 * Establecemos conexi�n y enviamos el socket
	 * Llama a los metodos de la contrase�a
	 * parseamos a contrase�a
	 * entrada: args
	 * salida: 0
	 */
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {		
		String lhost = "localhost";
		int puerto = 1234;
		
		System.out.println("CLIENTE >>>> Arranca cliente");
		Socket sckCliente = new Socket(lhost, puerto); //socket cliente
		ObjectInputStream InObjeto = new ObjectInputStream(sckCliente.getInputStream()); // entrada obj
		Contrasenya pass = (Contrasenya) InObjeto.readObject(); // Convertimos en contrase�a
		
		System.out.println("CLIENTE >>>> Recibe de SERVIDOR objeto Contrasenya con atributos: contraseña plana: "+pass.getPassNormal() +" --- contraseña encriptada: "+pass.getPassEncriptado()+" --- tipo de encriptaci�n: "+pass.getTipoEncriptacion());
		
		Scanner sc = new Scanner(System.in);
		pass.pedirContrasenyaNormal(sc);
		pass.pedirTipoEncriptacion(sc);
		sc.close();
		
		ObjectOutputStream OutObjeto = new ObjectOutputStream(sckCliente.getOutputStream()); //salida obj
		OutObjeto.writeObject(pass); // enviar obj
		
		System.out.println("CLIENTE >>>> Env�a al SERVIDOR la contrasenya con contrase�a: "+ pass.getPassNormal() +" --- contrase�a encriptada: "+pass.getPassEncriptado()+" --- tipo: "+ pass.getTipoEncriptacion());
		
		System.out.println("CLIENTE >>>> Recibe al SERVIDOR la contrasenya con contrase�a: "+ pass.getPassNormal() +" --- contrase�a encriptada: "+ pass.getPassEncriptado()+" --- tipo: "+ pass.getTipoEncriptacion());
		// cerramos todo
		InObjeto.close();
		OutObjeto.close();
		sckCliente.close();	
	}

}
