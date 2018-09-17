import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class VentanaBoss extends VentanaBatalla{

	protected FinalBoss finalBoss;
	protected Pregunta pregunta;
	protected JPanel pBotones, pPreguntas;
	protected JButton[] bOpciones;

	protected int contPreguntas;
	protected boolean respuestaIncorrecta = false;

	public VentanaBoss(Batalla b){
		super(b);
	}

	/*public void initComponents(){
		pVentBatalla = new JPanelImage("/img/inicio_wallpaper.jpg");
		pVentBatalla.setLayout(new GridLayout(3,2));
		this.contPreguntas = 0;
		this.bOpciones = new JButton[3];
		finalBoss = (FinalBoss)enemigo;
		pregunta = finalBoss.getPregunta(0);
		initbItems();
		initbAtacar();
		initbHeroes();
		initpStats();
		initbOpciones();
		narrador = new JLabel();
		refreshVentBatalla();
	}*/

	public void initbOpciones(){
		bOpciones[0] = new JButton(pregunta.getOpcion(0));
		bOpciones[0].addActionListener(new bOpcionListener());
		bOpciones[1] = new JButton(pregunta.getOpcion(1));
		bOpciones[1].addActionListener(new bOpcionListener());
		bOpciones[2] = new JButton(pregunta.getOpcion(2));
		bOpciones[2].addActionListener(new bOpcionListener());
	}

	public void refreshVentBatalla(Pregunta p){
		pVentBatalla.removeAll();
		refreshpPersonajes();
		refreshpAtacar();
		refreshpStats();
		refreshpItems();
		refreshpNarrador();
		refreshpPreguntas(p);
		revalidate();
		repaint();
	}

	public void refreshpPreguntas(Pregunta p){
		pPreguntas = new JPanel();
		pPreguntas.setLayout(new GridLayout(2,1));
		pPreguntas.add(new JLabel(p.getPregunta()));
		pBotones = new JPanel();
		pBotones.setLayout(new GridLayout(1,p.getNumOpciones()));
		initbOpciones();
		for (int i=0; i<p.getNumOpciones(); i++) {
			pBotones.add(bOpciones[i]);
		}
		pPreguntas.add(pBotones);
		pVentBatalla.add(pPreguntas);
	}

	public void habEnemiga(Heroe h){
		/*
		if (contPreguntas<finalBoss.getPreguntas().length) {
			pregunta = finalBoss.getPregunta(contPreguntas);
			System.out.println("UNA PREGUNTA!!");
			refreshVentBatalla(pregunta);
			new Thread() {
				public void run() {

				}
			}.start();
			//detener thread
			contPreguntas++;
		}*/
		int recuperacion = 40;
		finalBoss.setHP(finalBoss.getHP()+recuperacion);
		narracion = (finalBoss.getNombre()+" recupero "+recuperacion+" de HP!!");
	}

	public void checkPregunta(){
		if (respuestaIncorrecta==true) {
			finalBoss.setHP(finalBoss.getHP()+50);
		}
	}

	public abstract void pasarTurno();

	public class bOpcionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton bOpcion = (JButton)e.getSource();
			for (int i=0; i<bOpciones.length; i++) {
				if (bOpcion==bOpciones[i]) {
					if (pregunta.getRespuesta()==i) {
						respuestaIncorrecta=false;
					}
					else{ respuestaIncorrecta=true;
					}
				}
			}
		}
	}
}