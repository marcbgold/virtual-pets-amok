package virtualpetsamok;

public abstract class VirtualPet {

	protected String name;
	protected String description;
	protected int happinessLevel;
	protected int healthLevel;

	public VirtualPet(String name, String description, int happiness, int health) {
		this.name = name;
		this.description = description;
		happinessLevel = happiness;
		healthLevel = health;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getHappinessLevel() {
		return happinessLevel;
	}

	public void lowerHappinessLevel(int amount) {
		happinessLevel -= amount;
	}

	public int getHealthLevel() {
		return healthLevel;
	}

	public void raiseHealthLevel(int amount) {
		healthLevel += amount;
		if (healthLevel > 100) {
			healthLevel = 100;
		}
	}

	public void lowerHealthLevel(int amount) {
		healthLevel -= amount;
	}

	public abstract void play();

	public abstract void tick();

	public abstract void keepValuesInBounds();

	public boolean equals(VirtualPet input) {
		return name.hashCode() == input.hashCode();
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	// @Override
	// public String toString() {
	// return name + ": " + description;
	// }

	@Override
	public String toString() {
		String namePart = "";
		if (name.length() < 9) {
			namePart = name + "\t\t|";
		} else if (name.length() < 15) {
			namePart = name + "\t|";
		} else {
			namePart = name + " |";
		}
		return namePart;
	}
}
