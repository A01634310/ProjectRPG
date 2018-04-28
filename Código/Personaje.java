import java.io.Serializable;

public abstract class Personaje implements Serializable{
	
	private int maxHP, hp, atk, def;
	private String nombre;

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

	public int getHp(){ return hp;
	}

	public void setHp(int hp){ this.hp = hp;
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
	
}