from enum import Enum


class Direction(Enum):
    north = 1
    south = 2
    west = 3
    east = 4


class Action:
    """
        This class represents interface of an action.
        It should be derived from but never instantiated directly.
    """

    def __init__(self):
        pass


class Shot(Action):

    def __init__(self, angle):
        """
            Args:
                angle (float): value in range <0, 2pi>
        """
        Action.__init__(self)
        self.angle = angle


class Move(Action):

    def __init__(self, direction):
        """
            Args:
                direction (Direction): enum value
        """
        Action.__init__(self)
        self.direction = direction


class Nothing(Action):

    def __init__(self):
        Action.__init__(self)
