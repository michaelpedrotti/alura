"""

functions used to send output for CLI
"""


def clear():
    return ""


def spell_letters(word):
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
