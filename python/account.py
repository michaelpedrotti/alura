class ExpressAccount:

    def __init__(self):
        print("class {}".format(self))


class Conta:

    def __init__(self, numero, titular, saldo, limite):
        print("Construindo objeto ... {}".format(self))
        self.numero = numero
        self.titular = titular
        self.saldo = saldo
        self.limite = limite

    def extrato(self):
        print("Saldo de {} do titular {}".format(self.saldo, self.titular))

    def deposita(self, valor):
        self.saldo += valor

    def saca(self, valor):
        self.saldo -= valor


class Client:

    def __init__(self, name):
        self.__name = name

    @property
    def nome(self):
        # attribute client.nome will be executed as method getter
        return self.__name.title();

    @nome.setter
    def nome(self, name):
        # attribute client.nome = "fulano" will be executed as method setter
        self.__name = name

