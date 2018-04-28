import java.io.Serializable;

public class FinalBoss extends Enemigo{
	
	private Pregunta[] preguntas = new Pregunta[5];

	public FinalBoss(String nombre, int maxHP, int atk, int def){
		super(nombre, maxHP, atk, def);
		//this.setTipoEnemigo("Boss");
		initPreguntas();
	}

	public void preguntar(){

	}

	public void setFBCoords(int coordX, int coordY){ setCoords(coordX, coordY);
	}

	public Pregunta[] getPreguntas(){ return preguntas;
	}

	public Pregunta getPregunta(int index){ return preguntas[index];
	}

	public void addPregunta(Pregunta pregunta){
		if (preguntas[0]==null) { preguntas[0] = pregunta;
		}
		else if (preguntas[1]==null) { preguntas[1] = pregunta;
		}
		else if (preguntas[2]==null) { preguntas[2] = pregunta;
		}
		else if (preguntas[3]==null) { preguntas[3] = pregunta;
		}
		else if (preguntas[4]==null) { preguntas[4] = pregunta;
		}
	}

	public void initPreguntas(){
		preguntas[0] = null;
		preguntas[1] = null;
		preguntas[2] = null;
		preguntas[3] = null;
		preguntas[4] = null;
	}
}