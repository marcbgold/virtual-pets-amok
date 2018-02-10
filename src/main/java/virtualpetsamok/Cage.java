package virtualpetsamok;

public class Cage {

	private boolean isDirty;

	public Cage() {
		this(false);
	}

	public Cage(boolean dirtiness) {
		isDirty = dirtiness;
	}

	public boolean getIsDirty() {
		return isDirty;
	}

	public void addWaste() {
		isDirty = true;
	}

	public void cleanCage() {
		isDirty = false;
	}
}
