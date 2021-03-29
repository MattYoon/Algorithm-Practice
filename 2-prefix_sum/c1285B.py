def get_test():
    tmp = input().split(' ')
    return [int(i) for i in tmp]


def solve(a):
    sum1, sum2 = 0, 0
    for i in range(len(a)):
        sum1 += a[i]
        sum2 += a[len(a) - i - 1]
        if sum1 <= 0:
            return False
        if sum2 <= 0:
            return False
    return True


test_count = int(input())
for _ in range(test_count):
    input()
    a = get_test()

    if solve(a):
        print('YES')
    else:
        print('NO')