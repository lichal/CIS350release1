package nachogame;

import static org.junit.Assert.*;
import nachogame.BackGround;

import org.junit.Test;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;


public class SpaceTester2 {
	// NachoPanel nachos = new NachoPanel();
	Ship s = new Ship();
	SpaceObject space = (SpaceObject) s;
	Enemy e = new Enemy(100);
	//Projectile p = new Projectile(5, 5, 50, 40);

	
	/*******************************************************
	 * BACKGROUND
	 *******************************************************/
	@Test
	public void testBackground1() {
		BackGround b = new BackGround(5);
		b = new BackGround(1);
		Image image = b.getBack();
		assertTrue(image != null);
	}
	
	
	/*******************************************************
	 * Enemy
	 *******************************************************/
	@Test
	public void testEnemy1() {
		Enemy e = new Enemy(14);
		e.getDestroyed();
		e.setDestroyed(true);
		e.doLogic();
		e.collide(new Projectile(new Ship()));
		e.setHealth(45);
		e.doLogic();
		e.setCounting(500);
		e.getCounting();
		e.setDestroyed(new Boolean(true));
		e.incrementCount();
		e.resetCount();
		e.getShot();
		e.setShot();
		
		//assertTrue(image != null);
	}
	
	/*******************************************************
	 * Level
	 *******************************************************/
	@Test
	public void testLevel1() {
		// level 4
		Level l = new Level(LevelNum.LEVEL4);
		l.run();
		for (int i = 0; i < 190; i++) {
			l.incrementTime();	
		}
		l.incrementTime();
		l.run();
		for (int i = 0; i < 19000; i++) {
			l.incrementTime();	
		}
		l.run();

		// level 1
		l = new Level(LevelNum.LEVEL1);
		l.run();
		for (int i = 0; i < 190; i++) {
			l.incrementTime();	
		}
		l.incrementTime();
		l.run();
		for (int i = 0; i < 19000; i++) {
			l.incrementTime();	
		}
		l.run();
		
		l = new Level(LevelNum.LEVEL2);
		l.run();
		for (int i = 0; i < 190; i++) {
			l.incrementTime();	
		}
		l.incrementTime();
		l.run();
		for (int i = 0; i < 19000; i++) {
			l.incrementTime();	
		}
		l.run();
		
		l = new Level(LevelNum.LEVEL3);
		l.run();
		for (int i = 0; i < 190; i++) {
			l.incrementTime();	
		}
		l.incrementTime();
		l.run();
		for (int i = 0; i < 19000; i++) {
			l.incrementTime();	
		}
		l.run();
		
		l = new Level(LevelNum.LEVEL5);
		l.run();
		for (int i = 0; i < 190; i++) {
			l.incrementTime();	
		}
		l.incrementTime();
		l.run();
		for (int i = 0; i < 19000; i++) {
			l.incrementTime();	
		}
		l.run();
		
		l = new Level(LevelNum.LEVEL6);
		l.run();
		for (int i = 0; i < 190; i++) {
			l.incrementTime();	
		}
		l.incrementTime();
		l.run();
		for (int i = 0; i < 19000; i++) {
			l.incrementTime();	
		}
		l.run();
		
		l = new Level(LevelNum.LEVEL7);
		l.run();
		for (int i = 0; i < 190; i++) {
			l.incrementTime();	
		}
		l.incrementTime();
		l.run();
		for (int i = 0; i < 19000; i++) {
			l.incrementTime();	
		}
		l.run();
		
		l = new Level(LevelNum.LEVEL8);
		l.run();
		for (int i = 0; i < 190; i++) {
			l.incrementTime();	
		}
		l.incrementTime();
		l.run();
		for (int i = 0; i < 19000; i++) {
			l.incrementTime();	
		}
		l.run();
	
		l.getTime();
		l.manageSpawn();
		l.getSpawnBoss();
		l.getSpawnEnemy();
		l.getLevelTime();
		
	}
	
	/*******************************************************
	 * MyButton
	 *******************************************************/
	
	/*******************************************************
	 * MyDialog
	 *******************************************************/
	/*******************************************************
	 * MyMenuButton
	 *******************************************************/
	/*******************************************************
	 * NachoFrame
	 *******************************************************/
	
