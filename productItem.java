/*
 * Name:Juan L. Bernal-Quintero
 * Project: Cash Register Project for CS3700
 * 
 * productItem: this class creates an object that stores information
 * 		relevant to what would be necessary to do transactions and maintain
 * 		an inventory
 * 
 * variables: 	SKU - private int - the ID number for inventory search.
 * 			  	productName - private String - this is the name of the product.
 * 			  	price - private float - product's price.
 * 			  	inStock - private float - the number of said item remaining.
 * 					it is a float so things with decimals(ex. weight) can be counted.
 * 
 * constructors: one constructor is left blank so that it can be filled in later
 * 		the other sets up the object completely
 * 
 * 
 */
import java.util.*;

public class productItem {

	private int SKU = 0;
	private String productName;
	private float price;
	private float inStock;
	
	
	public productItem()
	{
	}
	
	
	public productItem(int sku, String name,  
			float price, float stock)
	{
		SKU = sku;
		productName = name;
		this.price = price;
		inStock = stock;
	}
	
	
	//sku
	public void setSKU(int sku)
	{
		SKU = sku;
		return;
	}
	public int displaySKU()
	{
		return SKU;
	}
	
	
	//name
	public void setName(String name)
	{
		productName = name;
		return;
	}
	public String displayName()
	{
		return productName;
	}
	
	
	//price
	public void setPrice(float price)
	{
		this.price = price;
		return;
	}
	public float returnPrice()
	{
		return price;
	}
	
	//stock
	public void setStock(float stock)
	{
		inStock = stock;
		return;
	}
	public float displayStock()
	{
		return inStock;
	}
	public void removeStock(float howMuch)
	{
		if(instock(howMuch))
		{
			inStock -= howMuch;
			return;
		}
		else
			return;
			
	}
	public void addStock(float howMuch)
	{
		inStock += howMuch;
		return;
	}
	public boolean instock(float stock)
	{
		if(inStock < stock)
		{
			return false;
		}
		else
			return true;
	}
	
	//displays everything
	public String displayItem()
	{
		String priceForm = String.format("%.02f", price);
		
		return productName + " " + priceForm + "  " + SKU + " " + inStock;
	}
}
