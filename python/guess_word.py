import random
import command as output


def draw_body(errors):
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


def display(secret, underscore, char):

    """
    Replaces underscore by right char many type it needs
    :param secret: BANANA
    :param underscore: ______
    :return: underscore
    """
    index = 0
    end = secret.count(char)

    print("end " + str(end))
    for number in range(0, end):
        index = secret.find(char, index)
        underscore[index] = char
        index += 1
    return underscore


def secret_word():
    """
    it chooses a word from a list in file
    :return string
    """

    words = []
    stream = open("guess_word.txt", "r")
    for line in stream:
        words.append(line.strip().upper())
    stream.close()

    index = random.randrange(0, len(words))
    return words[index]


def play():

    # print(display("BANANA", ["?", "?", "?", "?", "?", "?"], 'A'))
    # draw(4)
    # display("banana", "______", 'a')
    # output.spell_letters(["B", "?", "N", "?", "N", "?"])
    # exit(0)

    output.header(title="Guess The Word")
    # banana
    secret = secret_word()
    # ['?', '?', '?', '?', '?', '?']
    underscore = ["?" for char in secret]

    won = False
    # max errors are 6
    errors = 0

    while not won and errors < 6:
        char = input("Letter:").strip().upper()

        output.clear()
        # char exits in list
        if char in secret:
            underscore = display(secret, underscore, char)
        else:
            errors += 1
        draw_body(errors)
        output.spell_letters(underscore)

    if won:
        print("Win")
    else:
        print("Lose")


# if it was call directly by python binary
if __name__ == "__main__":
    play()
