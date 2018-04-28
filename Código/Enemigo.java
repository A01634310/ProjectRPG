import java.io.Serializable;

public abstract class Enemigo extends Personaje{
	
	private int coordX, coordY;

	public Enemigo(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def);
	}

	public int getCoordX(){ return coordX;
	}

	public int getCoordY(){ return coordY;
	}

	public void setCoords(int coordX, int coordY){
		this.coordX = coordX;
		this.coordY = coordY;
	}
}