package co.edu.eam.ingesoft.distribuidos.cliente.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase donde se va a gestionar el envio de paquetes por UDP
 *
 * @author MAO
 */
public class SalidaVideo extends Observable implements Runnable {

	DatagramSocket sock;

	public SalidaVideo(DatagramSocket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {

		CameraUtil cam = new CameraUtil();
		cam.initCamera();

		while (true) {

			try {
				byte[] data = cam.getFrame();
				DatagramPacket pkt = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"),
						27000);
				sock.send(pkt);

			} catch (IOException ex) {
				Logger.getLogger(SalidaVideo.class.getName()).log(Level.SEVERE, null, ex);
			}
			try {

				Thread.sleep(50);

			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

	}

}
