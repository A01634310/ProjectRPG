import java.io.Serializable;

public class Medico extends Heroe{

	private Habilidad[] habs;

	public Medico(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def);
	}
}