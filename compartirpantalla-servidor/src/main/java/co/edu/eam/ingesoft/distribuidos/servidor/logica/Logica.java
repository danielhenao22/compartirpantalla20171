package co.edu.eam.ingesoft.distribuidos.servidor.logica;

import co.edu.eam.dao.UsuarioJpaController;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.dto.LoginDTO;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.dto.RegistroDTO;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.modelo.Usuario;

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
			return true;
		}else{
			return false;
		}	
	}

	/**
	 * crear usuario
	 * 
	 * @param regDTO
	 */
	public boolean crearUsuario(RegistroDTO regDTO) {
		usu = new Usuario();
		usu.setUsuario(regDTO.getUsuario());
		usu.setContrasenia(regDTO.getPass());
		con.create(usu);
		return true;
	}

	/**
	 * metodo para actualizar la IP del usuario
	 * 
	 * @param ip
	 * @param usuario
	 */
	public void actualizarUsuario(String ip, String usuario) {
		usu = con.findUsuario(usuario);	
		usu.setIp(ip);
		con.edit(usu);
	}
}
