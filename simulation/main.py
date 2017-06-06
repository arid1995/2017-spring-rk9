# coding=utf-8
from websocket_base import *
from conveyor import Conveyor
from machine import Machine
from stacker import Stacker
from robot import Robot

if __name__ == '__main__':
    machine1 = Machine()
    machine2 = Machine()
    robot1 = Robot()
    robot2 = Robot()
    stacker = Stacker()
    conveyor = Conveyor()
    print("launched")