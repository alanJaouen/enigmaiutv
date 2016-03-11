import java.util.Arrays;

/**
 * https://en.wikipedia.org/wiki/Enigma_rotor_details
 * 
 */

public class Rotor extends ComposantCryptage
{

    public int positionInitiale;

    public int positionCourante;
    

	public char[] alp1= {'e','k','m','f','l','g','d','q','v','z','n','t','o','w','y','h','x','u','s','p','a','i','b','r','c','j'};
	public char[] alp2= {'a','j','d','k','s','i','r','u','x','b','l','h','w','t','m','c','q','g','z','n','p','y','f','v','o','e'};
    public char[] alp3= {'b','d','f','h','j','l','c','p','r','t','x','v','z','n','y','e','i','w','g','a','k','m','u','s','q','o'};
     
    
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

    	if(sens==1)
    	{
    		if(this.equals("r1")) // A L AIDE ALAN J AI PAS COMPRIS POURQUOI CA MARCHE
    		{
    			for(int i=0;i<lettresRotor.length;i++)
    	    		if(lettresRotor[i]==c)
    	    		{
    	    			i=i+this.positionCourante;
    	    			if(i>alp1.length)
    	    			{
    	    				i=i-alp1.length;
    	    			}
    	    			c=alp1[i];
    	    			
    	    			
    	    		}
    		}
    		else if(this.equals("r2")) 
    		{
    			for(int i=0;i<lettresRotor.length;i++)
    	    		if(lettresRotor[i]==c)
    	    		{
    	    			i=i+this.positionCourante;
    	    			if(i>alp2.length)
    	    			{
    	    				i=i-alp2.length;
    	    			}
    	    			c=alp2[i];
    	    			
    	    			
    	    		}
    		}
    		else if(this.equals("r3")) 
    		{
    			for(int i=0;i<lettresRotor.length;i++)
    	    		if(lettresRotor[i]==c)
    	    		{
    	    			i=i+this.positionCourante;
    	    			if(i>alp3.length)
    	    			{
    	    				i=i-alp3.length;
    	    			}
    	    			c=alp3[i];
    	    			
    	    			
    	    		}
    		}
    		
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
    	if(this.positionCourante==lettresRotor.length)
    	{	
    		
    		this.positionCourante=0;
    		
    	}
    	
    	else
    	{
    		this.positionCourante++;
    	}
    	
    	if(this.positionCourante==this.positionInitiale)
    	{	
    		Object o=super.getComposantSuivant();
    		Object p=super.getComposantSuivant().getComposantSuivant();
    		if( o instanceof Rotor)
    		{
    			((Rotor) o).tourner();
    			
    			if( p instanceof Rotor)
    			{
    				((Rotor) p).tourner();
    			}
    		}
    		
    	}
    }

    
}
