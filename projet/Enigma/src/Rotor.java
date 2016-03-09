import java.util.Arrays;

/**
 * https://en.wikipedia.org/wiki/Enigma_rotor_details
 * 
 */

public class Rotor extends ComposantCryptage
{

    public int positionInitiale;

    public int positionCourante;


    
    public Rotor( int pInit, int pCourante, char[] alettresRotor)
    {
    	this.positionInitiale = pInit;
    	this.positionCourante = pCourante;
    	super.lettresRotor= alettresRotor;
    }
    
    /**
     * position initiale = index de c dans tableau du rotor
     * position courante pareil
     */
    public void positionInitiale(char c) 
    {	
    	for(int i=0;i<lettresRotor.length;i++)
    		if(lettresRotor[i]==c)
    		{
    			this.positionInitiale=i;
    			this.positionCourante=i;
    			return;
    		}
    	
    		
    }

    @Override
	public String toString() {
		return "Rotor [positionInitiale=" + positionInitiale
				+ ", positionCourante=" + positionCourante + ", lettresRotor="
				+ Arrays.toString(lettresRotor) + "]";
	}

	/**
     * si sens = 1,  retourne c encodé selon l'alphabet et la position du rotor sans faire tourner le rotor sinon retourne c
     */
    public char encoder(char c, int sens) 
    {
    	if(sens==1)
    	{
    		
    		
    		
    		return c;
    	}
    	else 
    		return c;
    }

    /**
     * fais tourner le rotor en faisant attention de revenir au debut quand on arrive a la fin
     * et faire tourner les rotor suivant si on revient a la position initiale (nb instanceof pour le bug du reflecteur)
     */
    public void tourner() 
    {
    	
    }

    
}
