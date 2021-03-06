import java.awt.event.*;



public class EcouteurSouris  implements MouseListener, MouseMotionListener{

	private DFenetre fenetre;
	private int sourisX, sourisY;
	private boolean gauchePresse;
	
	public EcouteurSouris(DFenetre f){
		fenetre = f;
		gauchePresse = false;
	}
	
	public void mouseReleased(MouseEvent me){
		sourisX = me.getX()/20;
		sourisY = me.getY()/20;
		try{
		  if(!fenetre.perdu() && !fenetre.gagne() 
		       && !(fenetre.yaDrapeauSurCase(sourisY, sourisX))){
			
			if(me.getButton()==MouseEvent.BUTTON1){
				gauchePresse = false;
				try{
					fenetre.selectionnerCase(sourisY, sourisX);
				}
				catch(NullPointerException npe){  }
				try{
					fenetre.devoilerCase(sourisY,sourisX);
				}
				catch(NullPointerException npe){  }
				fenetre.lancerChrono();
				if(fenetre.gagne()){
					fenetre.goGagne();
					fenetre.arretChrono();
				}					
				else
					if(fenetre.perdu()){
						fenetre.goPerdu();
						fenetre.arretChrono();
					}
					else
						fenetre.goCool();
			}	
			
				

			
		  }
		}
		catch(NullPointerException npe){  }
		me.getComponent().repaint();
	}
	
	public void mousePressed(MouseEvent me){
		sourisX = me.getX()/20;
		sourisY = me.getY()/20;
		if(!fenetre.perdu() && !fenetre.gagne()){
			
			
			
			if(me.getButton()==MouseEvent.BUTTON1){
				gauchePresse = true;
				fenetre.selectionnerCase(sourisY, sourisX);;
				fenetre.goOups();
			}	
			if(me.getButton()==MouseEvent.BUTTON3){
				fenetre.drapeauAction(sourisY,sourisX);					
				fenetre.miseAJourCompteur();
				
			}
		}
		me.getComponent().repaint();
	}
	
	public void mouseExited(MouseEvent me){
		if(gauchePresse)
			fenetre.goCool();
	}
	
	public void mouseEntered(MouseEvent me){
		if(gauchePresse)
			fenetre.goOups();	
	}
	
	
	public void mouseDragged(MouseEvent me){
		int x = me.getX()/20;
		int y = me.getY()/20;
		if(((x!=sourisX) || (y!=sourisY)) && gauchePresse){
			try{
				fenetre.deselectionnerCase(sourisY, sourisX);
			}
			catch(NullPointerException npe){  }
			sourisX = x;
			sourisY = y;
			try{
				fenetre.selectionnerCase(sourisY, sourisX);;
			}
			catch(NullPointerException npe){  }
			me.getComponent().repaint();
		
		}	
	
	} 
	/* Non implementé  */
    public void mouseMoved(MouseEvent me){
    	
    }  
    
    public void mouseClicked(MouseEvent e){
    }
	

}
