# coding=utf-8
from conveyor.conveyor import Conveyor
from machine.machine import Machine
from robot.robot import Robot
from stacker.stacker import Stacker

MILLING_ID = '9bbf29a1-544a-4f7d-b978-71a5e6beba1d'
MILLING_TOKEN = '99ecbe43f4b51225aa41b4860ab51bb'
LATHE_ID = 'b0410017-8943-478c-a9c4-2c2407b7b770'
LATHE_TOKEN = 'c1f7eb508058e795d372bd1910e1e1'
MILLING_ROBOT_ID = 'a7a60605-ff3e-4559-adfd-388e8a13a168'
MILLING_ROBOT_TOKEN = 'f04f5cfefd635b7817b959b2465668b'
LATHE_ROBOT_ID = '97ea22e7-d83b-4415-b4fc-0a5f162ef893'
LATHE_ROBOT_TOKEN = 'b21fcd7f6ed13cd681b3b45d2d55ff'
STACKER_ID = 'f4812d51-d523-4b9b-a1a7-bdc501d37104'
STACKER_TOKEN = 'b2a0804a14243d71bfeaa5d7e87197f'
CONVEYOR_ID = 'ec2750ed-45d9-4335-8dbd-08f86408a2b1'
CONVEYOR_TOKEN = 'e3878f719b4bc9b0696f9f1f80202eb8'


if __name__ == '__main__':
    millingMachine = Machine(MILLING_ID, MILLING_TOKEN)
    latheMachine = Machine(LATHE_ID, LATHE_TOKEN)
    millingRobot = Robot(MILLING_ROBOT_ID, MILLING_ROBOT_TOKEN)
    latheRobot = Robot(LATHE_ROBOT_ID, LATHE_ROBOT_TOKEN)
    stacker = Stacker(STACKER_ID, STACKER_TOKEN)
    conveyor = Conveyor(CONVEYOR_ID, CONVEYOR_TOKEN)
    print("-------ЗАПУСК ПРОГРАММЫ СИМУЛЯЦИИ--------")