package shop;
import java.util.ArrayList;
import java.util.List;

public class CartItem {
	private String name;
	private String ID;
	private double price;
	private String Description;
	
	private static List<Product> items = new ArrayList<Product>();	
	
	CartItem(String name, String ID, double price, String Description)
	{
		this.name = name;
		this.ID = ID;
		this.price = price;
		this.Description = Description;
	}

	
	public CartItem(Object name2, Object iD2, Object object, Object description2) {
		// TODO Auto-generated constructor stub
	}


	public List<Product> getItems() {
		return items;
	}
	
	
	public static void addItem(Product item) {
		items.add(item);
	}
	 public double getPrice(){
	    	return price;
	    }
	    public String getDescription(){
	    	return Description;
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
}

