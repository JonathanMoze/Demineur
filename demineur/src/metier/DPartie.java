package metier;
import java.util.Random;

import javax.swing.ImageIcon;

public class DPartie implements IDPartie{
	
	private int hauteur, largeur, nbMines; // parametres de la partie
	private DCase[][] matrice; 
	private int caseNonMineeRestante;
	private boolean explosion;

	
	public DPartie(int h, int l, int nb){
		nouvellePartie(h, l, nb);
	}
	
	public void nouvellePartie(int h, int l, int nb){
		//nouvellePartie(h,l,nb);
				hauteur = h;
				largeur = l;
				nbMines = nb;
				explosion = false;
				matrice = new DCase[h][l];
				for(int i=0; i<hauteur; i++)
					for(int j=0; j<largeur; j++)
					matrice[i][j] = new DCase();
				miner();
				preparerAlentour(); 
				caseNonMineeRestante = hauteur*largeur-nbMines;	
	}
	
	public boolean gagne(){
		return getCaseNonMineeRestante()==0;
	}
	
	public boolean perdu(){
		return aExplose();
	}
	
	public int getHauteur(){
		return hauteur;
	}
	
	public int getLargeur(){
		return largeur;
	}
	
	public int getMines(){
		return nbMines;
	}
	
	
	public void devoilerCase(int i,int j){

		/* Case d�couverte */
		try{
			   	matrice[i][j].setDecouverte();
			   	caseNonMineeRestante--;
		}
		catch(ArrayIndexOutOfBoundsException e){  }
		
		
		  /* on regarde si la case est min�e */
		try{
			  if(matrice[i][j].estMine())
			  	explosion = true;
			  else{
			  
		  	
		 		/* propagation �ventuelle */

				if(matrice[i][j].getMinesAlentour()==0){
					
					try{
						if(!matrice[i-1][j-1].estDecouverte())
							devoilerCase(i-1,j-1);
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				
					try{
						if(!matrice[i-1][j].estDecouverte())
							devoilerCase(i-1,j);
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				
					try{
						if(!matrice[i-1][j+1].estDecouverte())
							devoilerCase(i-1,j+1);	
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				
					try{
						if(!matrice[i][j-1].estDecouverte())
							devoilerCase(i,j-1);	
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				
					try{
						if(!matrice[i][j+1].estDecouverte())
							devoilerCase(i,j+1);
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				
					try{
						if(!matrice[i+1][j-1].estDecouverte())
							devoilerCase(i+1,j-1);	
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				
					try{
						if(!matrice[i+1][j].estDecouverte())
							devoilerCase(i+1,j);	
					}
					catch(ArrayIndexOutOfBoundsException e){  }
								
					try{
						if(!matrice[i+1][j+1].estDecouverte())
							devoilerCase(i+1,j+1);	
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){  }
		
	}
		
	public void drapeauAction(int i, int j){
		matrice[i][j].drapeauAction();
	}

	private void preparerAlentour(){
		int minesCompteur;
			
		for(int i=0; i<hauteur; i++)
			for(int j=0; j<largeur; j++){
					minesCompteur=0;
					if(!matrice[i][j].estMine()){
						try{
							if(matrice[i-1][j-1].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
								
						try{
							if(matrice[i-1][j].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
						
						try{
							if(matrice[i-1][j+1].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
						
						try{
							if(matrice[i][j-1].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
						
						try{
							if(matrice[i][j+1].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
							
						try{
							if(matrice[i+1][j-1].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
						
						try{
							if(matrice[i+1][j].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
						
						try{
							if(matrice[i+1][j+1].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
					
		
				
						/* les mines ont �t�s compt�s*/
						matrice[i][j].setMinesAlentour(minesCompteur);								
					}
			
			}
				
		}
	
	private void miner(){
		int x,y;
		int i=0;
		Random alea = new Random();
		while(i<nbMines){
			x = alea.nextInt(hauteur);
			y = alea.nextInt(largeur);
			if(!matrice[x][y].estMine()){
				matrice[x][y].poserBombe();
				i++;
			}
			
		}
	}

	public int nbrDrapeau(){
		int compteur = 0;
		for(int i=0;i<hauteur;i++)
			for(int j=0;j<largeur;j++){
				if(matrice[i][j].yaDrapreau())
					compteur++;
			}
		return compteur;
}

	public boolean aExplose(){
		return explosion;
	}
	
	public int getCaseNonMineeRestante(){
		return caseNonMineeRestante;
	}

	public void afficher(){
		System.out.println("****carte****");
		for(int i=0; i<hauteur; i++){
			for(int j=0; j<largeur; j++){
				if(matrice[i][j].estMine())
					System.out.print("M ");
				else
					System.out.print(matrice[i][j].getMinesAlentour()+" ");
			}
			System.out.println("");
			
		}
		System.out.println("****carte connue****");
		for(int i=0; i<hauteur; i++){
			for(int j=0; j<largeur; j++){
				if(!matrice[i][j].estDecouverte())
					System.out.print("* ");
				else
					if(matrice[i][j].estMine())
						System.out.print("M ");
					else
						System.out.print(matrice[i][j].getMinesAlentour()+" ");
			}
			System.out.println("");
			
		}
	}
	
	
	public EtatCase getEtatCase(int i, int j) {
		
		if(!perdu() && !gagne()){
			if(matrice[i][j].yaDrapreau())
				return EtatCase.DRAPEAU;
			if(!matrice[i][j].estDecouverte()){
				if(matrice[i][j].select())
					return EtatCase.SELECTED;
				else
					return EtatCase.INCONNUE;
			}
			switch(matrice[i][j].getMinesAlentour()){
					case 0: return EtatCase.VIDE;
					case 1: return EtatCase.UN;
					case 2: return EtatCase.DEUX;
					case 3: return EtatCase.TROIS;
					case 4: return EtatCase.QUATRE;
					case 5: return EtatCase.CINQ;
					case 6: return EtatCase.SIX;
					case 7: return EtatCase.SEPT;
					case 8: return EtatCase.HUIT;
					default : return EtatCase.MINE;
				}
		}
		else{
			if(perdu()){
				if((matrice[i][j].yaDrapreau())
			   		&& !(matrice[i][j].estMine() ))
			   			return EtatCase.CROIX;
			   	if(matrice[i][j].estMine())
			   		return EtatCase.MINE;
			   	if(!matrice[i][j].estDecouverte())
					return EtatCase.INCONNUE;	
			 		switch(matrice[i][j].getMinesAlentour()){
			 			case 0: return EtatCase.VIDE;
			 			case 1: return EtatCase.UN;
			 			case 2: return EtatCase.DEUX;
			 			case 3: return EtatCase.TROIS;
			 			case 4: return EtatCase.QUATRE;
			 			case 5: return EtatCase.CINQ;
			 			case 6: return EtatCase.SIX;
			 			case 7: return EtatCase.SEPT;
			 			case 8: return EtatCase.HUIT;
			 			default : return EtatCase.MINE;
					}
			}
			else {
				switch(matrice[i][j].getMinesAlentour()){
					case 0: return EtatCase.VIDE;
					case 1: return EtatCase.UN;
					case 2: return EtatCase.DEUX;
					case 3: return EtatCase.TROIS;
					case 4: return EtatCase.QUATRE;
					case 5: return EtatCase.CINQ;
					case 6: return EtatCase.SIX;
					case 7: return EtatCase.SEPT;
					case 8: return EtatCase.HUIT;
					default : return EtatCase.MINE;
				}
			}
		}
	}
	
	
	public void deselectionnerCase(int y, int x) {
		this.matrice[y][x].deselectionner();
	}
	
	public void selectionnerCase(int y, int x) {
		this.matrice[y][x].selectionner();
	}
	
	public boolean yaDrapeauSurCase(int y, int x) {
		return this.matrice[y][x].yaDrapreau();
	}
	
	
	
	
	
	
	
	
	
		
}




















