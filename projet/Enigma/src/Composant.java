
public abstract class Composant {
    private Composant composantSuivant;

    private Composant composantPrecedant;

    public abstract char encoder(char c, int sens);

    public Composant getComposantSuivant() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.composantSuivant;
    }

    public void setComposantSuivant(Composant value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.composantSuivant = value;
    }

    public Composant getComposantPrecedant() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.composantPrecedant;
    }

    public void setComposantPrecedant(Composant value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.composantPrecedant = value;
    }

}
