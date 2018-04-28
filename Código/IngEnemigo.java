import java.io.Serializable;

public class IngEnemigo extends Enemigo{
	
	private int coordX, coordY;

	public IngEnemigo(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def);
	}
}