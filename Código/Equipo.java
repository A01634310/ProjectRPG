import java.io.Serializable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Esta clase es para lo que todo el equipo comparte
// como las coordenadas, items, etc.
public class Equipo extends JFrame implements Serializable{
	
	private int coordX, coordY;
	private Item[] items;

	private Licenciado lic;
	private Medico dr;
	private Ingeniero ing;

	private int batallasLibradas;

	private ImageIcon icono;

	public Equipo(Licenciado lic, Medico dr, Ingeniero ing){
		this.lic = lic;
		this.dr = dr;
		this.ing = ing;
		items = new Item[6];
		icono = new ImageIcon(Class.class.getResource("/img/equipo.png"));
		this.batallasLibradas = 0;
		initItems();
	}

	public Licenciado getLic(){ return lic;
	}

	public Medico getDr(){ return dr;
	}

	public Ingeniero getIng(){ return ing;
	}

	public Heroe getHeroe(int index){
		switch(index){
			case 0: return lic;
			case 1: return dr;
			case 2: return ing;
			default: break;
		}
		return lic;
	}

	public void setHeroes(Licenciado lic, Medico dr, Ingeniero ing){
		this.lic = lic; this.dr = dr; this.ing = ing;
	}

	public void setLic(Licenciado lic){ this.lic = lic;
	}

	public void setDr(Licenciado lic){ this.dr = dr;
	}

	public void setIng(Licenciado lic){ this.ing = ing;
	}

	public int getCoordX(){ return coordX;
	}

	public int getCoordY(){ return coordY;
	}

	public void setCoords(int coordX, int coordY){
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public Item getItem(int index){ return items[index];
	}

	public void addItem(Item item){
		for (int i=0; i<items.length; i++) {
			if (items[i]==null) {
				items[i] = item;
				break;
			}
		}
	}

	public void removeItem(int index){ items[index]=null;
	}

	public void initItems(){
		for (int i=0; i<items.length; i++) { items[i]=null;
		}
	}

	public Item[] getItems(){ return items;
	}

	public ImageIcon getIcono(){ return icono;
	}

	public void setIcono(ImageIcon i){ this.icono = i;
	}

	public void librarBatalla(){ batallasLibradas++;
	}

	public int getBatallasLibradas(){ return batallasLibradas;
	}
}