import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JPanelImage extends JPanel{

	private Image background;

	public JPanelImage(String imagePath){
		setBackground(imagePath);
	}

	public JPanelImage(ImageIcon imgIcon){
		this.setOpaque(false);
		this.background = imgIcon.getImage();
		repaint();
	}
 
	// Metodo que es llamado automaticamente por la maquina virtual Java cada vez que repinta
	public void paintComponent(Graphics g) {
 
		/* Obtenemos el tamaño del panel para hacer que se ajuste a este
		cada vez que redimensionemos la ventana y se lo pasamos al drawImage */
		int width = this.getSize().width;
		int height = this.getSize().height;
 
		// Mandamos que pinte la imagen en el panel
		if (this.background != null) {
			g.drawImage(this.background, 0, 0, width, height, null);
		}
 
		super.paintComponent(g);
	}
 
	// Metodo donde le pasaremos la dirección de la imagen a cargar.
	public void setBackground(String imagePath) {
		
		// Construimos la imagen y se la asignamos al atributo background.
		this.setOpaque(false);
		this.background = new ImageIcon(Class.class.getResource(imagePath)).getImage();
		repaint();
	}
}