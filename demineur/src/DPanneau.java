import javax.swing.*;
import java.awt.*;

public class DPanneau extends JPanel{
	
	//private DImageur imageur;
	private DFenetre fenetre;
	private int hauteur, largeur;
	
	public DPanneau(DFenetre fe, int h, int l){
		super();
		fenetre = fe;
		hauteur = h;
		largeur = l;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0; i<hauteur; i++)
			for(int j=0;j<largeur; j++){
				g.drawImage(fenetre.getIcon(fenetre.getPartie().getEtatCase(i, j)).getImage(),j*20,i*20,this);
			}
	}	
}