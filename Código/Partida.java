import java.io.Serializable;

/*
La partida es el objeto que contiene toda la
información sobre nuestro juego, los stats de cada
personaje, los items, los niveles, etc.
*/
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

	/*Definimos los stats de nuestros heroes
	así como sus habilidades*/
	public void createEquipo(){

		// Heroe(nombre, maxHP, atk, def)
		lic = new Licenciado("El Lic.", 250, 50, 10);
		dr = new Medico("El Doc.", 350, 30, 15);
		ing = new Ingeniero("El Inge", 150, 70, 7);

		//Habilidad(HabNombre, HabFuerza)
		lic.addHab(new HabAtaque("Mota", 30));
		lic.addHab(new HabCuracion("Tonayan", 40));
		lic.addHab(new HabAtaque("Bacacho", 20));

		dr.addHab(new HabCuracion("Paracetamol", 50));
		dr.addHab(new HabCuracion("Ibuprofeno", 60));
		dr.addHab(new HabAtaque("Paro Cardíaco", 30));

		ing.addHab(new HabAtaque("Calculo", 50));
		ing.addHab(new HabAtaque("Syntax Error", 40));
		ing.addHab(new HabCuracion("Virginidad", 30));

		equipo = new Equipo(lic, dr, ing);
	}

	/*Definir los items que hay en el juego*/
	public void createItems(){

		items[0] = new ItemCuracion("Pomadita",50);
		items[1] = new ItemCuracion("Pomadota",50);
		items[2] = new ItemCuracion("Rayo Emprendedor",50);
		items[3] = new ItemCuracion("Wenas",50);
		items[4] = new ItemCuracion("Pomadita",50);
		items[5] = new ItemCuracion("Pomadita",50);
	}

	//Enemigos genéricos que habrá y sus stats
	public void createEnemigos(){

		//EnemigoSpawnData(maxHP, atk, def, eExp);
		EnemigoSpawnData sdLic = new EnemigoSpawnData(150,40,15,10);
		EnemigoSpawnData sdDr = new EnemigoSpawnData(200,30,20,15);
		EnemigoSpawnData sdIng = new EnemigoSpawnData(150,50,10,15);


		//Enemigo(nombre, maxHP, atk, def);
		enemigos[0] = new LicEnemigo("Lic sholo", sdLic);
		enemigos[1] = new LicEnemigo("El Lic enemigo", sdLic);
		enemigos[2] = new LicEnemigo("Un lic locoshon", sdLic);
		enemigos[3] = new DrEnemigo("Doc House", sdDr);
		enemigos[4] = new LicEnemigo("Achichincle Lic", sdLic);
		enemigos[5] = new DrEnemigo("Dr García", sdDr);
		enemigos[6] = new IngEnemigo("Dr. Ledo", sdIng);
		enemigos[7] = new IngEnemigo("Otra vez Ledo", sdIng);
		enemigos[8] = new LicEnemigo("Achichincle", sdLic);
		enemigos[9] = new DrEnemigo("Achichincle", sdDr);
	}

	public void createFinalBosses(){
		//FinalBoss(bossNombre, maxHP, atk, def);
		finalBoss = new FinalBoss("Salty", 200, 70, 40, 30);

		//Pregunta(pregunta, respuesta, opcA, opcB, opcC);
		finalBoss.addPregunta(new Pregunta(
			"Wenas?",2,"Hola","Kionda","Wenas :v"));
		finalBoss.addPregunta(new Pregunta(
			"Wenas?",2,"Hola","Kionda","Wenas :v"));
		finalBoss.addPregunta(new Pregunta(
			"Wenas?",2,"Hola","Kionda","Wenas :v"));
		finalBoss.addPregunta(new Pregunta(
			"Wenas?",2,"Hola","Kionda","Wenas :v"));
		finalBoss.addPregunta(new Pregunta(
			"Wenas?",2,"Hola","Kionda","Wenas :v"));
	}

	public void createNiveles(){

		//Nivel(nombreNivel, longCasillas, altCasillas, equipo);
		niveles[0] = new Nivel("Disney", 10, 10, equipo, "/img/disney_mapa.jpg");
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
		//niveles[0].addEnemigo(enemigos[6], 9, 7);

		niveles[0].addFinalBoss(finalBoss);
	}

	public Nivel getNivel(int index){ return niveles[index];
	}

	public void setNivel(Nivel nivel, int index){
		this.niveles[index] = nivel;
	}

	public void refreshPartida(Nivel n){ niveles[0] = n;
	}
}