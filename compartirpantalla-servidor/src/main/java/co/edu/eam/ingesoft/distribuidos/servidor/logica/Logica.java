package co.edu.eam.ingesoft.distribuidos.servidor.logica;

import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.dto.LoginDTO;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.dto.RegistroDTO;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.modelo.Usuario;

public class Logica {

	// solo de ejemplo............

	/**
	 * metodo para verificar si esta el usuario.
	 * 
	 * @param usuario
	 * @return true
	 */
	public boolean verificarUsuario(LoginDTO usuario) {
		return true;
	}

	/**
	 * crear usuario
	 * 
	 * @param regDTO
	 */
	public boolean crearUsuario(RegistroDTO regDTO) {
		return true;
	}

	/**
	 * metodo para actualizar la IP del usuario
	 * 
	 * @param ip
	 * @param usuario
	 */
	public void actualizarUsuario(String ip, String usuario) {

	}
}
