package virtualpetsamok;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class VirtualPetShelter {

	private Map<String, VirtualPet> roster = new HashMap<>();
	private final String DEFAULT_NAME = "Mario";
	private final String DEFAULT_DESCRIPTION = "Short, fat, male tabby with bushy, kinda reddish fur.  Seems to like mushrooms and flowers for some odd reason.";
	private int foodBowlLevel;
	private int foodType;
	private int waterBowlLevel;
	private Map<String, Integer> litterBoxes = new HashMap<>();

	private int petHasUsedFloorCount;

	public VirtualPetShelter() {
		this(0);
	}

	public VirtualPetShelter(int litterBoxLevel) {
		litterBoxes.put("1", litterBoxLevel);
		admitNewPet(DEFAULT_NAME, DEFAULT_DESCRIPTION);
	}

	public int getFoodBowlLevel() {
		return foodBowlLevel;
	}

	public int getFoodType() {
		return foodType;
	}

	public int getWaterBowlLevel() {
		return waterBowlLevel;
	}

	public int getLitterBoxLevel(String litterBoxNum) {
		return litterBoxes.get(litterBoxNum);
	}

	public int getPetHasUsedFloorCount() {
		return petHasUsedFloorCount;
	}

	public void admitNewPet(String name, String description) {
		roster.put(name, new VirtualPet(name, description));
	}

	public void admitNewPetWithSpecialValues(String name, String description, int hunger, int thirst, int boredom, int tiredness, int waste, int dislikedFoodType,
			int hatedFoodType) {
		roster.put(name, new VirtualPet(name, description, hunger, thirst, boredom, tiredness, waste, dislikedFoodType, hatedFoodType));
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

	public void adoptOutPet(String name) {
		roster.remove(name);
	}

	public String playWithPet(String name) {
		VirtualPet pet = getPet(name);
		if (pet.getBoredomLevel() < 50) {
			return "not bored enough";
		} else if (pet.getTirednessLevel() >= 80) {
			return "too tired";
		} else if (pet.getHungerLevel() >= 80) {
			return "too hungry";
		} else {
			pet.play();
			return "success";
		}
	}

	public void putOutFood(int foodType) {
		foodBowlLevel = roster.size() * 2;
		this.foodType = foodType;
	}

	public String putOutWater() {
		if (waterBowlLevel < roster.size() * 2) {
			waterBowlLevel = roster.size() * 2;
			return "success";
		}
		return "no need";
	}

	public Map<String, Integer> getAllLitterBoxes() {
		return litterBoxes;
	}

	public String scoopLitterBox(String litterBoxNumber) {
		if (!litterBoxes.containsKey(litterBoxNumber)) {
			return "invalid number";
		} else if (litterBoxes.get(litterBoxNumber) == 0) {
			return "no need";
		}

		litterBoxes.put(litterBoxNumber, 0);
		return "success";
	}

	public void addLitterBox() {
		int currentSize = litterBoxes.size();
		litterBoxes.put(Integer.toString(currentSize + 1), 0);
	}

	public String findCleanLitterBox() {
		for (Entry<String, Integer> entry : litterBoxes.entrySet()) {
			if (Integer.valueOf(entry.getValue()) < 3) {
				return entry.getKey();
			}
		}
		return "0";
	}

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
