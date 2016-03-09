import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
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
		this.setBounds(w/10, h/10, (int)(w/1.5), (int)(h/1.5));
		this.pane=this.getContentPane();
		
		this.pane.add(this.getJPanel());
		
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
		p.setBackground(Color.green);
		return p;
	}

	private Component getPanelCable() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("Réglage des cables"));
		return p;
	}

	private Component getPanelRotor() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("Réglage des rotors"));
		p.setLayout(new GridLayout(1,3));
		this.tabRotor = new JTextField[3];
		p.setBackground(Color.pink);
		for (int i=0; i<3; i+=1)
		{
			JTextField l= new JTextField("i="+i); //todo initialiser avec 1ere valeur du rotor
			JPanel pint= new JPanel();
			this.tabRotor[i]=l;
			l.setEnabled(true);
			pint.setLayout(new GridLayout(3,3));
			pint.setOpaque(false);
			
			for(int j=0; j<9;j++)
			{
				if(j==4)
				{
					pint.add(l);
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
			this.tabTextArea[0]=t1; //TODO ajouter Listener
		}
		else
		{
			titre="Sortie";
			this.tabTextArea[1]=t1; //TODO ajouter Listener
		}
		
		p.setBorder(BorderFactory.createTitledBorder(titre));
		p.add(t1, BorderLayout.CENTER);
		
		return p;
	}
}
