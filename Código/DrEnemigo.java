import java.io.Serializable;

public class DrEnemigo extends Enemigo{
	
	private int coordX, coordY;

	public DrEnemigo(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def);
	}
}