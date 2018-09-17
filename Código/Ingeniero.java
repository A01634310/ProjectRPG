import javax.swing.*;
import java.io.Serializable;

public class Ingeniero extends Heroe{

	public Ingeniero(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def, 40);
		setImagen(new ImageIcon(Class.class.getResource("/img/ingeniero.png")));
		setIcono(new ImageIcon(Class.class.getResource("/img/ingeniero_mini.png")));
	}

	public void levelUp(){
		this.lvl++;
		this.exp = lvl*15 + 40;
		setMaxHP(getMaxHP()+20);
		setAtk(getAtk()+6);
		setDef(getDef()+2);
	}
}