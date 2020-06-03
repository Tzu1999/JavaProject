import java.util.*;
import java.math.*;
public class Role {
	protected int blood;
	protected int pos_x, pos_y;
	protected int tower_x = 200;
	protected int tower_y = 250;
	
	public Role(int b, int x, int y) {
		blood = b;
		pos_x = x;
		pos_y = y;
	}
	public int getblood() {
		return blood;
	}
	public int getposx() {
		return pos_x;
	}
	public int getposy() {
		return pos_y;
	}
	public void setblood(int change) {
		blood = blood + change;
	}
}
