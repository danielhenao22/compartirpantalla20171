package co.edu.eam.ingesoft.distribuidos.cliente.controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Observable;
import javax.swing.JOptionPane;
import co.edu.eam.ingesoft.distribuidos.cliente.gui.VentanaPantalla;
import co.edu.eam.ingesoft.distribuidos.cliente.gui.VentanaVideoLlamada;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.dto.ListaUsuariosDTO;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.dto.LoginDTO;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.dto.RegistroDTO;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.dto.SolicitarCamaraDTO;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.dto.SolicitarConDTO;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.modelo.Usuario;

/**
 * clase para controlar la comm con el servidor
 * 
 * @author caferrer
 *
 */
public class Controlador extends Observable implements Runnable {

	private Socket con;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private HiloDestino hDestino;

	private List<Usuario> usuarios;
	/**
	 * mi usuario
	 */
	private Usuario usuario;
	private EntradaVideo entradaVideo;

	/**
	 * metodo para loguearse al servidor
	 * 
	 * @param user
	 * @param pass
	 */
	public boolean login(String user, String pass) {

		try {

			con = new Socket("localhost", 45000);
			salida = new ObjectOutputStream(con.getOutputStream());
			entrada = new ObjectInputStream(con.getInputStream());

			LoginDTO login = new LoginDTO();
			login.setPass(pass);
			login.setUsuario(user);
			enviarMsj(login);
			usuario = new Usuario(user, InetAddress.getLocalHost().getHostAddress());

			Object resp = entrada.readObject();
			if (resp instanceof ListaUsuariosDTO) {
				ListaUsuariosDTO lista = (ListaUsuariosDTO) resp;
				usuarios = lista.getUsuarios();
				setChanged();
				notifyObservers(lista);
				new Thread(this).start();
				return true;
			}

			if (resp instanceof String) {
				String str = (String) resp;
				if (str.equals("ERROR")) {
					return false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public void solicitarCompartir(Object obj) throws IOException {
		SolicitarConDTO solicitar = (SolicitarConDTO) obj;
		SolicitarConDTO solicitar2 = new SolicitarConDTO(this.usuario, solicitar.getDestino(), 1);
		enviarMsj(solicitar2);

	}
	
	public void solicitarCompartirVideollamada(Object obj) throws IOException {
		SolicitarCamaraDTO solicitar = (SolicitarCamaraDTO) obj;
		SolicitarCamaraDTO solicitar2 = new SolicitarCamaraDTO(this.usuario, solicitar.getDestino(), 1);
		enviarMsj(solicitar2);

	}

	/**
	 * metodo para loguearse al servidor
	 * 
	 * @param user
	 * @param pass
	 */
	public boolean registrar(String user, String pass) {

		try {

			con = new Socket("localhost", 45000);
			salida = new ObjectOutputStream(con.getOutputStream());
			entrada = new ObjectInputStream(con.getInputStream());

			RegistroDTO dto = new RegistroDTO();
			dto.setPass(pass);
			dto.setUsuario(user);
			enviarMsj(dto);

			Object resp = entrada.readObject();

			if (resp instanceof String) {
				String str = (String) resp;
				if (str.equals("ERROR")) {
					return false;
				}
				if (str.equals("OK")) {
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public void run() {
		while (true) {

			try {
				System.out.println("esperando mensaje.......................");
				Object obj = entrada.readObject();

				if (obj instanceof ListaUsuariosDTO) {
					System.out.println("lista recibida:");
					ListaUsuariosDTO lista = (ListaUsuariosDTO) obj;
					usuarios = lista.getUsuarios();
					System.out.println(usuario.getUsuario() + "::lista recibida:" + usuarios);
					setChanged();
					notifyObservers(lista);
				}

				if (obj instanceof SolicitarConDTO) {
					SolicitarConDTO solicitar = (SolicitarConDTO) obj;
					if (solicitar.getEstado() == 1) {
						int respuesta = JOptionPane.showConfirmDialog(null,
								"¿Desea aceptar la solicitud de compartir?");
						if (respuesta == 0) {
							hDestino = new HiloDestino();
							new Thread(hDestino).start();
							SolicitarConDTO solicitar2 = new SolicitarConDTO(solicitar.getOrigen(),
									solicitar.getDestino(), 2);

							enviarMsj(solicitar2);
							VentanaPantalla pantalla = new VentanaPantalla(hDestino);
							hDestino.addObserver(pantalla);
							pantalla.setVisible(true);
						}
					}

					if (solicitar.getEstado() == 2) {
						Socket con2 = new Socket("localhost", 45001);
						// HiloDestino hiloDestino = new HiloDestino(con2);
						JOptionPane.showMessageDialog(null, "Conexion aceptada");
						HiloOrigen hiloOrigen = new HiloOrigen(con2);
						new Thread(hiloOrigen).start();
					}

				}

				if (obj instanceof SolicitarCamaraDTO) {
					SolicitarCamaraDTO solicitar = (SolicitarCamaraDTO) obj;
					System.out.println("Camara Estado: " + solicitar.getEstado());
					if (solicitar.getEstado() == 1) {
						int respuesta = JOptionPane.showConfirmDialog(null,
								"¿Desea aceptar la solicitud de Videollamada?");
						if (respuesta == 0) {
							entradaVideo = new EntradaVideo();
							new Thread(entradaVideo).start();
							SolicitarCamaraDTO solicitar2 = new SolicitarCamaraDTO(solicitar.getOrigen(),
									solicitar.getDestino(), 2);
							enviarMsj(solicitar2);
							VentanaVideoLlamada pantalla = new VentanaVideoLlamada(entradaVideo);
							entradaVideo.addObserver(pantalla);
							pantalla.setVisible(true);
						}
					}

					if (solicitar.getEstado() == 2) {
						DatagramSocket sock = new DatagramSocket();
						JOptionPane.showMessageDialog(null, "Videollamada aceptada");
						SalidaVideo salida = new SalidaVideo(sock);
						new Thread(salida).start();
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				break;
			}

		}

	}

	/**
	 * metodo para enviar un mensaje a un cliente
	 * 
	 * @param obj
	 * @throws IOException
	 */
	public void enviarMsj(Object obj) throws IOException {
		salida.writeObject(obj);
		salida.flush();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
