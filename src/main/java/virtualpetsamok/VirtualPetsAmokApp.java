package virtualpetsamok;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class VirtualPetsAmokApp {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		String choice;
		String nameInput;
		String descriptionInput;
		String actionResult;
		Collection<VirtualPet> currentPetList;
		Map<String, Integer> currentLitterBoxList;

		VirtualPetShelter myShelter = new VirtualPetShelter();

		System.out.println("Welcome to Crazy Bob's Virtual Cat Shelter for Computer-Based Cat Units.");
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
			System.out.println("Current Virtual Cat Roster");
			System.out.println("---------------------------");
			System.out.println("Name\t\t|Hunger\t|Thirst\t|Boredom |Tiredness \t|\"Waste\"");

			currentPetList = myShelter.getAllPets();
			for (VirtualPet currentPet : currentPetList) {
				System.out.println(currentPet.getStats());
			}

			System.out.println();
			System.out.println("Food Bowl Levels: " + myShelter.getFoodBowlLevel());
			if (myShelter.getFoodBowlLevel() > 0) {
				System.out.print("Food Type: ");
				if (myShelter.getFoodType() == 1)
					System.out.println("Trader Moe's dry food");
				else if (myShelter.getFoodType() == 2)
					System.out.println("Fancier Feast premium wet food");
				else if (myShelter.getFoodType() == 3)
					System.out.println("Raw meat");
				else
					System.out.println("Table scraps");
			}
			System.out.println("Water Bowl Levels: " + myShelter.getWaterBowlLevel());
			System.out.println("Litter Box Levels");
			System.out.println("------------------");
			currentLitterBoxList = myShelter.getAllLitterBoxes();
			for (Entry<String, Integer> entry : currentLitterBoxList.entrySet()) {
				System.out.println("Box #" + entry.getKey() + ": " + entry.getValue());
			}

			System.out.println();
			System.out.println("What do you want to do?");
			System.out.println();
			System.out.println("1. Play with a cat");
			System.out.println("2. Put out food");
			System.out.println("3. Put out water");
			System.out.println("4. Scoop out a litter box");
			System.out.println("5. Add an additional litter box");
			System.out.println("6. Admit new cat to shelter");
			System.out.println("7. Adopt out cat");
			System.out.println("8. Look at the description of a cat");
			System.out.println("9. Loaf around");
			System.out.println("10. Quit");
			choice = input.nextLine();
			System.out.println();

			switch (choice) {
			case "1":
				System.out.println("Current Virtual Cat Roster");
				System.out.println("---------------------------");
				for (VirtualPet currentPet : currentPetList) {
					System.out.println(currentPet);
				}
				System.out.println();
				System.out.println("Please enter the name of the cat you would like to play with:");
				nameInput = input.nextLine().trim();

				if (!myShelter.checkIfPetExists(nameInput)) {
					System.out.println("There is no cat with that name in the shelter.");
					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				actionResult = myShelter.playWithPet(nameInput);

				if (!actionResult.equals("success")) {
					if (actionResult.equals("not bored enough")) {
						System.out.println(nameInput + " isn't bored enough to play with you right now. Try again later.");
					} else if (actionResult.equals("too tired")) {
						System.out.println(nameInput + " is too tired to play with you right now. Try again later.");
					} else {
						System.out.println(nameInput + " is too hungry to play with you right now. Try again later.");
					}

					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				System.out.println("You played with " + nameInput + ". They look like they had fun.");
				System.out.println("Now let's see what needs to get done next.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "2":
				do {
					System.out.println("What kind of food do you want to put out?");
					System.out.println("Depending upon the kind of food, some cats may not be willing to eat it.");
					System.out.println();
					System.out.println("1. Trader Moe's dry food");
					System.out.println("2. Fancier Feast premium wet food");
					System.out.println("3. Raw meat");
					System.out.println("4. Table scraps");
					System.out.println("5. Never mind, go back to the main menu");
					choice = input.nextLine();
					System.out.println();

					switch (choice) {
					case "1":
					case "2":
					case "3":
					case "4":
					case "5":
						break;
					default:
						continue;
					}
					break;
				} while (true);

				if (choice.equals("5"))
					continue;

				myShelter.putOutFood(Integer.parseInt(choice));
				System.out.println("You refilled the food bowls. Now the cats will have plenty to eat.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "3":
				actionResult = myShelter.putOutWater();

				if (actionResult.equals("no need")) {
					System.out.println("The water bowls are all already full. No need to put out more water right now.");
					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				System.out.println("You refilled the water bowls. Now the cats will have plenty to drink.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "4":
				System.out.println("Please enter the number of the litter box you would like to scoop out:");
				choice = input.nextLine();
				System.out.println();

				actionResult = myShelter.scoopLitterBox();

				if (!actionResult.equals("success")) {
					if (actionResult.equals("invalid number")) {
						System.out.println("Invalid litter box number.");
						System.out.println("Press enter to continue.");
					} else {
						System.out.println("That litter box is already clean. No need to scoop it out right now.");
						System.out.println("Press enter to continue.");
					}
					input.nextLine();
					continue;
				} else {
					System.out.println("You scooped out the litter box. Gotta exercise proper hygiene.");
					System.out.println("Press enter to continue.");
					input.nextLine();
					break;
				}
			case "5":
				myShelter.addLitterBox();

				System.out.println("You put out an additional litter box. That should help keep the place clean.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "6":
				System.out.println("Please enter the name of the cat you would like to admit:");
				nameInput = input.nextLine().trim();
				if (nameInput.equals("")) {
					nameInput = "Solid Cat";
				}
				System.out.println();

				if (nameInput.length() > 15) {
					System.out.println("That name is too long.");
					System.out.println("Names over 15 characters screw up the roster formatting.  Try a shorter one.");
					System.out.println("Press enter to continue.");
					input.nextLine();
					continue;
				}

				if (myShelter.checkIfPetExists(nameInput)) {
					System.out.println("There's already a cat with that name in this shelter.");
					System.out.println("It'd be too confusing if two cats had the same name.  Try a different one.");
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
			case "7":
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
				System.out.println("Bye, " + nameInput + "!  Have a good life!");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "8":
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
			case "9":
				System.out.println("OK, I guess you can just stare at the cats if you feel like it.");
				System.out.println("Don't stare for too long, though.  You have work to do.");
				System.out.println("Press enter to continue.");
				input.nextLine();
				break;
			case "10":
				break;
			default:
				continue;
			}

			if (!choice.equals("10")) {
				System.out.println();
				myShelter.petsTakeCareOfSelves();
			}

		} while (!choice.equals("10") && myShelter.getPetHasUsedFloorCount() < 3);

		if (myShelter.getPetHasUsedFloorCount() >= 3) {
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
