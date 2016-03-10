
public class TestRotor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
			char[] q={'e','h','g','j'};
			
			
			// TEST DES CONSTRUCTEURS
		
			Rotor ro2=new Rotor(5,6,q);
			System.out.println(ro2);
			
			// TEST POSITION INITIALE
			ro2.positionInitiale('j');
			System.out.println(ro2);
	
			// TEST ENCODAGE
			
			
			
			
			//TEST TOURNER 
			
			ro2.tourner();
			System.out.println(ro2);
			ro2.tourner();
			System.out.println(ro2); // retour à la position 0
			ro2.tourner();
			System.out.println(ro2);
			ro2.tourner();
			System.out.println(ro2);
			ro2.tourner();
			System.out.println(ro2);
			ro2.tourner();
			System.out.println(ro2);

			
			
	}

}
