import java.io.Serializable;

public class Partida implements Serializable{

	private Item[] items;
	private Enemigo[] enemigos;
	private FinalBoss finalBoss;

	private Equipo equipo;
	private Licenciado lic;
	private Medico dr;
	private Ingeniero ing;

	private Nivel[] niveles;

	public Partida(){
		items = new Item[10];
		enemigos = new Enemigo[10];
		niveles = new Nivel[1];
		createEquipo();
		createItems();
		createEnemigos();
		createFinalBosses();
		createNiveles();
	}

	public void createEquipo(){

		// Heroe(nombre, maxHP, atk, def)
		lic = new Licenciado("El Lic.", 250, 50, 10);
		dr = new Medico("El Doc.", 350, 25, 15);
		ing = new Ingeniero("El Inge", 150, 75, 7);

		lic.addHab(new Habilidad("Mota", 30));
		lic.addHab(new Habilidad("Tonayan", 30));
		lic.addHab(new Habilidad("Bacacho", 30));

		dr.addHab(new Habilidad("Paracetamol", 30));
		dr.addHab(new Habilidad("Ibuprofeno", 30));
		dr.addHab(new Habilidad("Paro Cardíaco", 30));

		ing.addHab(new Habilidad("Calculo", 30));
		ing.addHab(new Habilidad("Syntax Error", 30));
		ing.addHab(new Habilidad("Virginidad", 30));

		equipo = new Equipo(lic, dr, ing);
	}

	public void createItems(){

		items[0] = new Item("Pomadita",50);
		items[1] = new Item("Pomadota",50);
		items[2] = new Item("Rayo Emprendedor",50);
		items[3] = new Item("Wenas",50);
		items[4] = new Item("Pomadita",50);
		items[5] = new Item("Pomadita",50);
	}

	public void createEnemigos(){

		//Enemigo(nombre, maxHP, atk, def);
		enemigos[0] = new LicEnemigo("Lic sholo", 250, 35, 5);
		enemigos[1] = new LicEnemigo("El Lic enemigo", 250, 35, 8);
		enemigos[2] = new LicEnemigo("Un lic locoshon", 250, 35, 5);
		enemigos[3] = new DrEnemigo("Doc House", 250, 35, 5);
		enemigos[4] = new LicEnemigo("Achichincle Lic", 250, 35, 5);
		enemigos[5] = new DrEnemigo("Dr García", 250, 35, 5);
		enemigos[6] = new IngEnemigo("Dr. Ledo", 250, 35, 30);
		enemigos[7] = new IngEnemigo("Otra vez Ledo", 250, 35, 5);
		enemigos[8] = new LicEnemigo("Achichincle", 250, 35, 5);
		enemigos[9] = new DrEnemigo("Achichincle", 250, 35, 5);
	}

	public void createFinalBosses(){
		//FinalBoss(bossNombre, maxHP, atk, def);
		finalBoss = new FinalBoss("JuanJo", 400, 70, 35);
	}

	public void createNiveles(){

		//Nivel(nombreNivel, longCasillas, altCasillas, equipo);
		niveles[0] = new Nivel("Disney", 10, 10, equipo);
		niveles[0].setEquipoCoords(3,7);

		niveles[0].addItem(items[0], 5, 7);
		niveles[0].addItem(items[1], 8, 4);
		niveles[0].addItem(items[2], 1, 2);
		niveles[0].addItem(items[3], 6, 8);
		niveles[0].addItem(items[4], 3, 1);
		niveles[0].addItem(items[5], 9, 2);

		//Nivel.addEnemigo(Enemigo, coordX, coordY);
		niveles[0].addEnemigo(enemigos[0], 1, 0);
		niveles[0].addEnemigo(enemigos[1], 4, 5);
		niveles[0].addEnemigo(enemigos[2], 1, 3);
		niveles[0].addEnemigo(enemigos[3], 8, 1);
		niveles[0].addEnemigo(enemigos[4], 9, 7);

		niveles[0].addFinalBoss(finalBoss, 8, 4);
	}

	public Nivel getNivel(int index){ return niveles[index];
	}

	public void refreshPartida(Nivel n){
		/*items = n.getItems();
		enemigos = n.getEnemigos();
		equipo = n.getEquipo();*/
		niveles[0] = n;
	}
}