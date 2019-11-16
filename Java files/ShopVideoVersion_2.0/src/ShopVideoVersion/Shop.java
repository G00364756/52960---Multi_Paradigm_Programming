package ShopVideoVersion;

import java.io.*; 
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Shop {
	String newLine = System.getProperty("line.separator"); // Putting in a new line when printing to console: https://stackoverflow.com/questions/4008223/print-in-new-line-java
	private double cash;
	private ArrayList<ProductStock> stock;

	public Shop(String fileName) {
		stock = new ArrayList<>();
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			System.out.println("The shop has €"+lines.get(0)+" in cash before any purchase is processed."+ newLine);
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
			}
		}		
		}
		//System.out.println("modifyQuantity() inititiated");
}
	
	public void processOrder(Customer c) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		double total = 0;
		System.out.println(newLine);
		System.out.println("The shop's product pricelist: ");
		for (ProductStock productStock : c.getShoppingList()) {
			Product p = productStock.getProduct();
			int quantity = productStock.getQuantity();
			double price = findPrice(p.getName());
			total =  total + (quantity*price);
			System.out.println(p.getName() + " costs " + price);
			
			p.setPrice(price);
		}
		c.setTotal(total);
		//System.out.println(newLine);
		System.out.println("The total cost of " + c.getName()+"'s order is €"+ formatter.format(c.total) + newLine);
	}
	
	public int returnTrigger(Customer c, Shop s) {
		int x = 1;
		int y = 1;
		for (ProductStock productStock : stock) {
			Product stockp = productStock.getProduct();
			int stockquantity = productStock.getQuantity();
			for (ProductStock cusproductStock : c.getShoppingList()) {
				Product cusp = cusproductStock.getProduct();
				int cusquantity = cusproductStock.getQuantity();
				if (stockp.getName().equals(cusp.getName())) {
					if (stockquantity < cusquantity) {
						System.out.println("Shop stock quantity:     " + stockquantity);
						System.out.println("Customer order quantity: " + cusquantity);
						x = 0;
						//System.out.println("x for " +stockp.getName() +" is "+ x);
					}
					else if(stockquantity >= cusquantity) {
						System.out.println("Shop stock quantity:     " + stockquantity);
						System.out.println("Customer order quantity: " + cusquantity);
						y = 1;
						//System.out.println("y for " +stockp.getName() +" is "+ y);
					}
				}
				}
			}
		int z = x * y;
		//System.out.println("Trigger method results in: " + z);
		System.out.println(newLine);
		return z;
}
	
	public void enoughtoBuy(Customer c, Shop s) {
		double result = 0;
		double cash = getCash();
		double budget = c.getBudget();
		double total = c.getTotal();
		if ( budget < total) {
			System.out.println(c.getName()+" has a budget of €" + budget);
			System.out.println(c.getName()+" does not have enough money to purchase the order of €" + total);
		}
		else {
			int trigger = returnTrigger(c,s); // develop method to determine value of trigger
			for (int z = 0; z < 1; z++) {
				if (trigger == 1)
				{
					cash = cash + total;
					s.setCash(cash);
					modifyQuantity(c);
					modifyCSV(s);
					result = s.getCash();
					System.out.println("The shop has €"+ result +" in cash before any purchase is processed.");
				}
				else {
					for (ProductStock productStock : stock) {
						Product stockp = productStock.getProduct();
						int stockquantity = productStock.getQuantity();
						for (ProductStock cusproductStock : c.getShoppingList()) {
							Product cusp = cusproductStock.getProduct();
							int cusquantity = cusproductStock.getQuantity();
							if (stockp.getName().equals(cusp.getName())) {
								if (stockquantity < cusquantity) {
									System.out.println("The shop has " + stockquantity + " of " +stockp.getName() + " remaining.");
									System.out.println(c.getName()+" has ordered " + cusquantity + " of " +cusp.getName());
									System.out.println("The shop does not have enough stock of " + stockp.getName()+ " to fill the customer's order." + newLine);
								}
							}
						}
					}
				}
				//System.out.println("EnoughtoBuy() inititiated");
			}
		}
	}
	
	public void modifyCSV(Shop s) {
		String shopcash = String.valueOf(s.getCash());
		List<String> test = new ArrayList<>();
		for (ProductStock productStock : stock) {
			Product stockp = productStock.getProduct();
			int stockquantity = productStock.getQuantity();
			test.add(stockp.getName() + ",");
			test.add(stockp.getPrice() + ",");
			test.add(String.valueOf(stockquantity));
			test.add("\r\n");
		}
		
		try {
	    FileWriter fw = new FileWriter("/Users/aoconnor.CER/Desktop/52960---Multi_Paradigm_Programming/Java files/ShopVideoVersion_2.0/src/ShopVideoVersion/stock.csv");
	    BufferedWriter bw = new BufferedWriter(fw);

	    String collect = test.stream().collect(Collectors.joining(""));
	    System.out.println("The shop has the remaining inventory:");
	    System.out.println(collect);

	    fw.write(shopcash);
	    fw.write("\r\n");
	    //bw.newLine();
	    fw.write(collect);
	    fw.close();
	    //System.out.println("modifyCSV() inititiated");
		}
		
		catch (IOException e){
			System.out.println("Error while writing to csv");
			e.printStackTrace();
		}
	}
	
}

