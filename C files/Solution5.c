/*Write a C program to check if y is greater than x, and z is greater
than y from three given integers x,y,z*/

#include <stdio.h>
#include <string.h>

int main()
{   
    int x;
    int y;
    int z;

    printf("Please enter an integer for 'x': ");
    scanf("%d", &x);

    printf("Please enter another integer for 'y': ");
    scanf("%d", &y);

    printf("Please enter another integer for 'z': ");
    scanf("%d", &z);

    if (y > x && z > y)
        {
            printf("'y' is greater than 'x' and 'z' is greater than 'y'.\n z = %d, y = %d, x = %d", z, y, x);
        }
    else
    {
        printf("No");
    }
    
    return 0;
}
