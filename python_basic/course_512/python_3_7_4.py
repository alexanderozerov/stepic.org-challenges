x, y = 0, 0

def east(n):
    global x
    x += n

def west(n):
    global x
    x -= n

def north(n):
    global y
    y += n

def south(n):
    global y
    y -= n

navi = {'север': north,
        'юг': south,
        'восток': east,
        'запад': west
        }

for i in range(int(input())):
    c, n = input().split()
    navi[c](int(n))
print(x, y)
