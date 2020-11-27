import metier.DPartie;

public class Main{
	public static void main(String[] args){	
			DPartie partie = new DPartie(9,9,10);
			DFenetre fenetre = new DFenetre(partie);
		
			EcouteurGo e = new EcouteurGo(fenetre);
			fenetre.getGo().addActionListener(e);
		
			EcouteurFenetre ecouteurFenetre = new EcouteurFenetre(fenetre);
			fenetre.addWindowListener(ecouteurFenetre);
		
			EcouteurMenu ecouteurMenu = new EcouteurMenu(fenetre);
			fenetre.ecouterMenu(ecouteurMenu);
	}	
}

