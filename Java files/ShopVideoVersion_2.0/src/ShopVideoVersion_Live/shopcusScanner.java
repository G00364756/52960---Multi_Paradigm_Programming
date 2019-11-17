package ShopVideoVersion_Live;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ShopVideoVersion.Shop;

public class shopcusScanner {

		public static void readinfo() {
			
			List myList = new ArrayList();
			
			// create the scanner to take in user input
			Scanner scan = new Scanner(System.in);

			// ask the user what their name is and save as a string
			System.out.println("What is your name?");
			String cusname = scan.nextLine();
			
			// ask the user what their budget is and save as a double
			System.out.println("What is your budget?");
			String budget_str = scan.nextLine();
			double budget = Double.valueOf(budget_str);
			
			// ask the user for what they want to buy and save as string
			System.out.println("How many stock products would you like to purchase?");
			String index = scan.nextLine();
			int cusindex = Integer.valueOf(index);
			
			for (int z = 1; z < (cusindex + 1); z++)
			{
			// ask the user for what they want to buy and save as string
			System.out.println("What is product number "+ cusindex + "that you want to buy?");
			String productName = scan.nextLine();
			myList.add(productName);
			// Ask how many they want and save as a integer
			System.out.println("How many " + productName + " do you want?");
			int amount = scan.nextInt();
			myList.add(amount);
			}
			System.out.println(myList);
			scan.close();

		}
		
		public static void main(String[] args) {
			readinfo();
		}
	}

