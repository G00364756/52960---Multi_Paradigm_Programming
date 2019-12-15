"""
The function prints the values of an array line by line recursively
"""
# Initialise the input array.
Input_arr = [1,2,4,6,7,11,16,20,22,23]

# Define the function
def print_array(Input_arr):
    # Set the base case, in this case, when the length of the array is 0.
    base_case = len(Input_arr)
    # if the base case has been reached return nothing.
    if base_case == 0:
        return 0
    # Print the value at index 0 of the input array.
    else:
        print(Input_arr[0])
        print_array(Input_arr[1:])

# Call the function on the Input array.
print_array(Input_arr)