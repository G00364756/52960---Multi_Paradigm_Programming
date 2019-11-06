/*Write a C program to get the absolute difference between n and 51. If
n is greater than 51 return triple the absolute difference.*/
#include <stdio.h>
#include <string.h>
#include <math.h>

int main()
{   
    int n;
    int m = 51;
    int abso1;
    int abso2;
    int prod1;

    printf("PLease enter an integer: ");
    scanf("%d", &n);

    if (n > m)
    {
        abso1 = (abs(n-m));
        prod1 = abso1*3;
        printf("n is greater than 51,\n the absolute value for the difference between 'n' and 51 is %d, \n and this number tripled is %d, \n", abso1, prod1);
    } 
    else 
    {
        abso2 = (abs(n-m));
        printf("Operation did not take place as 'n' is less than 51, \n the absolute value for the difference between 'n' and 51 is %d \n", abso2);
    }
    
    return 0;
}