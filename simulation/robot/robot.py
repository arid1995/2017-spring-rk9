from common.websocket_base import Websocket
from common.feedback import FeedbackMessage
from robot.robot_command import Message
import time


# noinspection PyTypeChecker
class Robot(Websocket):
    def __init__(self, wsId, wsToken):
        super().__init__(wsId, wsToken)
        self.__COMMAND_MESSAGE = '322f9ecd48e90f0d3f55'
        self.__SUCCESS_STATUS = 0
        self.__ERROR_STATUS = 1
        self.__PATH_DURATION = 5
        self.__CLAW_OPEN_DURATION = 2

    def on_message(self, ws, msg):
        message = Message(msg)
        print("ПОСТУПИЛА КОМАНДА ДЛЯ РОБОТА: " + msg)
        if hasattr(message, 'messageType'):
            self.__executeCommand(message)

    def on_open(self, ws):
        pass

    def on_close(self, ws):
        pass

    def on_error(self, ws, error):
        pass

    def __executeCommand(self, message):
        action = int(message.messages[0]['action'])
        switcher = {
            0: lambda: time.sleep(self.__CLAW_OPEN_DURATION),
            1: lambda: time.sleep(self.__PATH_DURATION),
            2: lambda: time.sleep(self.__CLAW_OPEN_DURATION),
            3: lambda: time.sleep(self.__PATH_DURATION)
        }
        switcher[action]()
        feedback = FeedbackMessage(self.__SUCCESS_STATUS, self.wsId)
        self.ws.send(feedback.toJson())
        print("СООБЩЕНИЕ ОБРАТНОЙ СВЯЗИ РОБОТА: " + feedback.toJson())
