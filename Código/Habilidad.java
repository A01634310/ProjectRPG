import java.io.Serializable;

public abstract class Habilidad implements Serializable{
	
	private String habNombre;
	private int habFuerza;

	public Habilidad(String habNombre, int habFuerza){
		this.habNombre = habNombre;
		this.habFuerza = habFuerza;
	}

	public String getHabNombre(){ return habNombre;
	}

	public void setHabNombre(String habNombre){ this.habNombre = habNombre;
	}

	public int getHabFuerza(){ return habFuerza;
	}

	public void setHabFuerza(int habFuerza){ this.habFuerza = habFuerza;
	}
}