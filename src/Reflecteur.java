public class Reflecteur extends ComposantCryptage 
{

	public Reflecteur ()
	{
		this(null);
	}
	
	public Reflecteur (char[] alettresRotor)
	{
		super.lettresRotor = alettresRotor;
	}
	
	/**
	 * encoder selon l'alphabet comme un rotor, et passer a l'element precedant en cahngeant le sens (machin.precedant.encoder)
	 */
	@Override
	public char encoder(char c, int sens)
	{
		/* reflecteur C http://www.codesandciphers.org.uk/enigma/rotorspec.htm */
		
		char[] codeLettresRotor = {'f','v','p','j','i','a','o','y','e','d','r','z','x','w','g','c','t','k','u','q','s','b','n','m','h','l'};
		
		return 0;
	}

}
