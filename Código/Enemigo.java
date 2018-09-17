import javax.swing.*;
import java.io.Serializable;

public abstract class Enemigo extends Personaje{
	
	private int coordX, coordY;
	private int eExp;
	private ImageIcon battleImg;

	public Enemigo(String nombre, int maxHP, int atk, int def, int enemyExp){
		super(nombre, maxHP, atk, def);
		this.eExp = enemyExp;
	}

	public int getCoordX(){ return coordX;
	}

	public int getCoordY(){ return coordY;
	}

	public void setCoords(int coordX, int coordY){
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public int geteExp(){ return eExp;
	}

	public void seteExp(int e){ this.eExp = e;
	}

	public ImageIcon getBattleImg(){ return battleImg;
	}

	public void setBattleImg(ImageIcon imgIc){
		this.battleImg = imgIc; 
	}
}