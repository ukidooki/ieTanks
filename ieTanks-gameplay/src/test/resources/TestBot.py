from pl.edu.agh.ietanks.engine.simple.actions import *
from pl.edu.agh.ietanks.engine.api import Bot,Board
from pl.edu.agh.ietanks.engine.api.Board import *

class MyBot(Bot):
    def __init__(self):
        pass

    def performAction(self,board):
        return Move(Board.Direction.Right,1)
