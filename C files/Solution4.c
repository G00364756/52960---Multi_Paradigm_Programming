/*Write a C program that accepts two integers and return true if either
one is 5 or their sum or difference is 5.*/

#include <stdio.h>
#include <string.h>

int main()
{   
    int n;
    int m;
    int sum;
    int dif;

    printf("Please enter an integer: ");
    scanf("%d", &n);

    printf("Please enter another integer: ");
    scanf("%d", &m);

    sum = n + m;
    dif = n - m;

    if (n == 5 || m == 5 || sum == 5 || dif == 5)
        {
            /*return TRUE;*/
            printf("True, values are as follows: n = %d, m = %d, sum = %d, dif = %d", n, m, sum, dif);
        }
    else
    {
        printf("No variables each to the value '5'.");
    }
    
    return 0;
}
