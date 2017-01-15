dicts = {input().lower() for _ in range(int(input()))}
words = set()
for i in range(int(input())):
    m = input().lower().split()
    words.update(m)
for k in words - dicts:
    print(k)
