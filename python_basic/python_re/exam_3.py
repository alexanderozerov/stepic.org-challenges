'''Sample Input:
(word) outside (1 open (2 open))
x * (3 + (x - 2) + 2)
Nothing here
Sample Output:
() outside ())
x * () + 2)
Nothing here'''



import re
import sys


pattern = r'\(([^)]+)\)'

for line in sys.stdin:
    line = line.rstrip()
    print(re.sub(pattern, '()', line))


