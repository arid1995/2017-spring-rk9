from common.websocket_base import Websocket
from machine.machine_command import Message
import time

class Machine(Websocket):
    def __init__(self, wsId, wsToken):
        super().__init__(wsId, wsToken)
        self.__COMMAND_MESSAGE = 'dd53c9c33eaedfbdd766'
        self.__PROGRAM_MESSAGE = 'd4035b0ae585f9ef4357'
        self.__COLLET_OPEN_CLOSE_DURATION = 5
        self.__PROGRAM_EXECUTION_DURATION = 70

    def on_message(self, ws, msg):
        message = Message(msg)
        if message.messageType == self.__COMMAND_MESSAGE:
            self.__executeCommand(message)
        else:
            self.__runProgram(message)

    def on_open(self, ws):
        pass

    def on_close(self, ws):
        pass

    def on_error(self, ws, error):
        pass

    def send(self, msgId, msg):
        pass

    def __executeCommand(self, message):
        time.sleep(self.__COLLET_OPEN_CLOSE_DURATION)

    def __runProgram(self, message):
        time.sleep(self.__PROGRAM_EXECUTION_DURATION)