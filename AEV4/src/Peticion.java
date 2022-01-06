import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Peticion implements Runnable{
	Socket sckCliente; // socket del cliente
	
	public Peticion(Socket sckCliente) { //constructor en entrada de socket asignada a nuestro socket
		this.sckCliente = sckCliente;
	}
	@Override
	public void run() {		
		try {
			/*Hacemos que el servidor pueda enviar objetos al cliente
			 *Ponemos que en el canal de salida pueda enviar objetos mediante cliente-server 
			 * iniciamos la contraseña nulo
			 */
			ObjectOutputStream OutObjeto = new ObjectOutputStream(sckCliente.getOutputStream());
			Contrasenya pass = new Contrasenya(null, null);
			OutObjeto.writeObject(pass); //envia un objeto
			
			System.err.println("SERVIDOR >>>> Envio al CLIENTE la Contrasenya: contraseña normal: "+pass.getPassNormal() +" --- contraseña encriptada: "+pass.getPassEncriptado()+" --- tipo de encriptado: "+pass.getTipoEncriptacion());
			/*
			 * Recibimos el objeto enviado desde el cliente y lo usamos para entrada datos
			 * Recibimos el objeto desde cliente y lo pasamos a la clase contraseña
			 */
			ObjectInputStream inObjeto = new ObjectInputStream(sckCliente.getInputStream());			
			pass = (Contrasenya) inObjeto.readObject(); //Leemos el objeto
			
			System.err.println("SERVIDOR >> Recibe de CLIENTE la Contrasenya: contraseña normal: "+pass.getPassNormal() +" --- contraseña encriptada: "+ pass.getPassEncriptado()+" --- tipo de encriptado: "+pass.getTipoEncriptacion());
			
			pass.elegirEncriptado(); //Encriptamos bien la contraseña
			OutObjeto.writeObject(pass); //Enviamos el objeto
			
			System.err.println("SERVIDOR >>>> Envio al CLIENTE la Contrasenya: contraseña normal: "+pass.getPassNormal() +" --- contraseña encriptada: "+pass.getPassEncriptado()+" --- tipo de encriptado: "+pass.getTipoEncriptacion());
			// Cerramos todo
			OutObjeto.close();
			inObjeto.close();
			sckCliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}