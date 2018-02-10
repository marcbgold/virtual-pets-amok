package virtualpetsamok;

public class OrganicDog extends OrganicPet implements Walkable, Cageable {

	public OrganicDog(String name, String description) {
		this(name, description, 20, 20, 50, 10, 0, 100);
	}

	public OrganicDog(String name, String description, int hunger, int thirst, int happiness, int tiredness, int waste, int health) {
		super(name, description, hunger, thirst, happiness, tiredness, waste, health);
	}

	@Override
	public void goForWalk() {
		happinessLevel += 20;
		tirednessLevel += 30;
		useBathroom();
	}

}
