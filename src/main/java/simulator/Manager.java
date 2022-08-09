package src.main.java.simulator;

public class Manager extends Thread {

	private Segment segment;

	public Manager(Segment segment) {
		this.segment = segment;
	}

	public Segment getSegment() {
		return segment;
	}

	/**
	* Faire acceder un vehicule au segment etroit.
	*
	* @param v vehicule 
	*/
	public synchronized void acceder(Vehicle v) {
		System.out.println("v" + v.getId() + " { Route: " + v.getCurrentRoute().getName()+ " } veut acceder au segment pour " + v.getDuree() + "s");
		while (!segment.estLibre() && v.getDirection() != segment.trafficDirection()) {
			try {
				System.out.println("v" + v.getId() + " est mise en attente");
				wait();
			} catch (Exception e) {
			}
		}
		System.out.println("v" + v.getId() + " occupe le segment");
		segment.setTrafficDirection(v.getDirection());
		segment.addVehicle(v);
		segment.occuper();
	}

	/**
	* Liberer un vehicule du segment etroit.
	*
	* @param v vehicule 
	*/
	public synchronized void liberer(Vehicle v) {
		segment.liberer();
		attendre(v);
		System.out.println("v" + v.getId() + " libere le segment\n");

		if (segment.trafficSize() == 0) {
			notifyAll();
		}
	}

	/**
	* Faire attendre la liberation du vehicule en tete de file.
	*
	* @param v vehicule 
	*/
	public void attendre(Vehicle v) {
		Vehicle head = segment.getVehicule(0);
		if (head.getId() != v.getId()) {
			try {
				Vehicle.sleep(head.getDuree() * 1000);
			} catch (InterruptedException e) {
			}
		}
		segment.removeVehicle(0);
	}

}
