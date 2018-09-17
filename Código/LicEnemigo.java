import javax.swing.*;
import java.io.Serializable;

public class LicEnemigo extends Enemigo{

	public LicEnemigo(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def, 15);
		setImagen(new ImageIcon(Class.class.getResource("/img/licEnemigo.png")));
		setIcono(new ImageIcon(Class.class.getResource("/img/licEnemigo_mini.png")));
	}

	public LicEnemigo(String nombre, EnemigoSpawnData esd){
		super(nombre, esd.getMaxHP(), esd.getAtk(), esd.getDef(), esd.geteExp());
		setImagen(new ImageIcon(Class.class.getResource("/img/licEnemigo.png")));
		setIcono(new ImageIcon(Class.class.getResource("/img/licEnemigo_mini.png")));
		setBattleImg(new ImageIcon(Class.class.getResource("/img/licEnemigo_background.jpg")));
	}
}