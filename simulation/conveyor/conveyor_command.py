import json

class Message(object):
    def __init__(self, message):
        self.messageType = None
        self.__dict__ = json.loads(message)