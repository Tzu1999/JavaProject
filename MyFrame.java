import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class MyFrame extends JFrame implements KeyListener{
	
	private static String button = new String("");
	private static int skill1_count = 0;
	private static int skill2_count = 0;
	private static int skill3_count = 0;
	private static int heal_count = 0;
	
	private static int tower_x = 200;       // tower_x = role.getposx();
	private static int tower_y = 250;       // tower_y = role.getposy();
	private static int tower_blood = 1500;  // tower_blood = role.getblood();
	
	private static int role_x = 560;        // tower_blood = role.getposx();
	private static int role_y = 260;        // tower_blood = role.getposy();
	private static int role_blood = 1000;   // tower_blood = role.getblood();
	
	private static int role2_x = 260;        // tower_blood = role.getposx();
	private static int role2_y = 460;        // tower_blood = role.getposy();
	private static int role2_blood = 1000;   // tower_blood = role.getblood();
	
	private static int tower_damage = 0;
	private static int role_damage = 0;
	private static int role2_damage = 0;
	private static int role_heal = 0;
	
	static Tower tower = new Tower(tower_blood, tower_x, tower_y);
	static Warrior role = new Warrior(role_blood, role_x, role_y);
	static Doctor role2 = new Doctor(role2_blood, role2_x, role2_y);
	
	private static Boolean isStart = false;
	private static JButton start, Skill_1, Skill_2, Skill_3, Heal;
	
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setVisible(true);
		frame.addKeyListener(frame);
		Timer timer = new Timer();
		TimerTask timetask = new TimerTask() {
			@Override
			public void run() {
				skill1_count++;
				skill2_count++;
				skill3_count++;
				heal_count++;
				if(!Skill_1.isEnabled() && skill1_count>5) {
					Skill_1.setEnabled(true);
				}
				else if(!Skill_2.isEnabled() && skill2_count>15) {
					Skill_2.setEnabled(true);
				}
				else if(!Skill_3.isEnabled() && skill3_count>20) {
					Skill_3.setEnabled(true);
				}
				else if(!Heal.isEnabled() && heal_count>5) {
					Heal.setEnabled(true);
				}
				frame.judge();
				frame.repaint();
	        }
		};
		timer.schedule(timetask, 0, 1000);
	}
	
	public MyFrame() {
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		initialize();
	}
	
	public void check() {
		if(role.getposx()!=role_x || role.getposy()!=role_y) {
			role_x = role.getposx();
			role_y = role.getposy();
			repaint();
		}
	}
	
	static public void reStart() {
		skill1_count = skill2_count = skill3_count = heal_count = 0;
		
		tower_x = 200;       // tower_x = role.getposx();
		tower_y = 250;       // tower_y = role.getposy();
		tower_blood = 1500;  // tower_blood = role.getblood();
		
		role_x = 560;        // tower_blood = role.getposx();
		role_y = 260;        // tower_blood = role.getposy();
		role_blood = 1000;   // tower_blood = role.getblood();
		
		role2_x = 260;        // tower_blood = role.getposx();
		role2_y = 460;        // tower_blood = role.getposy();
		role2_blood = 1000;   // tower_blood = role.getblood();
		
		tower_damage = 0;
		role_damage = 0;
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		// temp background
		Image backgroundImage = Toolkit.getDefaultToolkit().getImage("background.jpg");
		g.drawImage(backgroundImage, 0, 0, 1080, 720, this);
		// temp tower
		Image towerImage = Toolkit.getDefaultToolkit().getImage("tower.png");
		g.drawImage(towerImage, tower_x, tower_y, 250, 150, this);
		// temp role
		Image roleImage = Toolkit.getDefaultToolkit().getImage("role.png");
		g.drawImage(roleImage, role_x, role_y, 100, 180, this);
		
		Image role2Image = Toolkit.getDefaultToolkit().getImage("role2.png");
		g.drawImage(role2Image, role2_x, role2_y, 100, 180, this);
		
		// tower blood
		g.setColor(Color.RED);
		g.drawRect(tower_x+70, tower_y+160, 100, 8);
		g.fillRect(tower_x+70, tower_y+160, tower_blood/15, 8);
		
		// role blood
		g.setColor(Color.RED);
		g.drawRect(role_x+10, role_y+180, 100, 8);
		g.fillRect(role_x+10, role_y+180, role_blood/10, 8);
		
		// role blood
		g.setColor(Color.RED);
		g.drawRect(role2_x+10, role2_y+180, 100, 8);
		g.fillRect(role2_x+10, role2_y+180, role2_blood/10, 8);
		
		if(tower_damage > 0) {
			g.drawLine(role_x, role_y+90, tower_x+250, tower_y+100);
			Font f = new Font("Arial Bold",Font.BOLD|Font.ITALIC,30); 
			g.setFont(f);
			g.drawString("-"+tower_damage, 200, 250);
			tower_damage = 0;
		}
		
		if(role_damage > 0) {
			g.drawLine(role_x, role_y+100, tower_x+250, tower_y+110);
			Font f = new Font("Arial Bold",Font.BOLD|Font.ITALIC,20); 
			g.setFont(f);
			g.drawString("-"+role_damage, 560, 260);
			role_damage = 0;
		}
		
		if(role2_damage > 0) {
			g.drawLine(role2_x+50, role2_y-10, tower_x+150, tower_y+150);
			Font f = new Font("Arial Bold",Font.BOLD|Font.ITALIC,20); 
			g.setFont(f);
			g.drawString("-"+role2_damage, 260, 460);
			role_damage = 0;
		}

		if(role_heal > 0) {
			g.drawLine(role2_x+50, role2_y-10, role_x, role_y+100);
			Font f = new Font("Arial Bold",Font.BOLD|Font.ITALIC,20); 
			g.setFont(f);
			g.drawString("+"+role_heal, 500, 460);
			role_heal = 0;
		}
	}
	
	public void initialize() {
		start = new JButton("Start");
		ButtonListener startlistener = new ButtonListener();
		start.addActionListener(startlistener);
		start.setFocusable(false);
		start.setLocation(1100, 40);
		start.setSize(120, 30);
		add(start);
		
		Skill_1 = new JButton("Skill 1");
		ButtonListener skill1listener = new ButtonListener();
		Skill_1.addActionListener(skill1listener);
		Skill_1.setFocusable(false);
		Skill_1.setLocation(1100, 370);
		Skill_1.setSize(120, 80);
		add(Skill_1);
		
		Skill_2 = new JButton("Skill 2");
		ButtonListener skill2listener = new ButtonListener();
		Skill_2.addActionListener(skill2listener);
		Skill_2.setFocusable(false);
		Skill_2.setLocation(1100, 470);
		Skill_2.setSize(120, 80);
		add(Skill_2);
		
		Skill_3 = new JButton("Skill 3");
		ButtonListener skill3listener = new ButtonListener();
		Skill_3.addActionListener(skill3listener);
		Skill_3.setFocusable(false);
		Skill_3.setLocation(1100, 570);
		Skill_3.setSize(120, 80);
		add(Skill_3);
		
		Heal = new JButton("Heal");
		ButtonListener heallistener = new ButtonListener();
		Heal.addActionListener(heallistener);
		Heal.setFocusable(false);
		Heal.setLocation(1100, 270);
		Heal.setSize(120, 80);
		add(Heal);
	}
	
	public void judge() {
		if(role_blood <= 0) {
			isStart = false;
			JOptionPane.showMessageDialog(null, "lose game", "GAME OVER", JOptionPane.WARNING_MESSAGE);
			role_blood = 1;
			// stop everything
		}
		else if(tower_blood <= 0) {
			isStart = false;
			JOptionPane.showMessageDialog(null, "win game", "YOU WIN", JOptionPane.WARNING_MESSAGE);
			tower_blood = 1;
			// stop everything
		}
		else if(isStart){
			role_damage = tower.attack(role_x, role_y, 1, role);
			role_blood -= role_damage;
			role2_damage = tower.attack(role_x, role_y, 2, role2);
			role2_blood -= role2_damage;
		}
		if(role2_blood <= 0) {
			Heal.setEnabled(false);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		setFocusable(true);
		int keys = e.getKeyCode();
		if (keys == KeyEvent.VK_RIGHT && isStart) {
            role.move(+5, 0);
            check();
        }
        if (keys == KeyEvent.VK_LEFT && isStart) {
            role.move(-5, 0);
            check();
        }
        if (keys == KeyEvent.VK_UP && isStart) {
            role.move(0, -5);
            check();
        }
        if (keys == KeyEvent.VK_DOWN && isStart) {
            role.move(0, 5);
            check();
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	public static class ButtonListener implements ActionListener{
		
		public String getButtonString() {
			return button;
		}
		public void actionPerformed(ActionEvent e) {
			button = e.getActionCommand();
			System.out.println(button);
			if(button.equals("Start")) {
				isStart = true;
				reStart();
			}
			else if(button.equals("Skill 1") && isStart) {
				skill1_count = 0;
				Skill_1.setEnabled(false);
				tower_damage = tower_blood - role.skill_one(tower_blood);
				tower_blood -= tower_damage;
			}
			else if(button.equals("Skill 2") && isStart) {
				skill2_count = 0;
				Skill_2.setEnabled(false);
				tower_damage = tower_blood - role.skill_two(tower_blood);
				tower_blood -= tower_damage;
			}
			else if(button.equals("Skill 3") && isStart) {
				skill3_count = 0;
				Skill_3.setEnabled(false);
				tower_damage = tower_blood - role.skill_three(tower_blood);
				tower_blood -= tower_damage;
			}
			else if(button.equals("Heal") && isStart) {
				heal_count = 0;
				Heal.setEnabled(false);
				role_blood = role2.skill(role_blood);
				role_heal = 10;
			}
			System.out.println(button);
		}
	}
}
