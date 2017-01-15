class Points:
    def __init__(self, k):
        self.k = k
        self.points = []

    def point_equal(self, p1, p2):
        for i in range(self.k):
            if p1[i] != p2[i]:
                return False
        return True

    def add(self, *coords):
        self.points.append(coords)

    def remove(self, *coords):
        for i in range(len(self.points)):
            if self.point_equal(self.points[i], coords):
                self.points.pop(i)
                return

    def range_query(self, *coord_ranges):
        for point in  self.points:
            if self.coord_test(point, coord_ranges):
                yield point

    def coord_test(self, p, ranges):
        for i in range(self.k):
            if not (ranges[i][0] <= p[i] <= ranges[i][1]):
                return False
        return True



ps = Points(2)
ps.add(1, 1)
ps.add(3, 1)
print(list(ps.range_query((1, 3), (1, 1)))) # [(1, 1), (3, 1)]
ps.add(3, 1)
print(list(ps.range_query((2, 3), (1, 1)))) # [(3, 1), (3, 1)]
ps.remove(2, 1)
ps.remove(3, 1)
print(list(ps.range_query((1, 3), (1, 1)))) # [(1, 1), (3, 1)]

