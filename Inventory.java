/*
 * Name: Juan L. Bernal-Quintero
 * Project: Inventory
 * 
 * Description: This program handles the storage of items with
 * 		information that would be needed by a cash register in
 * 		a store.  The program takes in items from a file and 
 * 		stores them in an array-based list
 */
import java.io.*;
import java.util.*;
import java.util.Scanner;

// This class needs to be made without the main function
public class Inventory {
	
	//setup our array-based list with objects of productItem type(see productItem class for more information)
	//private static List<productItem> productList = new ArrayList<productItem>(); //our list of items
	
	//hash table for SKU
	private productItem skuHash[] = new productItem[100];
	//hash table for Names
	private productItem alphaHash[] = new productItem[100];
	
	
	//constructors
	public Inventory()
	{
		
	}
	public Inventory(String fileName) throws FileNotFoundException
	{
		//this is what we are using to load our values
		Scanner fileScan = new Scanner(new File(fileName));
			
		//while there are more inputs to load(this becomes a problem if an item does not have all these values)
		while(fileScan.hasNext())
		{
			productItem temp = new productItem();
			
			temp.setName(fileScan.next());
			temp.setPrice(fileScan.nextFloat());
			temp.setSKU(fileScan.nextInt());
			temp.setStock(fileScan.nextFloat());
			
			int key = temp.displaySKU() % 100;		//key used to check for item
			//Mid Squares algorithm
			//Collision Resolution if necessary
			//Store element in skuHash
			
			//using simple mod for now
			
			//while collisions occur
			while(skuHash[key] != null && skuHash[key].displaySKU() != -1)
			{
				key += 3;		//using linear probing with steps of 3
				key = key % 100;
			}
			
			skuHash[key] = new productItem();
			//save the item in the slot with key value
				skuHash[key].setName(temp.displayName());
				skuHash[key].setPrice(temp.returnPrice());
				skuHash[key].setSKU(temp.displaySKU());
				skuHash[key].setStock(temp.displayStock());
			
			/*
			productList.add(new productItem());										//create a new entry into our list
			productList.get(productList.size() -1).setName(fileScan.next());		//load the first thing we receive into the Name slot of this product
			productList.get(productList.size() -1).setPrice(fileScan.nextFloat());	//load the next float into the Price slot of this product
			productList.get(productList.size() -1).setSKU(fileScan.nextInt());		//load the next int into the SKU slot of the product
			productList.get(productList.size() -1).setStock(fileScan.nextFloat());	// and finally load the stock into the product
			*/
		}
		
		fileScan.close();
		
		/*
		//Put the list into a hash table
		for(int count = 0; count < productList.size(); count++)
		{
			int key;		//key used to check for item
			//Mid Squares algorithm
			//Colision Resolution if necessary
			//Store element in hashtable
			
			//using simple mod for now
			key = productList.get(count).displaySKU() % 100;	//this calculates the key
			
			//while colisions occur
			while(hashtable[key] != NULL ||hashtable[key].getSKU != -1)
				key *= 3;		//using linear probing with steps of 3
				key = key % 100;
			
			//save the item in the slot with key value
			hashtable[key] = productList.get(count);
			
		}
		*/
				
	}
	
	//Writes back to file to save our changes
	public void save(String fileName) throws IOException
	{
		//write to file

		FileWriter save = new FileWriter(fileName);
		for(int count = 0; count < skuHash.length; count++)
		{
			if(skuHash[count] != null && skuHash[count].displaySKU() != -1)
			{
				//prints out a single item in the form Name:Price(Formatted to two decimal places):SKU:Stock
				save.write(skuHash[count].displayItem());
				save.write(" ");
			}
		}
		save.close();
		
	}
	
