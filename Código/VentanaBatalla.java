import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class VentanaBatalla extends JFrame{
	
	protected Licenciado lic;
	protected Medico dr;
	protected Ingeniero ing;
	protected Item[] items;
	protected Enemigo enemigo;
	protected Equipo equipo;

	protected String narracion;
	protected int turno;
	protected boolean turnoEnemigo;

	protected HabCuracion selectedHabC;
	protected ItemCuracion selectedItemC;

	protected JPanelImage pVentBatalla;
	protected JPanel pPersonajes, pItems, pStats, pAtacar, pHabs;

	protected JButton[] bItems;

	protected JButton[] bHabsLic;
	protected JButton[] bHabsDr;
	protected JButton[] bHabsIng;

	protected JButton bGolpear;

	protected JButton bLic, bDr, bIng;
	protected JLabel lHPLic, lHPDr, lHPIng;
	protected JLabel lEnemigo;

	protected JLabel narrador;

	protected ImageIcon icGolpear;

	public VentanaBatalla(Batalla batalla){
		this.equipo = batalla.getEquipo();
		this.lic = equipo.getLic();
		this.dr = equipo.getDr();
		this.ing = equipo.getIng();
		this.enemigo = batalla.getEnemigo();
		this.items = equipo.getItems();
		this.bItems = new JButton[equipo.getItems().length];
		this.bHabsLic = new JButton[lic.getHabs().length];
		this.bHabsDr = new JButton[dr.getHabs().length];
		this.bHabsIng = new JButton[ing.getHabs().length];
		this.turno = 0;
		this.turnoEnemigo = false;
		this.narracion = "Comienza la pelea";
		initComponents();
		
	}

	public JPanel getVentana(){ return pVentBatalla;
	}

	public void initComponents(){
		ImageIcon background = enemigo.getBattleImg();
		pVentBatalla = new JPanelImage(background);
		//pVentBatalla.setLayout(new GridLayout(3,2));
		pVentBatalla.setLayout(new FlowLayout(0,0,0));
		icGolpear = new ImageIcon(Class.class.getResource("/img/golpear_icon.png"));
		initbItems();
		initbAtacar();
		initbHeroes();
		initpStats();
		narrador = new JLabel();
		narrador.setPreferredSize(new Dimension(768,128));
		refreshVentBatalla();
	}

	public void refreshVentBatalla(){
		pVentBatalla.removeAll();
		refreshpPersonajes();
		refreshpStats();
		refreshpItems();
		refreshpAtacar();
		refreshpNarrador();
		pVentBatalla.add(new JLabel());
		pVentBatalla.revalidate();
		pVentBatalla.repaint();
	}

	public void initbHeroes(){
		pPersonajes = new JPanel();
		pPersonajes.setBackground(new Color(0,0,0,0));
		pPersonajes.setPreferredSize(new Dimension(384,384));
		bLic = new JButton(lic.getNombre(),lic.getImagen());
		bLic.addActionListener(new bLicListener());
		bLic.setFont(new Font("Cambria", Font.PLAIN, 24));
		bDr = new JButton(dr.getNombre(),dr.getImagen());
		bDr.addActionListener(new bDrListener());
		bDr.setFont(new Font("Cambria", Font.PLAIN, 24));
		bIng = new JButton(ing.getNombre(),ing.getImagen());
		bIng.addActionListener(new bIngListener());
		bIng.setFont(new Font("Cambria", Font.PLAIN, 24));
		lEnemigo = new JLabel(enemigo.getNombre(), enemigo.getImagen(), 0);
		lEnemigo.setFont(new Font("Cambria", Font.PLAIN, 24));
		lEnemigo.setForeground(new Color(0,0,0,255));
	}

	public void initpStats(){
		pStats = new JPanel();
		pStats.setBackground(new Color(0,0,0,0));
		pStats.setPreferredSize(new Dimension(350,350));
	}

	public void initbItems(){
		pItems = new JPanel();
		pItems.setBackground(new Color(0,0,0,0));
		pItems.setPreferredSize(new Dimension(384,64));
		pItems.setLayout(new GridLayout(1,6));
		for (int i=0; i<bItems.length; i++) {
			if (equipo.getItem(i)==null) { bItems[i] = new JButton();
			} else{
				bItems[i] = new JButton(new ImageIcon(Class.class.getResource("/img/potion.png")));
				bItems[i].addActionListener(new bItemListener());
			}
			bItems[i].setBackground(new Color(204,209,255,128));
		}
	}

	public void initbAtacar(){
		pAtacar = new JPanel();
		pAtacar.setPreferredSize(new Dimension(350,256));
		pAtacar.setBackground(new Color(0,0,0,0));
		pAtacar.setLayout(new GridLayout(2,1));
		bGolpear = new JButton("Golpear", icGolpear);
		bGolpear.addActionListener(new bGolpearListener());
		pHabs = new JPanel();
		pHabs.setLayout(new GridLayout(1,3));
		pHabs.setBackground(new Color(0,0,0,0));
		for (int i=0; i<bHabsLic.length; i++) {
			if (lic.getHab(i)!=null) {
				bHabsLic[i] = new JButton(lic.getHab(i).getHabNombre());
				bHabsLic[i].addActionListener(new bHabLicListener());
				if (lic.getHab(i).getClass().getSimpleName()=="HabCuracion") {
					bHabsLic[i].setForeground(Color.blue);
				}
				bHabsLic[i].setBackground(new Color(255,255,255,155));
			}
		}
		for (int i=0; i<bHabsDr.length; i++) {
			if (lic.getHab(i)!=null) {
				bHabsDr[i] = new JButton(dr.getHab(i).getHabNombre());
				bHabsDr[i].addActionListener(new bHabDrListener());
				if (dr.getHab(i).getClass().getSimpleName()=="HabCuracion") {
					bHabsDr[i].setForeground(Color.blue);
				}
				bHabsDr[i].setBackground(new Color(255,255,255,155));
			}
		}
		for (int i=0; i<bHabsIng.length; i++) {
			if (lic.getHab(i)!=null) {
				bHabsIng[i] = new JButton(ing.getHab(i).getHabNombre());
				bHabsIng[i].addActionListener(new bHabIngListener());
				if (ing.getHab(i).getClass().getSimpleName()=="HabCuracion") {
					bHabsIng[i].setForeground(Color.blue);
				}
				bHabsIng[i].setBackground(new Color(255,255,255,155));
			}
		}
	}

	public void refreshpItems(){
		//pItems = new JPanel();
		pItems.removeAll();
		/*pItems.add(new JLabel());
		pItems.add(new JLabel("Items:"));
		pItems.add(new JLabel());*/
		for (int i=0; i<bItems.length; i++) {
			if (items[i]!=null) { pItems.add(bItems[i]);
			} else{ pItems.add(new JButton());
			}
		}
		pVentBatalla.add(pItems);
	}

	public void refreshpPersonajes(){
		//pPersonajes = new JPanel();
		pPersonajes.removeAll();
		pPersonajes.setLayout(new GridLayout(3,2));
		pPersonajes.setBackground(new Color(0,0,0,0));
		bLic.setBackground(new Color(255,255,255,155));
		bDr.setBackground(new Color(255,255,255,155));
		bIng.setBackground(new Color(255,255,255,155));
		lEnemigo.setForeground(new Color(0,0,0,255));
		if (turnoEnemigo==false) {
			switch(turno){
				case 0: bLic.setBackground(Color.green); break;
				case 1: bDr.setBackground(Color.green); break;
				case 2: bIng.setBackground(Color.green); break;
			}
		}
		else{
			lEnemigo.setForeground(new Color(255,0,0,255));
		}
		pPersonajes.add(bLic);
		pPersonajes.add(new JLabel());
		pPersonajes.add(bDr);
		pPersonajes.add(lEnemigo);
		pPersonajes.add(bIng);
		pPersonajes.add(new JLabel());
		pVentBatalla.add(pPersonajes);
	}

	public void refreshpAtacar(){
		//pAtacar = new JPanel();
		pAtacar.removeAll();
		pHabs.removeAll();
		//pAtacar.setLayout(new GridLayout(2,1));
		//pAtacar.setBackground(new Color(0,0,0,0));
		pAtacar.add(bGolpear);
		for (int i=0; i<bHabsLic.length; i++) {
			switch(turno){
				case 0:
				if (bHabsLic[i]!=null) { pHabs.add(bHabsLic[i]);
				}
				break;
				case 1:
				if (bHabsDr[i]!=null) { pHabs.add(bHabsDr[i]);
				}
				break;
				case 2:
				if (bHabsIng[i]!=null) { pHabs.add(bHabsIng[i]);
				}
				break;
			}
		}
		pAtacar.add(pHabs);
		pVentBatalla.add(pAtacar);
	}

	public void refreshpStats(){
		pStats.removeAll();
		//pStats = new JPanel();
		//pStats.setBackground(new Color(0,0,0,0));
		pStats.setLayout(new GridLayout(3,2));

		pStats.add(new JLabel(lic.getIcono()));
		JLabel lHPLic = new JLabel("   /   HP: " + lic.getHP());
		lHPLic.setFont(new Font("Cambria", Font.BOLD, 24));
		lHPLic.setForeground(new Color(0,0,0,255));
		if (lic.getHP()>=lic.getMaxHP()) { lHPLic.setForeground(new Color(11,52,77,255));
		} else if(lic.getHP()==0){ lHPLic.setForeground(Color.red);
		}
		pStats.add(lHPLic);

		pStats.add(new JLabel(dr.getIcono()));
		JLabel lHPDr = new JLabel("   /   HP: " + dr.getHP());
		lHPDr.setFont(new Font("Cambria", Font.BOLD, 24));
		lHPDr.setForeground(new Color(0,0,0,255));
		if (dr.getHP()>=dr.getMaxHP()) { lHPDr.setForeground(new Color(11,52,77,255));
		} else if(dr.getHP()==0){ lHPDr.setForeground(Color.red);
		}
		pStats.add(lHPDr);

		pStats.add(new JLabel(ing.getIcono()));
		JLabel lHPIng = new JLabel("   /   HP: " + ing.getHP());
		lHPIng.setFont(new Font("Cambria", Font.BOLD, 24));
		lHPIng.setForeground(new Color(0,0,0,255));
		if (ing.getHP()>=ing.getMaxHP()) { lHPIng.setForeground(new Color(11,52,77,255));
		} else if(ing.getHP()==0){ lHPIng.setForeground(Color.red);
		}
		pStats.add(lHPIng);

		pVentBatalla.add(pStats);
	}

	public void refreshpNarrador(){
		JLabel lNarracion = new JLabel(narracion);
		lNarracion.setForeground(new Color(0,0,0,255));
		lNarracion.setPreferredSize(new Dimension(768,64));
		lNarracion.setFont(new Font("Serif", Font.BOLD, 24));
		//pNarracion.setForeground(Color.white);
		pVentBatalla.add(lNarracion);
		System.out.println(narracion);
		narracion = "";
	}



	public abstract void pasarTurno();

	public void golpear(Heroe atacante){
		int danio = atacante.getAtk() - enemigo.getDef();
		enemigo.setHP(enemigo.getHP() - danio);
		narrador.setText(atacante.getNombre() + " ataca a " + enemigo.getNombre()
			+ " haciendo " + danio + " de danio");
		narracion = (atacante.getNombre() + " ataca a " + enemigo.getNombre()
			+ " haciendo " + danio + " de danio");
		System.out.println("HP Enemigo restante: " + enemigo.getHP());
		pasarTurno();
	}

	public void usarHabilidad(Heroe heroe, int index){
		Habilidad hab = heroe.getHab(index);
		switch(hab.getClass().getSimpleName()){

			case "HabAtaque":
				int danio = hab.getHabFuerza()+heroe.getAtk();
				switch(heroe.getClass().getSimpleName()){
					case "Licenciado":
					if (enemigo.getClass().getSimpleName()=="DrEnemigo") {
						double dDanio = danio*1.5;
						danio = (int) dDanio;
						narracion = "Tu ataque es Super efectivo!!";
					}
					break;

					case "Medico":
					if (enemigo.getClass().getSimpleName()=="IngEnemigo") {
						double dDanio = danio*1.5;
						danio = (int) dDanio;
						narracion = "Tu ataque es Super efectivo!!";
					}
					break;

					case "Ingeniero":
					if (enemigo.getClass().getSimpleName()=="LicEnemigo") {
						double dDanio = danio*1.5;
						danio = (int) dDanio;
						narracion = "Tu ataque es Super efectivo!! ";
					}
					break;
				}
				enemigo.setHP(enemigo.getHP() - danio + enemigo.getDef());
				narracion = (narracion + "\n" + heroe.getNombre()+" uso la habilidad "+
					hab.getHabNombre()+" causando "+ (danio-enemigo.getDef()) + " de danio");
				System.out.println(narracion);
				pasarTurno();
			break;

			case "HabCuracion":
				hab.setHabFuerza(hab.getHabFuerza() + heroe.getDef()/2);
				selectedHabC = (HabCuracion)hab;
			break;

			default: break;
		}
	}

	public void ataqueEnemigo(){
		if (Math.random()>0.7) {
			double random = Math.random();
			if (random>0.66 && lic.getHP()>0) { habEnemiga(lic);
			}
			else if(random>0.33 && dr.getHP()>0){ habEnemiga(dr);
			}
			else if(ing.getHP()>0){ habEnemiga(ing);
			}
		}
		else{
			double random = Math.random();
			if (random>0.66 && lic.getHP()>0) { golpeEnemigo(lic);
			}
			else if(random>0.33 && dr.getHP()>0){ golpeEnemigo(dr);
			}
			else if(ing.getHP()>0){ golpeEnemigo(ing);
			}
		}
	}

	public void golpeEnemigo(Heroe atacado){
		int danio = enemigo.getAtk() - atacado.getDef()/2;
		atacado.setHP(atacado.getHP() - danio);
		narrador.setText("El enemigo ha golpeado a " + atacado.getNombre() +
			", causando " + danio + " de danio.");
		narracion = ("El enemigo ha golpeado a " + atacado.getNombre() +
			", causando " + danio + " de danio.");
		if (atacado.getHP()<0) { atacado.setHP(0);
		}
	}

	public void habEnemiga(Heroe heroe){
		int danio = 0;
		double dDanio = enemigo.getAtk()*1.5;
		switch(enemigo.getClass().getSimpleName()){
			case "LicEnemigo":
			if (heroe.getClass().getSimpleName()=="Medico") {
				dDanio = dDanio*1.5;
				narracion = "Ataque enemigo Super efectivo!! ";
			}
			break;

			case "DrEnemigo":
			if (heroe.getClass().getSimpleName()=="Ingeniero") {
				dDanio = dDanio*1.5;
				narracion = "Ataque enemigo Super efectivo!! ";
			}
			break;

			case "IngEnemigo":
			if (heroe.getClass().getSimpleName()=="Licenciado") {
				dDanio = dDanio*1.5;
				narracion = "Ataque enemigo Super efectivo!! ";
			}
			break;
		}
		danio = (int) dDanio;
		heroe.setHP(heroe.getHP() - danio + heroe.getDef());
		narracion = (narracion + enemigo.getNombre()+" uso un ataque especial causando "+
			(danio-enemigo.getDef()) + " de danio en " +heroe.getNombre());
		if (heroe.getHP()<0) { heroe.setHP(0);
		}
	}

	public void curarHeroe(Heroe h, HabCuracion habC){
		int curacion = habC.getHabFuerza();
		h.setHP(h.getHP() + curacion);
		if (h.getHP()>h.getMaxHP()) { h.setHP(h.getMaxHP());
		}
		narracion = (h.getNombre()+" recupero "+curacion+" HP.");
		selectedHabC = null;
		selectedItemC = null;
		pasarTurno();
	}

	public void curarHeroe(Heroe h, ItemCuracion itemC){
		int curacion = itemC.getItemFuerza();
		h.setHP(h.getHP() + curacion);
		if (h.getHP()>h.getMaxHP()) { h.setHP(h.getMaxHP());
		}
		narracion = (h.getNombre()+" recupero "+curacion+" HP.");
		selectedHabC = null;
		selectedItemC = null;
		pasarTurno();
	}



	public class bItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			selectedHabC = null;
			JButton bItem = (JButton)e.getSource();
			for (int i=0; i<bItems.length; i++) {
				if (bItems[i]==bItem) {
					Item item = equipo.getItem(i);
					equipo.removeItem(i);
					switch(item.getClass().getSimpleName()){

						case "ItemAtaque":
							enemigo.setHP(enemigo.getHP()-item.getItemFuerza());
							pasarTurno();
						break;

						case "ItemCuracion":
							selectedItemC = (ItemCuracion)item;
						break;

						default: break;
					}
				}
			}
		}
	}

	public class bGolpearListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (selectedItemC==null) {
				new Thread() {
			        public void run() {
			            switch(turno){
							case 0: golpear(lic); break;
							case 1: golpear(dr); break;
							case 2: golpear(ing); break;
						}
			        }
			    }.start();
			}
		}
	}

	public class bHabLicListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (selectedItemC==null) {
				JButton bHabilidad = (JButton)e.getSource();
				for (int i=0; i<bHabsLic.length; i++) {
					if (bHabsLic[i]==bHabilidad) {
						int habNum = i;
						new Thread() {
							public void run() { usarHabilidad(lic, habNum);
							}
						}.start();
					}
				}
			}
		}
	}

	public class bHabDrListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (selectedItemC==null) {
				JButton bHabilidad = (JButton)e.getSource();
				for (int i=0; i<bHabsDr.length; i++) {
					if (bHabsDr[i]==bHabilidad) {
						int habNum = i;
						new Thread() {
							public void run() { usarHabilidad(dr, habNum);
							}
						}.start();
					}
				}
			}
		}
	}

	public class bHabIngListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (selectedItemC==null) {
				JButton bHabilidad = (JButton)e.getSource();
				for (int i=0; i<bHabsIng.length; i++) {
					if (bHabsIng[i]==bHabilidad) {
						int habNum = i;
						new Thread() {
							public void run() { usarHabilidad(ing, habNum);
							}
						}.start();
					}
				}
			}
		}
	}



	public class bLicListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (selectedHabC!=null) {
				new Thread() {
					public void run() {
						curarHeroe(lic, selectedHabC);
					}
				}.start();
			}
			else if(selectedItemC!=null){
				narracion = (lic.getNombre() + " uso el item " + selectedItemC.getItemNombre());
				System.out.println(narracion);
				//lic.useItem(items[selectedItem]);
				new Thread() {
					public void run() {
						curarHeroe(lic,selectedItemC);
					}
				}.start();
			}
		}
	}

	public class bDrListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (selectedHabC!=null) {
				new Thread() {
					public void run() {
						curarHeroe(dr,selectedHabC);
					}
				}.start();
			}
			else if(selectedItemC!=null){
				narracion = (dr.getNombre() + " uso el item " + selectedItemC.getItemNombre());
				System.out.println(narracion);
				//lic.useItem(items[selectedItem]);
				new Thread() {
					public void run() {
						curarHeroe(dr,selectedItemC);
					}
				}.start();
			}
		}
	}

	public class bIngListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (selectedHabC!=null) {
				new Thread() {
					public void run() {
						curarHeroe(ing,selectedHabC);
					}
				}.start();
			}
			else if(selectedItemC!=null){
				narracion = (ing.getNombre() + " uso el item " + selectedItemC.getItemNombre());
				System.out.println(narracion);
				//lic.useItem(items[selectedItem]);
				new Thread() {
					public void run() {
						curarHeroe(ing,selectedItemC);
					}
				}.start();
			}
		}
	}
}