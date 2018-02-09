package virtualpetsamok;

public class RobotDog extends RobotPet implements Walkable {

	public RobotDog(String name, String description) {
		this(name, description, 80, 50, 90, 100);
	}

	public RobotDog(String name, String description, int oil, int happiness, int charge, int health) {
		super(name, description, oil, happiness, charge, health);
	}

	@Override
	public void goForWalk() {
		oilLevel -= 20;
		happinessLevel += 20;
		chargeLevel -= 30;
	}

}
