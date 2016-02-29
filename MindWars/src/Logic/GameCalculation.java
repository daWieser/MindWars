package Logic;

import Entity.Character;

public class GameCalculation implements Runnable{
	private Character p1;
	private boolean flag;
	public GameCalculation (){
		p1 = new Character(new Vector (5,0));
		flag = true;
		Thread t = new Thread(this);
		t.start();
		
	}
	@Override
	public void run() {
		while (flag){
			Character p1t = new Character(p1.getPosition().add(p1.getMovement()));
			
		}		
	}

}
