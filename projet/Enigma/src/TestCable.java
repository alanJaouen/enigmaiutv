
public class TestCable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TEST DES CONSTRUCTEURS
		Cable ca1= new Cable();
		System.out.println(ca1);
		
		Cable ca2=new Cable('l','m');
		System.out.println(ca2);
		
		// TEST AVEC LES LETTRES 
		System.out.println(ca2.encode('l'));
		System.out.println(ca2.encode('k'));
		
		// TEST EQUALS
		System.out.println(ca1.equals(ca2));
		System.out.println(ca1.equals(ca1));
	}

}