	/*******************************************************
	 * NachoPanel
	 *******************************************************/
	@Test
	public void testNachoPanel1() {
		JFrame frame = new JFrame();
		Player p = new Player();
		NachoFrame f = new NachoFrame();
		NachoPanel panel = new NachoPanel(frame, p, f, LevelNum.LEVEL3);
	}
	
	
	/*******************************************************
	 * Player
	 *******************************************************/
	@Test
	public void testPlayer() {
		Player p = new Player();
		p.getXP();
		p.getFireRate();
		p.getClass();
		p.incrementXP();
		p.decrementXP();
		
		// test ranks...
		p.setXP(0);
		p.rankToString(p.getRank());
		p.setXP(751);
		p.rankToString(p.getRank());
		p.setXP(1001);
		p.rankToString(p.getRank());
		p.setXP(601);
		p.rankToString(p.getRank());
		p.setXP(501);
		p.rankToString(p.getRank());
		p.setXP(401);
		p.rankToString(p.getRank());
		p.setXP(301);
		p.rankToString(p.getRank());
		p.setXP(201);
		p.rankToString(p.getRank());
		p.setXP(101);
		p.rankToString(p.getRank());
		assertEquals(p.getRank(), Rank.LIMPNACHO);
	}
	
	/*******************************************************
	 * Projectile
	 *******************************************************/
	/*******************************************************
	 * Scaler
	 *******************************************************/
	/*******************************************************
	 * Ship
	 *******************************************************/
	/*******************************************************
	 * SpaceObject
	 *******************************************************/
	/*******************************************************
	 * TacoBoss
	 *******************************************************/
	
	
//	@Test
//	public void testTacoBoss() {
//		TacoBoss b = new TacoBoss(5);
//	}
//
//
//	@Test
//	public void testProjectiles_damage() {
//		int dm = 50;
//		p.setDMG(dm);
//		assertEquals(p.getDMG(), dm);
//	}
//
//
//	@Test
//	public void testProjectiles_positionX() {
//		int x = 5;
//		p.setX(x);
//		assertEquals(p.getX(), x);
//	}
//
//
//	@Test
//	public void testProjectiles_positionY() {
//		int y = 50;
//		p.setY(y);
//		assertEquals(p.getY(), y);
//	}
//
//
//	@Test
//	public void testProjectiles_height() {
//		int h = 50;
//		p.setHeight(h);
//		assertEquals(p.getHeight(), h);
//	}
//
//
//	@Test
//	public void testProjectiles_width() {
//		int w = 54;
//		p.setWidth(w);
//		assertEquals(p.getWidth(), w);
//	}
//
//
//	@Test
//	public void testProjectiles_velocity() {
//		assertEquals(p.getpVel(), 5);
//	}
//
//
//	@Test
//	public void testSpaceObject_doLogic() {
//		space.doLogic();
//	}
//
//
//	@Test
//	public void testSpaceObject_health() {
//		space.setHealth(5);
//		assertEquals(space.getHealth(), 5);
//	}
//
//
//	@Test
//	public void testSpaceObject_x() {
//		space.setX(5);
//		assertEquals(space.getX(), 5);
//	}
//
//
//	@Test
//	public void testSpaceObject_y() {
//		space.setY(5);
//		assertEquals(space.getY(), 5);
//	}
//
//
//	@Test
//	public void testSpaceObject_maxX() {
//		int j = Scaler.width - space.getWidth();
//		assertEquals(space.getMaxX(), j);
//	}
//
//
//	@Test
//	public void testSpaceObject_Image() {
//		space.setImage("Art/Nasty_Nacho_Lime.png");
//		assertNotEquals(space.getImage(), null);
//	}
//
//
//	@Test
//	public void testSpaceObject_move() {
//		space.move();
//	}
//
//
//	// This test failed because we assigned them wrong!
//	@Test
//	public void testSpaceObject_velocityY() {
//		space.setVelY(5);
//		assertEquals(space.getVelY(), 5);
//	}
//
//
//	@Test
//	public void testSpaceObject_velocityX() {
//		space.setVelX(5);
//		assertEquals(space.getVelx(), 5);
//	}
//
//
//	@Test
//	public void testSpaceObject_height() {
//		assertNotEquals(space.getHeight(), -5);
//	}
//
//
//	@Test
//	public void testSpaceObject_middle() {
//		assertNotEquals(space.getMiddle(), 0);
//	}
//
//
//	@Test(expected = IllegalArgumentException.class)
//	public void testScaler() {
//		Scaler.calcDimensions(-9.2, 0.54);
//		Scaler.calcDimensions(9.2, 2.54);
//		Scaler.calcDimensions(9.2, 1.0);
//	}
//
//
//	@Test
//	public void testTable() {
//		Table t = new Table("hello", 4, 5);
//		t.doLogic();
//	}
//
//
//	@Test
//	public void testBackground() {
//		BackGround g = new BackGround();
//		assertNotEquals(g.getBack(), null);
//	}
//
//
//	@Test
//	public void testEnemy_Counting() {
//		e.setCounting(12);
//		assertEquals(e.getCounting(), 12);
//	}
//
//
//	@Test
//	public void testEnemy_Destroyed() {
//		e.setDestroyed(true);
//		assertTrue(e.getDestroyed());
//	}
//
//
//	@Test
//	public void testEnemy_detectDestroyed() {
//		e.doLogic();
//
//		e.setHealth(5);
//		e.doLogic();
//	}
//
//
//	@Test
//	public void testEnemy_collide() {
//		e.collide(new Projectile(10, 10, 20, 20));
//	}
//
//
//	@Test
//	public void testEntity() {
//		Table t = new Table("Art/tacosalad.png", 10, 10);
//		Entity e = (Entity) t;
//		e.setX(5);
//		assertEquals(e.getX(), 5);
//		e.setY(4);
//		assertEquals(e.getX(), 4);
//		e.setHealth(4);
//		assertEquals(e.getHealth(), 4);
//		e.setImage("Art/tacosalad.png");
//		assertNotEquals(e.getImage(), null);
//
//	}
//
//
//	@Test
//	public void testLogic_background() {
//		Logic l = new Logic();
//		assertNotEquals(l.getBackGround(), null);
//	}
//
//
//	@Test
//	public void testLogic_moveProjectiles() {
//		Logic l = new Logic();
//		l.moveProjectiles();
//	}
//
//
//	@Test
//	public void testLogic_stopShip() {
//		Logic l = new Logic();
//		l.stopShip();
//	}
//
//
//	@Test
//	public void testLogic_drive() {
//		Logic l = new Logic();
//		l.driveRight();
//		l.driveRight();
//	}
//
//
//	@Test
//	public void testLogic_getShip() {
//		Logic l = new Logic();
//		l.setShip(new Ship());
//		assertNotEquals(l.getShip(), null);
//	}
//
//
//	@Test
//	public void testLogic_getStuff() {
//		Logic l = new Logic();
//		assertNotEquals(l.getBoss(), null);
//		assertNotEquals(l.getEnemies(), null);
//		assertNotEquals(l.getProjectiles(), null);
//		assertNotEquals(l.getTables(), null);
//		assertNotEquals(l.getPissedOffTables(), null);
//		assertNotEquals(l.getSpentProjectiles(), null);
//		assertNotEquals(l.getDeadEnemies(), null);
//	}
//
//
//	@Test
//	public void testLogic_moveShip() {
//		Logic l = new Logic();
//		l.moveShip();
//	}
//
//
//	@Test
//	public void testLogic_moveEnemies() {
//		Logic l = new Logic();
//		l.moveEnemies();
//	}
//
//
//	@Test
//	public void testLogic_checkPosition() {
//		Logic l = new Logic();
//		l.checkShipPosition();
//	}
//
//
//	@Test
//	public void testLogic_detectCollisions() {
//		Logic l = new Logic();
//		l.detectCollisions();
//	}
//
//
//	@Test
//	public void testLogic_spawnEnemies() {
//		Logic l = new Logic();
//		l.spawnEnemy(10);
//		l.spawnBoss();
//	}
//
//
//	@Test
//	public void testNachoPanel_main() {
//		NachoPanel.main(null);
//	}
//
//
//	@Test
//	public void testNachoPanel_testKeys() {
//		NachoPanel n = new NachoPanel();
//
//		// left right escape enter dummy
//		KeyEvent k1 = new KeyEvent(n, 0, 0, 3, KeyEvent.VK_ESCAPE,
//				'\u0048');
//		KeyEvent k2 = new KeyEvent(n, 0, 0, 3, KeyEvent.VK_SPACE, ' ');
//		KeyEvent k3
//		= new KeyEvent(n, 0, 0, 3, KeyEvent.VK_LEFT, '\u2190');
//		KeyEvent k4
//		= new KeyEvent(n, 0, 0, 3, KeyEvent.VK_RIGHT, '\u2192');
//		KeyEvent k5
//		= new KeyEvent(n, 0, 0, 3, KeyEvent.VK_ENTER, '\u2386');
//		KeyEvent k6 = new KeyEvent(n, 0, 0, 3, KeyEvent.VK_Q, '?');
//		n.keyPressed(k1);
//		n.keyPressed(k2);
//		n.keyPressed(k3);
//		n.keyPressed(k4);
//		n.keyPressed(k5);
//		n.keyPressed(k6);
//		n.keyReleased(k1);
//		n.keyReleased(k2);
//		n.keyReleased(k3);
//		n.keyReleased(k4);
//		n.keyReleased(k5);
//		n.keyReleased(k6);
//		n.keyTyped(k1);
//		n.keyTyped(k2);
//		n.keyTyped(k3);
//		n.keyTyped(k4);
//		n.keyTyped(k5);
//		n.keyTyped(k6);
//	}
//}

}
