import javax.swing.*;
import java.io.Serializable;

public class FinalBoss extends Enemigo{
	
	private Pregunta[] preguntas = new Pregunta[5];

	public FinalBoss(String nombre, int maxHP, int atk, int def, int enemyExp){
		super(nombre, maxHP, atk, def, enemyExp);
		//this.setTipoEnemigo("Boss");
		initPreguntas();
		setImagen(new ImageIcon(Class.class.getResource("/img/finalBoss.png")));
		setBattleImg(new ImageIcon(Class.class.getResource("/img/finalBoss_battle.jpg")));
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
		for (int i=0; i<preguntas.length; i++) {
			if (preguntas[i]==null) {
				preguntas[i]=pregunta;
				break;
			}
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