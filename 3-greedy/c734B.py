#https://codeforces.com/problemset/problem/734/b

input_line = input().split()
numbers = [int(entry) for entry in input_line]
twos, threes, fives, sixes = numbers

c256 = min(twos, fives, sixes)
twos -= c256
c32 = min(twos, threes)

result = 256 * c256 + 32 * c32
print(result)