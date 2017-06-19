from common.websocket_base import Websocket
from machine.machine_command import Message
from common.feedback import FeedbackMessage
import time

class Machine(Websocket):
    def __init__(self, wsId, wsToken):
        super().__init__(wsId, wsToken)
        self.__COMMAND_MESSAGE = 'dd53c9c33eaedfbdd766'
        self.__PROGRAM_MESSAGE = 'd4035b0ae585f9ef4357'
        self.__SUCCESS_STATUS = 0
        self.__ERROR_STATUS = 1
        self.__COLLET_OPEN_CLOSE_DURATION = 2
        self.__PROGRAM_EXECUTION_DURATION = 10

    def on_message(self, ws, msg):
        message = Message(msg)
        print("ПОСТУПИЛА КОМАНДА ДЛЯ СТАНКА: " + msg)
        if hasattr(message, 'messageType') and message.messageType == self.__COMMAND_MESSAGE:
            self.__executeCommand(message)
        elif hasattr(message, 'messageType'):
            self.__runProgram(message)

    def on_open(self, ws):
        pass

    def on_close(self, ws):
        pass

    def on_error(self, ws, error):
        pass

    def __executeCommand(self, message):
        time.sleep(self.__COLLET_OPEN_CLOSE_DURATION)
        feedback = FeedbackMessage(self.__SUCCESS_STATUS, self.wsId)
        self.ws.send(feedback.toJson())
        print("СООБЩЕНИЕ ОБРАТНОЙ СВЯЗИ СТАНКА: " + feedback.toJson())

    def __runProgram(self, message):
        time.sleep(self.__PROGRAM_EXECUTION_DURATION)
        feedback = FeedbackMessage(self.__SUCCESS_STATUS, self.wsId)
        self.ws.send(feedback.toJson())
        print("СООБЩЕНИЕ ОБРАТНОЙ СВЯЗИ СТАНКА: " + feedback.toJson())