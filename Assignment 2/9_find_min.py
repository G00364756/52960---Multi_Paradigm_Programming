"""
The function returns the minimum value of an input array.
"""
# Initialise the input array.
Input_arr = [52,42,70,22,16,80,39,23]

# Define the function
def find_min(Input_arr):
    # Set the base case, in this case, when the length of the array is 0.
    base_case = len(Input_arr)
    if base_case == 0:
        return("Empty array")
    # If the base case has been reached return the value in index 0.
    if base_case == 1:
        return Input_arr[0]
    # Recursively call the function on the rest of the values of the array.
    # If hte results of the recursive call are less than the value in index 0 then the result of the recursive call is the new minimum value.
    else:
        minNumber = find_min(Input_arr[1:])
        min = Input_arr[0]
        if minNumber < min:
            min = minNumber
        return min

# Call the function on the Input array and print the results.
print(find_min(Input_arr))