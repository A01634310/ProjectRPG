import java.io.Serializable;

public class ItemAtaque extends Item{
	
	private String itemNombre;
	private int itemCuracion;
	private int coordX, coordY;

	public ItemAtaque(String itemNombre, int itemCuracion){
		super(itemNombre, itemCuracion);
	}
}