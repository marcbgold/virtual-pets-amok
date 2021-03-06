package virtualpetsamok;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class OrganicCatTest {
	private static final String NAME = "Test";
	private static final String DESCRIPTION = "Description";
	private static final int HUNGER = 60;
	private static final int THIRST = 60;
	private static final int HAPPINESS = 60;
	private static final int TIREDNESS = 60;
	private static final int WASTE = 60;
	private static final int HEALTH = 60;
	private OrganicCat underTest;

	@Before
	public void setup() {
		underTest = new OrganicCat(NAME, DESCRIPTION, HUNGER, THIRST, HAPPINESS, TIREDNESS, WASTE, HEALTH);
	}

	@Test
	public void shouldKeepValuesInBounds() {
		underTest = new OrganicCat(NAME, DESCRIPTION, 110, 110, 110, 110, 110, 110);
		OrganicCat alsoUnderTest = new OrganicCat(NAME, DESCRIPTION, HUNGER, THIRST, -10, TIREDNESS, WASTE, HEALTH);
		underTest.keepValuesInBounds();
		alsoUnderTest.keepValuesInBounds();

		assertThat(underTest.getHungerLevel(), is(100));
		assertThat(underTest.getThirstLevel(), is(100));
		assertThat(underTest.getHappinessLevel(), is(100));
		assertThat(underTest.getTirednessLevel(), is(100));
		assertThat(underTest.getWasteLevel(), is(100));
		assertThat(underTest.getHealthLevel(), is(100));
		assertThat(alsoUnderTest.getHappinessLevel(), is(0));
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
	public void shouldRaiseHealthLevelBySpecifiedAmount() {
		underTest.raiseHealthLevel(10);

		assertThat(underTest.getHealthLevel(), is(70));
	}

	@Test
	public void shouldLowerHealthLevelBySpecifiedAmount() {
		underTest.lowerHealthLevel(10);

		assertThat(underTest.getHealthLevel(), is(50));
	}

	@Test
	public void shouldLowerHappinessLevelBySpecifiedAmount() {
		underTest.lowerHappinessLevel(10);

		assertThat(underTest.getHappinessLevel(), is(50));
	}
}
