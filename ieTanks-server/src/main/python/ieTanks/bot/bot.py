class Bot:
    """
        This class represents interface of a bot.
        It should be derived from but never instantiated directly.
    """

    def __init__(self, id):
        self.id = id

    def next_action(self, board):
        """
            This method should be overridden in subclasses.
            This method should return a concrete Action implementation.
        """
        raise NotImplementedError
