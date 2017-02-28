package co.edu.eam.ingesoft.distribuidos.cliente.controlador;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.eam.ingesoft.distribuidos.cliente.gui.VentanaVideoLlamada;

public class EntradaVideo extends Observable implements Runnable{
	
	ServerSocket con;

	@Override
	public void run() {
		try {

			con = new ServerSocket(28000);

		} catch (IOException ex) {
			Logger.getLogger(VentanaVideoLlamada.class.getName()).log(Level.SEVERE, null, ex);
		}

		
		
		try {
			DatagramSocket sock = new DatagramSocket(27000);
			while (true) {
				try {
					DatagramPacket pkt = new DatagramPacket(new byte[65535], 65535);
					sock.receive(pkt);
					
					setChanged();
					notifyObservers(pkt);
				} catch (Exception ex) {
					Logger.getLogger(VentanaVideoLlamada.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		} catch (SocketException ex) {
			ex.printStackTrace();
		} 		
	}

}
