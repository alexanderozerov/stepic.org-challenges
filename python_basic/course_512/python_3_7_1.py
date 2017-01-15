d = {}
for i in range(int(input())):
    m = input().split(';')
    a = str(m[0])
    b = str(m[2])
    if a not in d:
        d[a] = [1, 0, 0, 0, 0]
    else:
        d[a][0] += 1
    if b not in d:
        d[b] = [1, 0, 0, 0, 0]
    else:
        d[b][0] += 1
    if int(m[1]) > int(m[3]):
        d[a][1] += 1
        d[a][4] += 3
        d[b][3] += 1
    elif int(m[1]) < int(m[3]):
        d[b][1] += 1
        d[b][4] += 3
        d[a][3] += 1
    else:
        d[a][2] += 1
        d[a][4] += 1
        d[b][2] += 1
        d[b][4] += 1
for key, value in d.items():
    print(key + ':' + ' '.join(str(i) for i in value))
