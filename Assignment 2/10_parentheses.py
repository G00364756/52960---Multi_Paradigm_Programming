"""
The function checks whether there is zero or more pairs of parentheses in a given string.
"""
# Initialise with a String
Input_Str = '(((()))))'

# Define the function
def parenthesis(elements, i, j):
    # If the i element is greater or equal to the j element then return 0.
    if i >= j:
        return 0
    # Check if the first element in the string is 1 side of the parentheses. Set x as 1 if this is true.
    if elements[i] == "(":
        x = 1
    # Otherwise set x as 0.
    else:
        x = 0
    # Iterate i by 1.
    i += 1
    # Check if the last element in the string is the other side of the parentheses. Set y as 1 if this is true.
    if elements[j] == ")":
        y = 1
    # Otherwise set y as 0.
    else:
        y = 0
    # Iterate j by 1 if the difference between i and j is greater than 1. 
    if j - i > 1:
        j -= 1
    # Sum x and y 
    sumxy = x + y
    # return the sum of x and y plus the recursive call of the string with new values for i and j.
    return sumxy + parenthesis(elements,i,j)

# Another function which assesses whether the results of parenthesis are even or odd.
def calculate_par(paren):
    # if the result of parenthesis is even then return the following text.
    if paren % 2 == 0:
        print("The array contains zero or more pairs of parentheses.")
    # if the result of parenthesis is odd then return the following text.
    elif paren % 2 == 1:
        print("The array contains an odd number of parentheses.")

# Initialising i and j for the function.
i = 0
j = len(Input_Str) - 1
# Call to both functions 
calculate_par(parenthesis(Input_Str,i, j))