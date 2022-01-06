import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Contrasenya implements Serializable {

	private String passNormal;
	private String passEncriptado;
	private String tipoEncriptacion;

	/*
	 * Constructor para inicializar las contraseñas normales y encriptados
	 * parametros entrada: strings de las contraseñas: normales y encriptadas
	 * salida: contraseñas: normales encriptadas
	 */
	public Contrasenya(String passNormal, String passEncriptado) {
		this.passNormal = passNormal;
		this.passEncriptado = passEncriptado;
	}
	
	/*
	 * GETTERS y SETTERS
	 */

	public String getPassNormal() {
		return passNormal;
	}

	public String getPassEncriptado() {
		return passEncriptado;
	}

	public String getTipoEncriptacion() {
		return tipoEncriptacion;
	}

	public void setPwdPlano(String passNormal) {
		this.passNormal = passNormal;
	}

	public void setPwdEncriptado(String passEncriptado) {
		this.passEncriptado = passEncriptado;
	}

	public void setTipoEncriptacion(String tipoEncriptacion) {
		this.tipoEncriptacion = tipoEncriptacion;
	}
	
	/*
	 * Metodo para pedir la contraseña
	 * pedimos scanner y la asignamos
	 * entrada: scanner sc
	 * salida: contraseña asignada
	 */

	public void pedirContrasenyaNormal(Scanner sc) {
		System.out.print("\n\tContraseña: ");
		passNormal = sc.next();
	}
	
	/*
	 * Elegimos la encriptación mediante un menu y asignamos encriptación
	 * entrada: scanner sc
	 * salida: string para el tipo de encriptado
	 */

	public void pedirTipoEncriptacion(Scanner sc) {
		System.out.println(" ---------- Elige una encriptación ----------");
		System.out.println("A. Encriptado ASCII");
		System.out.println("B. Encriptado MD5");
		System.out.println("\n ---------- Elige una encriptación ----------");
		String tipo = sc.next();

		switch (tipo.toUpperCase()) {
		case "A":
			tipoEncriptacion = "ASCII";
			break;
		case "B":
			tipoEncriptacion = "MD5";
			break;
		default:
			System.out
					.println("\tEl valor introducido no es vÃ¡lido. Se aplicarÃ¡ la encriptaciÃ³n por defecto (MD5).");
			tipoEncriptacion = "MD5";
			break;
		}
		System.out.println("\n");
	}

	/*
	 * Elegimos el encriptado y lo aplicamos
	 * entrada: 0
	 * salida: aplicamos la encriptación mediante metodos
	 */
	
	public void elegirEncriptado() {

		if (tipoEncriptacion.equals("ASCII")) {
			encriptadoASCII();
		} else if (tipoEncriptacion.equals("MD5")) {
			encriptadoMD5();
		} else {
			System.out.println("No ha sido posible encriptar");
		}

	}
	
	/*
	 * Encriptado ASCII
	 * crea una nueva contraseña con la antigua y la generamos nueva
	 * entrada: 0
	 * salida: contraseña generada por encriptado ASCII
	 */

	private void encriptadoASCII() {

		String[] Passnew = new String[passNormal.length()];

		for (int i = 0; i < passNormal.length(); i++) {
			char caracter = passNormal.charAt(i);
			int valorAscii = caracter;
			int Ascii = valorAscii + 1;

			if (Ascii < 33 || Ascii > 126) {
				Ascii = 42;
			}

			char car = (char) Ascii;

			Passnew[i] = String.valueOf(car);
		}
		passEncriptado = String.join("", Passnew);
	}
	
	/*
	 * Encriptación MD5
	 * creamos una nueva contraseña partiendo de la vieja y generamos una nueva 
	 * entrada: 0
	 * salida: contraseña generada por encriptado MD5
	 */

	private void encriptadoMD5() {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			byte[] messageDigest = md.digest(passNormal.getBytes());
			BigInteger big = new BigInteger(1, messageDigest);
			String cadena = big.toString(16);

			while (cadena.length() < 32) {
				cadena = "0" + cadena;
			}

			passEncriptado = cadena;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
