def main():
    tmpn, tmpk = input().split(' ')
    n, k = int(tmpn), int(tmpk)

    tmpa = input().split(' ')
    a = [int(i) for i in tmpa]
    p = [0]
    for i, _ in enumerate(a):
        p.append(p[i] + a[i])

    maximum = 0
    for i in range(k, n):
        for j in range(n - i + 1):
            maximum = max((p[i + j] - p[j])/i, maximum)
            
    maximum = max(sum(a)/n, maximum)
    print(maximum)

main()