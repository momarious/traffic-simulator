package src.main.java.simulator;

public class Route {

	private String name;
	private boolean occupe = false;

	public Route(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean estLibre() {
		return occupe == false;
	}

	public void occuper() {
		this.occupe = true;
	}

	public void liberer() {
		this.occupe = false;
	}
	
	@Override
	public String toString() {
		return "Route [name=" + name + ", libre=" + estLibre() + "]";
	}

}
