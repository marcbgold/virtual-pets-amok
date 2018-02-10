package virtualpetsamok;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class VirtualPetsAmokApp {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		String choice;
		String nameInput = null;
		String descriptionInput;
		String actionResult;
		Collection<VirtualPet> currentPetList;
		Map<VirtualPet, Cage> currentCageList;

		VirtualPetShelter myShelter = new VirtualPetShelter();

		System.out.println("Welcome to Flexible Capacity Shelter #32098-AXA-8 for Virtual Pet Units.");
		System.out.println("Thank you for volunteering your time here.");
		System.out.println("Please enter your name, so that we can address you properly.");
		String yourName = input.nextLine().trim();

		if (yourName.equals("")) {
			yourName = "Rando";
		}

		do {
			System.out.println();
			System.out.println("Volunteer Menu for " + yourName + " the Human");
			System.out.println();
			System.out.println("Current Organic Pet Roster");
			System.out.println("---------------------------");
			System.out.println("Name\t\t|Hunger\t|Thirst\t|Happiness |Tiredness \t|\"Waste\"\t|Health");

			currentPetList = myShelter.getAllOrganicPets();
			for (VirtualPet currentPet : currentPetList) {
				System.out.println(currentPet);
			}

			System.out.println();
			System.out.println("Current Robot Pet Roster");
			System.out.println("---------------------------");
			System.out.println("Name\t\t|Oil\t|Happiness |Charge\t |Health");

			currentPetList = myShelter.getAllRobotPets();
			for (VirtualPet currentPet : currentPetList) {
				System.out.println(currentPet);
			}

			System.out.println();
			System.out.println("Food Bowl Levels: " + myShelter.getFoodBowlLevel());
			System.out.println("Water Bowl Levels: " + myShelter.getWaterBowlLevel());
			System.out.println("Litter Box Levels: " + myShelter.getLitterBoxLevel());
			System.out.println();

			currentCageList = myShelter.getAllCages();
			for (Entry<VirtualPet, Cage> entry : currentCageList.entrySet()) {
				if (entry.getValue().getIsDirty()) {
					System.out.println(entry.getKey().getName() + "'s cage is dirty.");
				}
			}
			System.out.println();
			if (myShelter.checkIfFloorIsDirty()) {
				System.out.println("The floor is dirty.");
			}

			// System.out.println();
			// System.out.println("What do you want to do?");
			// System.out.println();
			// System.out.println("1. Play with a pet");
			// System.out.println("2. Put out food");
			// System.out.println("3. Put out water");
			// System.out.println("4. Scoop out the litter boxes");
			// System.out.println("5. Clean all the cages");
			// System.out.println("6. Admit new cat to shelter");
			// System.out.println("7. ");
			// System.out.println("7. Adopt out cat");
			// System.out.println("8. Look at the description of a pet");
			// System.out.println("9. Loaf around");
			// System.out.println("10. Quit");

			System.out.println();
			System.out.println("What do you want to do?");
			System.out.println();
			System.out.println("1. Put out food\t\t\t|2. Put out water");
			System.out.println("3. Clean all cages\t\t\t|4. Scoop out all litter boxes");
			System.out.println("5. Play with a pet\t\t\t|6. Walk all dogs");
			System.out.println("7. Oil all robots\t\t\t|8. Recharge all robots");
			System.out.println("9. Admit new pet to shelter\t\t\t|10. Adopt out pet");
			System.out.println("11. Look at description of a pet\t|12. Loaf around");
			System.out.println("13. Quit");
			if (myShelter.checkIfFloorIsDirty()) {
				System.out.println("14. Clean floor");
			}
			choice = input.nextLine();
			System.out.println();

			switch (choice) {
			case "1":
				actionResult = myShelter.putOutFood();

				if (actionResult.equals("no need")) {
					System.out.println("The food bowls are all already full. No need to put out more food right now.");
					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				System.out.println("You refilled the food bowls. Now the organic pets will have plenty to eat.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "2":
				actionResult = myShelter.putOutWater();

				if (actionResult.equals("no need")) {
					System.out.println("The water bowls are all already full. No need to put out more water right now.");
					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				System.out.println("You refilled the water bowls. Now the organic pets will have plenty to drink.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "3":
				myShelter.cleanAllCages();

				System.out.println("You cleaned all the cages, regardless of whether or not they needed it.  You are very fastidious, human unit.");
				System.out.println("Now the organic pets who utilize those cages will not see their health levels drop.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "4":
				actionResult = myShelter.scoopLitterBox();

				if (actionResult.equals("no need")) {
					System.out.println("The litter boxes are all already clean. No need to scoop them out right now.");
					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				System.out.println("You scooped out the litter boxes. Proper hygiene is important for maintaining proper organic cat unit health levels.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "5":
				System.out.println("Current Virtual Pet Roster");
				System.out.println("---------------------------");
				for (VirtualPet currentPet : currentPetList) {
					System.out.println(currentPet.getName() + ": " + currentPet.getDescription());
				}
				System.out.println();
				System.out.println("Please enter the name of the pet you would like to play with:");
				nameInput = input.nextLine().trim();

				if (!myShelter.checkIfPetExists(nameInput)) {
					System.out.println("There is no pet with that name in this shelter.");
					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				actionResult = myShelter.playWithPet(nameInput);

				if (!actionResult.equals("success")) {
					if (actionResult.equals("too tired")) {
						System.out.println(nameInput + " is too tired to play with you right now. Try again later.");
					} else if (actionResult.equals("too hungry")) {
						System.out.println(nameInput + " is too hungry to play with you right now. Try again later.");
					} else if (actionResult.equals("oil too low")) {
						System.out.println(nameInput + "'s oil levels are too low to play with you right now. Try again later.");
					} else if (actionResult.equals("charge too low")) {
						System.out.println(nameInput + "'s battery levels are too low to play with you right now. Try again later.");
					} else {
						System.out.println(nameInput + " is too unhealthy to play with you right now. You should really address that immediately.");
					}
					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				System.out.println("You played with " + nameInput + ". Quantifiable fun levels have been achieved.");
				System.out.println("Now you should identify another task and complete it.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "6":
				myShelter.walkAllDogs();

				System.out.print("You walked all the dogs. Now they are happier, and the organic ones will not make a mess of their cages for the time being.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "7":
				myShelter.oilAllRobots();

				System.out.print("You oiled all the robots. Now they will be able to function with maximum mobility.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "8":
				myShelter.chargeAllRobots();

				System.out.print("You recharged all the robots. This is good. No robot should have to put up with an empty battery.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "9":
				System.out.println("Please enter the name of the cat you would like to admit:");
				nameInput = input.nextLine().trim();
				if (nameInput.equals("")) {
					nameInput = "Solid Cat";
				}
				System.out.println();

				if (nameInput.length() > 15) {
					System.out.println("That name is too long.");
					System.out.println("Names over 15 characters screw up the roster formatting. Try a shorter one.");
					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				if (myShelter.checkIfPetExists(nameInput)) {
					System.out.println("There's already a cat with that name in this shelter.");
					System.out.println("It'd be too confusing if two cats had the same name. Try a different one.");
					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				System.out.println("Please enter a description of this cat:");
				descriptionInput = input.nextLine().trim();
				if (descriptionInput.equals("")) {
					descriptionInput = "Cat? Cat?! Caaaaat!";
				}
				System.out.println();

				myShelter.admitNewPet(descriptionInput);

				System.out.println(nameInput + " has successfully been admitted to the shelter.");
				System.out.println("Please take good care of them.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "10":
				System.out.println("Current Virtual Cat Roster");
				System.out.println("---------------------------");
				for (VirtualPet currentPet : currentPetList) {
					System.out.println(currentPet);
				}
				System.out.println();
				System.out.println("Please enter the name of the cat you would like to adopt out:");
				nameInput = input.nextLine().trim();
				System.out.println();

				if (!myShelter.checkIfPetExists(nameInput)) {
					System.out.println("There is no cat with that name in the shelter.");
					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				myShelter.adoptOutPet(nameInput);

				System.out.println(nameInput + " has successfully been adopted out to a new home.");
				System.out.println("Bye, " + nameInput + "! Have a good life!");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "11":
				System.out.println("Please enter the name of the cat whose description you would like to see:");
				nameInput = input.nextLine().trim();
				System.out.println();

				if (!myShelter.checkIfPetExists(nameInput)) {
					System.out.println("There is no cat with that name in the shelter.");
					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				System.out.println("OK, here's the description:");
				System.out.println();
				System.out.println(myShelter.getPet(nameInput));
				System.out.println();
				System.out.println("Press enter to continue.");
				input.nextLine();
				continue;
			case "12":
				System.out.println("OK, I guess you can just stare at the cats if you feel like it.");
				System.out.println("Don't stare for too long, though. You have work to do.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "13":
				break;
			case "14":
				myShelter.cleanFloor();

				System.out.println("You cleaned the floor.  Now the organic pets will not get sick from stepping in their own filth.");
				System.out.println("This is your fault for not cleaning the cages and litter boxes sooner, you know.  Don't let it happen again.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			default:
				continue;
			}

			if (!choice.equals("10")) {
				System.out.println();
				myShelter.petsTakeCareOfSelves();
			}

		} while (!choice.equals("10") && !myShelter.checkIfPetIsDead());

		if (myShelter.checkIfPetIsDead())

		{
			System.out.println("OK, seriously.  Why did you never scoop out the litter boxes on time?!");
			System.out.print("You forced the cats to go on the floor so many times that Animal Protection Services shut down the shelter");
			System.out.println(" for being a health hazard!");
			System.out.println("That's gross, and you suck.  Last time I ever let YOU help me with anything.");
		} else {
			System.out.println("Bye! Thanks for volunteering. Come back later and help out again, OK?");
		}
		input.close();

	}

}
