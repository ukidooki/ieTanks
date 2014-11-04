from enum import Enum

class Action:
    """
        This class represents interface of an action.
        It should be derived from but never instantiated directly.
    """
    def __init__(self):
        pass
        
class Direction(Enum):
    north = 1
    south = 2
    west = 3
    east = 4        

class Shot(Action):
    def __init__(self, angle):
        """
            The second parameter is an integer value <1..360>
        """
        Action.__init__(self)
        self.angle = angle

class Move(Action):
    def __init__(self, direction):
        """
            The second parameter is one of the Direction enum values
        """
        Action.__init__(self)
        self.direction = direction

class Nothing(Action):
    def __init__(self):
        Action.__init__(self)
