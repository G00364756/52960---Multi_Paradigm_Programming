"""
The function sums a given array. When recursively called within itself,
the function ommits the value in index 0 of the array, this ensures that the function,
works towards the base case which is working towards an array of length 1.
"""
# Initialise an array.
Test_array = [0,2,4,6,8,10]

# Define the recursive function.
def sumarr(Test_arr):
    # Define the base case for the function.
    base_case = len(Test_arr)
    # If the length of the array is 1 this will return the only value in the array as this is the sum.
    if base_case == 1:
        return Test_arr[0]
    # Otherwise the function adds the value in index 0 with the output of the function when applied to value at index 1.
    # This is where the recurrsion occurs.
    else:
        summed = Test_arr[0]+sumarr(Test_arr[1:])
        return summed

# Print the result of calling the function sumarr on the input array Test_array.
print("The sum of the array is ", sumarr(Test_array))