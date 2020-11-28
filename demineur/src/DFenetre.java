import javax.swing.*;

import metier.DPartie;
import metier.EtatCase;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class DFenetre extends JFrame implements WindowListener{

	private static final long serialVersionUID = 1L;
	final static int DEBUTANT = 1;
	final static int INTER = 2;
	final static int EXPERT = 3;
	final static int PERSO = 4;


	JMenuBar barreMenus;
	JMenu jeu, options, aPropos;

	public JRadioButtonMenuItem debutant, intermediaire, expert, perso;
	public JMenuItem nouvelle, quitter, aide, createur, design, stat;

	private JLabel minesRestantes;
	private DChronoLabel temps;
	private JPanel nord; 

	private JButton go;
	private DPartie partie;

	private DImageur imageur;
	private DPanneau centre;


	public DFenetre(DPartie p){
		super("Demineur");
		addWindowListener(this);
		menu();
		imageur = new DImageur();
		miseEnPage();

		connecterPartie(p);
	}

	public void connecterPartie(DPartie p){
		partie = p; 

		miseAJourCompteur();
		goCool();

		/* partie centrale : damier */
		if(centre!=null)
			getContentPane().remove(centre);
		centre = new DPanneau(this);

		EcouteurSouris ecouteurSouris = new EcouteurSouris(this);
		centre.addMouseListener(ecouteurSouris);
		centre.addMouseMotionListener(ecouteurSouris);

		getContentPane().add(centre, BorderLayout.CENTER);	

		/* Affichage */

		this.setSize(20*partie.getLargeur() + 6, 20*partie.getHauteur() + 50 + 23 + 25);

		this.setResizable(false);

		this.setVisible(true);

		this.repaint();


	}

	private void menu(){
		/* creation du menu de jeu */
		jeu  = new JMenu("Jeu");
		nouvelle = new JMenuItem("Nouvelle partie");
		nouvelle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
		nouvelle.setToolTipText("Partie avec les mêmes paramètres");
		jeu.add(nouvelle);
		jeu.addSeparator();

		ButtonGroup groupRadio=new ButtonGroup();

		debutant = new JRadioButtonMenuItem("Débutant");
		debutant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_DOWN_MASK));
		debutant.setToolTipText("81 cases 10 mines");

		intermediaire = new JRadioButtonMenuItem("Intermédiaire");
		intermediaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,InputEvent.CTRL_DOWN_MASK));
		intermediaire.setToolTipText("256 cases 40 mines");

		expert = new  JRadioButtonMenuItem("Expert");
		expert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_DOWN_MASK));
		expert.setToolTipText("480 cases 99 mines");

		perso = new  JRadioButtonMenuItem("Personnalisé...");
		perso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_DOWN_MASK));
		perso.setToolTipText("Partie avec vos votres paramètres");

		jeu.add(debutant);
		jeu.add(intermediaire);
		jeu.add(expert);
		jeu.add(perso);

		groupRadio.add(debutant);
		groupRadio.add(intermediaire);
		groupRadio.add(expert);
		groupRadio.add(perso);

		jeu.addSeparator();
		quitter = new JMenuItem("Quitter");
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_DOWN_MASK));

		jeu.add(quitter);


		/* creation du menu de options */
		options = new JMenu("Options");

		design = new JMenuItem("Graphisme");
		design.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_DOWN_MASK));
		design.setToolTipText("Pour choisir le style d'image");
		options.add(design);
		options.addSeparator();

		stat = new JMenuItem("Statistiques");
		stat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK));
		stat.setToolTipText("Pour connaitre les scores");
		options.add(stat);


		/* creation du menu A propos */
		aPropos = new JMenu("?");

		aide = new JMenuItem("Aide");
		aide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_DOWN_MASK));
		aide.setToolTipText("Pour obtenir de l'aide");
		aPropos.add(aide);

		aPropos.addSeparator();

		createur = new JMenuItem("Créateurs");
		createur.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_DOWN_MASK));
		createur.setToolTipText("Par qui ?");
		aPropos.add(createur);


		/* ajout des menus à la barre */
		barreMenus = new JMenuBar();
		barreMenus.add(jeu);
		barreMenus.add(options);
		barreMenus.add(aPropos);
		this.setJMenuBar(barreMenus);
	}

	public void ecouterMenu(){
		debutant.addActionListener(al -> actionMenuPerformed(al));
		intermediaire.addActionListener(al -> actionMenuPerformed(al));
		expert.addActionListener(al -> actionMenuPerformed(al));
		perso.addActionListener(al -> actionMenuPerformed(al));
		nouvelle.addActionListener(al -> actionMenuPerformed(al));
		quitter.addActionListener(al -> actionMenuPerformed(al));
		design.addActionListener(al -> actionMenuPerformed(al));
		stat.addActionListener(al -> actionMenuPerformed(al));
		aide.addActionListener(al -> actionMenuPerformed(al));
		createur.addActionListener(al -> actionMenuPerformed(al));

	}

	public JMenuItem getNouvelle(){
		return nouvelle;
	}

	public JMenuItem getDebutant(){
		return debutant;
	}

	public JMenuItem getIntermediaire(){
		return intermediaire;
	}

	public JMenuItem getExpert(){
		return expert;
	}

	public JMenuItem getPerso(){
		return perso;
	}

	public JMenuItem getDesign(){
		return design;
	}

	public JMenuItem getQuitter(){
		return quitter;
	}	

	public JMenuItem getAide(){
		return aide;
	}

	public JMenuItem getCreateur(){
		return createur;
	}

	public int getHauteur(){
		return partie.getHauteur();
	}

	public int getLargeur(){
		return partie.getLargeur();
	}

	public int getMines(){
		return partie.getMines();
	}	

	private void miseEnPage(){
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout(5, 5));

		/* partie haute de l'IHM */
		nord = new JPanel();
		nord.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(5,0,1,0);

		/* en haut a gauche */
		minesRestantes = new JLabel("00");
		//nord.add(minesRestantes, BorderLayout.WEST);
		nord.add(minesRestantes, gbc);
		/* centre */
		go = new JButton();

		goCool();
		nord.add(go, gbc);	

		temps = new DChronoLabel();	
		nord.add(temps,gbc);



		c.add(nord, BorderLayout.NORTH);


	}

	public void setGo() {
		go.addActionListener(e -> {
			initChrono();
			nouvellePartie(getHauteur(), getLargeur(), getMines());
			connecterPartie(getPartie());
		});
	}

	public DPanneau getPanneauCentral(){
		return centre;
	}

	public DImageur getImageur(){
		return imageur;
	}

	public void goPerdu(){
		go.setIcon(new ImageIcon(imageur.getRepertoire()+"/Perdu.GIF"));
	}

	public void goGagne(){
		go.setIcon(new ImageIcon(imageur.getRepertoire()+"/Gagne.GIF"));
	}

	public void goOups(){
		go.setIcon(new ImageIcon(imageur.getRepertoire()+"/Oups.GIF"));
	}

	public void goCool(){
		go.setIcon(new ImageIcon(imageur.getRepertoire()+"/Cool.GIF"));
	}

	@SuppressWarnings("deprecation")
	public void miseAJourCompteur(){
		int nb = partie.getMines()
				-partie.nbrDrapeau();
		Integer integer = new Integer(nb);

		if((nb>9) || (nb<0))
			minesRestantes.setText(integer.toString());
		else
			minesRestantes.setText("0"+integer.toString());

	}

	public void lancerChrono(){
		if(!temps.estActif())
			temps.getThread().start();
	}

	public void arretChrono(){
		temps.stop();	
	}

	public void initChrono(){
		temps.initChrono();
	}

	public void pauseChrono(){
		temps.mettreEnPause();	
	}

	public void repriseChrono(){
		temps.enleverPause();
	}

	public int getChrono(){
		return temps.getTime();
	}

	public ImageIcon getIcon(EtatCase e) {
		return imageur.getIcon(e);
	}

	public DPartie getPartie() {
		return this.partie;
	}

	public boolean perdu(){
		return partie.perdu();
	}

	public boolean gagne(){
		return partie.gagne();
	}

	public void selectionnerCase(int y, int x) {
		partie.selectionnerCase(y, x);
	}

	public void deselectionnerCase(int y, int x) {
		partie.deselectionnerCase(y, x);
	}

	public void devoilerCase(int i,int j){
		partie.devoilerCase(i, j);
	}

	public void drapeauAction(int i, int j){
		partie.drapeauAction(i, j);
	}

	public boolean yaDrapeauSurCase(int y, int x) {
		return partie.yaDrapeauSurCase(y, x);
	}

	public void nouvellePartie(int h, int l, int nb){
		partie.nouvellePartie(h, l, nb);
	}

	@SuppressWarnings("unused")
	public void actionMenuPerformed(ActionEvent ae){
		if(ae.getSource() == getNouvelle()){
			arretChrono();
			initChrono();
			nouvellePartie(getHauteur(),
					getLargeur(),
					getMines());
			connecterPartie(getPartie());
		}
		if(ae.getSource() == getDebutant()){
			arretChrono();
			initChrono();
			nouvellePartie(9,9,10);
			connecterPartie(getPartie());
		}

		if(ae.getSource() == getIntermediaire()){
			arretChrono();
			initChrono();
			nouvellePartie(16,16,40);
			connecterPartie(getPartie());
		}	
		if(ae.getSource() == getExpert()){
			arretChrono();
			initChrono();
			nouvellePartie(16,30,99);
			connecterPartie(getPartie());
		}

		if(ae.getSource() == getPerso()){
			Pref pref = new Pref(this);

		}

		if(ae.getSource() == getDesign()){
			DImageChooser choix = 
					new DImageChooser(getImageur(),
							getPanneauCentral() );
		}

		if(ae.getSource() == getQuitter())
			System.exit(0);

		if(ae.getSource()== getAide()){
			File f = new File("resources/Aide");
			Aide a = new Aide(f);;
		}
		if(ae.getSource()==getCreateur()) 
			JOptionPane.showMessageDialog(this,
					" Réalisé par Igor DAURIAC et Nicolas FRANCOIS, Projet IHM"
					,"Créateurs...",JOptionPane.INFORMATION_MESSAGE);

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		repriseChrono();
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		pauseChrono();
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		repriseChrono();

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		pauseChrono();
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}


}
