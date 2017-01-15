n = int(input())
dx, dy = 0, 1
x, y = 0, 0
arr = [[None] * n for _ in range(n)]
for i in range(1, n**2+1):
    arr[x][y] = i
    nx, ny = x+dx, y+dy
    if 0 <= nx < n and 0 <= ny < n and not arr[nx][ny]:
        x, y = nx, ny
    else:
        dx, dy = dy, -dx
        x, y = x+dx, y+dy
for i in range(n):
    for j in range(n):
        print(arr[i][j], end=' ')
    print()
