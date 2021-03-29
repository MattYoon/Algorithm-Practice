#https://codeforces.com/contest/448/problem/C

def bootstrap(f, stack=[]):
    from types import GeneratorType
    def wrappedfunc(*args, **kwargs):
        if stack:
            return f(*args, **kwargs)
        else:
            to = f(*args, **kwargs)
            while True:
                if type(to) is GeneratorType:
                    stack.append(to)
                    to = next(to)
                else:
                    stack.pop()
                    if not stack:
                        break
                    to = stack[-1].send(to)
            return to
 
    return wrappedfunc
 
 
@bootstrap
def solve(p, q):
    global heights, s, t
    
    minh = heights[p]
    for i in range(p, q):
        minh = min(minh, heights[i])
 
    answer2 = minh
 
    for i in range(p, q):
        heights[i] -= minh
 
    s, t = -1, -1
    for i in range(p, q):
        if (heights[i] != 0) and (s < 0):
            s = i
        if (heights[i] == 0) and (s >= 0):
            t = i
            answer2 += yield solve(s, t)
            s, t = -1, -1
        if (s >= 0) and (i + 1 == q):
            answer2 += yield solve(s, q)
 
    yield min(q-p, answer2)
 
 
n = int(input())
height_input = input().split()
heights = [int(h) for h in height_input]
 
answer = solve(0, n)
print(answer)