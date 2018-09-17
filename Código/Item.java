import java.io.Serializable;

public class Item implements Serializable{
	
	private String itemNombre;
	private int itemFuerza;
	private int coordX, coordY;

	public Item(String itemNombre, int itemFuerza){
		this.itemNombre = itemNombre;
		this.itemFuerza = itemFuerza;
	}

	public String getItemNombre(){
		return itemNombre;
	}

	public void setItemNombre(String itemNombre){
		this.itemNombre = itemNombre;
	}

	public int getItemFuerza(){
		return itemFuerza;
	}

	public void setItemFuerza(int itemFuerza){
		this.itemFuerza = itemFuerza;
	}

	public int getCoordX(){ return coordX;
	}

	public int getCoordY(){ return coordY;
	}

	public void setCoords(int coordX, int coordY){
		this.coordX = coordX; this.coordY = coordY;
	}
}