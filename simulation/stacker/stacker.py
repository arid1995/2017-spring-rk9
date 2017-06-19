from common.websocket_base import Websocket
from stacker.stacker_command import Message
from common.feedback import FeedbackMessage
import time

class Stacker(Websocket):
    def __init__(self, wsId, wsToken):
        super().__init__(wsId, wsToken)
        self.__COMMAND_MESSAGE = '6b29d622a3f6da5e1193'
        self.__SUCCESS_STATUS = 0
        self.__ERROR_STATUS = 1
        self.__TRANSPORT_DURATION = 2

    def on_message(self, ws, msg):
        message = Message(msg)
        print("ПОСТУПИЛА КОМАНДА ДЛЯ ШТАБЕЛЕРА: " + msg)
        if hasattr(message, 'messageType') and message.messageType == self.__COMMAND_MESSAGE:
            self.__executeCommand(message)

    def on_open(self, ws):
        pass

    def on_close(self, ws):
        print("Closed")

    def on_error(self, ws, error):
        pass

    def __executeCommand(self, message):
        time.sleep(self.__TRANSPORT_DURATION)
        feedback = FeedbackMessage(self.__SUCCESS_STATUS, self.wsId)
        self.ws.send(feedback.toJson())
        print("СООБЩЕНИЕ ОБРАТНОЙ СВЯЗИ ШТАБЕЛЕРА: " + feedback.toJson())
