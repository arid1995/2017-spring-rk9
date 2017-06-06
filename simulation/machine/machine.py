from common.websocket_base import Websocket
from machine.machine_command import Message


class Machine(Websocket):
    def __init__(self, wsId, wsToken):
        super().__init__(wsId, wsToken)
        self.__commandMessage = 'dd53c9c33eaedfbdd766'
        self.__programMessage = 'd4035b0ae585f9ef4357'

    def on_message(self, ws, msg):
        message = Message(msg)
        print(message.messageType)

    def on_open(self, ws):
        pass

    def on_close(self, ws):
        pass

    def on_error(self, ws, error):
        pass

    def send(self, msgId, msg):
        pass