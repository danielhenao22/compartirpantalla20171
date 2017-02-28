package co.edu.eam.ingesoft.distribuidos.cliente.controlador;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class HiloDestino extends Observable implements Runnable {

	ObjectInputStream entrada;
	Socket con;
	
	public HiloDestino(Socket con){
		this.con = con;
	}
	
	public HiloDestino(){
		
	}

	public void run() {

		try {
			ServerSocket soc = new ServerSocket(45001);
			con = soc.accept();
			entrada = new ObjectInputStream(con.getInputStream());
			while (true) {				
				byte[] data1 = (byte[]) entrada.readObject();
				try {
					setChanged();
					notifyObservers(data1);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}