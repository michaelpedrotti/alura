import random
import command as output


def play():

    output.header(title="Guess Number Game")

    # current = 1
    begin = 1
    end = 100
    # number of tries
    maximum = 10
    found = False
    # range isn't include the end number
    number = random.randrange(begin, end + 1)
    score = 1000

    print("select your level:")
    print("1: Easy")
    print("2: Normal")
    print("3: Hard")
    typed = int(input("Level: "))
    if typed == 1:
        maximum = 15
    elif typed == 3:
        maximum = 5

    # while maximum >= current:
    for current in range(begin, maximum + 1):
        # range last one is out of range

        # print("trying", current, "/", maximum)
        print("Trying {}/{}".format(current, maximum))

        print("Type a number between 1 and 100")
        # captures a input from CLI
        # string para int, on python 3 all args are string
        typed = int(input("Number: "))

        if typed < 1 or typed > 100:
            print("Error: you must type a number between 1 and 100")
            continue

        if typed == number:
            print("Acertou! Ah, meseravi")
            found = True
            break
        else:
            if typed > number:
                print("secret number is lower than {}".format(typed))
            elif typed < number:
                print("secret number is bigger than {}".format(typed))

            #  absolute value of a number
            score = score - abs(number - typed)
        # current = current + 1

    if not found:
        print("secret number was {}".format(number))

    print('-' * 12)
    print("Game Over")
    print("Score {}", score)
    print('-' * 12)


# if it was call directly by python binary
if __name__ == "__main__":
    play()
