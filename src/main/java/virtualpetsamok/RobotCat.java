package virtualpetsamok;

public class RobotCat extends RobotPet {

	public RobotCat(String name, String description) {
		this(name, description, 80, 50, 90, 100);
	}

	public RobotCat(String name, String description, int oil, int happiness, int charge, int health) {
		super(name, description, oil, happiness, charge, health);
	}

}
