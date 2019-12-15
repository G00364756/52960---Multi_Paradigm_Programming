"""
The function returns the index of an array element if it exists in the array. 
"""
# Initialise with an array
Input_Str = ['bam','jam','ham','ram','yam']

# Define the function
def recur_index(Input_array,element):
    # If the array doesn't contain the element then return the text
    if element not in Input_array: 
      return "Element not in array"
    # If the element in the array is the same as the value in position 0 of the index of the input array then return 0.
    if Input_array[0] == element:
        return 0
    # Otherwise add 1 to the recursive call of the function applied to the rest of the values in the input array
    else:
        return 1 + recur_index(Input_array[1:], element)

# Prints the results of calling recur_index - e.g. index of the 'yam' in the input array.
print(recur_index(Input_Str, 'yam'))