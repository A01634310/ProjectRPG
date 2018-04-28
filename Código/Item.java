import java.io.Serializable;

public class Item implements Serializable{
	
	private String itemNombre;
	private int itemCuracion;
	private int coordX, coordY;

	public Item(String itemNombre, int itemCuracion){
		this.itemNombre = itemNombre;
		this.itemCuracion = itemCuracion;
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public String getItemNombre(){
		return itemNombre;
	}

	public void setItemNombre(String itemNombre){
		this.itemNombre = itemNombre;
	}

	public int getItemCuracion(){
		return itemCuracion;
	}

	public void setItemCuracion(int itemCuracion){
		this.itemCuracion = itemCuracion;
	}

	public int getCoordX(){ return coordX;
	}

	public int getCoordY(){ return coordY;
	}

	public void setCoords(int coordX, int coordY){
		this.coordX = coordX; this.coordY = coordY;
	}
}