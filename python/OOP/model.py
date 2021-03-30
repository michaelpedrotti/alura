from abc import ABC


class Cinema:
    def __init__(self, name, year):
        # name mangling is a way to access private attribute
        self._name = name.title()
        self._year = year
        self._likes = 0

    @property
    def likes(self):
        """
        it behaviors as a property on instance, but like a getter
        :return: numeric
        """
        return self._likes

    @property
    def name(self):
        return self._name

    @name.setter
    def name(self, name):
        self._name = name.title()

    @property
    def year(self):
        return self._year

    def like_it(self):
        self._links += 1

    def __str__(self):
        return f"name: {self._name}, year: {self._year}"


class Movie(Cinema):
    def __init__(self, name, year, runtime):
        super().__init__(name, year)
        self._runtime = runtime

    def __str__(self):
        return f"name: {self._name}, year: {self._year}, runtime: {self._runtime}"


class TvShow(Cinema):
    def __init__(self, name, year, seasons):
        super().__init__(name, year)
        self._seasons = seasons

    @property
    def seasons(self):
        return self._seasons

    def __str__(self):
        return f"name: {self._name}, year: {self._year}, seasons: {self._seasons}"


# class Playlist(list):
#     def __init__(self, name, items):
#         self.name = name
#         super().__init__(items)


# Duck typing
# Extension ()
class Playlist:
    def __init__(self, name, items):
        self.name = name
        self._items = items

    # Composição
    def __getitem__(self, index):
        return self._items[index]

    def __len__(self):
        return len(self._items)

    @property
    def items(self):
        return self._items

    @property
    def size(self):
        return len(self._items)


# play = Playlist("My Play List", [
#     TvShow("Monk", 2009, 8),
#     Movie("Avengers", 2012, 120),
#     TvShow("Every Body Hates Cris", 2010, 4)
# ])
#
# print(len(play))
#
# for row in play:
#     print(row)

# from abc import ABC # Abstract Base Classes
# from collections.abc import MutableSequence
# from numbers import Complex
# from collections.abc import Sized


