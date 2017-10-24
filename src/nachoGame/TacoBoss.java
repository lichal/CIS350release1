package nachoGame;

import java.awt.Image;

public class TacoBoss extends Enemy {
	
	private int velY = 1;

	public TacoBoss(int health) {
		super(health);
		refactor(0.75, 0.10, "Art/tacosalad.png");
		destroyed = false;
	}
}
