/*Write a C program to check two given integers, and return true if one
of them is 30 or if their sum is 30.*/

#include <stdio.h>
#include <string.h>

/*Adapted from https://stackoverflow.com/questions/39730583/return-value-of-a-boolean-expression-in-c*/
#define TRUE (!0)

int main()
{   
    int n;
    int m;
    int sum;

    printf("Please enter an integer: ");
    scanf("%d", &n);

    printf("Please enter another integer: ");
    scanf("%d", &m);

    sum = n + m;

    if (n==30 || m==30 || sum==30)
    {
        return TRUE;
    }
    return 0;
}


/*  I developed the below piece of code 1st then reread the question and realised it was supposed to return true if any of the conditions were met.
    if (n == 30) 
    {
        printf("The 1st integer 'n' is equal to 30.\n", n);
    } 
    if (m == 30)
    {
        printf("The 2nd integer 'm' is equal to 30.\n", m);
    }
    if (sum == 30)
    {
        printf("The sum of the integers 'n' and 'm' is equal to 30.\n", sum);
    }
    return 0;
}
*/