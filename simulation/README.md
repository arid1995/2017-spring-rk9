# Device information

| NAME             | IDS                                  | TOKENS                           |
| ---------------- |:------------------------------------:| --------------------------------:|
| universal device | d6db8eb0-56fe-4385-8d85-3084dd1218c1 | 3434125beeea0ed87cff6a7df1170c4 |
| conveyor         | ec2750ed-45d9-4335-8dbd-08f86408a2b1 | e3878f719b4bc9b0696f9f1f80202eb8 |
| stacker          | f4812d51-d523-4b9b-a1a7-bdc501d37104 | b2a0804a14243d71bfeaa5d7e87197f  |
| milling machine  | 9bbf29a1-544a-4f7d-b978-71a5e6beba1d | 99ecbe43f4b51225aa41b4860ab51bb  |
| lathe machine    | b0410017-8943-478c-a9c4-2c2407b7b770 | c1f7eb508058e795d372bd1910e1e1   |
| milling robot    | a7a60605-ff3e-4559-adfd-388e8a13a168 | f04f5cfefd635b7817b959b2465668b  |
| lathe robot      | 97ea22e7-d83b-4415-b4fc-0a5f162ef893 | b21fcd7f6ed13cd681b3b45d2d55ff   |
--------------------------------------------------------
# Message information
## Machine

### Machine command:
#### Direction: To Device
#### ID: dd53c9c33eaedfbdd766
#### Fields:
timestamp - time of creation;<br>
action - required action;
#### Action values:
| Action         | Value |
|----------------|-------|
|Open collet     |0      |
|Close collet    |1      |
#### Description
Makes machine do some action

### Machine program execution:
#### Direction: To Device
#### ID: d4035b0ae585f9ef4357
#### Fields:
timestamp - time of creation;<br>
programName - name of a program to be executed;
#### Description
Makes machine execute a program

----------------------------------------------------
## Robot

### Robot command:
#### Direction: To Device
#### ID: 322f9ecd48e90f0d3f55
#### Fields:
timestamp - time of creation;<br>
action - required action;
#### Action values:
| Action                       | Value |
|------------------------------|-------|
|Take Billet From Pallet       |0      |
|Put Billet Into Machine       |1      |
|Take Billet From Machine      |2      |
|Put Billet On Pallet          |3      |

#### Description
Makes robot perform some action

---------------------------------------------------
## Conveyor

### Conveyor command:
#### Direction: To Device
#### ID: 1636660af8dbb0b49055
#### Fields:
timestamp - time of creation;<br>
palletId - id of the pallet to be transported;<br>
to - number of key to which to take the pallet;

#### Description
Commands conveyor to take pallet to a certain key

---------------------------------------------------
## Stacker

### Stacker command:
#### Direction: To Device
#### ID: 6b29d622a3f6da5e1193
#### Fields:
timestamp - time of creation;<br>
action - action to perform;<br>
cellNumber - to which cell to put or from which to take pallet;

#### Description
Commands stacker to place pallet from conveyor in the stock or on conveyor from the stock

---------------------------------------------------
# Feedback
### Direction: From device
### ID: 60a149ed9ff3de2df57e
### Fields:
timestamp - time of creation;<br>
status - status of operation

| Status         | Value |
|----------------|-------|
|Success         |0      |
|Error           |1      |

#### Description
Feedback message about operation completion
