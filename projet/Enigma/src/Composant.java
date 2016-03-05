import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("2dd05c17-8e8a-46b6-94ce-bcba77ce1649")
public abstract class Composant {
    @objid ("bfa25dbc-d081-470c-97cf-7392ef1c5cea")
    private Composant composantSuivant;

    @objid ("2dd75901-1e6f-4652-a70d-4da012c9ab56")
    private Composant composantPrecedant;

    @objid ("ff8ad837-e66b-4a4c-9569-f714093b849b")
    public abstract char encoder(char c);

    @objid ("0103d81a-7aeb-4da5-a237-0459583d5ce2")
    Composant getComposantSuivant() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.composantSuivant;
    }

    @objid ("7a13d86c-ef51-4204-b6ba-f4ea79c7d0b1")
    void setComposantSuivant(Composant value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.composantSuivant = value;
    }

    @objid ("e72c0d19-fd30-49a8-a09b-852968364d7e")
    Composant getComposantPrecedant() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.composantPrecedant;
    }

    @objid ("fcd34df5-ead6-4dcc-856a-b7fb348b738b")
    void setComposantPrecedant(Composant value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.composantPrecedant = value;
    }

}
