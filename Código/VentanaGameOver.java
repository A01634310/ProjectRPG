import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaGameOver extends JFrame{

	protected JPanel pVentGameOver;
	
	public void initComponents(){
		pVentGameOver = new JPanelImage("/img/gameOver_wallpaper.jpg");
		revalidate();
		repaint();
	}

	public JPanel getVentGameOver(){ return pVentGameOver;
	}
}