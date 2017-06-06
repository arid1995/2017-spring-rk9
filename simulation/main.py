# coding=utf-8
from websocket_base import *
from conveyor import Conveyor
from machine import Machine
from stacker import Stacker
from robot import Robot

MILLING_ID = ''
MILLING_TOKEN = ''
LATHE_ID = ''
LATHE_TOKEN = ''
MILLING_ROBOT_ID = ''
MILLING_ROBOT_TOKEN = ''
LATHE_ROBOT_ID = ''
LATHE_ROBOT_TOKEN = ''
STACKER_ID = ''
STACKER_TOKEN = ''
CONVEYOR_ID = ''
CONVEYOR_TOKEN = ''


if __name__ == '__main__':
    millingMachine = Machine(MILLING_ID, MILLING_TOKEN)
    latheMachine = Machine(LATHE_ID, LATHE_TOKEN)
    millingRobot = Robot(MILLING_ROBOT_ID, MILLING_ROBOT_ID)
    latheRobot = Robot(LATHE_ROBOT_ID, LATHE_ROBOT_TOKEN)
    stacker = Stacker(STACKER_ID, STACKER_TOKEN)
    conveyor = Conveyor(CONVEYOR_ID, CONVEYOR_TOKEN)
    print("launched")