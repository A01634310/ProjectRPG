import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.Serializable;

public class Ventana extends JFrame implements Serializable{
	
	private JPanel pVentana;

	private VentMapa vMapa;
	private VentBatalla vBatalla;
	private VentInicio vInicio;
	private VentGameOver vGameOver;
	private VentBatallaBoss vBatallaBoss;

	private Nivel nivel;
	private Equipo equipo;
	private Partida partida;

	public Ventana(){
		super("Juego");
		pVentana = new JPanel();
		displayVentInicio();
		setSize(768,768);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	//Abrir la ventana de selección de archivo
	public void displayVentInicio(){
		getContentPane().removeAll();
		vInicio = new VentInicio();
		add(vInicio.getVentInicio());
		revalidate();
		repaint();
	}

	public Ventana(Nivel nivel){
		super(nivel.getNombreNivel());
		this.nivel = nivel;
		this.equipo = nivel.getEquipo();
		pVentana = new JPanel();
		displayVentMapa();
		setSize(768,768);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	//Abrir la ventana del Mapa
	public void displayVentMapa(){
		getContentPane().removeAll();
		vMapa = new VentMapa(nivel);
		add(vMapa.getVentana());
		revalidate();
		repaint();
	}

	//Abrir la ventana de la Batalla
	public void displayVentBatalla(Batalla batalla){
		getContentPane().removeAll();
		vBatalla = new VentBatalla(batalla);
		add(vBatalla.getVentana());
		revalidate();
		repaint();
	}

	//Mostrar GameOver
	public void displayVentGameOver(){
		getContentPane().removeAll();
		vGameOver = new VentGameOver();
		add(vGameOver.getVentGameOver());
		revalidate();
		repaint();
	}

	public void displayVentWin(){
		getContentPane().removeAll();
		vGameOver = new VentGameOver();
		add(vGameOver.getVentGameOver());
		revalidate();
		repaint();
	}

	public void displayBatallaBoss(Batalla batalla){
		getContentPane().removeAll();
		vBatallaBoss = new VentBatallaBoss(batalla);
		add(vBatallaBoss.getVentana());
		revalidate();
		repaint();
	}

	public void saveFile(){
		partida.refreshPartida(nivel);
		try{
			JOptionPane joPane = new JOptionPane();
			String nombreArchivo = joPane.showInputDialog("¿Como llamaras a tu archivo?");
			FileOutputStream filOutStr = new FileOutputStream(nombreArchivo);
			ObjectOutputStream objOutStr = new ObjectOutputStream(filOutStr);
			partida.setNivel(nivel,0);
			objOutStr.writeObject(partida);
			objOutStr.close();
		}catch(FileNotFoundException fnfEx){
			fnfEx.printStackTrace();
		}catch(IOException ioEx){
			ioEx.printStackTrace();
		}
	}




	public class VentInicio extends VentanaInicio{

		public void createPartida(){
			partida = new Partida();
			nivel = partida.getNivel(0);
			System.out.println("Nueva partida");
			displayVentMapa();
		}

		public void cargarPartida(){
			JFileChooser fchooser = new JFileChooser("C:\\Users\\Diego\\OneDrive - Instituto Tecnologico y de Estudios Superiores de Monterrey\\ITESM Segundo Semestre\\TC2016 Programación Orientada a Objetos\\Proyecto Semestral RPG\\Código\\SaveFiles");
			fchooser.showOpenDialog(null);
			try{
				File savedFile = fchooser.getSelectedFile();
				FileInputStream filInpStr = new FileInputStream(savedFile);
				ObjectInputStream objInpStr = new ObjectInputStream(filInpStr);
				partida = (Partida)objInpStr.readObject();
				nivel = partida.getNivel(0);
				System.out.println("Cargamos partida");
				displayVentMapa();
			}/*catch(NullPointerException npEx){
				partida = new Partida();
				nivel = partida.getNivel(0);
				System.out.println("Nueva partida");
				displayVentMapa();
			}*/catch(FileNotFoundException fnfEx){
				partida = new Partida();
				nivel = partida.getNivel(0);
				System.out.println("Nueva partida");
				displayVentMapa();
			}catch(IOException ioEx){
				ioEx.printStackTrace();
			}catch(ClassNotFoundException cnfEx){
				cnfEx.printStackTrace();
			}
		}
	}



	public class VentMapa extends VentanaMapa{

		public VentMapa(Nivel nivel){
			super(nivel);
		}

		public void checkEnemigos(){
			for (int i=0; i<enemigos.length && enemigos[i]!=null; i++) {
				if (equipo.getCoordX()==enemigos[i].getCoordX()&&equipo.getCoordY()==enemigos[i].getCoordY()) {
					System.out.println("Nos encontramos un enemigo");
					enemigos[i].setCoords(-1,-1);
					displayVentBatalla(new Batalla(equipo, enemigos[i]));
					break;
				}
			}
		}

		public void invokeFinalBoss(){
			displayBatallaBoss(new Batalla(equipo,finalBoss));
		}

		public void guardarArchivo(){ saveFile();
		}
	}



	public class VentBatalla extends VentanaBatalla{
	
		public VentBatalla(Batalla batalla){
			super(batalla);
		}

		public void pasarTurno(){
			//Venimos de un movimiento del personaje
			turnoEnemigo = true;
			System.out.println();
			System.out.println("Turno nuestro:");
			refreshVentBatalla();
			if (enemigo.getHP()>0) {
				try{ Thread.sleep(2500);
				}catch(InterruptedException e){}
				System.out.println();
				System.out.println("Turno enemigo: ");
				ataqueEnemigo();
				turnoEnemigo = false;
				if (lic.getHP()>0||dr.getHP()>0||ing.getHP()>0) {
					/*
					if (turno==2) { turno = 0;
					} else{ turno++;
					}*/
					switch(turno){
						case 0:
						if (dr.getHP()>0) { turno=1;
						} else if(ing.getHP()>0){ turno = 2;
						} else{ turno = 0;
						}
						break;
						case 1:
						if (ing.getHP()>0) { turno=2;
						} else if(lic.getHP()>0){ turno = 0;
						} else{ turno = 1;
						}
						break;
						case 2:
						if (lic.getHP()>0) { turno=0;
						} else if(dr.getHP()>0){ turno = 1;
						} else{ turno = 2;
						}
						break;
					}
					refreshVentBatalla();
				}else{
					displayVentGameOver();
					System.out.println("GAME OVER");
				}
			}
			else{
				System.out.println("La batalla ha terminado");
				lic.addExp(enemigo.geteExp());
				dr.addExp(enemigo.geteExp());
				ing.addExp(enemigo.geteExp());
				equipo.librarBatalla();
				equipo.setHeroes(lic,dr,ing);
				nivel.setEquipo(equipo);
				displayVentMapa();
			}
		}
	}



	public class VentGameOver extends VentanaGameOver{
		public VentGameOver(){
			initComponents();
		}
	}



	public class VentWin extends VentanaWin{
		public VentWin(){
			initComponents();
		}
	}



	public class VentBatallaBoss extends VentanaBoss{

		public VentBatallaBoss(Batalla b){
			super(b);
			getContentPane().removeAll();
		}

		public void pasarTurno(){
			//Venimos de un movimiento del personaje
			turnoEnemigo = true;
			System.out.println();
			System.out.println("Turno nuestro:");
			refreshVentBatalla();
			if (enemigo.getHP()>0) {
				checkPregunta();
				try{ Thread.sleep(2500);
				}catch(InterruptedException e){}
				System.out.println();
				System.out.println("Turno enemigo: ");
				ataqueEnemigo();
				turnoEnemigo = false;
				if (lic.getHP()>0||dr.getHP()>0||ing.getHP()>0) {
					if (turno==2) { turno = 0;
					} else{ turno++;
					}
					refreshVentBatalla();
				}else{
					displayVentGameOver();
					System.out.println("GAME OVER");
				}
			}
			else{
				System.out.println("La batalla ha terminado");
				equipo.setHeroes(lic,dr,ing);
				nivel.setEquipo(equipo);
				displayVentMapa();
			}
		}
	}
}