class Action:
    """
        This class represents interface of an action.
        It should be derived from but never instantiated directly.
    """
    def __init__(self):
        pass

class Shot(Action):
    def __init__(self, angle):
        Action.__init__(self)
        self.angle = angle

class Move(Action):
    def __init__(self, direction):
        Action.__init__(self)
        self.direction = direction

class Nothing(Action):
    def __init__(self):
        Action.__init__(self)
