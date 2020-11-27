import javax.swing.*;

import metier.DPartie;
import metier.EtatCase;

import java.awt.*;

public class DImageur{
	
	//private EtatCase etat;
	//private DPartie partie;
	public String repertoire = "resources/Images/Classic";
	
	public DImageur(/*EtatCase e*/){
		//etat = e;
	}
	
	public String getRepertoire(){
		return repertoire;
	}
	
	public void setRepertoire(String s){
		repertoire = s;
	}
	
	/*public DPartie getPartie() {
		return partie;
	}*/
	
	/*public EtatCase getEtat() {
		return etat;
	}*/

	
	ImageIcon getIcon(EtatCase etat) {
		switch(etat) {
		case VIDE: return new ImageIcon(repertoire+"/Vide.GIF");
		case DRAPEAU: return new ImageIcon(repertoire+"/Drapeau.GIF");
		case MINE: return new ImageIcon(repertoire+"/Mine.GIF");
		case CROIX: return new ImageIcon(repertoire+"/Croix.GIF");
		case SELECTED: return new ImageIcon(repertoire+"/Select.GIF");
		case UN: return new ImageIcon(repertoire+"/1.GIF");
		case DEUX: return new ImageIcon(repertoire+"/2.GIF");
		case TROIS: return new ImageIcon(repertoire+"/3.GIF");
		case QUATRE: return new ImageIcon(repertoire+"/4.GIF");
		case CINQ: return new ImageIcon(repertoire+"/5.GIF");
		case SIX: return new ImageIcon(repertoire+"/6.GIF");
		case SEPT: return new ImageIcon(repertoire+"/7.GIF");
		case HUIT: return new ImageIcon(repertoire+"/8.GIF");
		default: return new ImageIcon(repertoire+"/Inconnue.GIF");
		}
	}
	
	
	
	
	
	
	
	
	
}