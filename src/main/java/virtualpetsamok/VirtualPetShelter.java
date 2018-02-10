package virtualpetsamok;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VirtualPetShelter {

	// TODO add cage class
	// TODO add 1-to-1 dog/cage relationship
	// TODO add ability to walk all dogs
	// TODO add ability to oil all robots

	private Map<String, VirtualPet> roster = new HashMap<>();

	private final String organicDogName = "Crono";
	private final String organicDogDescription = "Reddish-orange male mutt with short, spiky fur.  Very friendly and athletic, but you've never heard him bark.  Ever.";
	private final OrganicDog defaultOrganicDog = new OrganicDog(organicDogName, organicDogDescription);

	private final String organicCatName = "Lara";
	private final String organicCatDescription = "Black and gray female tabby.  Extra nimble and slinky.  Inists upon trying to get into every enclosed space she can find.";
	private final OrganicCat defaultOrganicCat = new OrganicCat(organicCatName, organicCatDescription);

	private final String robotCatName = "GLaDOS";
	private final String robotCatDescription = "Mostly white with large, yellow eyes.  Seems to always be mocking you.  Possibly wants to kill you.";
	private final RobotCat defaultRobotPet = new RobotCat(robotCatName, robotCatDescription);

	private int foodBowlLevel;
	private int waterBowlLevel;
	private int litterBoxLevel;
	// private Map<VirtualPet, Cage> cageList = new HashMap<>();

	private boolean floorHasCrapOnIt;

	public VirtualPetShelter() {
		this(0);
	}

	public VirtualPetShelter(int litterBoxLevel) {
		this.litterBoxLevel = litterBoxLevel;
		admitNewPet(defaultOrganicDog);
		admitNewPet(defaultOrganicCat);
		admitNewPet(defaultRobotPet);
	}

	public int getFoodBowlLevel() {
		return foodBowlLevel;
	}

	public int getWaterBowlLevel() {
		return waterBowlLevel;
	}

	public int getLitterBoxLevel() {
		return litterBoxLevel;
	}

	public boolean checkIfLitterBoxesAreFull() {
		return litterBoxLevel >= getOrganicCatCount() * 2;
	}

	public boolean checkIfFloorHasCrapOnIt() {
		return floorHasCrapOnIt;
	}

	public void admitNewPet(VirtualPet petInput) {
		roster.put(petInput.getName(), petInput);
	}

	public boolean checkIfPetExists(String name) {
		return roster.containsKey(name);
	}

	public VirtualPet getPet(String name) {
		return roster.get(name);
	}

	public Collection<VirtualPet> getAllPets() {
		return roster.values();
	}

	public int getOrganicPetCount() {
		int count = 0;
		for (VirtualPet i : getAllPets()) {
			if (i instanceof OrganicPet) {
				count++;
			}
		}
		return count;
	}

	public int getOrganicCatCount() {
		int count = 0;
		for (VirtualPet i : getAllPets()) {
			if (i instanceof OrganicCat) {
				count++;
			}
		}
		return count;
	}

	public void adoptOutPet(String name) {
		roster.remove(name);
	}

	public String playWithPet(String name) {
		VirtualPet pet = getPet(name);
		if (pet.getHealthLevel() <= 30) {
			return "too unhealthy";
		}

		if (pet instanceof OrganicPet) {
			OrganicPet orgPet = (OrganicPet) pet;
			if (orgPet.getTirednessLevel() >= 80) {
				return "too tired";
			} else if (orgPet.getHungerLevel() >= 80) {
				return "too hungry";
			}
		}

		if (pet instanceof RobotPet) {
			RobotPet roboPet = (RobotPet) pet;
			if (roboPet.getOilLevel() <= 20) {
				return "oil too low";
			} else if (roboPet.getChargeLevel() <= 20) {
				return "charge too low";
			}
		}
		pet.play();
		return "success";
	}

	public String putOutFood() {
		if (foodBowlLevel < getOrganicPetCount() * 2) {
			foodBowlLevel = getOrganicPetCount() * 2;
			return "success";
		}
		return "no need";
	}

	public String putOutWater() {
		if (waterBowlLevel < getOrganicPetCount() * 2) {
			waterBowlLevel = getOrganicPetCount() * 2;
			return "success";
		}
		return "no need";
	}

	public String scoopLitterBoxes() {
		if (litterBoxLevel == 0) {
			return "no need";
		} else {
			litterBoxLevel = 0;
			return "success";
		}
	}

	public void cleanFloor() {
		floorHasCrapOnIt = false;
	}

	public void petsTakeCareOfSelves() {
		for (VirtualPet currentPet : getAllPets()) {
			currentPet.tick();

			if (currentPet instanceof OrganicPet) {
				OrganicPet orgPet = (OrganicPet) currentPet;

				if (orgPet.getHungerLevel() >= 50 && foodBowlLevel > 0) {
					orgPet.eat();
					foodBowlLevel--;
				}

				if (orgPet.getThirstLevel() >= 50 && waterBowlLevel > 0) {
					orgPet.drink();
					waterBowlLevel--;
				}

				if (orgPet instanceof OrganicCat) {
					OrganicCat orgCat = (OrganicCat) orgPet;
					if (orgCat.getWasteLevel() == 100 && checkIfLitterBoxesAreFull()) {
						orgCat.useBathroom();
						floorHasCrapOnIt = true;
					} else if (orgPet.getWasteLevel() >= 70 && !checkIfLitterBoxesAreFull()) {
						orgCat.useBathroom();
						litterBoxLevel++;
					}
				}

				if (orgPet.getTirednessLevel() >= 80) {
					orgPet.sleep();
				}
			}
		}
	}
}
