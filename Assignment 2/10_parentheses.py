"""
The function returns a string that has a specified character replaced by another cpecified character
if it exists in the string.
"""
# Initialise with a String
Input_Str = '(((())))'

# Define the function
def parenthesis(elements, i, j):
    # If the string doesn't contain any characters return nothing.
    if i >= j:
        return 0
    if elements[i] == "(":
        x = 1
    else:
        x = 0
    i += 1
    if elements[j] == ")":
        y = 1
    else:
        y = 0
    j -= 1
    sumxy = x + y
    print(sumxy)
    return sumxy + parenthesis(elements,i,j)

def calculate_par(paren):
    if paren % 2 == 0:
        print("The array contains zero or more pairs of parentheses.")
    elif paren % 2 == 1:
        print("The array contains an odd number of parentheses.")

i = 0
j = len(Input_Str) - 1
calculate_par(parenthesis(Input_Str,i, j))