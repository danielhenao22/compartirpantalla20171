package co.edu.eam.ingesoft.distribuidos.compartitrpantalla.dto;

import java.io.Serializable;

import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.modelo.Usuario;

public class SolicitarConDTO implements Serializable {

	private Usuario origen;
	private Usuario destino;
	int estado;

	public SolicitarConDTO(Usuario origen, Usuario destino) {
		this.origen = origen;
		this.destino = destino;
	}

	public SolicitarConDTO(Usuario origen, Usuario destino, int estado) {
		this.origen = origen;
		this.destino = destino;
		this.estado = estado;
	}

	public Usuario getOrigen() {
		return origen;
	}

	public void setOrigen(Usuario origen) {
		this.origen = origen;
	}

	public Usuario getDestino() {
		return destino;
	}

	public void setDestino(Usuario destino) {
		this.destino = destino;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
