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
		underTest = new VirtualPetShelter(4);
		testPet = new OrganicDog(NAME, DESCRIPTION);
		underTest.admitNewPet(testPet);
	}

	@Test
	public void shouldAdmitNewPet() {
		assertThat(underTest.checkIfPetExists(NAME), is(true));
	}

	@Test
	public void shouldRemovePetWhenAdopted() {
		underTest.adoptOutPet(NAME);

		assertThat(underTest.checkIfPetExists(NAME), is(false));
	}

	@Test
	public void putOutFoodShouldRaiseFoodBowlLevelToOrganicPetCountTimes2() {
		underTest.putOutFood();

		assertThat(underTest.getFoodBowlLevel(), is(6));
	}

	@Test
	public void putOutWaterShouldRaiseWaterBowlLevelToOrganicPetCountTimes2() {
		underTest.putOutWater();

		assertThat(underTest.getWaterBowlLevel(), is(6));
	}

	@Test
	public void scoopLitterBoxShouldLowerLitterBoxLevelToZero() {
		underTest.scoopLitterBoxes();

		assertThat(underTest.getLitterBoxLevel(), is(0));
	}

	@Test
	public void shouldPlayWithBothOrganicAndRobotPets() {
		RobotCat extraRobotPet = new RobotCat("robot", "");
		underTest.admitNewPet(extraRobotPet);
		String organicResult = underTest.playWithPet(NAME);
		String robotResult = underTest.playWithPet("robot");

		assertThat(organicResult, is("success"));
		assertThat(robotResult, is("success"));
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

	@Test
	public void petsShouldTakeCareOfSelves() {
		OrganicCat extraCat = new OrganicCat("Extra", DESCRIPTION, 60, 60, 60, 60, 60, 60);
		underTest.admitNewPet(extraCat);

		underTest.putOutFood();
		underTest.putOutWater();
		underTest.scoopLitterBoxes();
		underTest.petsTakeCareOfSelves();

		assertThat(underTest.getFoodBowlLevel(), is(7));
		assertThat(underTest.getWaterBowlLevel(), is(7));
		assertThat(underTest.getLitterBoxLevel(), is(1));
	}

	@Test
	public void organicCatShouldUseFloorWhenLitterBoxesAreFull() {
		OrganicCat extraCat = new OrganicCat("Extra", DESCRIPTION, 60, 60, 60, 60, 100, 60);
		underTest.admitNewPet(extraCat);
		underTest.petsTakeCareOfSelves();

		assertThat(underTest.checkIfFloorHasCrapOnIt(), is(true));
	}

}
