import java.awt.event.*;
import javax.swing.*;

import metier.DPartie;

import java.io.*;

public class EcouteurMenu implements ActionListener{
	
	private DFenetre fenetre;
	
	public EcouteurMenu(DFenetre f){

		fenetre = f;
	}
	
	
	public void actionPerformed(ActionEvent ae){
	    	if(ae.getSource() == fenetre.getNouvelle()){
			fenetre.arretChrono();
			fenetre.initChrono();
			fenetre.nouvellePartie(fenetre.getHauteur(),
		                      fenetre.getLargeur(),
		                      fenetre.getMines());
			fenetre.connecterPartie(fenetre.getPartie());
		}
	       	if(ae.getSource() == fenetre.getDebutant()){
				fenetre.arretChrono();
				fenetre.initChrono();
				fenetre.nouvellePartie(9,9,10);
				fenetre.connecterPartie(fenetre.getPartie());
				}
	    
		if(ae.getSource() == fenetre.getIntermediaire()){
				fenetre.arretChrono();
				fenetre.initChrono();
				fenetre.nouvellePartie(16,16,40);
				fenetre.connecterPartie(fenetre.getPartie());
		}	
		if(ae.getSource() == fenetre.getExpert()){
				fenetre.arretChrono();
				fenetre.initChrono();
				fenetre.nouvellePartie(16,30,99);
				fenetre.connecterPartie(fenetre.getPartie());
		}
		
		if(ae.getSource() == fenetre.getPerso()){
			Pref pref = new Pref(fenetre);

		}
		
		if(ae.getSource() == fenetre.getDesign()){
			DImageChooser choix = 
			      new DImageChooser(fenetre.getImageur(),
			                        fenetre.getPanneauCentral() );
		}
		
		if(ae.getSource() == fenetre.getQuitter())
							System.exit(0);
							
		if(ae.getSource()==fenetre.getAide()){
			File f = new File("resources/Aide");
			Aide a = new Aide(f);;
		}
		if(ae.getSource()==fenetre.getCreateur()) 
				JOptionPane.showMessageDialog(fenetre,
				     " Réalisé par Igor DAURIAC et Nicolas FRANCOIS, Projet IHM"
				      ,"Créateurs...",JOptionPane.INFORMATION_MESSAGE);
			
			
		
						
	}
	
	
}
