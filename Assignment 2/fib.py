def fib(n):
    if(n==1 or n==0):
        return n
    return fib(n-1) + fib(n-2)

for i in range(1,10):
    #print("i is ",i, "in this case")
    print(fib(i))