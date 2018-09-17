import javax.swing.*;
import java.io.Serializable;

public class IngEnemigo extends Enemigo{

	public IngEnemigo(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def, 15);
		setImagen(new ImageIcon(Class.class.getResource("/img/ingEnemigo.png")));
		setIcono(new ImageIcon(Class.class.getResource("/img/ingEnemigo_mini.png")));
	}

	public IngEnemigo(String nombre, EnemigoSpawnData esd){
		super(nombre, esd.getMaxHP(), esd.getAtk(), esd.getDef(), esd.geteExp());
		setImagen(new ImageIcon(Class.class.getResource("/img/ingEnemigo.png")));
		setIcono(new ImageIcon(Class.class.getResource("/img/ingEnemigo_mini.png")));
		setBattleImg(new ImageIcon(Class.class.getResource("/img/cedetec_battle.jpg")));
	}
}