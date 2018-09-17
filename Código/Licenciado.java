import javax.swing.*;
import java.io.Serializable;

public class Licenciado extends Heroe{

	public Licenciado(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def, 30);
		setImagen(new ImageIcon(Class.class.getResource("/img/licenciado.png")));
		setIcono(new ImageIcon(Class.class.getResource("/img/licenciado_mini.png")));
	}

	public void levelUp(){
		this.lvl++;
		this.exp = lvl*10 + 30;
		setMaxHP(getMaxHP()+30);
		setAtk(getAtk()+3);
		setDef(getDef()+4);
	}
}