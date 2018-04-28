import java.io.Serializable;

public abstract class Heroe extends Personaje{

	private Habilidad[] habs;

	public Heroe(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def);
		habs = new Habilidad[3];
		initHabs();
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
}