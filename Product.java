package shop;
public class Product {
	
	private String name;
	private String ID;
	private double price;
	private String Description;
	
	Product(String name, String ID, double price, String Description)
	{
		this.name = name;
		this.ID = ID;
		this.price = price;
		this.Description = Description;
	}
	public String getName()
	{
		return name;
	}
	public String getID()
	{
		int count = 0;
		int IdNum;
		IdNum = ++count;
		return ID;
	}
	public String toString() {
        return "Name of the Product: " + this.name +"\n"
        		+"Price of the product: €" + this.price +"\n"
                	+ "Description of the product : " + this.Description +"\n"
                		+ "Id of the product : R00" + this.ID;
    }
	public void print()
    {
    	System.out.println(toString());
    }
    public double getPrice(){
    	return price;
    }
    public String getDescription(){
    	return Description;
    }
}

