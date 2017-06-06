# Device information

| NAME             | IDS                                  | TOKENS                           |
| ---------------- |:------------------------------------:| --------------------------------:|
| universal device | 75c98d13-8978-4d55-bc6c-d1c385e9fc35 | e9c938e7e384347ab5fe37c9f558de9c |
| conveyor         | 6937b58a-d211-4504-ad96-d1acaf47f939 | e779fb2f735eee3c2296cc7a7e7a89b6 |
| stacker          | 5b580079-fbe6-4519-b56f-417af57f8aa9 | 60e611e01ab51f5f85179b858732bf3f |
| milling          | 1af6fa22-8ca0-489a-8702-ce30266707bc | fdf76b5ab05f1efa6e16f218f236935a |
| lathe            | d71c1172-a50f-4cf0-a89f-192462a2b2c9 | a512c9f6bef381c186884382d9c79772 |
| milling robot    | 71e22a22-0ae6-4bc4-8404-a21ef88017b1 | ef33338f4bf47614912c2911f9e61822 |
| lathe robot      | 79386cc8-ca43-4533-b735-f29ca0eab9b9 | 979f743f7e2aec8c2475bf16cab0b4   |
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

### Machine message:
#### Direction: From device
#### ID: 3e7bb584e61d06821ba6
#### Fields:
timestamp - time of creation;<br>
status - status of operation

| Status         | Value |
|----------------|-------|
|Success         |0      |
|Error           |1      |

#### Description
Feedback message about operation completion

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

### Robot message:
#### Direction: From device
#### ID: e0961e4b8595ea82c908
#### Fields:
timestamp - time of creation;<br>
status - status of operation

| Status         | Value |
|----------------|-------|
|Success         |0      |
|Error           |1      |

#### Description
Feedback message about operation completion

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

### Conveyor message:
#### Direction: From device
#### ID: bbec753c4767238a640a
#### Fields:
timestamp - time of creation;<br>
status - status of operation

| Status         | Value |
|----------------|-------|
|Success         |0      |
|Error           |1      |

#### Description
Feedback message about operation completion

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

### Conveyor message:
#### Direction: From device
#### ID: b42133ef313105550892
#### Fields:
timestamp - time of creation;<br>
status - status of operation

| Status         | Value |
|----------------|-------|
|Success         |0      |
|Error           |1      |

#### Description
Feedback message about operation completion

---------------------------------------------------
