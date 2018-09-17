import java.io.Serializable;
import javax.swing.*;

public abstract class Personaje extends JFrame implements Serializable{
	
	private int maxHP, hp, atk, def;
	private String nombre;

	private ImageIcon imagen;
	private ImageIcon icono;

	public Personaje(String nombre, int maxHP, int atk, int def){
		this.maxHP = maxHP;
		this.hp = maxHP;
		this.atk = atk;
		this.def = def;
		this.nombre = nombre;
	}

	public String getNombre(){ return nombre;
	}

	public void setNombre(String nombre){ this.nombre = nombre;
	}

	public int getHP(){ return hp;
	}

	public void setHP(int hp){ this.hp = hp;
	}

	public int getMaxHP(){ return maxHP;
	}

	public void setMaxHP(int maxHP){ this.maxHP = maxHP;
	}

	public int getAtk(){ return atk;
	}

	public void setAtk(int atk){ this.atk = atk;
	}

	public int getDef(){ return def;
	}

	public void setDef(int def){ this.def = def;
	}

	public ImageIcon getImagen(){ return imagen;
	}

	public void setImagen(ImageIcon i){ this.imagen = i;
	}

	public ImageIcon getIcono(){ return icono;
	}

	public void setIcono(ImageIcon i){ this.icono = i;
	}
	
}