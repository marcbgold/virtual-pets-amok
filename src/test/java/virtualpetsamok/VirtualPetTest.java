package virtualpetsamok;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import virtualpetsamok.VirtualPet;

public class VirtualPetTest {

	private static final String NAME = "Test";
	private static final String DESCRIPTION = "Description";
	private static final int HUNGER = 60;
	private static final int THIRST = 60;
	private static final int BOREDOM = 60;
	private static final int TIREDNESS = 60;
	private static final int WASTE = 60;
	private static final int DISLIKED_FOOD_TYPE = 0;
	private static final int HATED_FOOD_TYPE = 0;
	private VirtualPet underTest;

	@Before
	public void setup() {
		underTest = new VirtualPet(NAME, DESCRIPTION, HUNGER, THIRST, BOREDOM, TIREDNESS, WASTE, DISLIKED_FOOD_TYPE, HATED_FOOD_TYPE);
	}

	@Test
	public void checkForValuesOver100ShouldKeepValuesFromGoingOver100() {
		underTest = new VirtualPet(NAME, DESCRIPTION, 110, 110, 110, 110, 110, DISLIKED_FOOD_TYPE, HATED_FOOD_TYPE);
		underTest.checkForValuesOver100();

		assertThat(underTest.getHungerLevel(), is(100));
		assertThat(underTest.getThirstLevel(), is(100));
		assertThat(underTest.getBoredomLevel(), is(100));
		assertThat(underTest.getTirednessLevel(), is(100));
		assertThat(underTest.getWasteLevel(), is(100));
	}

	@Test
	public void eatShouldRaiseOrLowerLevels() {
		underTest.eat();

		assertThat(underTest.getHungerLevel(), is(20));
		assertThat(underTest.getThirstLevel(), is(70));
		assertThat(underTest.getTirednessLevel(), is(70));
		assertThat(underTest.getWasteLevel(), is(80));
	}

	@Test
	public void drinkShouldRaiseOrLowerLevels() {
		underTest.drink();

		assertThat(underTest.getThirstLevel(), is(20));
		assertThat(underTest.getWasteLevel(), is(80));
	}

	@Test
	public void playShouldRaiseOrLowerLevels() {
		underTest.play();

		assertThat(underTest.getHungerLevel(), is(70));
		assertThat(underTest.getThirstLevel(), is(70));
		assertThat(underTest.getBoredomLevel(), is(20));
		assertThat(underTest.getTirednessLevel(), is(90));
	}

	@Test
	public void sleepShouldRaiseOrLowerLevels() {
		underTest.sleep();

		assertThat(underTest.getHungerLevel(), is(80));
		assertThat(underTest.getThirstLevel(), is(80));
		assertThat(underTest.getTirednessLevel(), is(0));
	}

	@Test
	public void useBathroomShouldLowerWasteLevelToZero() {
		underTest.useBathroom();

		assertThat(underTest.getWasteLevel(), is(0));
	}

	@Test
	public void tickShouldRaiseValuesBy10() {
		underTest.tick();

		assertThat(underTest.getHungerLevel(), is(70));
		assertThat(underTest.getThirstLevel(), is(70));
		assertThat(underTest.getBoredomLevel(), is(70));
		assertThat(underTest.getTirednessLevel(), is(70));
		assertThat(underTest.getWasteLevel(), is(70));
	}

}
