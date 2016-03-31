import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Enigma extends Composant
{
    private Cable cable1;

    private Cable cable2;

	private BufferedReader br;
	
	private FenetreWait avanc; 
	
	private ArrayList<String> dico;
	
	private int nb_mot;
    
    public int getNb_mot() {
		return nb_mot;
	}

	/**
     * fait tourner le 1er rotor (cascade pour les autre) (nb singe: super.suivant ;) )
     * 
     */
    public void rotationRotor() 
    {
    	((Rotor) super.getComposantSuivant()).tourner();
    }

    /**
     * met c1, c2,c3 en postion initiale de chaque rotor
     * @param c1
     * @param c2
     * @param c3
     */
    public void reglerRotor(char c1, char c2, char c3) 
    {
    	Composant r=this;
    	
    	char[] reglages={ c1,c2,c3};
    			
    	for (int i=0; i<3; i++)
    	{
    		r=((Rotor) r.getComposantSuivant());
    		((Rotor)r).positionInitiale(reglages[i]);
    	}
    }

    /**
     * remplace cable 1 par cable 2 en fonction de nbCable, par un nouvel objet
     * @param nbCable
     * @param lettre1
     * @param lettre2
     */
    public void reglerCable(int nbCable, char lettre1, char lettre2)
    {
    	switch(nbCable)
    	{
    	case 1:
    		this.cable1= new Cable(lettre1, lettre2);
    		break;
    	case 2:
    		this.cable2= new Cable(lettre1, lettre2);
    		break;
    	}
    }

    /**
     * creer la chaine de composant (liste doublement chain�e)
     * @param r1
     * @param r2
     * @param r3
     * @param reflec
     * 
     * https://en.wikipedia.org/wiki/Enigma_rotor_details
     */
    public Enigma() 
    {
    	super();
    	
    	final char[][] alp= {{'d','m','t','w','s','i','l','r','u','y','q','n','k','f','e','j','c','a','z','b','p','g','x','o','h','v'},
    			{'h','q','z','g','p','j','t','m','o','b','l','n','c','i','f','d','y','a','w','v','e','u','s','r','k','x'},
    			{'u','q','n','t','l','s','z','f','m','r','e','h','d','p','x','k','i','b','v','y','g','j','c','w','o','a'}};
        
    	Composant c=this;
    	Composant r=null;
    	
        for(int i=0; i<4; i++)
        {
        	switch (i)
        	{
        	case 3:
        		r=new Reflecteur();
            	c.setComposantSuivant(r);
            	r.setComposantPrecedant(c);
            	c=r;
            	break;
            default:
            	r=new Rotor(0,alp[i]);
            	c.setComposantSuivant(r);
            	r.setComposantPrecedant(c);
            	c=r;
        	}
        }
        
        
        //les cables
        this.reglerCable(1, 'a', 'h');
        this.reglerCable(2, 'm', 'k');
        
        this.dico= new ArrayList<String>();
    }

	@Override
	public char encoder(char c, int sens) {
		c=this.cable1.encode(c);
		c=this.cable2.encode(c);
		switch (sens)
		{
		case 1:
			return super.getComposantSuivant().encoder(c, sens);
		case -1:
			((Rotor)super.getComposantSuivant()).tourner();
			
			return c;
		}
		return '&';
	}
	public char encoder(char c)
	{
		return this.encoder(c, 1);
	}
	
	public String encoder(String chaine)
	{
		String result="";
		
		for(int i=0; i<chaine.length(); i++)
		{
			int ch= (int)(chaine.charAt(i));
			if(chaine.charAt(i) == ' ')
			{
				result=result+' ';
				continue;
			}
				
			if(ch <97 || ch > 122)
				continue;
			
			char c=this.encoder(chaine.charAt(i));
			result=result+c;
		}
		return result;
	}
	
	public String decoder(String chaine) // ne gere pas encore les cables
	{
		int nb_mots = 0;
		int nb_mots_trouve;
		String chaine_crypte="";
		String retour ="";
		//creation d'un tableau simulant les rotors
		//le but est de trouver la configuration puis de l'appliquer une fois tous les elements trouves
		char[] tab_rotor = new char[26];
		for( int i=0; i<tab_rotor.length; i++) 
		{
			tab_rotor[i] = (char)('A' + i-1);
		}
		
		//this.avanc.setAvancement(0);
		
		for(int i=0 ; i<tab_rotor.length ; i++)
		{
			
			int vali=(i*100)/25;
			for(int j=0 ; j<tab_rotor.length ; j++)
			{
				int valj=(j*(100/25))/25;
				
				for(int k=0 ; k<tab_rotor.length ; k++)
				{
					this.avanc.setAvancement(vali+valj+  (((k*(100/25))/25)/25)  );
					reglerRotor(tab_rotor[i], tab_rotor[j], tab_rotor[k]) ;
					chaine_crypte = encoder(chaine);
					// la chaine est decoder d'une certaine façon, comparer les mots avec ceux du dictionnaires pour voir la cohérence
					nb_mots_trouve = parcours_dico(chaine_crypte);
					
					if(nb_mots_trouve > nb_mots)
					{
						nb_mots = nb_mots_trouve;
						retour = chaine_crypte;
					}
				}
			}
		}
		if(nb_mots < 10)
			retour="Pas assez de correspondance avec le dictionnaire pour proposer une solution";
		
		this.avanc.dispose();
		this.nb_mot=nb_mots;
		return retour;
	}

	public FenetreWait getAvanc() {
		return avanc;
	}

	public void setAvanc(FenetreWait avanc) {
		this.avanc = avanc;
	}

	public int parcours_dico(String s) 
	{	
				
		if(this.dico.size() == 0)
		{
			// ACCES AU FICHIER DICO
			try {
			br = new BufferedReader(new FileReader("dico.txt"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				System.err.println("Impossible de trouver le fichier");
			}
			
			// RECUPERATION DES MOTS
		    String mot;
		          	
			try {
				while((mot=br.readLine()) != null)
					this.dico.add(mot);
			} 
			catch (IOException e) {
				e.printStackTrace();
				System.err.println("Impossible de lire le fichier");
			}
		}
		
		// SPLIT DE S
		String str[]= s.split(" ");
		
		// CALCUL DU NB D OCCURENCE
		int nb_occurence=0;
		
		for(int i=0; i<str.length;i++)
			if(this.dico.contains(str[i]))
				nb_occurence++;
		
		return nb_occurence;
	}

	@Override
	public String toString() {
		return "Enigma [cable1=" + cable1 + ", cable2=" + cable2 + "]";
	}

}