	/*
	 * Method: addElement
	 * Inputs: String - name: the desired name of the item
	 * 		   float - price: the price of the item
	 * 		   int - SKU: the sku of the item
	 * 		   float - stock: the stock of the item
	 * 
	 * Description:
	 * 		This method creates a new productItem object and puts it in the hashtable(s).
	 * 		receives the inputs to set the object and uses the hash function along with the collision
	 *		resolution to find an empty spot to place the new productItem
	 */
	public void addElement(String name, float price, int SKU, float stock)
	{
		productItem temp = new productItem();
		
		temp.setName(name);
		temp.setPrice(price);
		temp.setSKU(SKU);
		temp.setStock(stock);
		
		int key = temp.displaySKU() % 100;		//key used to check for item
		//Mid Squares algorithm
		//Colision Resolution if necessary
		//Store element in hashtable
		
		//using simple mod for now
		
		//while collisions occur
		while(skuHash[key] != null && skuHash[key].displaySKU() != -1)
		{
			key += 3;		//using linear probing with steps of 3
			key = key % 100;
		}
		
		//save the item in the slot with key value
			skuHash[key].setName(temp.displayName());
			skuHash[key].setPrice(temp.returnPrice());
			skuHash[key].setSKU(temp.displaySKU());
			skuHash[key].setStock(temp.displayStock());
		
	}
	
	/*
	 * Method: removeElement
	 * Inputs: int - target: the target SKU to of the item we would like to remove
	 * Description: 
	 * 		search and destroy.  looks though the hash table and removes the item.
	 * 		if the method does not find the item, it returns a message saying it could not be found
	 */
	public void removeElement(int target)
	{
		int key = target % 100;		//key used to check for item
		String name;	//name of item to be removed
		//Mid Squares algorithm
		//Colision Resolution if necessary
		//Store element in hashtable
		
		//using simple mod for now
		
		//while collisions occur
		while(skuHash[key] != null)
		{
			//if the sku doesn't match
			if(skuHash[key].displaySKU() != target)
			{
				//check for collisions
				key += 3;		//using linear probing with steps of 3
				key = key % 100;
			}
			else		//sku does match
			{
				name = skuHash[key].displayName();
				
				//	
				//search and remove item from alphaHash
				//
			
				skuHash[key].setSKU(-1);
				
				return;	//done here
			}

		}
		//if we made it out here, we did not find the item
		System.out.println("Item #"+ target  + " not found.");
		return;
	}
	/*
	 * Method: displayList()
	 * Inputs: none
	 * 
	 * Description:
	 * 		This method prints out all the items in the list
	 * 		using the displayItem function of productList
	 */
	public void displayList()
	{
		//
		for(int count = 0; count < skuHash.length; count++)
		{
			//prints out a single item in the form Name:Price(Formatted to two decimal places):SKU:Stock
			if(skuHash[count] != null && skuHash[count].displaySKU() != -1)
				System.out.println(skuHash[count].displayItem());
		}
		return;
	}
	 
