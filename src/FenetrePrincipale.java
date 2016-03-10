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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class FenetrePrincipale extends JFrame {
	
	private Container pane;
	
	private JTextArea[] tabTextArea;
	
	private JTextField[] tabRotor;
	
	private JLabel c11;
	private JLabel c12;
	private JLabel c21;
	private JLabel c22;
	
	private CableThread thread;
	
	private static final char[][] tabClavier= {
			{'a', 'z','e','r','t','y','u','i','o','p'},
			{'q', 's','d','f','g','h','j','k','l','m'},
			{' ', ' ','w','x','c','v','b','n',' ',' '}
			};
	
	private Enigma enigma;

	public static void main(String[] args) {
		new FenetrePrincipale();

	}

	public FenetrePrincipale()
	{
		super("IHM cool");
		int w = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int h = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setBounds(h/100, w/100, (int)((h/1.5)*1.7), (int)(h/1.5));
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
		JPanelCable p = new JPanelCable() ;
		p.setLayout(new GridLayout(5,1,getWidth()/25,getWidth()/25));
		p.setBorder(BorderFactory.createTitledBorder("Réglage des cables"));
		
		
		//haut
		JPanel cable1= new JPanel();
		this.c11= new JLabel("a");//TODO initialisation
		this.c12= new JLabel("b");
		this.c21= new JLabel("c");//TODO initialisation
		this.c22= new JLabel("d");
		
		cable1.add(c11);
		cable1.add(new JLabel("Cable 1"));
		cable1.add(c12);
		p.add(cable1);
		//millieu
		
		
		
		JPanel[] panelTouches= new JPanel[3];
		
		
		
		for(int j=0; j<3; j++)
		{
			panelTouches[j]=new JPanel();
			panelTouches[j].setLayout(new GridLayout(1,10,getWidth()/75,getWidth()/75));
			for(char i: tabClavier[j])
			{
				MonJLabel t=new MonJLabel(i+"");

				if(i !=' ')
					t.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				
				if(i == c11.getText().charAt(0) || i == c12.getText().charAt(0) || i == c21.getText().charAt(0) || i == c22.getText().charAt(0))
					t.setRelier(true);
				else t.setRelier(false);
				t.addMouseListener(new CableListener());
				
				t.setHorizontalAlignment(JTextField.CENTER);
				
				panelTouches[j].add(t);
			}
			p.add(panelTouches[j]);
		}
		//bas
		JPanel cable2= new JPanel();		
		cable2.add(c21);
		cable2.add(new JLabel("Cable 2"));
		cable2.add(c22);
		p.add(cable2);
		
		//fin
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
					
					Image img = new ImageIcon("./img/rotor.png").getImage();
					for(int i=0;i<3;i++)
						g.drawImage(img, 10+i*(h/3 +h/25), h/11+h/80, h/3,h/3, null);
	
					super.paint(g);
				}
			};
			p.setOpaque(false);
			
		p.setBorder(BorderFactory.createTitledBorder("Réglage des rotors"));
		p.setLayout(new GridLayout(1,3));
		this.tabRotor = new JTextField[3];
		for (int i=0; i<3; i+=1)
		{
			JTextField l= new JTextField("a"); //todo initialiser avec 1ere valeur du rotor
			JPanel pint= new JPanel();
			JPanel centre= new JPanel();
			centre.add(l);
			centre.setOpaque(false);
			this.tabRotor[i]=l;
			
			l.setEditable(false);
			l.setPreferredSize(new Dimension( 25, 25 ));
			l.setHorizontalAlignment(JTextField.CENTER);
			pint.setLayout(new GridLayout(3,3));
			pint.setOpaque(false);
			BoutonRotor listener= new BoutonRotor(l);
			for(int j=0; j<9;j++)
			{
				if(j==4)
				{
					pint.add(centre);
					continue;
				}
				else if(j==3)
				{
					JPanel pan= new JPanel();
					pan.setOpaque(false);
					JButton b=new JButton("-");
					b.addActionListener(listener);
					pan.add(b);
					pint.add(pan);
					continue;
				}
				else if(j==5)
				{
					JPanel pan= new JPanel();
					pan.setOpaque(false);
					JButton b=new JButton("+");
					b.addActionListener(listener);
					pan.add(b);
					pint.add(pan);
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
		
		this.tabTextArea = new JTextArea[2];
		
		p.add(getUnPanelSaisie(true));
		p.add(getUnPanelSaisie(false));

		return p;
	}
	
	private Component getUnPanelSaisie(boolean ediatable) {
		JPanel p= new JPanel();
		p.setLayout(new BorderLayout());
		String titre;
		

		
		JTextArea t1= new JTextArea();
		t1.setEditable(ediatable);
		t1.setLineWrap(true);
		t1.setWrapStyleWord(true);
		
		((AbstractDocument) t1.getDocument()).setDocumentFilter(new MyDocumentFilter());
		
		if(ediatable)
		{
			titre="Entrée";
			this.tabTextArea[0]=t1;
			JButton reglage = new JButton("Convertir avec réglages");//TODO ajouter listener
			JButton nonreglage = new JButton("Convertir sans réglages");//TODO ajouter listener
			JButton fichier = new JButton("Lire un fichier");
			fichier.addActionListener(new FicherListener());
			
			JPanel panelbouton= new JPanel();
			panelbouton.setLayout(new GridLayout(1,2));
			panelbouton.add(fichier);
			panelbouton.add(reglage);
			panelbouton.add(nonreglage);
			
			p.add(panelbouton, BorderLayout.SOUTH);
		}
		else
		{
			titre="Sortie";
			this.tabTextArea[1]=t1;
		}
		
		p.setBorder(BorderFactory.createTitledBorder(titre));
		
		JScrollPane scroll= new JScrollPane(t1);//on ajoute une barre de scrolling a la zone de texte
		p.add(scroll, BorderLayout.CENTER);
		return p;
	}
	
	public class FicherListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			//Create a file chooser
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(FenetrePrincipale.this);//return 1 si erreur
			
			if(returnVal == 0) //si l'utilisateur a chois un fichier
			{
				File f=fc.getSelectedFile();
				String str="";
				try
				{
					InputStream ips=new FileInputStream(f.getAbsolutePath()); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					int lu;
					while ((lu=br.read()) != -1)
					{
						if( (lu>=97 && lu<=122)/*a à z*/ || lu==32 /*espace*/ )
							str+=(char)lu;
					}
					br.close();				
				}  
				catch (Exception e)
				{
					System.err.println(e.toString());
				}
				FenetrePrincipale.this.tabTextArea[0].setText(str);
			}
			
			
			
			
		}
		
		
	}//fin FichierListener
	
	public class BoutonRotor implements ActionListener
	{
		
		private JTextField text;
		
		public BoutonRotor(JTextField t)
		{
			super();
			this.text=t;
			
			
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int lettre=((int)this.text.getText().charAt(0));
			if(  e.getActionCommand() == "+" && lettre <122)
				this.text.setText(  (char)(lettre+1)+""   );
			else if(e.getActionCommand() == "-" && lettre >97)
				this.text.setText(  (char)(((int)this.text.getText().charAt(0))-1)+""   );

			
			
			
			
		}
	}
	
	public class CableListener implements MouseListener
	{
		

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			MonJLabel source= (MonJLabel) arg0.getSource();
			
			if(source.getText().charAt(0)==' ')
					return;
			
			char i='*';
			
			//si on a un cable en main
			if(i == c11.getText().charAt(0) || i == c12.getText().charAt(0) || i == c21.getText().charAt(0) || i == c22.getText().charAt(0))
			{
				i=source.getText().charAt(0);
				
				//si la lettre est deja relier a un cable on anulle
				if(i == c11.getText().charAt(0) || i == c12.getText().charAt(0) || i == c21.getText().charAt(0) || i == c22.getText().charAt(0))
					return;
				
				source.setRelier(true);
				if('*'==c11.getText().charAt(0))
				{
					c11.setText(i+"");
				}
				else if('*'==c12.getText().charAt(0))
				{
					c12.setText(i+"");
				}
				else if('*'==c21.getText().charAt(0))
				{
					c21.setText(i+"");
				}
				else if('*'==c22.getText().charAt(0))
				{
					c22.setText(i+"");
				}
				FenetrePrincipale.this.thread.finir();
				
			}
			else
			{//on met la lettre a *
				char lettre= source.getText().charAt(0);
				source.setRelier(false);
				if(lettre==c11.getText().charAt(0))
				{
					c11.setText(i+"");
					this.lancerThread();
				}
				else if(lettre==c12.getText().charAt(0))
				{
					c12.setText(i+"");
					this.lancerThread();
				}
				else if(lettre==c21.getText().charAt(0))
				{
					c21.setText("*");
					this.lancerThread();
				}
				else if(lettre==c22.getText().charAt(0))
				{
					c22.setText("*");
					this.lancerThread();
				}
				
				
			}
			FenetrePrincipale.this.repaint();
		}
		
		
		private void lancerThread()
		{
			FenetrePrincipale.this.thread= new CableThread("thread de refraichissement de l'ihm");
			FenetrePrincipale.this.thread.start();
		}
		
	}
	
	public class MonJLabel extends JLabel
	{
		private boolean relier;
		
		public MonJLabel(String string) {
			super(string);
		}

		public boolean getRelier()
		{
			return relier;
		}
		
		public void setRelier(boolean r)
		{
			this.relier=r;
		}
		
	}
	
	public class JPanelCable extends JPanel
	{
		private static final long serialVersionUID = 1L;

		private int x;
		
		private int y;
		
		public MonMouseListener m;
		
		public JPanelCable()
		{
			super();
			m=new MonMouseListener();
			this.addMouseMotionListener(m);
		}
		
		public void paint(Graphics g)
		{
			super.paint(g);
			
			//cable 1
			g.setColor(Color.BLUE);
			char[] lettre= {c11.getText().charAt(0), c12.getText().charAt(0)};
			int[] x = new int[2];
			int[] y = new int[2];
			
			for(int k=0; k<2; k++)
			{
				if(lettre[k]=='*')
				{
					x[k]=this.x;
					y[k]=this.y;
					continue;
				}
				for(int i=0; i<3; i++)
					for(int j=0; j<10; j++)
					{
						if(lettre[k]== tabClavier[i][j])
						{
							x[k]=j*(getWidth()/10)+getWidth()/20;
							y[k]=(1+i)*(getHeight()/5) + (getHeight()/10) + (getHeight()/100);
						}
					}
				
			}
			
			g.drawLine(x[0], y[0], x[1], y[1]);
			
			//cable 2
			
			
			g.setColor(Color.RED);
			char[] lettre2= {c21.getText().charAt(0), c22.getText().charAt(0)};
			
			for(int k=0; k<2; k++)
			{
				if(lettre2[k]=='*')
				{
					x[k]=this.x;
					y[k]=this.y;
					continue;
				}
				for(int i=0; i<3; i++)
					for(int j=0; j<10; j++)
					{
						if(lettre2[k]== tabClavier[i][j])
						{
							x[k]=j*(getWidth()/10)+getWidth()/20;
							y[k]=(1+i)*(getHeight()/5) + (getHeight()/10) + (getHeight()/100) +2;
						}
					}
				
			}
			
			g.drawLine(x[0], y[0], x[1], y[1]);
			
			
		}
		

		public class MonMouseListener implements MouseMotionListener 
		{
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
			}
	
			@Override
			public void mouseMoved(MouseEvent e) {
				JPanelCable.this.y=e.getY();
				JPanelCable.this.x=e.getX();
				
			}
		}

	}
	
	public class CableThread extends Thread
	{
		private boolean continu=true;
		
		public CableThread(String string) {
			super(string);
		}

		public void finir()
		{
			this.continu=false;
		}
		
		public void run(){
			while(continu)
			{
				FenetrePrincipale.this.repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class MyDocumentFilter extends DocumentFilter {

	    @Override
	    public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
	        for (int n = string.length(); n > 0; n--) {//si copier coller
	            char c = string.charAt(n - 1);//on fait les caractere 1 par 1
	            if ((c>=97 && c<=122) || c == ' ') //si une lettre minuscule ou un espace
	                super.replace(fb, i, i1, String.valueOf(c), as);//on ajoute au document
	        }
	        
	    }

	    @Override
	    public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
	        super.remove(fb, i, i1);
	    }

	    @Override
	    public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {
	        super.insertString(fb, i, string, as);

	    }
	}
	
}
