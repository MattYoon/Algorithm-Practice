#https://codeforces.com/problemset/problem/466/c

n = int(input())

nums_input = input().split(" ")
nums = [int(i) for i in nums_input]
total_sum = sum(nums)

if total_sum % 3 != 0:
    print(0)
else:
    sum = total_sum // 3
    count, total_count = 0, 0
    if nums[0] == sum:
        count = 1
    pref_sum = nums[0]
    for num in nums[1:-1]:
        pref_sum += num
        if pref_sum == sum * 2:
            total_count += count
        if pref_sum == sum:
            count += 1
    print(total_count)        
    