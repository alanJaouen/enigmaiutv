
public class Enigma extends Composant
{
    private Cable cable1;

    private Cable cable2;

    
	/* SUPPRIME MOI!*/
	public static void main(String args[])
	{
		Enigma bob= new Enigma();
		String chaine= "je suis trop fort je pese dans le game allez tous crever en enfer";
		chaine=bob.encoder(chaine);
		System.out.println(chaine);
		bob= new Enigma();
		
		System.out.println(bob.encoder(chaine));
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
			c=this.cable1.encode(c);
			c=this.cable2.encode(c);
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

	@Override
	public String toString() {
		return "Enigma [cable1=" + cable1 + ", cable2=" + cable2 + "]";
	}

}
