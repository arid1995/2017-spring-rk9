from common.websocket_base import Websocket


class Conveyor(Websocket):
    def __init__(self, wsId, wsToken):
        super().__init__(wsId, wsToken)


