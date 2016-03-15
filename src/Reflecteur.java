public class Reflecteur extends ComposantCryptage 
{
	
	public Reflecteur ()
	{
		char[] tab= {'f','v','p','j','i','a','o','y','e','d','r','z','x','w','g','c','t','k','u','q','s','b','n','m','h','l'};
		//char[] tab= {'a','b','c'};
		super.setLettresRotor(tab);
	}
	
	/**
	 * encoder selon l'alphabet comme un rotor, et passer a l'element precedant en cahngeant le sens (machin.precedant.encoder)
	 */
	@Override
	public char encoder(char c, int sens)
	{
		/* reflecteur C http://www.codesandciphers.org.uk/enigma/rotorspec.htm */
		//System.out.println(c+" devient "+super.getLettresRotor()[((int) c)-97]);
		return super.getComposantPrecedant().encoder(super.getLettresRotor()[((int) c)-97], -1);

	}

}
