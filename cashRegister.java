import java.io.*;
import java.util.*;

public class cashRegister {

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		Inventory inventory = new Inventory("productlist.txt");	//the inventory we plan on using
		LinkedList<transaction> transactionList = new LinkedList<transaction>();
		
		int state = 0;					//holds state of program
		Scanner keyboard = new Scanner(System.in);
		while(state != 5)				//state 5 is end
		{
			System.out.println("Enter an operation\n1) Display inventory "
								+ "2) Display transactions\n3) Start new transaction"
								+ "\n5) End program");
			state = keyboard.nextInt();
			
			//display inventory
			if(state == 1)
			{
				inventory.displayList();
			}
			//display transactions
			if(state == 2)
			{
				int count = 0;
				boolean end = false;
				transaction placeholder = new transaction(inventory);
				
				while(!end && transactionList.peekFirst() != null)
				{
					placeholder = transactionList.get(count);
					
					placeholder.itemList();
					System.out.println("Tax: " + placeholder.getTax());
					System.out.println("Total: " + placeholder.getTotal() + "\n");
					
					System.out.println("Do you want to see the next transaction? (Y/N)");
					keyboard.nextLine();
					String input = keyboard.nextLine();
					
					if(input.equalsIgnoreCase("Y"))
					{
						count++;
					}
					else if(input.equalsIgnoreCase("N"))
					{
						end = true;
					}
				}
				
			}
			//Start a new transaction
			if(state == 3)
			{
				boolean end = false;
				
				transactionList.add(new transaction(inventory));
				
				transaction temp = transactionList.get(transactionList.size() - 1);
				
				while (!end)
				{
					int possSKU;
					temp.itemList();
					
					System.out.println("Enter an SKU or 0 to exit");
					
					possSKU = keyboard.nextInt();
					
					if(possSKU != 0)
					{
						float next;
						System.out.println("1) Add 2) Remove: ");
						next = keyboard.nextInt();
						
						if(next == 1)
						{
							System.out.println("How many items?");
							float newIn = keyboard.nextFloat();
							
							temp.addItem(possSKU, newIn);
						}
						if(next == 2)
						{
							temp.voidItem(possSKU);
						}					
					}
					else
						end = true;
				}
				
				temp.itemList();
				System.out.println("Tax: " + temp.getTax());
				System.out.println("Total: " + temp.getTotal());
				
			}
			
		}
		inventory.save("productlist.txt");
		keyboard.close();
		
	}

}
