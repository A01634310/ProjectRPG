import java.io.Serializable;

public abstract class Heroe extends Personaje{

	private Habilidad[] habs;
	protected int exp;
	protected int lvl;

	public Heroe(String nombre, int maxHP, int atk, int def, int exp){
		super(nombre, maxHP, atk, def);
		habs = new Habilidad[3];
		this.exp = exp;
		this.lvl = 1;
		initHabs();
	}

	public Habilidad[] getHabs(){ return habs;
	}

	public Habilidad getHab(int index){ return habs[index];
	}

	public void initHabs(){
		for (int i=0; i<habs.length; i++) { habs[i]=null;
		}
	}

	public void addHab(Habilidad h){
		for (int i=0; i<habs.length; i++) {
			if (habs[i]==null) { habs[i]=h; break;
			}
		}
	}

	public int getExp(){ return exp;
	}

	public void setExp(int exp){ this.exp = exp;
	}

	public void addExp(int exp){
		this.exp = this.exp-exp;
		if (this.exp<=0) { levelUp();
		}
	}

	public int getLvl(){ return lvl;
	}

	public void setLvl(int lvl){ this.lvl = lvl;
	}

	public abstract void levelUp();
}