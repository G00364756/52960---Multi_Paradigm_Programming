/*Write a C program to compute the sum of the two given integers. If
the sum is in the range 10..20 inclusive return 30*/

#include <stdio.h>
#include <string.h>

int main()
{   
    int n;
    int m;
    int sum;
    int sol = 30;

    printf("Please enter an integer: ");
    scanf("%d", &n);

    printf("Please enter another integer: ");
    scanf("%d", &m);

    sum = n + m;

    for (int i=10; i<=20; i++)
    {
        if (i==sum)
        {
            /*return 30;*/
            printf("Answer is : %d", sol);
        }
    }
    return 0;
}
