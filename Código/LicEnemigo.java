import java.io.Serializable;

public class LicEnemigo extends Enemigo{
	
	private int coordX, coordY;

	public LicEnemigo(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def);
	}
}