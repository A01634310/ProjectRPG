import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class VentanaMapa extends JFrame{

	protected int longCasillas;
	protected int altCasillas;
	protected String nombreNivel;
	protected String mapaImgURL;
	protected Equipo equipo;
	protected Item[] items;
	protected Enemigo[] enemigos;
	protected FinalBoss finalBoss;
	protected Batalla batalla;

	protected JPanel pVentMapa;
	protected JPanel pMapa, pBotones, pStats;
	protected JPanel pStatsLic, pStatsDr, pStatsIng;
	protected JPanel[] pStatsHeroes;
	protected JButton bArriba, bAbajo, bIzquierda, bDerecha;
	protected JButton bSaveFile;

	protected ImageIcon icLic, icDr, icIng;
	protected ImageIcon icHP, icAtk, icDef, icExp;

	/*
	private ImageIcon iEquipo;
	private ImageIcon iLicEnemigo;
	private ImageIcon iDrEnemigo;
	private ImageIcon iIngEnemigo;
	*/

	public VentanaMapa(Nivel nivel){
		this.nombreNivel = nivel.getNombreNivel();
		this.longCasillas = nivel.getLongCasillas();
		this.altCasillas = nivel.getAltCasillas();
		this.equipo = nivel.getEquipo();
		this.items = nivel.getItems();
		this.finalBoss = nivel.getFinalBoss();
		this.enemigos = nivel.getEnemigos();
		this.mapaImgURL = nivel.getImgURL();
		initComponents();
	}

	public JPanel getVentana(){ return pVentMapa;
	}


	public void initComponents(){
		if (equipo.getBatallasLibradas()==enemigos.length) {
			invokeFinalBoss();
		}
		pVentMapa = new JPanel();
		icHP = new ImageIcon(Class.class.getResource("/img/icHP.png"));
		icAtk = new ImageIcon(Class.class.getResource("/img/icAtk.png"));
		icDef = new ImageIcon(Class.class.getResource("/img/icDef.png"));
		icExp = new ImageIcon(Class.class.getResource("/img/icExp.png"));
		//pVentMapa.setLayout(new GridLayout(2,2));
		pVentMapa.setLayout(new FlowLayout(0,0,0));
		//pVentMapa.addKeyListener(new kListener());
		//pVentMapa.setFocusable(true);
		//pVentMapa.setFocusTraversalKeysEnabled(false);
		initBotones();
		initpStats();
		bSaveFile = new JButton("Save File");
		bSaveFile.addActionListener(new bSaveFileListener());
		bSaveFile.setPreferredSize(new Dimension(512,256));
		//initImagenes();
		refreshVentana();
	}

	public void initBotones(){
		pBotones = new JPanel();
		pBotones.setPreferredSize(new Dimension(256,256));
		pBotones.setLayout(new GridLayout(3,3));
		bArriba = new JButton("Arriba");
		bArriba.addActionListener(new bMovimientoListener());
		bAbajo = new JButton("Abajo");
		bAbajo.addActionListener(new bMovimientoListener());
		bIzquierda = new JButton("Izquierda");
		bIzquierda.addActionListener(new bMovimientoListener());
		bDerecha = new JButton("Derecha");
		bDerecha.addActionListener(new bMovimientoListener());
	}

	public void initpStats(){
		pStats = new JPanel();
		pStats.setPreferredSize(new Dimension(232,512));
		pStats.setLayout(new GridLayout(3,1));

		pStatsHeroes = new JPanel[3];
		for (int i=0; i<pStatsHeroes.length; i++) {
			Heroe heroe = equipo.getHeroe(i);
			if (heroe==null) { System.out.println("Muy mal");
			}
			pStatsHeroes[i] = new JPanel();
			pStatsHeroes[i].setLayout(new GridLayout(7,2));
			pStatsHeroes[i].add(new JLabel());
			pStatsHeroes[i].add(new JLabel());
			pStatsHeroes[i].add(new JLabel(heroe.getIcono()));
			JLabel lHPHeroe = new JLabel("   HP: "+heroe.getHP()
				+" / "+heroe.getMaxHP(),icHP,0);
			if (heroe.getHP()==heroe.getMaxHP()) { lHPHeroe.setForeground(Color.blue);
			} else if(heroe.getHP()==0){ lHPHeroe.setForeground(Color.red);
			}
			pStatsHeroes[i].add(lHPHeroe);
			pStatsHeroes[i].add(new JLabel(heroe.getNombre()));
			pStatsHeroes[i].add(new JLabel("   Atk: "+heroe.getAtk(),icAtk,0));
			pStatsHeroes[i].add(new JLabel("Nivel: " + heroe.getLvl()));
			pStatsHeroes[i].add(new JLabel("   Def: "+heroe.getDef(),icDef,0));
			pStatsHeroes[i].add(new JLabel());
			pStatsHeroes[i].add(new JLabel());
			pStatsHeroes[i].add(new JLabel("Next LV: "+heroe.getExp(),icExp,0));
			pStatsHeroes[i].add(new JLabel());
			pStats.add(pStatsHeroes[i]);
		}
	}

	public void refreshVentana(){
		pVentMapa.removeAll();
		refreshMapa();
		pVentMapa.add(pStats);
		pVentMapa.add(bSaveFile);
		refreshBotones();
		revalidate();
		repaint();
	}

	public void refreshBotones(){
		pBotones = new JPanel();
		pBotones.setLayout(new GridLayout(3,3));
		pBotones.setPreferredSize(new Dimension(230,230));
		pBotones.add(new JLabel());
		pBotones.add(bArriba);
		pBotones.add(new JLabel());
		pBotones.add(bIzquierda);
		pBotones.add(new JLabel());
		pBotones.add(bDerecha);
		pBotones.add(new JLabel());
		pBotones.add(bAbajo);
		pBotones.add(new JLabel());
		pVentMapa.add(pBotones);
		pVentMapa.revalidate();
		pVentMapa.repaint();
	}

	public void refreshMapa(){
		pMapa = new JPanelImage(mapaImgURL);
		pMapa.setPreferredSize(new Dimension(512,512));
		pMapa.setLayout(new GridLayout(longCasillas,altCasillas));
		for (int i=0; i<longCasillas; i++) {
			for (int j=0; j<altCasillas; j++) {
				//Esto es lo que revisa por casilla
				if (i==equipo.getCoordY()&&j==equipo.getCoordX()) {
					pMapa.add(new JLabel(equipo.getIcono()));
				}
				else{
					boolean ocupado=false;
					for (int k=0; k<items.length && items[k]!=null && ocupado==false; k++){
						if (i==items[k].getCoordY() && j==items[k].getCoordX()){
							pMapa.add(new JLabel(new ImageIcon(Class.class.getResource("/img/item.png"))));
							ocupado=true;
							break;
						}
					}
					for (int k=0; k<enemigos.length && enemigos[k]!=null && ocupado==false; k++){
						if (i==enemigos[k].getCoordY() && j==enemigos[k].getCoordX()){
							pMapa.add(new JLabel(enemigos[k].getIcono()));
							ocupado=true;
							break;
						}
					}
					if (ocupado==false){ pMapa.add(new JLabel(""));
					}
				}
			}
		}
		//pMapa.setPreferredSize(new Dimension(640,640));
		//pMapa.setMinimumSize(new Dimension(640, 640));
		//pMapa.setMaximumSize(new Dimension(640, 640));
		pVentMapa.add(pMapa);
		pVentMapa.revalidate();
		pVentMapa.repaint();
	}

	public void checkItems(){
		for (int i=0; i<items.length && items[i]!=null; i++) {
			if (equipo.getCoordX()==items[i].getCoordX() && equipo.getCoordY()==items[i].getCoordY()) {
				System.out.println("Conseguiste el item "+items[i].getItemNombre());
				items[i].setCoords(-1,-1);
				equipo.addItem(items[i]);
				break;
			}
		}
	}

	public abstract void checkEnemigos();

	public void moveEquipo(String input){
		switch(input){
			case "up":
			if (equipo.getCoordY()!=0) {
				equipo.setCoords(equipo.getCoordX(), equipo.getCoordY()-1);
			}
			break;

			case "down":
			if (equipo.getCoordY()!=altCasillas-1) {
				equipo.setCoords(equipo.getCoordX(), equipo.getCoordY()+1);
			}
			break;

			case "left":
			if (equipo.getCoordX()!=0) {
				equipo.setCoords(equipo.getCoordX()-1, equipo.getCoordY());
			}
			break;

			case "right":
			if (equipo.getCoordX()!=longCasillas-1) {
				equipo.setCoords(equipo.getCoordX()+1, equipo.getCoordY());
			}
			break;
		}
	}

	public abstract void guardarArchivo();

	public abstract void invokeFinalBoss();



	public class kListener implements KeyListener{
		public void keyPressed(KeyEvent e){
		}

		public void keyReleased(KeyEvent e){
			boolean toRefresh = false;
			switch(e.getKeyCode()){
				case KeyEvent.VK_W: moveEquipo("up"); toRefresh=true; break;
				case KeyEvent.VK_A: moveEquipo("left"); toRefresh=true; break;
				case KeyEvent.VK_S: moveEquipo("down"); toRefresh=true; break;
				case KeyEvent.VK_D: moveEquipo("right"); toRefresh=true; break;
				default: break;
			}
			if (toRefresh == true) {
				refreshVentana();
				checkItems();
				checkEnemigos();
			}
		}

		public void keyTyped(KeyEvent e){
		}
	}

	public class bMovimientoListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton bPresionado = (JButton)e.getSource();
			boolean toRefresh = false;
			switch(bPresionado.getActionCommand()){
				case "Arriba": moveEquipo("up"); toRefresh=true; break;
				case "Abajo": moveEquipo("down"); toRefresh=true; break;
				case "Izquierda": moveEquipo("left"); toRefresh=true; break;
				case "Derecha": moveEquipo("right"); toRefresh=true; break;
				default: break;
			}
			if (toRefresh == true) {
				refreshVentana();
				checkItems();
				checkEnemigos();
			}
		}
	}

	public class bSaveFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			guardarArchivo();
		}
	}
}