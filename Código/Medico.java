import javax.swing.*;
import java.io.Serializable;

public class Medico extends Heroe{

	private Habilidad[] habs;

	public Medico(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def, 60);
		setImagen(new ImageIcon(Class.class.getResource("/img/medico.png")));
		setIcono(new ImageIcon(Class.class.getResource("/img/medico_mini.png")));
	}

	public void levelUp(){
		this.lvl++;
		this.exp = lvl*15 + 30;
		setMaxHP(getMaxHP()+50);
		setAtk(getAtk()+2);
		setDef(getDef()+5);
	}
}