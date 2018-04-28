import java.io.Serializable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BatallaBoss extends JFrame{

	private Licenciado lic;
	private Medico dr;
	private Ingeniero ing;

	private Item[] items;
	private FinalBoss finalBoss;
	private Equipo equipo;

	private int selectedItem = -1;
	private int selectedAtaque = -1;
	private int turno = 0;

	private JPanel pPrincipal, pPersonajes, pItems, pStats, pAtacar;
	private JButton bItem0, bItem1, bItem2, bItem3, bItem4, bItem5;
	private JButton bHab0, bHab1, bHab2, bGolpear;
	private JButton bLic, bDr, bIng;
	private JLabel lHPLic, lHPDr, lHPIng;

	public BatallaBoss(FinalBoss finalBoss, Equipo equipo){
		super("Batalla!!");
		System.out.println("Batalla contra el Boss!!");
		this.equipo = equipo;
		this.lic = equipo.getLic();
		this.dr = equipo.getDr();
		this.ing = equipo.getIng();
		this.finalBoss = finalBoss;
		initComponents();
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}


	public void initComponents(){
		pPrincipal = new JPanel();
		pPrincipal.setLayout(new GridLayout(2,2));
		refreshPanel();
		add(pPrincipal);
	}

	public void refreshPanel(){
		pPrincipal.removeAll();
		initpPersonajes();
		initpAtacar();
		initpStats();
		initpItems();
		pPrincipal.revalidate();
		pPrincipal.repaint();
	}

	public void initbItems(){
		//Intentarlo con un for es muy dificil, a mano tiene que ser.
		if (equipo.getItem(0)==null) { bItem0 = new JButton();
		}
		else{
			bItem0 = new JButton(equipo.getItem(0).getItemNombre());
			bItem0.addActionListener(new bItem0Listener());
		}
		if (equipo.getItem(1)==null) { bItem1 = new JButton();
		}
		else{
			bItem1 = new JButton(equipo.getItem(1).getItemNombre());
			bItem1.addActionListener(new bItem1Listener());
		}
		if (equipo.getItem(2)==null) { bItem2 = new JButton();
		}
		else{
			bItem2 = new JButton(equipo.getItem(2).getItemNombre());
			bItem2.addActionListener(new bItem2Listener());
		}
		if (equipo.getItem(3)==null) { bItem3 = new JButton();
		}
		else{
			bItem3 = new JButton(equipo.getItem(3).getItemNombre());
			bItem3.addActionListener(new bItem3Listener());
		}
		if (equipo.getItem(4)==null) { bItem4 = new JButton();
		}
		else{
			bItem4 = new JButton(equipo.getItem(4).getItemNombre());
			bItem4.addActionListener(new bItem4Listener());
		}
		if (equipo.getItem(5)==null) { bItem5 = new JButton();
		}
		else{
			bItem5 = new JButton(equipo.getItem(5).getItemNombre());
			bItem5.addActionListener(new bItem5Listener());
		}
	}

	public void initpItems(){
		pItems = new JPanel();
		pItems.setLayout(new GridLayout(3,3));
		initbItems();
		pItems.add(new JLabel());
		pItems.add(new JLabel("Items:"));
		pItems.add(new JLabel());
		pItems.add(bItem0);
		pItems.add(bItem1);
		pItems.add(bItem2);
		pItems.add(bItem3);
		pItems.add(bItem4);
		pItems.add(bItem5);
		pPrincipal.add(pItems);
	}

	public void initbHeroes(){
		bLic = new JButton(lic.getNombre());
		bLic.addActionListener(new bLicListener());
		bDr = new JButton(dr.getNombre());
		bDr.addActionListener(new bDrListener());
		bIng = new JButton(ing.getNombre());
		bIng.addActionListener(new bLicListener());
	}

	public void initpPersonajes(){
		pPersonajes = new JPanel();
		pPersonajes.setLayout(new GridLayout(3,2));
		initbHeroes();
		pPersonajes.add(bLic);
		pPersonajes.add(new JLabel());
		pPersonajes.add(bDr);
		pPersonajes.add(new JLabel(finalBoss.getNombre()));
		pPersonajes.add(bIng);
		pPersonajes.add(new JLabel());
		pPrincipal.add(pPersonajes);
	}

	public void initbAtacar(){
		bGolpear = new JButton("Golpear");
		bGolpear.addActionListener(new bGolpearListener());
		bHab0 = new JButton(lic.getHab(0).getHabNombre());
		bHab0.addActionListener(new bHab0Listener());
		bHab1 = new JButton(dr.getHab(0).getHabNombre());
		bHab1.addActionListener(new bHab1Listener());
		bHab2 = new JButton(ing.getHab(0).getHabNombre());
		bHab2.addActionListener(new bHab2Listener());
	}

	public void initpAtacar(){
		pAtacar = new JPanel();
		pAtacar.setLayout(new GridLayout(2,1));
		initbAtacar();
		pAtacar.add(bGolpear);
		switch(turno){
			case 0: pAtacar.add(bHab0); break;
			case 1: pAtacar.add(bHab1); break;
			case 2: pAtacar.add(bHab2); break;
		}
		pPrincipal.add(pAtacar);
	}

	public void initpStats(){
		pStats = new JPanel();
		pStats.setLayout(new GridLayout(3,2));
		pStats.add(new JLabel(lic.getNombre()));
		pStats.add(new JLabel("   /   HP: " + lic.getHp()));
		pStats.add(new JLabel(dr.getNombre()));
		pStats.add(new JLabel("   /   HP: " + dr.getHp()));
		pStats.add(new JLabel(ing.getNombre()));
		pStats.add(new JLabel("   /   HP: " + ing.getHp()));
		pPrincipal.add(pStats);
	}



	public void golpear(){
		Heroe atacante;
		switch (turno){
			case 0: atacante = lic; break;
			case 1: atacante = dr; break;
			case 2: atacante = ing; break;
			default: atacante = lic; break;
		}
		finalBoss.setHp(finalBoss.getHp() - atacante.getAtk());
		System.out.println(atacante.getNombre()+ " ataca");
		System.out.println("HP FinalBoss restante: " + finalBoss.getHp());
	}

	public void pasarTurno(){
		if (finalBoss.getHp()>0&&(lic.getHp()>0||dr.getHp()>0||ing.getHp()>0)) {
			if (Math.random()>0) { new Preguntar(finalBoss.getPregunta(0));
			}
			else{ golpeEnemigo(finalBoss);
			}
			if (turno==2) { turno = 0;
			}
			else{ turno++;
			}
			refreshPanel();
		}
		else{ System.out.println("La batalla ha terminado");
		}
	}

	public void golpeEnemigo(FinalBoss finalBoss){
		if (Math.random()>0.67) { lic.setHp(lic.getHp()-finalBoss.getAtk());
			System.out.println("El finalBoss ha golpeado a " + lic.getNombre() +", " + finalBoss.getAtk());
		}
		else if (Math.random()>0.33) { dr.setHp(dr.getHp()-finalBoss.getAtk());
			System.out.println("El finalBoss ha golpeado a " + dr.getNombre() +", " + finalBoss.getAtk());
		}
		else{ ing.setHp(ing.getHp()-finalBoss.getAtk());
			System.out.println("El finalBoss ha golpeado a " + ing.getNombre() +", " + finalBoss.getAtk());
		}
	}

	public void usarHabilidad(){
		System.out.println("Se uso una habilidad (aun no programada XD)");
	}



	public class bItem0Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			selectedItem = 0;
			selectedAtaque = -1;
		}
	}

	public class bItem1Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			selectedItem = 1;
			selectedAtaque = -1;
		}
	}

	public class bItem2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			selectedItem = 2;
			selectedAtaque = -1;
		}
	}

	public class bItem3Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			selectedItem = 3;
			selectedAtaque = -1;
		}
	}

	public class bItem4Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			selectedItem = 4;
			selectedAtaque = -1;
		}
	}

	public class bItem5Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			selectedItem = 5;
			selectedAtaque = -1;
		}
	}

	public class bGolpearListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			golpear();
			pasarTurno();
		}
	}

	public class bHab0Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			selectedAtaque = 0;
			selectedItem = -1;
			usarHabilidad();
			pasarTurno();
		}
	}

	public class bHab1Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			selectedAtaque = 1;
			selectedItem = -1;
			pasarTurno();
		}
	}

	public class bHab2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			selectedAtaque = 2;
			selectedItem = -1;
			pasarTurno();
		}
	}

	public class bLicListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (selectedItem>=0) {
				System.out.println(lic.getNombre() + " uso el item " + equipo.getItem(selectedItem).getItemNombre());
				//lic.useItem(items[selectedItem]);
				lic.setHp(lic.getHp() + equipo.getItem(selectedItem).getItemCuracion());
				equipo.removeItem(selectedItem);
				selectedItem = -1;
				refreshPanel();
				pasarTurno();
			}
		}
	}

	public class bDrListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (selectedItem>=0) {
				System.out.println(dr.getNombre() + " uso el item " + equipo.getItem(selectedItem).getItemNombre());				dr.setHp(dr.getHp() + equipo.getItem(selectedItem).getItemCuracion());
				equipo.removeItem(selectedItem);
				selectedItem = -1;
				refreshPanel();
				pasarTurno();
			}
		}
	}

	public class bIngListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (selectedItem>=0) {
				System.out.println(ing.getNombre() + " uso el item " + equipo.getItem(selectedItem).getItemNombre());				ing.setHp(ing.getHp() + equipo.getItem(selectedItem).getItemCuracion());
				equipo.removeItem(selectedItem);
				selectedItem = -1;
				refreshPanel();
				pasarTurno();
			}
		}
	}

	/*
	public class bFinalBossListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (selectedAtaque>=0) {
				atacar(selectedAtaque);
			}
		}
	}

	public class bFinalBoss1Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}

	public class bFinalBoss2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}*/
}