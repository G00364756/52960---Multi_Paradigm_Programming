
"""
The function raises the provided number to the power supplied. 
This is peformed by the use of recursion. This method has
no side effects.
"""
def pow(num, power):
    # this is the base case for recursion as 
    # any number to the power of 1 is itself
    # i.e. 2^1 = 2
    if (power == 1):
        return num
    # Here we perform recursion by invoking our method
    # with a lesser power thereby reducing the work and
    # moving toward the base case
    # as 2^3 = 2 * (2^2)
    return num * pow(num, power-1)