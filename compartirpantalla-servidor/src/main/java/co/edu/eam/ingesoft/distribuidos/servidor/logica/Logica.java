package co.edu.eam.ingesoft.distribuidos.servidor.logica;

import co.edu.eam.dao.UsuarioJpaController;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.dto.LoginDTO;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.dto.RegistroDTO;
import co.edu.eam.modelo.Usuario;

public class Logica {
	
	UsuarioJpaController con;
	Usuario usu;
	
	public Logica(){
		con = new UsuarioJpaController();
	}

	// solo de ejemplo............

	/**
	 * metodo para verificar si esta el usuario.
	 * 
	 * @param usuario
	 * @return true
	 */
	public boolean verificarUsuario(LoginDTO usuario) {	
		usu = con.findUsuario(usuario.getUsuario());
		if(usu.getContrasenia().equals(usuario.getPass())){
			System.out.println("ENTRO");
			return true;
		}else{
			System.out.println("NO ENTRO");
			return false;
		}	
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
