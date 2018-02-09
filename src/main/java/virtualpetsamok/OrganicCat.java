package virtualpetsamok;

public class OrganicCat extends OrganicPet {

	public OrganicCat(String name, String description) {
		this(name, description, 20, 20, 50, 10, 0, 100);
	}

	public OrganicCat(String name, String description, int hunger, int thirst, int happiness, int tiredness, int waste, int health) {
		super(name, description, hunger, thirst, happiness, tiredness, waste, health);
	}

}
