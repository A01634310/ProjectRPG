import java.io.Serializable;

public class Batalla implements Serializable{

	private Equipo equipo;
	private Enemigo enemigo;
	
	public Batalla(Equipo equipo, Enemigo enemigo){
		this.equipo = equipo;
		this.enemigo = enemigo;
	}

	public Batalla(Equipo e){ this.equipo = e;
	}

	public Equipo getEquipo(){ return equipo;
	}

	public void setEquipo(Equipo e){ this.equipo = e;
	}

	public Enemigo getEnemigo(){ return enemigo;
	}

	public void setEnemigo(Enemigo e){ this.enemigo = e;
	}
}