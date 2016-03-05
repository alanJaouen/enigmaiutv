import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("6e74f38a-fbf2-4320-8a90-b218a8ce28bb")
public class Enigma extends Composant {
    @objid ("fd52486e-2d26-4ab9-bac0-99a932669dfd")
    public Cable cable1;

    @objid ("8aeb1895-285c-4777-ad92-7b6cd67dfb75")
    public Cable cable2;

    @objid ("a5160b22-fde9-4c02-9647-f2fecf01ae17")
    public void rotationRotor(Rotor r) {
    }

    @objid ("7f331199-b632-4fa1-8f73-b684d25814e8")
    public void reglerRotor(char c1, char c2, char c3) {
    }

    @objid ("b1327287-6038-4cec-b99c-9d8df0001b15")
    public void reglerCable(int nbCable, char lettre1, char lettre2) {
    }

    @objid ("ba04b75f-eb0f-4227-914e-09dd021f990e")
    public Enigma() {
    }

    @objid ("ffe2526c-0616-4585-872d-d272450e1c14")
    public Enigma(Rotor r1, Rotor r2, Rotor r3, Reflecteur reflec) {
    }

}
