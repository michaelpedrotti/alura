"""

functions used to send output for CLI
"""
import os


def clear():
    print("\x1b[2J\x1b[1;1H")


def yaml(dictionary):
    for key, val in dictionary.items():
        print(key, ": ", val)


def word(word):
    print('+-' + ('--' * len(word)) + '+')
    print('| ' + ' '.join(word) + ' |')
    print('+-' + ('--' * len(word)) + '+')


# Keyword Arguments
def header(title):
    repeat = len(title) + 4
    # string repeat
    print('#' * repeat)
    # separates with space each argument
    print('#', title, '#')
    print('#' * repeat)
    # for line in lines:
    #     print(line)


def body(errors):
    """
    shows human body based on error numbers
    :param errors:
    :return:
    """

    # each line has 9 chars, when join command is done there are 10 chars per line
    lines = [
        " +---+-  ",
        " |   |   ",
        " |  \o/  ",
        " |   |   ",
        " |  / \  ",
        " |       ",
        "-+-------",
    ]

    # put all items into a string with \n every end of line
    string = "\n".join(lines)

    # replaces human body char to empty
    indexes = [
        24, 25, 26,
        35,
        44, 46
    ]

    # needs to start from the left foot to right arm
    indexes.reverse()
    # removes total errors from body parts
    end = abs(errors - len(indexes))
    # intersect
    indexes = indexes[0:end]

    for index in indexes:

        # string[index] = "*"
        # https://pythonexamples.org/python-string-replace-character-at-specific-position/
        string = string[:index] + ' ' + string[index+1:]

    print(string)