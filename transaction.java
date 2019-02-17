import java.util.*;
public class transaction 
{
	private LinkedList<Integer> skuList = new LinkedList<Integer>();//linked list that stores the SKU
	private LinkedList<Float> quantList = new LinkedList<Float>();//another linked list that stores the quantity
	
	private int itemCount = 0;	//number of items
	private float subtotal = 0;	//storage of subtotal
	
	private Inventory inventory;	//where we will store the inventory that we wish to use
	
	public transaction(Inventory referTo)
	{
		inventory = referTo;
	}
	
	//get subtotal
	public float getSubtotal()
	{
		return subtotal;
	}
	//get tax
	public float getTax()
	{
		return subtotal * .0725f;		//calculate the tax
	}
	//get total
	public float getTotal()
	{
		return subtotal + getTax();
	}
	
	//method that prints out list of items
	public void itemList()
	{
		//temp linkedlists(maybe)
		//run through the whole list
		for(int count = 0; count < skuList.size(); count++)
		{
			if(inventory.search(skuList.get(count)) != -1)
			{
				productItem item = inventory.returnItem(inventory.search(skuList.get(count)));	//item to be printed
				
				float quantity = quantList.get(count);											//get the number of items			
				float priceFor = item.returnPrice() * quantity;									//price of item * number of items
				
				
				//print the folowing : ItemName numberOfItems priceForNumberOfItems
				System.out.println(item.displayName() + " " + quantity + " "
									+ priceFor);
			}
			else
				System.out.println("Item not found");
		}
		System.out.println("Items: " + itemCount);
		System.out.println("\nSubtotal: " + subtotal);
	}
	
	//method that adds items to the list
	public void addItem(int SKU, float quantity)
	{
		int location = inventory.search(SKU);
		int index = skuList.indexOf(SKU);
		//if the item doesn't exist or we don't have enough in stock
		if(location == -1 || !inventory.returnItem(location).instock(quantity))
			return;	//if top statement is true, there is nothing to add
		
		//if we make it here, then the item does exist and we have enough
		//
		
		//if the item doesn't exist yet in the list
		
		skuList.add(SKU);
		quantList.add(quantity);
			
		productItem item = inventory.returnItem(location);
		
		float price = item.returnPrice() * quantity;
		
		subtotal += price;
		itemCount += quantity;
		item.removeStock(quantity);
			
	}
	//method that removes items
	public void voidItem(int SKU)
	{
		int index = skuList.indexOf(SKU);
		float quantity;
		//if we have the value of sku in the list
		if(index != -1)
		{
			skuList.remove(index);
			quantity = quantList.remove(index);
		}
		else		//else, we didn't find our item
			return;
		
		productItem item = inventory.returnItem(inventory.search(SKU));
		float price = item.returnPrice() * quantity;
		
		subtotal -= price;
		itemCount -= quantity;
		
		item.addStock(quantity);
	}
}
