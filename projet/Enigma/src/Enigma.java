
public class Enigma extends Composant
{
    public Cable cable1;

    public Cable cable2;

    /**
     * fait tourner le 1er rotor (cascade pour les autre) (nb singe: super.suivant ;) )
     * 
     */
    public void rotationRotor() 
    {
    }

    /**
     * met c1, c2,c3 en postion initiale de chaque rotor
     * @param c1
     * @param c2
     * @param c3
     */
    public void reglerRotor(char c1, char c2, char c3) 
    {
    }

    /**
     * remplace cable 1 par cable 2 en fonction de nbCable, par un nouvel objet
     * @param nbCable
     * @param lettre1
     * @param lettre2
     */
    public void reglerCable(int nbCable, char lettre1, char lettre2)
    {
    }

    /**
     * creer la chaine de composant (liste doublement chainée)
     * @param r1
     * @param r2
     * @param r3
     * @param reflec
     */
    public Enigma(Rotor r1, Rotor r2, Rotor r3, Reflecteur reflec) 
    {
    }

	@Override
	public char encoder(char c, int sens) {
		// TODO Auto-generated method stub
		return 0;
	}

}
