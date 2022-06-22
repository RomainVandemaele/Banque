INSERT INTO bank
VALUES (1,'Picsou'),(2,'ING'),(3,'Belfius');



INSERT INTO account (accounttype, balance, number, creditline, lastwithdrawdate, owner_id, bank_id)
VALUES ('CURRENT',250.0,'BE40 4362 6514 9964',50.0,null,1,1 )
    ('CURRENT',250.0,'BE97 9539 5348 8949',25.5,null,1,1 ),
    ('SAVING',250.0,'BE27 5614 8528 3373',null,null,1,1 ),
    ('CURRENT',250.0,'BE47 9883 6982 9280',75.0,null,2,1 ),
    ('SAVING',250.0,'BE42 2332 5691 4254',null,null,2,1 ),
    ('SAVING',250.0,'BE30 6313 7664 8311',null,null,3,1 ),
    ('SAVING',250.0,'BE27 4293 2659 5473',null,null,3,1 ),
    ('CURRENT',250.0,'BE80 8126 3347 3977',1000.0,null,3,1 );
