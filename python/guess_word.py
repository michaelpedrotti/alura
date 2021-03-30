import random
import draw


def display(secret, shadow, char):

    """
    Replaces underscore by right char many type it needs
    :param char:
    :param secret: BANANA
    :param shadow: ______
    :return: underscore
    """
    index = 0
    end = secret.count(char)

    for number in range(0, end):
        index = secret.find(char, index)
        shadow[index] = char
        index += 1
    return shadow


def secret_word():
    """
    it chooses a word from a list in file
    :returns string, string
    """

    words = []
    stream = open("guess_word.txt", "r")
    for line in stream:
        words.append(line.strip().upper())
    stream.close()

    index = random.randrange(0, len(words))
    secret = words[index]
    # ['?', '?', '?', '?', '?', '?']
    shadow = ["?" for char in secret]

    return secret, shadow


def play():

    draw.header(title="Guess The Word")

    secret, shadow = secret_word()

    # max errors are 6
    errors = 0
    hits = 0

    while errors < 6:
        char = input("Letter:").strip().upper()

        draw.clear()
        if char in secret:
            shadow = display(secret, shadow, char)
            hits += 1
        else:
            errors += 1

        draw.body(errors)
        draw.yaml({
            "Hits": hits,
            "Errors": errors,
            # "Word": secret,
            # "Shadow": shadow
        })
        if '?' in shadow:
            draw.word(shadow)
        else:
            draw.word(secret)
            break

    if errors < 6:
        print("Win")
    else:
        print("Lose")


# if it was call directly by python binary
if __name__ == "__main__":
    play()
