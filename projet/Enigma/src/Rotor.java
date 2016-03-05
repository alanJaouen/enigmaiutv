import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("6141490c-4679-44b7-ae18-7163cf71598b")
public class Rotor extends ComposantCryptage {
    @objid ("303eba76-0291-4995-839c-b9522eeb45d7")
    public int positionRotor;

    @objid ("cd9ca03d-8a0f-4997-a0ff-edc0917b4ce3")
    public int positionInitiale;

    @objid ("4b38cc53-b3c6-4784-bc2c-58de4fccfe79")
    public int positionCourante;

    @objid ("11f7c4d7-60bd-4988-86e9-ae94cbd540e0")
    public void positionInitiale(char c) {
    }

    @objid ("8620f804-6303-403a-90a7-8ffe59496b90")
    public synchronized char encoder(char c, int sens) {
    }

    @objid ("30824316-60ed-499d-ae4f-732e45e06f58")
    public void tourner() {
    }

}
