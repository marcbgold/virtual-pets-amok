package virtualpetsamok;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import virtualpetsamok.VirtualPet;
import virtualpetsamok.VirtualPetShelter;

public class VirtualPetShelterTest {

	private static final String NAME = "Name";
	private static final String DESCRIPTION = "Description";
	private VirtualPetShelter underTest;

	@Before
	public void setup() {
		underTest = new VirtualPetShelter(3);
		underTest.admitNewPet(NAME, DESCRIPTION);
	}

	@Test
	public void shouldAdmitNewPet() {
		VirtualPet cat = underTest.getPet(NAME);

		assertThat(cat.getName(), is(NAME));
	}

	@Test
	public void shouldRemovePetWhenAdopted() {
		underTest.adoptOutPet(NAME);

		assertThat(underTest.checkIfPetExists(NAME), is(false));
	}

	@Test
	public void putOutFoodShouldRaiseFoodBowlLevelToPetCountTimes2AndAddFoodType() {
		underTest.putOutFood(1);

		assertThat(underTest.getFoodBowlLevel(), is(4));
		assertThat(underTest.getFoodType(), is(1));
	}

	@Test
	public void putOutWaterShouldRaiseWaterBowlLevelToPetCountTimes2() {
		underTest.putOutWater();

		assertThat(underTest.getWaterBowlLevel(), is(4));
	}

	@Test
	public void scoopLitterBoxShouldLowerLitterBoxLevelToZero() {
		underTest.scoopLitterBox("1");

		assertThat(underTest.getLitterBoxLevel("1"), is(0));
	}

	@Test
	public void shouldAddNewLitterBox() {
		underTest.addLitterBox();

		assertThat(underTest.getLitterBoxLevel("2"), is(0));
	}

	@Test
	public void shouldFindCleanLitterBox() {
		underTest.scoopLitterBox("1");
		String boxNum = underTest.findCleanLitterBox();

		assertThat(boxNum, is("1"));
	}

	@Test
	public void shouldPlayWithPet() {
		VirtualPet pet = underTest.getPet(NAME);
		pet.play();

		assertThat(pet.getBoredomLevel(), is(10));
	}

	@Test
	public void petShouldRefuseToPlayWhenNotBoredEnoughOrTooTired() {
		underTest.admitNewPetWithSpecialValues("NotBoredEnough", DESCRIPTION, 60, 60, 0, 60, 60, 0, 0);
		underTest.admitNewPetWithSpecialValues("TooTired", DESCRIPTION, 60, 60, 60, 80, 60, 0, 0);
		String firstResult = underTest.playWithPet("NotBoredEnough");
		String secondResult = underTest.playWithPet("TooTired");

		assertThat(firstResult, is("not bored enough"));
		assertThat(secondResult, is("too tired"));
	}

	@Test
	public void petsShouldTakeCareOfSelves() {
		underTest.admitNewPetWithSpecialValues("Extra", DESCRIPTION, 60, 60, 60, 60, 60, 3, 4);
		underTest.putOutFood(1);
		underTest.putOutWater();
		underTest.scoopLitterBox("1");
		underTest.petsTakeCareOfSelves();

		VirtualPet testPet = underTest.getPet("Extra");
		assertThat(testPet.getHungerLevel(), is(50));
		assertThat(testPet.getThirstLevel(), is(60));
		assertThat(testPet.getWasteLevel(), is(0));
		assertThat(underTest.getFoodBowlLevel(), is(5));
		assertThat(underTest.getWaterBowlLevel(), is(5));
		assertThat(underTest.getLitterBoxLevel("1"), is(1));
	}

	@Test
	public void petShouldUseFloorWhenThereIsNoCleanLitterBox() {
		underTest.admitNewPetWithSpecialValues("Extra", DESCRIPTION, 60, 60, 60, 60, 100, 0, 0);
		underTest.petsTakeCareOfSelves();

		assertThat(underTest.getPetHasUsedFloorCount(), is(1));
	}

}
