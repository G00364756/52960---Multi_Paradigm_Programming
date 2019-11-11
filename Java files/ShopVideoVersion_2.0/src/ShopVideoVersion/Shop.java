package ShopVideoVersion;

import java.io.*; 
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Shop {

	private double cash;
	private ArrayList<ProductStock> stock;

	public Shop(String fileName) {
		stock = new ArrayList<>();
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			System.out.println(lines.get(0));
			cash = Double.parseDouble(lines.get(0));
			// i am removing at index 0 as it is the only one treated differently
			lines.remove(0);
			for (String line : lines) {
				String[] arr = line.split(",");
				String name = arr[0];
				double price = Double.parseDouble(arr[1]);
				int quantity = Integer.parseInt(arr[2].trim());
				Product p = new Product(name, price);
				ProductStock s = new ProductStock(p, quantity);
				stock.add(s);
			}
		}

		catch (IOException e) {

			System.out.println("Error while reading in csv");
			e.printStackTrace();
		}
	}

	public double getCash() {
		return cash;
	}
	
	public void setCash(double cash) {
		this.cash = cash;
	}

	public ArrayList<ProductStock> getStock() {
		return stock;
	}

	public void modifyCSV(Shop s) {
		String shopcash = String.valueOf(s.getCash());
		List<String> test = new ArrayList<>();
		for (ProductStock productStock : stock) {
			Product stockp = productStock.getProduct();
			int stockquantity = productStock.getQuantity();
			test.add(stockp.getName() + ",");
			test.add(String.valueOf(stockquantity));
			test.add("\r\n");
		}
		
		try {
	    FileWriter fw = new FileWriter("/Users/aoconnor.CER/Desktop/52960---Multi_Paradigm_Programming/Java files/ShopVideoVersion_2.0/src/ShopVideoVersion/test.csv");
	    BufferedWriter bw = new BufferedWriter(fw);

	    String collect = test.stream().collect(Collectors.joining(""));
	    System.out.println(collect);

	    fw.write(shopcash);
	    fw.write("\r\n");
	    //bw.newLine();
	    fw.write(collect);
	    fw.close();
	    System.out.println("modifyCSV() inititiated");
		}
		
		catch (IOException e){
			System.out.println("Error while writing to csv");
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String toString() {
		return "Shop [cash=" + cash + ", stock=" + stock + "]";
	}

	public static void main(String[] args) {
		Shop shop = new Shop("src/ShopVideoVersion/stock.csv");
	}
	
	private double findPrice(String name) {
		
		for (ProductStock productStock : stock) {
			Product p =productStock.getProduct();
			if (p.getName().equals(name)) {
				return p.getPrice();
			}
		}
		
		return -1;
	}
	
	public void modifyQuantity(Customer c) {
		
		for (ProductStock productStock : stock) {
			Product stockp = productStock.getProduct();
			int stockquantity = productStock.getQuantity();
			for (ProductStock cusproductStock : c.getShoppingList()) {
				Product cusp = cusproductStock.getProduct();
				int cusquantity = cusproductStock.getQuantity();
				if (stockp.getName().equals(cusp.getName())) {
					stockquantity = stockquantity - cusquantity;
					productStock.setQuantity(stockquantity);
					//System.out.println(cusquantity + " changed to " + stockquantity);
			}
		}		
		//int newquantity = productStock.getQuantity();
		//System.out.println("This better be right" + newquantity);
		}
		System.out.println("modifyQuantity() inititiated");
}
	
	public void processOrder(Customer c) {
		double total = 0;
		for (ProductStock productStock : c.getShoppingList()) {
			Product p = productStock.getProduct();
			int quantity = productStock.getQuantity();
			double price = findPrice(p.getName());
			total =  total + (quantity*price);
			System.out.println(p.getName() + " costs " + price);
			
			p.setPrice(price);
		}
		c.setTotal(total);
		System.out.println("Total costs " + c.total);
	}
	
//	public void DiditSet(Customer c) {
//		String name;
//		int quantity;
//		double price;
//		
//		for (ProductStock productStock : stock) {
//			Product p = productStock.getProduct();
//			name = p.getName();
//			quantity = productStock.getQuantity();
//			price = findPrice(p.getName());
//			System.out.println("Product name is " + name + " Product Quantity is now "+ quantity + " and product price is " + price);
//	}
//	}
	
	public void enoughtoBuy(Customer c, Shop s) {
		double result = 0;
		double cash = getCash();
		double budget = c.getBudget();
		double total = c.getTotal();
		if ( budget < total) {
			System.out.println(c.getName()+"has a budget of" + budget + " does not have enough money to purchase the order of " + total);
		}
		else {
			int trigger = 1; // develop method to determine value of trigger
			for (int z = 0; z < 1; z++) {
				if (trigger == 1)
				{
					cash = cash + total;
					s.setCash(cash);
					modifyQuantity(c);
					modifyCSV(s);	
				}
				System.out.println("EnoughtoBuy() inititiated");
			}
		}
	}
}

