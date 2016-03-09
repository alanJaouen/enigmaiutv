import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetrePrincipale extends JFrame {
	
	private Container pane;
	
	private TextArea[] tabTextArea;
	
	private JTextField[] tabRotor;
	
	private Enigma enigma;

	public static void main(String[] args) {
		new FenetrePrincipale();

	}

	public FenetrePrincipale()
	{
		super("IHM cool");
		int w = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int h = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setBounds(0, 0, (int)((h/1.5)*1.7), (int)(h/1.5));
		this.pane=this.getContentPane();
		
		this.pane.add(this.getJPanel());
		
		
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	private Component getJPanel() {
		JPanel p= new JPanel();
		p.setLayout(new GridLayout(1,2));
		p.add(this.getPanelSaisie());
		p.add(this.getPanelParam());
		return p;
	}

	private Component getPanelParam() {
		JPanel p= new JPanel();
		p.setLayout(new GridLayout(2,1));
		p.add(this.getPanelRotor());
		p.add(this.getPanelCable());
		return p;
	}

	private Component getPanelCable() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5,1));
		p.setBorder(BorderFactory.createTitledBorder("Réglage des cables"));
		
		
		//haut
		JPanel cable1= new JPanel();
		JLabel c11= new JLabel("M");//TODO variable de classe + methode paint de p l.70
		JLabel c12= new JLabel("N");//TODO mangey
		
		cable1.add(c11);
		cable1.add(new JLabel("Cable 1"));
		cable1.add(c12);
		p.add(cable1);
		//millieu
		char[][] tabClavier= {
			{'A', 'Z','E','R','T','Y','U','I','O','P'},
			{'q', 's','d','f','g','h','j','k','l','m'},
			{' ', ' ','w','x','c','v','b','n',' ',' '}
			};
		
		
		JPanel[] panelTouches= new JPanel[3];
		
		
		
		for(int j=0; j<3; j++)
		{
			panelTouches[j]=new JPanel();
			panelTouches[j].setLayout(new GridLayout(1,10,5,5));
			for(char i: tabClavier[j])
			{
				JLabel t=new JLabel(i+"");
				t.setHorizontalAlignment(JTextField.CENTER);
				panelTouches[j].add(t);
			}
			p.add(panelTouches[j]);
		}
		//bas
		p.add(new JLabel());
		return p;
	}

	private Component getPanelRotor() {
		JPanel p = new JPanel()
			{
				private static final long serialVersionUID = 1L;

				public void paint(Graphics g)
				{
					int w = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2;
					int h = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2;
					super.paint(g);
					Image img = new ImageIcon("./img/rotor.png").getImage();
					for(int i=0;i<3;i++)
						g.drawImage(img, 10+i*(h/3 +h/25), h/11+h/80, h/3,h/3, null);
	
					
				}
			};
			
			
		p.setBorder(BorderFactory.createTitledBorder("Réglage des rotors"));
		p.setLayout(new GridLayout(1,3));
		this.tabRotor = new JTextField[3];
		for (int i=0; i<3; i+=1)
		{
			JTextField l= new JTextField(); //todo initialiser avec 1ere valeur du rotor
			JPanel pint= new JPanel();
			JPanel centre= new JPanel();
			centre.add(l);
			this.tabRotor[i]=l;
			
			l.setEnabled(true);
			l.setPreferredSize(new Dimension( 25, 25 ));
			l.setHorizontalAlignment(JTextField.CENTER);
			pint.setLayout(new GridLayout(3,3));
			pint.setOpaque(false);
			
			for(int j=0; j<9;j++)
			{
				if(j==4)
				{
					pint.add(centre);
					continue;
				}
				JLabel k= new JLabel("")
					{
						private static final long serialVersionUID = 1L;

						public void paint(Graphics g)
						{
							return;
						}
					};
				pint.add(k);
			}
			
			
			p.add(pint);
			
		}
		
		return p;
	}

	private Component getPanelSaisie() {
		JPanel p= new JPanel();
		p.setLayout(new GridLayout(2,1));
		
		this.tabTextArea = new TextArea[2];
		
		p.add(getUnPanelSaisie(true));
		p.add(getUnPanelSaisie(false));

		return p;
	}
	
	private Component getUnPanelSaisie(boolean ediatable) {
		JPanel p= new JPanel();
		p.setLayout(new BorderLayout());
		String titre;
		

		
		TextArea t1= new TextArea();
		t1.setEditable(ediatable);
		
		if(ediatable)
		{
			titre="Entrée";
			this.tabTextArea[0]=t1;
			JButton valider = new JButton("Valider");//TODO ajouter listener
			JButton fichier = new JButton("Lire un fichier");//TODO ajouter listener
			
			JPanel panelbouton= new JPanel();
			panelbouton.setLayout(new GridLayout(1,2));
			panelbouton.add(fichier);
			panelbouton.add(valider);
			
			p.add(panelbouton, BorderLayout.SOUTH);
		}
		else
		{
			titre="Sortie";
			this.tabTextArea[1]=t1;
		}
		
		p.setBorder(BorderFactory.createTitledBorder(titre));
		p.add(t1, BorderLayout.CENTER);
		
		return p;
	}
}
