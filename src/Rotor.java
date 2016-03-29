import java.util.Arrays;

/**
 * https://en.wikipedia.org/wiki/Enigma_rotor_details
 * 
 */

public class Rotor extends ComposantCryptage
{
	
	
    private int positionInitiale;

    private int positionCourante;     
    
    public Rotor( int pInit, char[] alettresRotor)
    {	
    	super();
    	this.positionInitiale = pInit;
    	this.positionCourante = pInit;
    	super.setLettresRotor(alettresRotor);
    }
    
    /**
     * position initiale = index de c dans tableau du rotor
     * position courante pareil
     */
    public void positionInitiale(char c) 
    {	
    	for(int i=0;i<super.getLettresRotor().length;i++)
    		if(super.getLettresRotor()[i]==c)
    		{
    			this.positionInitiale=i;
    			this.positionCourante=i;
    			System.out.println("posI: "+this.positionCourante);
    			return;
    		}
    	
    		
    }

    @Override
	public String toString() {
		return "Rotor [positionInitiale=" + positionInitiale
				+ ", positionCourante=" + positionCourante + ", lettresRotor="
				+ Arrays.toString(super.getLettresRotor()) + "]";
	}

    public char encoder(char c, int sens) 
    {
    	
    	if(sens==1)//on passe de decoder a coder	
    	{
    		 return super.getComposantSuivant().encoder(super.getLettresRotor()[     ((((int) c)-97) +this.positionCourante)%26      ], sens);
    	}
    	else if(sens == -1)
    	{
    		int rang=0;
    		for(int i= 0; i<super.getLettresRotor().length ; i++)
    			if(super.getLettresRotor()[i] == c)
    			{
    				rang=i-this.positionCourante;
    				break;
    			}
    		
    		if(rang<0)//cas particulier de position courante>rang
    			rang=26+rang;//on retourne simplement a l'autre bout du tableau
    		
    		return super.getComposantPrecedant().encoder((char)((rang%26)+97), sens);

    	}
    	
    	return ' ';
    }

    /**
     * fais tourner le rotor en faisant attention de revenir au debut quand on arrive a la fin
     * et faire tourner les rotor suivant si on revient a la position initiale (nb instanceof pour le bug du reflecteur)
     */
    public void tourner() 
    {
    	if(this.positionCourante==super.getLettresRotor().length-1)
    		this.positionCourante=0;
    	else
    		this.positionCourante++;
    	
    	if(this.positionCourante==this.positionInitiale)
    	{	
    		Object o=super.getComposantSuivant();
    		Object p=super.getComposantSuivant().getComposantSuivant();
    		if( o instanceof Rotor)
    		{
    			((Rotor) o).tourner();
    			
    			if( p instanceof Rotor)
    				((Rotor) p).tourner();
    		}
    		
    	}
    }

    
}
