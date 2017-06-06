import json

class Message(object):
    def __init__(self, message):
        self.messageType = ''
        self.__dict__ = json.loads(message)