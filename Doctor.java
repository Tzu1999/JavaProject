package Tzu;

public class Doctor extends Role {
	public Doctor(int b, int x, int y) {
		super(b, x, y);
	}
	public int skill(int warriorblood) {
		return warriorblood + 10;
	}
}
