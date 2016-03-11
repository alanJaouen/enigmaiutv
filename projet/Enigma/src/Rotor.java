import java.util.Arrays;

/**
 * https://en.wikipedia.org/wiki/Enigma_rotor_details
 * 
 */

public class Rotor extends ComposantCryptage
{

    public int positionInitiale;

    public int positionCourante;


    /* 1er rotor = {'e','k','m','f','l','g','d','q','v','z','n','t','o','w','y','h','x','u','s','p','a','i','b','r','c','j'} 
     * 2eme rotor = {'a','j','d','k','s','i','r','u','x','b','l','h','w','t','m','c','q','g','z','n','p','y','f','v','o','e'}
     * 3eme rotor = {'b','d','f','h','j','l','c','p','r','t','x','v','z','n','y','e','i','w','g','a','k','m','u','s','q','o'}
     */
    public Rotor( int pInit, int pCourante, char[] alettresRotor)
    {	
    	super();
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
     * si sens = 1,  retourne c encod� selon l'alphabet et la position du rotor sans faire tourner le rotor sinon retourne c
     */
    public char encoder(char c, int sens) 
    {
<<<<<<< .mine
    	
=======
    	if(sens==1)
    	{
    		
    		
    		
    		return c;
    	}
    	else 
    		return c;
>>>>>>> .r20
    }

    /**
     * fais tourner le rotor en faisant attention de revenir au debut quand on arrive a la fin
     * et faire tourner les rotor suivant si on revient a la position initiale (nb instanceof pour le bug du reflecteur)
     */
    public void tourner() 
    {
    	if(this.positionCourante==lettresRotor.length)
    	{	
    		
    		this.positionCourante=0;
    		
    	}
    	
    	else
    	{
    		this.positionCourante++;
    	}
    	
    	if(this.positionCourante==this.positionInitiale)//TODO les 2 suivants
    	{	
    		Object o=super.getComposantSuivant();
    		
    		if( o instanceof Rotor)
    		{
    			((Rotor) o).tourner();
    		}
    	}
    }

    
}
