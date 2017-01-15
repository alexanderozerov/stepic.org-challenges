message, code, d = [str(i) for i in input()], [str(j) for j in input()], {}
for i in range(len(code)):
    d[message[i]] = code[i]
encode, decode = [str(i) for i in input()], [str(j) for j in input()]
print(''.join(d[j] for j in encode))
for k in decode:
    print(''.join(key for key in d if d[key] == k), end='')
