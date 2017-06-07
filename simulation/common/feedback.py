import json
import time

class FeedbackMessage(object):
    def __init__(self, status):
        self.messageType = '60a149ed9ff3de2df57e'
        self.messages = []
        message = {}
        message['timestamp'] = int(time.time())
        message['status'] = status
        self.messages.append(message)

    def toJson(self):
        return json.dumps(self.__dict__)


