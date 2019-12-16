"""
The function returns a string that has a specified character replaced by another defined character,
if it exists in the string.
"""
# Initialise with a String
Input_Str = 'skejfnvilerbvierbv;werjve;wrbvje;ib;ervbjs.kfv'

# Define the function
def replaceChar(String_orig, old_char, new_char):
    # If the string doesn't contain any characters return nothing.
    if String_orig == '':
        return ''
    # If the character in the string is the same as the specified character then replace it with the new character and call the function recursively.
    if String_orig[0] == old_char:
        return new_char + replaceChar(String_orig[1:], old_char, new_char)
    # Otherwise leave the character as is and recursively call the function on the next character in the string.
    return String_orig[0] + replaceChar(String_orig[1:], old_char, new_char)

# Print the results of replacing the specified character in the string.
print(replaceChar(Input_Str, ';', '*'))