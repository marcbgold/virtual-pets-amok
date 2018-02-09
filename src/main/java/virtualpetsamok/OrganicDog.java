package virtualpetsamok;

public class OrganicDog implements Walkable {

	private String name;
	private String description;
	private int hungerLevel;
	private int thirstLevel;
	private int happinessLevel;
	private int tirednessLevel;
	private int wasteLevel;

	public OrganicDog(String name, String description) {
		this(name, description, 20, 20, 50, 10, 0);
	}

	public OrganicDog(String name, String description, int hunger, int thirst, int happiness, int tiredness, int waste) {
		this.name = name;
		this.description = description;
		hungerLevel = hunger;
		thirstLevel = thirst;
		happinessLevel = happiness;
		tirednessLevel = tiredness;
		wasteLevel = waste;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

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
		happinessLevel = 100;
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

	public void goForWalk() {
		happinessLevel += 30;
		tirednessLevel += 30;
		useBathroom();
	}

	public void tick() {
		hungerLevel += 10;
		thirstLevel += 10;
		happinessLevel -= 10;
		tirednessLevel += 10;
		wasteLevel += 10;

		checkForValuesOver100();
	}

	public void checkForValuesOver100() {
		if (hungerLevel > 100)
			hungerLevel = 100;

		if (thirstLevel > 100)
			thirstLevel = 100;

		if (happinessLevel > 100)
			happinessLevel = 100;

		if (tirednessLevel > 100)
			tirednessLevel = 100;

		if (wasteLevel > 100)
			wasteLevel = 100;
	}

}
