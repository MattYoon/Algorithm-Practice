import math

input()
input_line = input().split()
enrtries = [int(entry) for entry in input_line]

ones, twos, threes, fours = 0, 0, 0, 0
for entry in enrtries:
    if entry == 1:
        ones += 1
    elif entry == 2:
        twos += 1
    elif entry == 3:
        threes += 1
    elif entry == 4:
        fours += 1
    else:
        assert "Something Wrong"

cars = fours
cars += threes
ones -= threes
cars += math.ceil(twos / 2)

if twos % 2:
    ones -= 2

if ones > 0:
    cars += math.ceil(ones / 4)

print(cars)
