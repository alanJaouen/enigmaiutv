
public class Rotor extends ComposantCryptage
{
    public int positionRotor;

    public int positionInitiale;

    public int positionCourante;

    public Rotor()
    {
    	this.positionRotor = 0;
    	this.positionInitiale = 0;
    	this.positionCourante = 0;
    }
    
    public Rotor(int pRotor, int pInit, int pCourante)
    {
    	this.positionRotor = pRotor;
    	this.positionInitiale = pInit;
    	this.positionCourante = pCourante;
    }
    
    public void positionInitiale(char c) 
    {
    }

    public char encoder(char c, int sens) 
    {
    }

    public void tourner() 
    {
    }



	@Override
	public char encoder(char c) 
	{
		return 0;
	}

}
