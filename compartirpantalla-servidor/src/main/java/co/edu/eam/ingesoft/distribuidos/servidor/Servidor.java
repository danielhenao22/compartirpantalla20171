package co.edu.eam.ingesoft.distribuidos.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import co.edu.eam.dao.UsuarioJpaController;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.modelo.Usuario;
import co.edu.eam.ingesoft.distribuidos.servidor.logica.Logica;

public class Servidor implements Runnable {

	/**
	 * clientes conectado.
	 */
	private Map<String, HiloProcesarCliente> clientesConectados;

	private Executor pool;

	public void run() {
		try {
			Logica logica = new Logica();
			clientesConectados = new HashMap<String, HiloProcesarCliente>();
			ServerSocket soc = new ServerSocket(45000);
			pool = Executors.newFixedThreadPool(100);
			while (true) {
				try {
					System.out.println("esperando usuario..................");
					UsuarioJpaController cone = new UsuarioJpaController();
					cone.findUsuario(1);
					
					System.out.println("quien ");
					Socket con = soc.accept();
					HiloProcesarCliente cliente = new HiloProcesarCliente(con, this, logica);

					pool.execute(cliente);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * metodo para enviar a todos
	 * 
	 * @param obj
	 * @throws IOException
	 */
	public void enviarTodos(Object obj) throws IOException {
		for (Map.Entry<String, HiloProcesarCliente> entry : clientesConectados.entrySet()) {
			
			HiloProcesarCliente cli = entry.getValue();
			System.out.println("enviando a todos:"+cli.getUsuario().getUsuario());
			cli.enviarMsj(obj);
		}
	}

	/**
	 * metodo para obetener los usuarios conectados.
	 * 
	 * @return
	 */
	public List<Usuario> listarUsuarios() {
		System.out.println("listando usuarios.......................");
		List<Usuario> usuarios = new ArrayList<Usuario>();

		for (Map.Entry<String, HiloProcesarCliente> entry : clientesConectados.entrySet()) {
			HiloProcesarCliente cli = entry.getValue();
			usuarios.add(cli.getUsuario());
		}
		return usuarios;
	}

	/**
	 * metodo para agregar un cliente a la lista de lciente.
	 * 
	 * @param cliente
	 */
	public void agregarUsuario(HiloProcesarCliente cliente) {
		System.out.println("registrando usuario " + cliente.getUsuario().getUsuario());
		clientesConectados.put(cliente.getUsuario().getUsuario(), cliente);
	}

	/**
	 * metodo para agregar un cliente a la lista de lciente.
	 * 
	 * @param cliente
	 */
	public void quitarUsuario(HiloProcesarCliente cliente) {
		System.out.println("eliminado usuario " + cliente.getUsuario().getUsuario());

		clientesConectados.remove(cliente.getUsuario().getUsuario());
	}
	
	
	public static void main(String[] args) {
		new Thread(new Servidor()).start();
	}

}
