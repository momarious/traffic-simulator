package src.main.java.simulator;

import java.io.Serializable;

public class Vehicle extends Thread implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Manager manager;
	private Thread timer = null;
	private Route currentRoute;
	private int duree;
	private Direction direction;

	public Vehicle(String direction, int max, int min) {
		this.direction = Direction.valueOf(direction);
		this.duree = max - (int) Math.round(Math.random() * (max - min));
	}

	public int getDuree() {
		return this.duree;
	}

	@Override
	public void run() {
		manager.acceder(this);
		occuper();
		manager.liberer(this);
	}

	public void occuper() {
		try {
			while (duree > 0) {
				if (duree == 1) {
					System.out.println("V" + this.getId() + " " + duree + "s restante");
				} else
					System.out.println("V" + this.getId() + " " + duree + "s restantes");

				sleep(1000);
				duree--;
			}
		} catch (InterruptedException e) {
		}
	}

	@Override
	public String toString() {
		return "v" + this.getId() + " [currentRoute=" + currentRoute + ", duree=" + duree + "]";
	}

	@Override
	public synchronized void start() {
		if (timer == null) {
			timer = new Thread(this);
			timer.start();
		}
	}

	public Direction getDirection() {
		return direction;
	}

	public void setManager(Manager manager) {
		Vehicle.manager = manager;
	}

	public void setCurrentRoute(Carrefour cd, Carrefour cg) {
		this.currentRoute = direction.equals(Direction.DROITE) ? cg.getRandomRoute() : cd.getRandomRoute();
	}

	public Route getCurrentRoute() {
		return currentRoute;
	}

	public void setCurrentRoute(Route currentRoute) {
		this.currentRoute = currentRoute;
	}

}
