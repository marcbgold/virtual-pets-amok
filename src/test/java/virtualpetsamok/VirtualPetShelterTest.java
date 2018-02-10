package virtualpetsamok;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class VirtualPetShelterTest {

	private static final String NAME = "Name";
	private static final String DESCRIPTION = "Description";
	private VirtualPetShelter underTest;
	private VirtualPet testPet;

	@Before
	public void setup() {
		underTest = new VirtualPetShelter(3);
		testPet = new OrganicDog(NAME, DESCRIPTION);
		underTest.admitNewPet(testPet);
	}

	@Test
	public void shouldAdmitNewPets() {
		assertThat(underTest.checkIfPetExists("Mario"), is(true));
		assertThat(underTest.checkIfPetExists(NAME), is(true));
	}

	@Test
	public void shouldRemovePetWhenAdopted() {
		underTest.adoptOutPet(NAME);

		assertThat(underTest.checkIfPetExists(NAME), is(false));
	}

	@Test
	public void putOutFoodShouldRaiseFoodBowlLevelToOrganicPetCountTimes2() {
		RobotCat extraRobotPet = new RobotCat("test", "");
		underTest.admitNewPet(extraRobotPet);
		underTest.putOutFood();

		assertThat(underTest.getFoodBowlLevel(), is(4));
	}

	@Test
	public void putOutWaterShouldRaiseWaterBowlLevelToOrganicPetCountTimes2() {
		RobotCat extraRobotPet = new RobotCat("test", "");
		underTest.admitNewPet(extraRobotPet);
		underTest.putOutWater();

		assertThat(underTest.getWaterBowlLevel(), is(4));
	}

	@Test
	public void scoopLitterBoxShouldLowerLitterBoxLevelToZero() {
		underTest.scoopLitterBoxes();

		assertThat(underTest.getLitterBoxLevel(), is(0));
	}

	// boolean wasPlayedWith = false;
	//
	// public class PlayableDouble implements Playable {
	// @Override
	// public void play() {
	// wasPlayedWith = true;
	// }
	// }

	@Test
	public void shouldPlayWithBothOrganicAndRobotPets() {
		RobotCat extraRobotPet = new RobotCat("robot", "");
		underTest.admitNewPet(extraRobotPet);
		underTest.playWithPet(NAME);
		underTest.playWithPet("robot");

		assertThat(testPet.getHappinessLevel(), is(100));
		assertThat(extraRobotPet.getHappinessLevel(), is(100));
	}

	@Test
	public void petShouldRefuseToPlayWhenTooUnhealthy() {
		OrganicDog tooUnHealthy = new OrganicDog("too unhealthy", "", 50, 50, 50, 50, 50, 10);
		underTest.admitNewPet(tooUnHealthy);

		String tooSickResult = underTest.playWithPet("too unhealthy");

		assertThat(tooSickResult, is("too unhealthy"));
	}

	@Test
	public void organicPetShouldRefuseToPlayWhenTooHungry() {
		OrganicDog tooHungry = new OrganicDog("too hungry", "", 100, 50, 50, 50, 50, 50);
		underTest.admitNewPet(tooHungry);

		String tooHungryResult = underTest.playWithPet("too hungry");

		assertThat(tooHungryResult, is("too hungry"));
	}

	@Test
	public void organicPetShouldRefuseToPlayWhenTooTired() {
		OrganicCat tooTired = new OrganicCat("too tired", "", 50, 50, 50, 100, 50, 50);
		underTest.admitNewPet(tooTired);

		String tooTiredResult = underTest.playWithPet("too tired");

		assertThat(tooTiredResult, is("too tired"));
	}

	@Test
	public void robotPetShouldRefuseToPlayWhenOilTooLow() {
		RobotDog oilTooLow = new RobotDog("oil too low", "", 10, 50, 50, 50);
		underTest.admitNewPet(oilTooLow);

		String oilTooLowResult = underTest.playWithPet("oil too low");

		assertThat(oilTooLowResult, is("oil too low"));
	}

	@Test
	public void robotPetShouldRefuseToPlayWhenChargeTooLow() {
		RobotCat chargeTooLow = new RobotCat("charge too low", "", 50, 50, 10, 50);
		underTest.admitNewPet(chargeTooLow);

		String chargeTooLowResult = underTest.playWithPet("charge too low");

		assertThat(chargeTooLowResult, is("charge too low"));
	}
	//
	// @Test
	// public void petsShouldTakeCareOfSelves() {
	// underTest.admitNewPetWithSpecialValues("Extra", DESCRIPTION, 60, 60, 60, 60,
	// 60, 3, 4);
	// underTest.putOutFood(1);
	// underTest.putOutWater();
	// underTest.scoopLitterBox("1");
	// underTest.petsTakeCareOfSelves();
	//
	// VirtualPet testPet = underTest.getPet("Extra");
	// assertThat(testPet.getHungerLevel(), is(50));
	// assertThat(testPet.getThirstLevel(), is(60));
	// assertThat(testPet.getWasteLevel(), is(0));
	// assertThat(underTest.getFoodBowlLevel(), is(5));
	// assertThat(underTest.getWaterBowlLevel(), is(5));
	// assertThat(underTest.getLitterBoxLevel("1"), is(1));
	// }
	//
	// @Test
	// public void petShouldUseFloorWhenThereIsNoCleanLitterBox() {
	// underTest.admitNewPetWithSpecialValues("Extra", DESCRIPTION, 60, 60, 60, 60,
	// 100, 0, 0);
	// underTest.petsTakeCareOfSelves();
	//
	// assertThat(underTest.getPetHasUsedFloorCount(), is(1));
	// }
	//
}
