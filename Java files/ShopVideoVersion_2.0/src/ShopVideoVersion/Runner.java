package ShopVideoVersion;

public class Runner {

	public static void main(String[] args) {
		Shop shop = new Shop("src/ShopVideoVersion/stock.csv");
		Customer james = new Customer("src/ShopVideoVersion/customer.csv");
		shop.processOrder(james);
		shop.enoughtoBuy(james,shop);
		//shop.DiditSet(james);
	}
}
