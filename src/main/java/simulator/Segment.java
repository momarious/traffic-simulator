package src.main.java.simulator;

import java.util.ArrayList;
import java.util.List;

public class Segment extends Route {

	private Direction direction;
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	public Segment(String name) {
		super(name);
	}

	/**
	 * Definir la direction du traffic
	 *
	 * @param direction La direction du traffic
	 */
	public void setTrafficDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Ajouter un vehicule au segment etroit.
	 *
	 * @param v vehicule
	 */
	public void addVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);
	}

	/**
	 * Retirer le vehicule a la tete du segment etroit.
	 *
	 * @param i indice du vehicule en tete de liste. i = 0
	 */
	public void removeVehicle(int i) {
		this.vehicles.remove(i);
	}

	/**
	 * Retourner le nombre de vehicules dans le segment etroit.
	 *
	 * return Le nombre de vehicles
	 */
	public int trafficSize() {
		return this.vehicles.size();
	}

	/**
	 * Retourner la direction du traffic.
	 *
	 *@return La direction
	 */
	public Direction trafficDirection() {
		return this.direction;
	}

	/**
	 * Retourner le vehicule a la position.
	 *
	 * @param index La position
	 * @return v Le vehicule
	 */
	public Vehicle getVehicule(int index) {
		return this.vehicles.get(index);
	}

	/**
	 * Afficher la liste des vehicules dans le segment etroit.
	 *
	 * @return vehicles
	 */
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

}
