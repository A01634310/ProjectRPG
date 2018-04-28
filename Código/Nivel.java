import java.io.Serializable;

public class Nivel implements Serializable{

	private int longCasillas, altCasillas;
	private String nombreNivel;

	private Equipo equipo;

	private Item[] items = new Item[10];
	private Enemigo[] enemigos = new Enemigo[10];
	private FinalBoss finalBoss;

	public Nivel(String nombreNivel, int longCasillas, int altCasillas, Equipo equipo){
		this.longCasillas = longCasillas;
		this.altCasillas = altCasillas;
		this.equipo = equipo;
		this.nombreNivel = nombreNivel;
		initElementos();
	}

	public Nivel(String nombreNivel, int longCasillas, int altCasillas,
		Equipo equipo, Item[] items, Enemigo[] enemigos, FinalBoss finalBoss){
		this.nombreNivel = nombreNivel;
		this.longCasillas = longCasillas;
		this.altCasillas = altCasillas;
		this.equipo = equipo;
		this.items = items;
		this.enemigos = enemigos;
		this.finalBoss = finalBoss;
	}

	public void initElementos(){
		for (int i=0; i<items.length; i++) { items[i]=null;
		}
		for (int i=0; i<enemigos.length; i++) { enemigos[i]=null;
		}
	}

	public int getLongCasillas(){ return longCasillas;
	}

	public int getAltCasillas(){ return altCasillas;
	}

	public String getNombreNivel(){ return nombreNivel;
	}

	public Equipo getEquipo(){ return equipo;
	}

	public void setEquipo(Equipo e){ this.equipo = e;
	}

	public Item[] getItems(){ return items;
	}

	public Enemigo[] getEnemigos(){ return enemigos;
	}

	public FinalBoss getFinalBoss(){ return finalBoss;
	}

	public void setEquipoCoords(int coordX, int coordY){ equipo.setCoords(coordX, coordY);
	}

	public void addEnemigo(Enemigo enemigo, int coordX, int coordY){
		for (int i=0; i<enemigos.length; i++) {
			if (enemigos[i]==null) {
				enemigos[i] = enemigo;
				enemigos[i].setCoords(coordX, coordY);
				break;
			}
		}
	}

	public void addItem(Item item, int coordX, int coordY){
		for (int i=0; i<items.length; i++) {
			if (items[i] == null) {
				items[i] = item;
				items[i].setCoords(coordX,coordY);
				break;
			}
		}
	}

	public void addFinalBoss(FinalBoss finalBoss, int coordX, int coordY){
		this.finalBoss = finalBoss;
		finalBoss.setFBCoords(coordX, coordY);
	}
}