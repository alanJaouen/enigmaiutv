
public class Cable 
{
    public char lettre1;

    public char lettre2;

    public Cable()
    {
    	this.lettre1 = ' ';
    	this.lettre2 = ' ';
    }
    
    public Cable(char l1, char l2)
    {
    	this.lettre1 = l1;
    	this.lettre2 = l2;
    }
    
    public void setLettre1(char l)
    {
    	this.lettre1 = l;
    }
    
    public void setLettre2(char l)
    {
    	this.lettre2 = l;
    }
    
    public char getLettre1()
    {
    	return this.lettre1;
    }
    
    public char getLettre2()
    {
    	return this.lettre2;
    }
    
    public char encode(char c) 
    {
    	if(c==this.lettre1)
    		return this.lettre2;
    	if(c==this.lettre2)
    		return this.lettre1;
    	return c;
    }

    public boolean equals(Cable c)
    {
    	if (this == c)
    		if(this.lettre1 == c.lettre1)
    			if(this.lettre2 == c.lettre2)
    				return true;
    	return false;
    }
    
	@Override
	public String toString() {
		return "Cable [lettre1=" + lettre1 + ", lettre2=" + lettre2 + "]";
	}
    
}
