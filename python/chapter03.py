from account import ExpressAccount

account = ExpressAccount()
# remove reference for instance
account = None


class Retangulo:

    def __init__(self, x, y):
        self.__x = x
        self.__y = y
        self.__area = x * y

    def obter_area(self):
        return self.__area


r = Retangulo(7, 6)
r.area = 7
print(r.obter_area())

# attribute starting with two underscore means private

# def cria_conta(numero, titular, saldo, limite):
#     conta = {"numero": numero, "titular": titular, "saldo": saldo, "limite": limite}
#     return conta
#
#
# def deposita(conta, valor):
#     conta["saldo"] += valor
#
#
# def saca(conta, valor):
#     conta["saldo"] -= valor
#
#
# def extrato(conta):
#     print("Saldo {}".format(conta["saldo"]))


class Parent:
    def __init__(self):
        self.parent_attribute = 'I am a parent'


# Create a child class that inherits from Parent
class Child(Parent):
    def __init__(self):
        Parent.__init__(self)
        self.child_attribute = 'I am a child'


# Create instance of child
child = Child()

# Show attributes and methods of child class
print(child.child_attribute)
print(child.parent_attribute)
child.parent_method()


