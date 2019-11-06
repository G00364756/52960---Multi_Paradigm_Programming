// Shop - Adapted from Dominic Carr's code for the module assignment.
// Name: Aidan O'Connor
// Date created: 16/10/2019
// Student Num: G00364756

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

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
};

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

double findProductPrice(struct Shop s,struct Customer c)
{
	for (int i = 0; i < c.index; i++)
	{
		struct Product product = s.stock[i].product;
		char *name = product.name;
		struct Product cusproduct = c.shoppingList[i].product;
		char *cusname = cusproduct.name;
		if (strcmp(name,cusproduct.name) == 0)
		{
			return s.stock[i].product.price;
		}
	}
	return -1;
}

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
		double price = findProductPrice(s,c);
		struct Product product = { name , price};
		struct ProductStock custOrder = { product, quantity };
		customer.shoppingList[customer.index++] = custOrder;
	}      
return customer;
}			

void printProduct(struct Product p)
{
    printf("-------------\n");
	printf("PRODUCT NAME: %s \nPRODUCT PRICE: %.2f\n", p.name, p.price);
	printf("-------------\n");
}

void printShop(struct Shop s)
{
	printf("Shop has %.2f in cash\n", s.cash);
	for (int i = 0; i < s.index; i++)
	{
		struct Product product = s.stock[i].product;
		printProduct(product);
		printf("The shop has %d of the above\n", s.stock[i].quantity);
	}
}

double getTotal(double a, double b, struct Customer c)
{
	double x;
	double y;
	for (int i = 0; i < c.index; i++)
	{
		x = a * b;
		y = y + x;
	}
	return y;
}

void printCustomer(struct Customer c)
{
	printf("CUSTOMER NAME: %s \nCUSTOMER BUDGET: %.2f\n", c.cusname, c.budget);
	double res;
	for (int i = 0; i < c.index; i++)
	{
		res = getTotal(c.shoppingList[i].product.price, c.shoppingList[i].quantity, c);
		struct Product product = c.shoppingList[i].product;
		
		printf("CUSTOMER ORDER: %d of %s at a price of %d \n", c.shoppingList[i].quantity, c.shoppingList[i].product.name, c.shoppingList[i].product.price);
		printf("-------------\n");
	}
	printf("The cost of this order is: %.2f", res);
}

int main(void) 
{

	struct Shop shop = createAndStockShop();
	struct Customer customer = createCustomer(shop, customer);
	printShop(shop);
	printCustomer(customer);
	//double price = findProductPrice(shop, "Coke Can");
	//printf("%.2f/n", price);
	
    return 0;
}