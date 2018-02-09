package virtualpetsamok;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class OrganicDogTest {

	private static final String NAME = "Test";
	private static final String DESCRIPTION = "Description";
	private static final int HUNGER = 60;
	private static final int THIRST = 60;
	private static final int HAPPINESS = 60;
	private static final int TIREDNESS = 60;
	private static final int WASTE = 60;
	private OrganicDog underTest;

	@Before
	public void setup() {
		underTest = new OrganicDog(NAME, DESCRIPTION, HUNGER, THIRST, HAPPINESS, TIREDNESS, WASTE);
	}

	@Test
	public void checkForValuesOver100ShouldKeepValuesFromGoingOver100() {
		underTest = new OrganicDog(NAME, DESCRIPTION, 110, 110, 110, 110, 110);
		underTest.checkForValuesOver100();

		assertThat(underTest.getHungerLevel(), is(100));
		assertThat(underTest.getThirstLevel(), is(100));
		assertThat(underTest.getHappinessLevel(), is(100));
		assertThat(underTest.getTirednessLevel(), is(100));
		assertThat(underTest.getWasteLevel(), is(100));
	}

	@Test
	public void eatShouldRaiseOrLowerLevels() {
		underTest.eat();

		assertThat(underTest.getHungerLevel(), is(10));
		assertThat(underTest.getThirstLevel(), is(70));
		assertThat(underTest.getTirednessLevel(), is(70));
		assertThat(underTest.getWasteLevel(), is(80));
	}

	@Test
	public void drinkShouldRaiseOrLowerLevels() {
		underTest.drink();

		assertThat(underTest.getThirstLevel(), is(10));
		assertThat(underTest.getWasteLevel(), is(80));
	}

	@Test
	public void playShouldRaiseOrLowerLevels() {
		underTest.play();

		assertThat(underTest.getHungerLevel(), is(70));
		assertThat(underTest.getThirstLevel(), is(70));
		assertThat(underTest.getHappinessLevel(), is(100));
		assertThat(underTest.getTirednessLevel(), is(90));
	}

	@Test
	public void sleepShouldRaiseOrLowerLevels() {
		underTest.sleep();

		assertThat(underTest.getHungerLevel(), is(80));
		assertThat(underTest.getThirstLevel(), is(80));
		assertThat(underTest.getHappinessLevel(), is(40));
		assertThat(underTest.getTirednessLevel(), is(0));
	}

	@Test
	public void useBathroomShouldLowerWasteLevelToZero() {
		underTest.useBathroom();

		assertThat(underTest.getWasteLevel(), is(0));
	}

	@Test
	public void tickShouldRaiseOrLowerValuesBy10() {
		underTest.tick();

		assertThat(underTest.getHungerLevel(), is(70));
		assertThat(underTest.getThirstLevel(), is(70));
		assertThat(underTest.getHappinessLevel(), is(50));
		assertThat(underTest.getTirednessLevel(), is(70));
		assertThat(underTest.getWasteLevel(), is(70));
	}

	@Test
	public void goForWalkShouldRaiseOrLowerValues() {
		underTest.goForWalk();

		assertThat(underTest.getHappinessLevel(), is(90));
		assertThat(underTest.getTirednessLevel(), is(90));
		assertThat(underTest.getWasteLevel(), is(0));
	}

}
