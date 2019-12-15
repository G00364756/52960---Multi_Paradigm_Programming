"""
The function returns the sum of the digits in the wbole number 
that is inputted into the function.
"""
# Initialise with a whole number.
Input = 523
# Convert to string
Input_Str = str(Input)

# Define the function
def sum_dig(Input_Number):
    # If the length of the digits in the whole number is 0 then return 0.
    base_case = len(Input_Number)
    if base_case == 0:
        return 0
    # Return the digit at index 0 added to the recursive call of the function on the rest of the digits in the whole number.
    else:
        return int(Input_Number[0]) + sum_dig(Input_Number[1:])

# Print the results of sum_dig when the whole number is passed to it.
print(sum_dig(Input_Str))