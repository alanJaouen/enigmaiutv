
public abstract class Composant 
{
    private Composant composantSuivant;

    private Composant composantPrecedant;

    public abstract char encoder(char c);
    
    
    
    Composant getComposantSuivant() 
    {
        return this.composantSuivant;
    }

    void setComposantSuivant(Composant value) 
    {
        this.composantSuivant = value;
    }

    Composant getComposantPrecedant() 
    {
        return this.composantPrecedant;
    }

    void setComposantPrecedant(Composant value) 
    {
        this.composantPrecedant = value;
    }

}
