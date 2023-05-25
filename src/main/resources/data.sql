INSERT INTO Drone (serial_number, model, weight_limit, battery, state)
VALUES ('DR001', 'HEAVYWEIGHT', 500, 75, 'IDLE');

INSERT INTO Drone (serial_number, model, weight_limit, battery, state)
VALUES ('DR002', 'HEAVYWEIGHT', 400, 100, 'IDLE');

INSERT INTO Drone (serial_number, model, weight_limit, battery, state)
VALUES ('DR003', 'HEAVYWEIGHT', 300, 40, 'LOADING');



INSERT INTO Medication (code, name, weight, picture, drone_id)
VALUES ('MED001', 'Medication 1', 100, 'picture1.jpg', NULL);

INSERT INTO Medication (code, name, weight, picture, drone_id)
VALUES ('MED002', 'Medication 2', 150, 'picture2.jpg', NULL);

INSERT INTO Medication (code, name, weight, picture, drone_id)
VALUES ('MED003', 'Medication 3', 200, 'picture3.jpg', NULL);