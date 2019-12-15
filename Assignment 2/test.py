def find_odds(numbers):
  if not numbers:
    return []
  if numbers[0] % 2 == 1:
    return [numbers[0]] + find_odds(numbers[1:])
  return find_odds(numbers[1:])

numbers = [1,2,3,4,5,6,7]

print(find_odds(numbers))