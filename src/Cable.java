
public class Cable 
{
    private char lettre1;

    private char lettre2;

    public Cable()
    {
    	this(' ',' ');
    }
    
    public Cable(char l1, char l2)
    {
    	this.lettre1 = l1;
    	this.lettre2 = l2;
    }
    
    
    public char encode(char c) 
    {
    	if(c==this.lettre1)
    		return this.lettre1;
    	if(c==this.lettre2)
    		return this.lettre2;
    	return c;
    }


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cable other = (Cable) obj;
		if (lettre1 != other.lettre1)
			return false;
		if (lettre2 != other.lettre2)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cable [lettre1=" + lettre1 + ", lettre2=" + lettre2 + "]";
	}
    
}
