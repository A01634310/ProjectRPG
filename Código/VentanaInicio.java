import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class VentanaInicio extends JFrame{

	protected JPanelImage pVentInicio;
	protected JButton bNewPartida, bLoadPartida;

	public VentanaInicio(){
		super("Videojuego");
		initComponents();
	}

	public void initComponents(){
		pVentInicio = new JPanelImage("/img/home_wallpaper.jpg");
		//pVentInicio.setLayout(new GridLayout(5,5));
		pVentInicio.setLayout(new FlowLayout(0,0,0));
		bNewPartida = new JButton("Nueva Partida");
		bNewPartida.setPreferredSize(new Dimension(320,128));
		bLoadPartida = new JButton("Cargar Partida");
		bLoadPartida.setPreferredSize(new Dimension(320,128));
		bNewPartida.addActionListener(new bNewPartidaListener());
		bLoadPartida.addActionListener(new bLoadPartidaListener());
		JPanel pEspacioVacio = new JPanel();
		pEspacioVacio.setBackground(new Color(0,0,0,0));
		pEspacioVacio.setPreferredSize(new Dimension(768,512));
		pVentInicio.add(pEspacioVacio);
		pVentInicio.add(bNewPartida);
		JPanel pEspacioVacioDos = new JPanel();
		pEspacioVacioDos.setBackground(new Color(0,0,0,0));
		pEspacioVacioDos.setPreferredSize(new Dimension(64,64));
		pVentInicio.add(pEspacioVacioDos);
		pVentInicio.add(bLoadPartida);
		revalidate();
		repaint();
	}

	public JPanel getVentInicio(){ return pVentInicio;
	}

	public abstract void createPartida();

	public abstract void cargarPartida();

	public class bNewPartidaListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			createPartida();
		}
	}

	public class bLoadPartidaListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			cargarPartida();
		}
	}
	
}