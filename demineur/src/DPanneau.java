import javax.swing.*;
import java.awt.*;

public class DPanneau extends JPanel{
	
	private DFenetre fenetre;
	
	public DPanneau(DFenetre fe){
		super();
		fenetre = fe;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0; i<fenetre.getHauteur(); i++)
			for(int j=0;j<fenetre.getLargeur(); j++){
				g.drawImage(fenetre.getIcon(fenetre.getPartie().getEtatCase(i, j)).getImage(),j*20,i*20,this);
			}
	}	
}