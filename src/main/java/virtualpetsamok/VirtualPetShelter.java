package virtualpetsamok;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VirtualPetShelter {

	// TODO add cage class
	// TODO add 1-to-1 dog/cage relationship
	// TODO remove multiple litterboxes and raise max litterbox level

	private Map<String, VirtualPet> roster = new HashMap<>();
	private final String DEFAULT_NAME = "Mario";
	private final String DEFAULT_DESCRIPTION = "Short, fat, male tabby with bushy, kinda reddish fur.  Seems to like mushrooms and flowers for some odd reason.";
	private final OrganicCat defaultPet = new OrganicCat(DEFAULT_NAME, DEFAULT_DESCRIPTION);
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
		admitNewPet(defaultPet);
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

	public boolean getFloorHasCrapOnIt() {
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

	// public Map<String, Integer> getAllLitterBoxes() {
	// return litterBoxes;
	// }

	public String scoopLitterBoxes() {
		if (litterBoxLevel == 0) {
			return "no need";
		} else {
			litterBoxLevel = 0;
			return "success";
		}
	}

	// public void addLitterBox() {
	// int currentSize = litterBoxes.size();
	// litterBoxes.put(Integer.toString(currentSize + 1), 0);
	// }
	//
	// public String findCleanLitterBox() {
	// for (Entry<String, Integer> entry : litterBoxes.entrySet()) {
	// if (Integer.valueOf(entry.getValue()) < 3) {
	// return entry.getKey();
	// }
	// }
	// return "0";
	// }

	public void petsTakeCareOfSelves() {
		for (VirtualPet currentPet : getAllPets()) {
			currentPet.tick();

			if (currentPet.getHungerLevel() >= 50 && foodType != currentPet.getHatedFoodType() && foodBowlLevel > 0) {
				if (foodType != currentPet.getDislikedFoodType()) {
					currentPet.eat();
				} else if (currentPet.getHungerLevel() >= 70)
					currentPet.eat();
				foodBowlLevel--;
			}

			if (currentPet.getThirstLevel() >= 50 && waterBowlLevel > 0) {
				currentPet.drink();
				waterBowlLevel--;
			}

			String cleanBoxNum = findCleanLitterBox();
			if (currentPet.getWasteLevel() == 100 && cleanBoxNum == "0") {
				currentPet.useBathroom();
				petHasUsedFloorCount++;
			} else if (currentPet.getWasteLevel() >= 70 && !cleanBoxNum.equals("0")) {
				currentPet.useBathroom();
				int boxLevel = litterBoxes.get(cleanBoxNum);
				litterBoxes.put(cleanBoxNum, boxLevel + 1);
			}

			if (currentPet.getTirednessLevel() >= 80) {
				currentPet.sleep();
			}
		}
	}
}
