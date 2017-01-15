import os.path
import csv

def dog_counter(file, number):
    dogs = 0
    cats = 0
    for row in file:
        if row[number].lower() == 'dog':
            dogs += 1
        elif row[number].lower() == 'cat':
            cats += 1
    if cats < dogs:
        return True
    return False

counter = 0

for cur_dir, dirs, files in os.walk(r'./data'):
    for filename in files:
        if filename.endswith('csv'):
            with open(r'' + (cur_dir + '/' + filename)) as f:
                headers = csv.DictReader(f).fieldnames
                for i in range(len(headers)):
                    if headers[i].lower() == 'pet':
                        # print(headers)
                        reader = csv.reader(f)
                        if dog_counter(reader, i):
                            counter += 1

print(counter)
