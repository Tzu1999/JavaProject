import java.util.*;
import java.math.*;
public class Role {
	private int blood;
	private int pos_x, pos_y;
	private long skill2time = 0;
	private long skill3time = 0;
	
	public int skill_one(int towerblood) {
		/*if(pox_x > tower_x(左邊) && pos_x < tower_x(右邊))
		 * 	return towerblood - 5;
		 *else if(pox_y > tower_y(上面) && pos_y < tower_y(下面))
		 * 	return towerblood - 5;
		 * */
		return towerblood;
	}
	public int skill_two(int towerblood) {
		long current = System.currentTimeMillis( );
		/*if(pox_x > tower_x(左邊) && pos_x < tower_x(右邊))
		 * else if(pox_y > tower_y(上面) && pos_y < tower_y(下面))*/
		if(skill2time == 0 || current - skill2time >= 15000) {
			skill2time = current;
			return towerblood - 10;
		}
		else 
			return towerblood;
	}
	public int skill_three(int towerblood) {
		long current = System.currentTimeMillis( );
		if(skill3time == 0 || current - skill3time >= 15000) {
			skill3time = current;
			int random = (int) (Math.random()*10);
			if(random < 5)
				return towerblood - 50;
			else
				return towerblood - 100;
		}
		else
			return towerblood;
	}
	public void move(int x, int y) {
					
	}
	public Role() {
		blood = 1500;
		pos_x = 0;
		pos_y = 300;
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
	public void setposx(int change) {
		pos_x = pos_x + change;
	}
	public void setposy(int change) {
		pos_y = pos_y + change;
	}
}


