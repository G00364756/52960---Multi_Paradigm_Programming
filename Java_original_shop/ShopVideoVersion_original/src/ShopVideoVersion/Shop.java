package ShopVideoVersion;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop {

	private double cash;
	private ArrayList<ProductStock> stock;

	public Shop(String fileName) {
		stock = new ArrayList<>();
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			//System.out.println(lines.get(0));
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

			// do something
			e.printStackTrace();
		}
	}

	public double getCash() {
		return cash;
	}

	public ArrayList<ProductStock> getStock() {
		return stock;
	}
	
	private double findPrice (String name) {
		
		for (ProductStock productStock : stock) {
			Product p = productStock.getProduct();
			if (p.getName().equals(name)) {
				return p.getPrice();
			}
		}
		return -1;
	}
	
	public void processOrder(Customer c) {
		for (ProductStock productStock :  c.getShoppingList()) {
			Product p = productStock.getProduct();
			int quantity = productStock.getQuantity();
			double price  = findPrice(p.getName());
			System.out.println(p.getName() + " costs " + price);
		
			p.setPrice(price);
			System.out.println(p);
		}
	}

//	public void totalofOrder(Customer c) {
//		for (ProductStock productStock : stock) {
//			Product p = productStock.getProduct();
//			System.out.println(p.getName());
//			int quantity = productStock.getQuantity();
//			double price  = findPrice(p.getName());
//			System.out.println(p.getName() + " costs " + price);
//		
//			p.setPrice(price);
//		}
//	}
	
	@Override
	public String toString() {
		return "Shop [cash=" + cash + ", stock=" + stock + "]";
	}

	public static void main(String[] args) {
		Shop shop = new Shop("src/ShopVideoVersion/stock.csv");
		Customer james = new Customer("src/ShopVideoVersion/customer.csv");
		shop.processOrder(james);
		
		//System.out.println(shop);
		//System.out.println(james);
	}

}
