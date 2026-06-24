DROP DATABASE IF EXISTS PlantReferenceSystem;
CREATE DATABASE PlantReferenceSystem;
USE PlantReferenceSystem;

CREATE TABLE Admin (
    Admin_ID INT PRIMARY KEY,
    Username VARCHAR(100) NOT NULL,
    Email VARCHAR(150) NOT NULL UNIQUE,
    Pass_word VARCHAR(100) NOT NULL
);

CREATE TABLE User (
    User_ID INT PRIMARY KEY,
    Username VARCHAR(100) NOT NULL,
    Email VARCHAR(150) NOT NULL UNIQUE,
    Password VARCHAR(100) NOT NULL,
    Role VARCHAR(30) NOT NULL
);

CREATE TABLE Plant (
    Plant_ID INT PRIMARY KEY,
    Plant_Name VARCHAR(100) NOT NULL,
    Scientific_Name VARCHAR(150) UNIQUE,
    Plant_Type VARCHAR(50) NOT NULL,
    Description VARCHAR(255),
    Admin_ID INT NOT NULL,
    FOREIGN KEY(Admin_ID) REFERENCES Admin(Admin_ID)
);

CREATE TABLE Care_Guide (
    Care_ID INT PRIMARY KEY,
    Watering_Frequency VARCHAR(100) NOT NULL,
    Sunlight_Needs VARCHAR (100) NOT NULL,
    Soil_Type VARCHAR (100) NOT NULL,
    Temperature VARCHAR(50) NOT NULL,
    Plant_ID INT NOT NULL,
    FOREIGN KEY(Plant_ID) REFERENCES Plant(Plant_ID)
);

CREATE TABLE Disease( Disease_ID INT PRIMARY KEY,Disease_Name VARCHAR(100) NOT NULL,Symptoms VARCHAR(255) NOT NULL,Treatment VARCHAR(255) NOT NULL,Plant_ID INT NOT NULL,FOREIGN KEY(Plant_ID) REFERENCES Plant(Plant_ID));

CREATE TABLE User_Plant(User_ID INT NOT NULL,Plant_ID INT NOT NULL,PRIMARY KEY(User_ID, Plant_ID),FOREIGN KEY(User_ID) REFERENCES User(User_ID),FOREIGN KEY(Plant_ID) REFERENCES Plant(Plant_ID));


INSERT INTO Admin VALUES
(1, 'jana_admin', 'jana@gmail.com', '123'),(2, 'shahha_admin', 'shahha@gmail.com', '523'),(3, 'nada_admin', 'nada@gmail.com', '456'),(4, 'layan_admin', 'layan@gmail.com', '385'),(5, 'reemadmin5', 'a5@gmail.com', '8347'),(6, 'mohmmedadmin6', 'a6@gmail.com', '897'),(7, 'ail_admin', 'a7@gmail.com', '986'),(8, 'shamma_admin', 'a8@gmail.com', '8745'),(9, 'saraadmin', 'a9@gmail.com', '857'),(10, 'noha_admin', 'a10@gmail.com', '875785');


INSERT INTO User VALUES
(1, 'user1', 'kjghk@gmail.com', '176823', 'user'),(2, 'user2', 'jkhu2@gmail.com', '175723', 'user'),(3, 'user3', 'fj3@gmail.com', '126563', 'user'),(4, 'user4', 'ujkg4@gmail.com', '1856823', 'user'),(5, 'user5', 'hgbu5@gmail.com', '67548', 'user'),(6, 'user6', 'hjbgjhu6@gmail.com', '6537', 'user'),(7, 'user_7', 'ujhgj7@gmail.com', '875875', 'user'),(8, 'user8', 'uhgjh8@gmail.com', '876', 'user'),(9, 'user_9', 'uhguyg9@gmail.com', '9867856', 'user'),(10, 'user10', 'uuyfgn10@gmail.com', '7568', 'user');


INSERT INTO Plant VALUES
(101, 'Rose', 'Rosa', 'Flower', 'Red flower', 1),(102, 'Mint', 'Mentha', 'Herb', 'Used in tea', 2),(103, 'Basil', 'Ocimum', 'Herb', 'Cooking plant', 3),(104, 'Cactus', 'Cactaceae', 'Desert', 'Needs little water', 4),(105, 'Aloe Vera', 'Aloe', 'Medicinal', 'Skin treatment', 5),(106, 'Lavender', 'Lavandula', 'Flower', 'Nice smell', 6),(107, 'Sunflower', 'Helianthus', 'Flower', 'Follows sun', 7),(108, 'Palm', 'Arecaceae', 'Tree', 'Tall tree', 8),(109, 'Fern', 'Polypodiopsida', 'Indoor', 'Green leaves', 9),(110, 'Orchid', 'Orchidaceae', 'Flower', 'Decorative', 10);

