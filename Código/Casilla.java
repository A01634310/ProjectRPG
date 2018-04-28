import java.io.Serializable;

public class Casilla implements Serializable{

	private Item item;
	private Enemigo enemigo;
	private Heroe[] heroes = new Heroe[3];

	public Item getItem(){
		return item;
	}

	public void setItem(Item item){

	}

	public Enemigo getEnemigo(){
		return this.enemigo;
	}

	public void setEnemigo(Enemigo enemigo){
		this.enemigo = enemigo;
	}

	public Heroe[] getHeroes(){
		return this.heroes;
	}

	public void setHeroes(Heroe[] heroes){
		this.heroes = heroes;
	}

}