import java.io.Serializable;

public class ItemCuracion extends Item{
	
	private String itemNombre;
	private int itemCuracion;
	private int coordX, coordY;

	public ItemCuracion(String itemNombre, int itemCuracion){
		super(itemNombre, itemCuracion);
	}
}