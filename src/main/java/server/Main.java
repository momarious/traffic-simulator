package src.main.java.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import src.main.java.simulator.Carrefour;
import src.main.java.simulator.Manager;
import src.main.java.simulator.Segment;
import src.main.java.simulator.Vehicle;

public class Main {

	public static void main(String[] args) throws IOException {

		Segment segment = new Segment("Segment etroit");

		Carrefour carrefourDroite = new Carrefour("Droite");
		carrefourDroite.addRoute(segment);

		Carrefour carrefourGauche = new Carrefour("Gauche");
		carrefourGauche.addRoute(segment);

		Manager manager = new Manager(segment);

		ServerSocket listener = new ServerSocket(7777);
		System.out.println("Attente de la connection du client...");

		Socket clientSocket = listener.accept();
		System.out.println("Client connect� !");

		InputStream inputStream = clientSocket.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

		try {
			Vehicle v = null;
			while (true) {
				v = (Vehicle) objectInputStream.readObject();
				v.setCurrentRoute(carrefourGauche, carrefourDroite);
				v.setManager(manager);
				v.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objectInputStream.close();
			inputStream.close();
			clientSocket.close();
			listener.close();
		}

	}

}
