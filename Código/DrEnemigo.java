import javax.swing.*;
import java.io.Serializable;

public class DrEnemigo extends Enemigo{
	
	public DrEnemigo(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def, 15);
		setImagen(new ImageIcon(Class.class.getResource("/img/drEnemigo.png")));
		setIcono(new ImageIcon(Class.class.getResource("/img/drEnemigo_mini.png")));
	}

	public DrEnemigo(String nombre, EnemigoSpawnData esd){
		super(nombre, esd.getMaxHP(), esd.getAtk(), esd.getDef(), esd.geteExp());
		setImagen(new ImageIcon(Class.class.getResource("/img/drEnemigo.png")));
		setIcono(new ImageIcon(Class.class.getResource("/img/drEnemigo_mini.png")));
		setBattleImg(new ImageIcon(Class.class.getResource("/img/ciee_background.jpg")));
	}
}