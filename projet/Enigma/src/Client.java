import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Client 
{
	private BufferedReader br;

	public static void main(String args[])
	{
		Client bob=new Client();
		bob.parcours_dico("bla le petit chien dans la petite maison");
	}
		public Client() {
		super();
	
	}

		
///////////////////////////////////////////////////////////////////////////////////////
		int parcours_dico(String s) 
		{	
			int nb_occurence=0;
			
			ArrayList<String> dico= new ArrayList<String>();
			
					// ACCES AU FICHIER DICO
					
					try {
						br = new BufferedReader(new FileReader("dico.txt"));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
						System.out.println("Impossible de trouver le fichier");
					}
					
					// RECUPERATION DES MOTS
			        String mot = "";      
			            	
					try {
						
						while(mot!= null){
							
							dico.add(mot);
							mot=br.readLine();
						}
						
					} catch (IOException e) {
						e.printStackTrace();System.out.println("Impossible de lire le fichier");
					}
					 
					
					// AFFICHEGE DONNEES 
					
					for(int i=0; i<dico.size();i++)
					{
					System.out.println(dico.get(i));
					}
					
					// SPLIT DE S
					
					 String str[]= s.split(" ");
					 
					 for(int j=0;j<str.length;j++)
					 {
					 System.out.println(str[j]);
					 }
					 
					 // CALCUL DU NB D OCCURENCE
					 
			
					 for(int l=0; l<str.length;l++)
					 {
						 System.out.println("2");
						
						 if(dico.contains(str[l]))
						 {
							 System.out.println("3--------");
							 nb_occurence++;
						 }
						 
					 }	 
						 
					 System.out.println(nb_occurence);
						return nb_occurence;
		}
}
