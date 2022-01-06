import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMultiHilo {
	/* Arrancamos el servidor y establecemos un puerto de escucha permanente esperando al cliente
	 * no utilizamos la args como parametros de entradas
	 * no hay parametros de salida
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		System.err.println("SERVIDOR >>>> Arrancamos servidor, esperando petición");
		ServerSocket sckEscucha = null;
		try {
			sckEscucha = new ServerSocket(1234); //puerto de escucha
		} catch (IOException e) {
			System.err.println("SERVIDOR >>>> Error al conectar "); //comprobamos el error
			e.printStackTrace();
		}
			
		/*esperamos y establecemos la conexión cliente-servidor
		 */
		
		while (true) {
			Socket con = sckEscucha.accept();
			System.err.println("SERVIDOR >>>> Conexión recibida --> Lanza hilo Petición");
			Peticion pet = new Peticion(con);
			Thread hilo = new Thread(pet);
			hilo.start(); // lanzamos el hilo
		}
	}

}
