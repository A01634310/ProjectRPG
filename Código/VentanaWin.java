import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.Serializable;

public class VentanaWin extends JFrame{
	
	protected JPanel pVentWin;
	
	public void initComponents(){
		pVentWin = new JPanelImage("/img/gameOver_wallpaper.jpg");
		revalidate();
		repaint();
	}

	public JPanel getVentGameOver(){ return pVentWin;
	}
}