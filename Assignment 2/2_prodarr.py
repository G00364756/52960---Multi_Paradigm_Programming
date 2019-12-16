"""
The function returns the product of a given array. When recursively called within itself,
the function ommits the value in index 0 of the array, this ensures that the function,
works towards the base case which is working towards an array of length 1.
"""
# Initialise an array.
Test_array = [1,2,4,6,8,10]

# Define the recursive function.
def prodarr(Test_arr):
    # Define the base case for the function.
    base_case = len(Test_arr)
    # If the length of the array is 1 this will return the only value in the array as this is the sum.
    if base_case == 1:
        return Test_arr[0]
    # Otherwise the function adds the value in index 0 with the output of the function when applied to value at index 1.
    # This is where the recurrsion occurs.
    else:
        multiplied = Test_arr[0]*prodarr(Test_arr[1:])
        return multiplied

# Print the result of calling the function prodarr on the input array Test_array.
print("The product of the array is ", prodarr(Test_array))