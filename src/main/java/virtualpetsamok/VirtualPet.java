package virtualpetsamok;

public abstract class VirtualPet {

	// TODO add health level

	protected String name;
	protected String description;
	// private int hungerLevel;
	// private int thirstLevel;
	protected int happinessLevel;
	// private int tirednessLevel;
	// private int wasteLevel;

	// int dislikedFoodType;
	// int hatedFoodType;
	// int minFoodType = 1;
	// int maxFoodType = 4;
	// Random randomGen = new Random();

	// TODO add nature modififer (normal/hyperactive/lazy)

	public VirtualPet(String name, String description) {
		this(name, description, 20, 20, 50, 10, 0, 0, 0);
	}

	public VirtualPet(String name, String description, int hunger, int thirst, int happiness, int tiredness, int waste, int dislikedFoodType, int hatedFoodType) {
		this.name = name;
		this.description = description;
		// hungerLevel = hunger;
		// thirstLevel = thirst;
		happinessLevel = happiness;
		// tirednessLevel = tiredness;
		// wasteLevel = waste;

		// if (dislikedFoodType != 0) {
		// this.dislikedFoodType = dislikedFoodType;
		// this.hatedFoodType = hatedFoodType;
		// } else {
		// dislikedFoodType = randomGen.nextInt((maxFoodType - minFoodType) + 1) +
		// minFoodType;
		// do {
		// hatedFoodType = randomGen.nextInt((maxFoodType - minFoodType) + 1) +
		// minFoodType;
		// } while (dislikedFoodType == hatedFoodType);
		// }
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	// public String getStats() {
	// String namePart = "";
	// if (name.length() < 9) {
	// namePart = name + "\t\t|";
	// } else { // if (name.length() < 15) {
	// namePart = name + "\t|";
	// } // else {
	// // namePart = name + " |";
	// // }
	// return namePart + hungerLevel + "\t|" + thirstLevel + "\t|" + happinessLevel
	// + "\t |" + tirednessLevel + "\t\t|" + wasteLevel;
	// }
	//
	public int getHungerLevel() {
		return hungerLevel;
	}

	public int getThirstLevel() {
		return thirstLevel;
	}

	public int getHappinessLevel() {
		return happinessLevel;
	}

	public int getTirednessLevel() {
		return tirednessLevel;
	}

	public int getWasteLevel() {
		return wasteLevel;
	}

	public int getDislikedFoodType() {
		return dislikedFoodType;
	}

	public int getHatedFoodType() {
		return hatedFoodType;
	}

	public void eat() {
		hungerLevel -= 50;
		thirstLevel += 10;
		tirednessLevel += 10;
		wasteLevel += 20;

		checkForValuesOver100();
	}

	public void drink() {
		thirstLevel -= 50;
		wasteLevel += 20;

		checkForValuesOver100();
	}

	public void play() {
		hungerLevel += 10;
		thirstLevel += 10;
		happinessLevel = 0;
		tirednessLevel += 30;

		checkForValuesOver100();
	}

	public void sleep() {
		hungerLevel += 20;
		thirstLevel += 20;
		happinessLevel -= 20;
		tirednessLevel = 0;

		checkForValuesOver100();
	}

	public void useBathroom() {
		wasteLevel = 0;
	}

	public void tick() {
		hungerLevel += 10;
		thirstLevel += 10;
		happinessLevel += 10;
		tirednessLevel += 10;
		wasteLevel += 10;

		checkForValuesOver100();
	}

	public abstract void checkForValuesOver100();

	@Override
	public String toString() {
		return name + ": " + description;
	}
}
