"""
The function returns an array that has had the even values
removed from it.
"""
# Initialise the input array.
Input_arr = [1,2,4,6,7,11,16,20,22,23]

# Define the function
def remove_even(Input_arr):
  # If the input array contains no values return an empty list.
  if not Input_arr:
    return []
  # Otherwise test that the value in the 1st position of the array returns a remainder.
      # If this is true return the value in this position and recusively call the function again omitting the first value in the input array as it has alreafy been done.
  if Input_arr[0] % 2 == 1:
    return [Input_arr[0]] + remove_even(Input_arr[1:])
    # If the value in position 1 of the input array does not return a remainder then recursively call the function to the next value in the array omitting the 1st value.
  return remove_even(Input_arr[1:])

# Call the function and store the output of the function as "answer"
answer = (remove_even(Input_arr))
# Print the answer
print("The input array with odd numbers numbers removed is ", answer)