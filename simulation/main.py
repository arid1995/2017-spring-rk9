# coding=utf-8
from websocket_base import *
from conveyor import Conveyor
from machine import Machine
from stacker import Stacker
from robot import Robot

MILLING_ID = '1af6fa22-8ca0-489a-8702-ce30266707bc'
MILLING_TOKEN = 'fdf76b5ab05f1efa6e16f218f236935a'
LATHE_ID = 'd71c1172-a50f-4cf0-a89f-192462a2b2c9'
LATHE_TOKEN = 'a512c9f6bef381c186884382d9c79772'
MILLING_ROBOT_ID = '71e22a22-0ae6-4bc4-8404-a21ef88017b1'
MILLING_ROBOT_TOKEN = 'ef33338f4bf47614912c2911f9e61822'
LATHE_ROBOT_ID = '79386cc8-ca43-4533-b735-f29ca0eab9b9'
LATHE_ROBOT_TOKEN = '979f743f7e2aec8c2475bf16cab0b4'
STACKER_ID = '5b580079-fbe6-4519-b56f-417af57f8aa9'
STACKER_TOKEN = '60e611e01ab51f5f85179b858732bf3f'
CONVEYOR_ID = '6937b58a-d211-4504-ad96-d1acaf47f939'
CONVEYOR_TOKEN = 'e779fb2f735eee3c2296cc7a7e7a89b6'


if __name__ == '__main__':
    millingMachine = Machine(MILLING_ID, MILLING_TOKEN)
    latheMachine = Machine(LATHE_ID, LATHE_TOKEN)
    millingRobot = Robot(MILLING_ROBOT_ID, MILLING_ROBOT_TOKEN)
    latheRobot = Robot(LATHE_ROBOT_ID, LATHE_ROBOT_TOKEN)
    stacker = Stacker(STACKER_ID, STACKER_TOKEN)
    conveyor = Conveyor(CONVEYOR_ID, CONVEYOR_TOKEN)
    print("launched")