input()
input_line = input().split()
enrtries = [int(entry) for entry in input_line]
enrtries.sort(reverse=True)

total = sum(enrtries)
value = 0
for i, entry in enumerate(enrtries):
    value += entry
    if value > total - value:
        break

print(i + 1)