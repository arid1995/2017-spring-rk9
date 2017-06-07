from abc import ABCMeta, abstractmethod

import websocket
import threading


class Websocket(metaclass=ABCMeta):
    __base_url = 'ws://37.204.2.4:8080/'

    def __init__(self, wsId, wsToken):
        self.ws = websocket.WebSocketApp(
            self.__base_url + wsId,
            header=["Authorization: Bearer " + wsToken],
            on_message=self.on_message,
            on_error=self.on_error,
            on_close=self.on_close)
        self.wsId = wsId
        self.wsToken = wsToken
        self.__connect()

    def __connect(self):
        websocket.enableTrace(True)
        self.ws.on_open = self.on_open
        tr = threading.Thread(target=self.ws.run_forever)
        tr.start()

    def on_message(self, ws, msg):
        print(msg)

    def on_error(self, ws, error):
        print(error)

    def on_close(self, ws):
        self.ws.close()
        print("Closed")

    def on_open(self, ws):
        print("Opened")
