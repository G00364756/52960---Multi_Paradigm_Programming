// Shop - Adapted from Dominic Carr's code for the module assignment.
// Name: Aidan O'Connor
// Date created: 16/10/2019
// Student Num: G00364756

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Below are all the structs that will be used in this c file.

struct Product {
	char* name;
	double price;
};

struct ProductStock {
	struct Product product;
	int quantity;
	int index;
};

struct Shop {
	double cash;
	struct ProductStock stock[20];
	int index;
};

struct Customer {
	char* cusname;
	double budget;
	struct ProductStock shoppingList[10];
	int index;
	double total;
};

// This function creates the Shop struct from a csv file.
struct Shop createAndStockShop()
{
    FILE * fp;
    char * line = NULL;
    size_t len = 0;
    ssize_t read;

    fp = fopen("stock.csv", "r");
    if (fp == NULL)
        exit(EXIT_FAILURE);
	
	getline(&line, &len, fp);
	double cashInShop = atof(line);
	struct Shop shop = { cashInShop };

    while ((read = getline(&line, &len, fp)) != -1) 
	{
		char *n = strtok(line, ",");
		char *p = strtok(NULL, ",");
		char *q = strtok(NULL, ",");
		int quantity = atoi(q);
		double price = atof(p);
		char *name = malloc(sizeof(char) * 50);
		strcpy(name, n);
		struct Product product = { name, price };
		struct ProductStock stockItem = { product, quantity };
		shop.stock[shop.index++] = stockItem;
    }
	return shop;
}

//This function assigns the price of the Shop prduct to the Customer Product.
void findProductPrice(struct Shop s, struct Customer* c)
{
	for (int j = 0; j < s.index; j++)
	{	
		for (int i = 0; i < c->index; i++)
		{
			char *name = s.stock[j].product.name;
			//printf(" name = %s \n", name);
			char *cusname = c->shoppingList[i].product.name;
			//printf(" cusname = %s \n", cusname);
			
			if (strcmp(name,cusname) == 0)
			{
				//printf(" price = %.2f \n", s.stock[j].product.price);
				c->shoppingList[i].product.price = s.stock[j].product.price;
				//printf(" price = %.2f \n", c->shoppingList[i].product.price);
			}
		}
	}
}

// This function creates the Customer from a csv file.
struct Customer createCustomer(struct Shop s, struct Customer c) 
{
    FILE * fp;
    char * line = NULL;
    size_t len = 0;
    ssize_t read;

    fp = fopen("order.csv", "r");
    if (fp == NULL)
        exit(EXIT_FAILURE);
	
	read = getline(&line, &len, fp);
	char *buyer = strtok(line, ",");
	char *b = strtok(NULL, ",");
	char *cusname = malloc(sizeof(char) * 50);
	strcpy(cusname, buyer);
	double budget = atof(b);
	struct Customer customer = { cusname, budget };

	while ((read = getline(&line, &len, fp)) != -1) 
	{
		char *na = strtok(line, ",");
		char *qu = strtok(NULL, ",");
		int quantity = atoi(qu);
		char *name = malloc(sizeof(char) * 50);
		strcpy(name, na);
		struct Product product = { name };
		struct ProductStock custOrder = { product, quantity };
		customer.shoppingList[customer.index++] = custOrder;
	}      
return customer;
}			

// void printProduct(struct Product p)
// {
//     printf("-------------\n");
// 	printf("PRODUCT NAME: %s \nPRODUCT PRICE: %.2f \n", p.name, p.price);
// 	printf("-------------\n");
// }

// void printShop(struct Shop s)
// {
// 	printf("Shop has %.2f in cash\n", s.cash);
// 	for (int i = 0; i < s.index; i++)
// 	{
// 		struct Product product = s.stock[i].product;
// 		printProduct(product);
// 		printf("The shop has %d of the above\n", s.stock[i].quantity);
// 	}
// }

// This function gets the total cost of the produce for the customer and determines whether they have enough money to buy the products.
double getTotal(struct Customer c)
{
	double z = 0;
	for (int i = 0; i < c.index; i++)
	{
	double x = c.shoppingList[i].quantity;
	double y = c.shoppingList[i].product.price;
	z = z + (y * x);
	}
	return z;
}

void printCustomer(struct Customer c, struct Customer* custo)
{
	printf("-------------\n");
	printf("CUSTOMER NAME: %s\n-------------\nCUSTOMER BUDGET: %.2f\n", c.cusname, c.budget);
	printf("-------------\n");
	double res;
	for (int i = 0; i < c.index; i++)
	{
		res = getTotal(c);
		custo->total = res;
		printf("CUSTOMER ORDER: %d of %s at a price of %.2f \n", c.shoppingList[i].quantity, c.shoppingList[i].product.name, c.shoppingList[i].product.price);
		printf("-------------\n");
	}
	printf("The cost of this order is: %.2f \n-------------\n", custo->total);
}

void enoughforPurchase(struct Customer c, struct Shop s, struct Shop* ptr_shop)
{
	if (c.total > c.budget)
	{
        printf("%s does not have enough money to purchase the items in his/her shopping list.\n", c.cusname );
		printf("%s has a budget of €%.2f \n", c.cusname, c.budget );
		printf("The items %s's the shopping list come to a toal of €%.2f\n", c.cusname, c.total );
	}
	else
	{
		ptr_shop -> cash = s.cash + c.total;
		for (int j = 0; j < s.index; j++)
		{	
			for (int i = 0; i < c.index; i++)
			{
				char *name = s.stock[j].product.name;
				char *cusname = c.shoppingList[i].product.name;
				if (strcmp(name,cusname) == 0)
				{
					ptr_shop -> stock[j].quantity = s.stock[j].quantity - c.shoppingList[i].quantity;
					printf("Shop has %d of %s remaining\n-----------\n", ptr_shop -> stock[j].quantity, ptr_shop -> stock[j].product.name);	
				}
			}
		}
	}
	printf("The shop's cash is now: %.2f", ptr_shop->cash);
}

void overwrite_csv(struct Shop s, struct Shop* ptr_shop)
{
 	FILE * fp;
    char * line = NULL;
    size_t len = 0;
    ssize_t read;

    fp = fopen("stock.csv", "w+");
	fprintf(fp,"%.2f\n", ptr_shop->cash);
	for (int j = 0; j < ptr_shop->index; j++)
	{
		fprintf(fp,"%s, %.2f, %d \n", ptr_shop -> stock[j].product.name, ptr_shop -> stock[j].product.price, ptr_shop -> stock[j].quantity);
	}
	fclose(fp);
}

int main(void)
{

	struct Shop shop = createAndStockShop();
	struct Customer customer = createCustomer(shop, customer);
	//printShop(shop);
	struct Customer *ptr_customer;
	ptr_customer = &customer;
	findProductPrice(shop, ptr_customer);
	printCustomer(customer, ptr_customer);
	struct Shop *ptr_shop;
	ptr_shop = &shop;
	enoughforPurchase(customer, shop, ptr_shop);
	overwrite_csv(shop, ptr_shop);
	//double price = findProductPrice(shop, "Coke Can");
	//printf("%.2f/n", price);
	
    return 0;
}