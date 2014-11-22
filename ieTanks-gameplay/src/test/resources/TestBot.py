from pl.edu.agh.ietanks.gameplay.bot.api import *

class MyBot(Bot):
    def __init__(self):
        pass

    def performAction(self, board):
        return Move(MoveDirection.East)