INSERT INTO Care_Guide VALUES
(1, 'Daily', 'Full Sun', 'Sandy', '25C', 101),(2, 'Twice a week', 'Partial Sun', 'Loamy', '20C', 102),(3, 'Weekly', 'Full Sun', 'Clay', '22C', 103),(4, 'Rarely', 'Full Sun', 'Sandy', '30C', 104),(5, 'Weekly', 'Indirect Sun', 'Loamy', '24C', 105),(6, 'Daily', 'Full Sun', 'Loamy', '26C', 106),(7, 'Daily', 'Full Sun', 'Sandy', '28C', 107),(8, 'Weekly', 'Full Sun', 'Sandy', '35C', 108),(9, 'Twice a week', 'Shade', 'Loamy', '20C', 109),(10, 'Weekly', 'Indirect Sun', 'Clay', '23C', 110);

INSERT INTO Disease VALUES
(1, 'Fungus', 'White spots', 'Use fungicide', 101),(2, 'Root rot', 'Brown roots', 'Reduce water', 102),(3, 'Leaf spot', 'Black dots', 'Remove leaves', 103),(4, 'Mold', 'White layer', 'Air circulation', 104),(5, 'Pest attack', 'Holes in leaves', 'Use pesticide', 105),(6, 'Wilt', 'Drooping leaves', 'Water properly', 106),(7, 'Rust', 'Orange spots', 'Fungicide', 107),(8, 'Blight', 'Dead patches', 'Remove plant', 108),(9, 'Yellowing', 'Yellow leaves', 'Check soil', 109),(10, 'Dry tips', 'Brown tips', 'Increase humidity', 110);

INSERT INTO User_Plant VALUES
(1,101),(2,102),(3,103),(4,104),(5,105),
(6,106),(7,107),(8,108),(9,109),(10,110);

UPDATE Plant SET Description = 'Updated plant' WHERE Plant_ID = 101;
DELETE FROM Disease WHERE Disease_ID = 10;

SELECT *
FROM Plant
WHERE Plant_Name LIKE 'A%';

SELECT *
FROM Plant
WHERE Plant_ID BETWEEN 101 AND 105;

SELECT *
FROM Plant
WHERE Plant_Type IN ('Flower', 'Herb');

SELECT *
FROM Plant
ORDER BY Plant_Name;


SELECT p.Plant_Name, d.Disease_Name
FROM Plant p
JOIN Disease d ON p.Plant_ID = d.Plant_ID;

SELECT Plant_ID, COUNT(*) AS total
FROM Disease
GROUP BY Plant_ID
HAVING COUNT(*) >= 1;

SELECT *
FROM Plant p
WHERE EXISTS (
    SELECT *
    FROM Disease d
    WHERE d.Plant_ID = p.Plant_ID);



SELECT Plant_Name
FROM Plant
WHERE Plant_ID IN (
SELECT Plant_ID FROM Disease);

SELECT *
FROM Plant
WHERE Description IS NULL;


SELECT * FROM plant_view;

CREATE VIEW plant_view AS
SELECT Plant_Name, Plant_Type
FROM Plant;

SELECT p.Plant_Name, d.Disease_Name
FROM Plant p
LEFT JOIN Disease d
ON p.Plant_ID = d.Plant_ID;

SELECT Email FROM User
UNION
SELECT Email FROM Admin;



SELECT u.Email
FROM User u
INNER JOIN Admin a
ON u.Email = a.Email;


SELECT u.Email
FROM User u
INNER JOIN Admin a
ON u.Email = a.Email;

UPDATE User
SET Email = 'jana@gmail.com'
WHERE User_ID = 1;

SELECT u.Email
FROM User u
WHERE u.Email NOT IN (
    SELECT Email FROM Admin
);

SELECT Plant_Name, Disease_Name
FROM Plant
NATURAL JOIN Disease;


DELIMITER $$

CREATE FUNCTION GetPlantType(p_id INT)
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    DECLARE p_type VARCHAR(50);

    SELECT Plant_Type
    INTO p_type
    FROM Plant
    WHERE Plant_ID = p_id;

    RETURN p_type;
END$$

DELIMITER ;

SELECT GetPlantType(101) AS Plant_Type;
DELIMITER ;

SELECT GetPlantType(101) AS Plant_Type;

DELIMITER $$

CREATE PROCEDURE GetPlantsByType(IN p_type VARCHAR(50))
BEGIN
    SELECT *
    FROM Plant
    WHERE Plant_Type = p_type;
END$$

DELIMITER ;

CALL GetPlantsByType('Flower');

SELECT * FROM User;
SELECT * FROM Plant;
SELECT * FROM Care_Guide;
SELECT * FROM Disease;
SELECT * FROM User_Plant;