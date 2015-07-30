INSERT INTO category(id, name) VALUES (1001, 'BIRDS');
INSERT INTO product(id, name, unitCost, description, Category_Id) VALUES (1001, 'Hand-Feeding Formula',145.00, 'Kaytee exact Hand-Feeding Formula for baby birds and macaws.', 1001);
INSERT INTO product(id, name, unitCost, description, Category_Id) VALUES (1002, 'PET CARRIER BAG TOTE',315.00, 'Maltese yorkie chihuahua toy PET CARRIER BAG TOTE.', 1001);
INSERT INTO product(id, name, unitCost, description, Category_Id) VALUES (1003, 'Bathing Brush',270.00, 'FURminator Tub Nub Bathing Brush.', 1001);


INSERT INTO category(id, name) VALUES (1002, 'Dogs');
INSERT INTO product(id, name, unitCost, description, Category_Id) VALUES (1004, 'Pet Carrier',125.00, 'N2N PetHouse Pet Carrier.', 1002);
INSERT INTO product(id, name, unitCost, description, Category_Id) VALUES (1005, 'Martha Stewart Bath',49.00, 'Martha Stewart Bath.', 1002);


INSERT INTO category(id, name) VALUES (1003, 'Cats');
INSERT INTO product(id, name,unitCost, description, Category_Id) VALUES (1006, 'Persian',50.00, 'Persian cat breeds info, Origin: Iran (Persia), Other names:Longhair, Persian Longhair, kitten Price.', 1003);
INSERT INTO product(id, name,unitCost, description, Category_Id) VALUES (1007, 'Maine Coon',70.00,'Maine Coon (American Longhair) is a largest long hair cat breeds, Origin: United States, Colors: Blac', 1003);

INSERT INTO category(id, name) VALUES (1004, 'Fish');
INSERT INTO product(id, name,unitCost, description, Category_Id) VALUES (1008, 'Exo Terra Rainforest Kit',520.00, 'We have numerous kinds of different and amazing commodities that can really help you show all your love for your pet.', 1004);

INSERT INTO customer(id,loginname,password,firstname,lastname,email,mobile,street,city,country,zipcode) VALUES (1323,'demo','demo','demo','demo','demo@gmail.com','9811000000','demo','demo','demo','400602');
