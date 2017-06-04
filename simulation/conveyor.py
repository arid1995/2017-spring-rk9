from websocket_base import Websocket


class Conveyor(Websocket):
    def __init__(self, wsUrl, wsDevice, wsToken):
        super().__init__(wsUrl, wsDevice, wsToken)

    def on_message(self, ws, msg):
        pass

    def on_open(self, ws):
        pass

    def on_close(self, ws):
        pass

    def on_error(self, ws, error):
        pass

    def send(self, msgId, msg):
        pass
