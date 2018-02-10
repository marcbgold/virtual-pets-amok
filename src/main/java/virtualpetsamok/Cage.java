package virtualpetsamok;

public class Cage {

	private boolean isDirty;

	public boolean checkIfDirty() {
		return isDirty;
	}

	public void addWaste() {
		isDirty = true;
	}

	public void cleanCage() {
		isDirty = false;
	}
}