	/*
	 * Method: quickSort()
	 * Inputs: boolean nameOrSKU - this determines whether we are sorting by Name or SKU number
	 * 		   int index1 - sets the original lower index used to calculate the original pivot
	 * 		   int index2 - sets the original high index used to calculate the original pivot
	 * 
	 * Description:
	 * 		This method sorts the list using the quick sort algorithm
	 *
	public static void quickSort(boolean nameOrSKU, int index1, int index2)
	{
		int loIndex = index1;	//the lower bound used to calculate the pivot
		int hiIndex = index2;	// the upper bound used to calculate the pivot
		
		//Sorting by Name
		if(nameOrSKU)
		{
			
			//pivot is calculated by taking the average of the low index and the difference between the high and low index
			String pivot = productList.get(loIndex + (hiIndex - loIndex)/2).displayName();
			
			//while the low index does not cross the high index
			while(loIndex <= hiIndex)
			{
				//find a value on the left of the pivot that is greater than pivot value
				while(productList.get(loIndex).displayName().compareTo(pivot) < 0)
				{
					loIndex++;
				}
				//find a value on the right of the pivot that is smaller than pivot value
				while(productList.get(hiIndex).displayName().compareTo(pivot) > 0)
				{
					hiIndex--;
				}
				
				//if these values should be switched
				if(loIndex <= hiIndex)
				{
					//place holder for the item to be switched
					productItem temp;
					
					temp = productList.get(loIndex);						//store our item in productList(loIndex)
					productList.add(loIndex, productList.get(hiIndex));		//replace the value in the loIndex slot of the list with value in hiIndex slot
					productList.remove(loIndex + 1);						//remove the original value of loIndex slot
					productList.add(hiIndex, temp);							//add the original value of loIndex into the hiIndex slot
					productList.remove(hiIndex + 1);						//remove original value of hiIndex
					
					loIndex++;	//increment loIndex
					hiIndex--;	//increment hiIndex
				}
			}
			//recursion
			
			if(index1 < hiIndex)
				quickSort(true, index1, hiIndex);
			
			if(loIndex < index2)
				quickSort(true, loIndex, index2);
			
		}
		//same procedure as above, only using int values instead of String comparisons
		else
		{
			int pivot = productList.get(loIndex + (hiIndex - loIndex)/2).displaySKU();
			
			while(productList.get(loIndex).displaySKU() < pivot)
			{
				loIndex++;
			}
			while(productList.get(hiIndex).displaySKU() > pivot)
			{
				hiIndex--;
			}
			
			if(loIndex <= hiIndex)
			{
				productItem temp;
				
				temp = productList.get(loIndex);
				productList.add(loIndex, productList.get(hiIndex));
				productList.remove(loIndex + 1);
				productList.add(hiIndex, temp);
				productList.remove(hiIndex + 1);
				
				loIndex++;
				hiIndex--;
			}
			
			//recursion
			if(index1 < hiIndex)
				quickSort(false, index1, hiIndex);
			
			if(loIndex < index2)
			{
				quickSort(false, loIndex, index2);
			}
			
			
		}
	}
		*/	//quicksort has been made obsolete by hash tables i think
	
	/*
	 * Method: searchName()
	 * Inputs: String target - the string that we are looking for
	 * 
	 * Description: 
	 * 		This method searches through our list using a binary search.(now using hash table(possibly))
	 * 		This method sorts through the values in the list using the 
	 * 		quick sort method above and then compares values using String
	 * 		comparison.  If it finds the target string it returns the index
	 * 		where the target string was found.
	 */
	public static void search(String target)
	{
		/* this is the old form
		//We must sort our algorithm first before using a binary search
		quickSort(true, 0, productList.size() - 1);
		
		int leftBound = 0; 							//original left bound(the leftmost element of the list)
		int rightBound = productList.size();		//original right bound(the last element of the list)
		int center = (rightBound + leftBound)/2;	//the average of the two bounds where the search begins
		
		//while left bound and right bound do not cross
		while(center > leftBound && center < rightBound)
		{
			//if we found our target string
			if(productList.get(center).displayName().equals(target))
			{
				return center;	//return the index of where the string was found				
			}
			//if the string we are comparing our target to is "bigger"
			else if(productList.get(center).displayName().compareTo(target) > 0)
			{
				rightBound = center;	// look to the left side of the center
			}
			//only other case is that the string we are comparing our target to was "smaller"
			else
				leftBound = center;		//if this is the case, search the right side of center
			
			center = (rightBound + leftBound)/2; //recalculate our center			
		}
		//if the right bound and the left bound(as is the case in the first and last elements of the array), we must search that case as well
		if(productList.get(center).displayName().equals(target))
			return center;	//if equal, return index
		//if the code makes it here, the target was not found
		else
			return -1;	//return an error
			*/
		
		//
		//use the alpha hash to find our item
		//send an error code if not found
		//
		
	}
	
	//We might want to remove the sort in searchName since we won't be changing the order of items anymore
	
	public int search(int SKU)
	{
		//Use the hash function
		//reference hash table
		//do colision resolution if not there
		//if not found, return -1
		int key = SKU % 100;
		
		while(skuHash[key] != null)
		{
			//if the sku doesn't match
			if(skuHash[key].displaySKU() != SKU)
			{
				//check for collisions
				key += 3;		//using linear probing with steps of 3
				key = key % 100;
			}
			else		//sku does match
			{
				return key;
			}

		}
		
		//item not found error code
		return -1;
		
	}
	
	public productItem returnItem(int location)
	{
		return skuHash[location];
	}

}
