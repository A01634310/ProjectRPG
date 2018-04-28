import java.io.Serializable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pregunta extends JFrame{
	
	private String pregunta;
	private int numOpciones = 3;
	private int respuesta; // 0 - (numOpciones-1)
	private String[] opciones = new String[numOpciones];

	public Pregunta(String pregunta, int respuesta, String opcionA, String opcionB, String opcionC){
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.opciones[0] = opcionA;
		this.opciones[1] = opcionB;
		this.opciones[2] = opcionC;
	}



	public String getPregunta(){ return pregunta;
	}

	public void setPregunta(String pregunta){ this.pregunta = pregunta;
	}

	public int getRespuesta(){ return respuesta;
	}

	public void setRespuesta(int respuesta){ this.respuesta = respuesta;
	}

	public String getOpcion(int index){ return opciones[index];
	}

	public void setOpcion(int index, String opcion){ this.opciones[index] = opcion;
	}

	public int getNumOpciones(){ return numOpciones;
	}
}