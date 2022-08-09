package src.main.java.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Carrefour {

	private static final int NB_ROUTES_DROITE = 5;
	private String name;
	private List<Route> listRoutes;

	public Carrefour(String name) {
		this.name = name;
		this.listRoutes = new ArrayList<Route>();
		for (int i = 0; i < NB_ROUTES_DROITE; i++) {
			addRoute(new Route("Route droite " + i));
		}
	}

	/**
	 * Ajouter une route au Carrefour.
	 *
	 * @param e Une nouvelle route
	 */
	public void addRoute(Route e) {
		this.listRoutes.add(e);
	}

	/**
	 * Retourner le nombre de routes associee au carrefour .
	 *
	 * @return Le nombre de routes 
	 */
	public int getNbRoutes() {
		return listRoutes.size() - 1;
	}

	/**
	 * Retourner une route associee au carrefour.
	 *
	 * @return Une route 
	 */
	public Route getRandomRoute() {
		Random r = new Random();
		Route randomRoute = null;
		int randomInt = 0;
		do {
			randomInt = r.nextInt(getNbRoutes());
			randomRoute = listRoutes.get(randomInt + 1);
		} while (randomRoute.getName().equals("Segment etroit") || !randomRoute.estLibre());
		randomRoute.occuper();
		return randomRoute;
	}

	@Override
	public String toString() {
		return "Carrefour " + name + ", listRoutes=" + listRoutes;
	}

}
