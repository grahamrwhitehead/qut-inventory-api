DROP TABLE IF EXISTS inventory_item;
DROP TABLE IF EXISTS manufacturer;

CREATE TABLE manufacturer (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(150) NOT NULL UNIQUE,
     home_page VARCHAR(150) DEFAULT NULL,
     phone VARCHAR(50) DEFAULT NULL
);

CREATE TABLE inventory_item (
     id INT AUTO_INCREMENT PRIMARY KEY,
     inv_item_no VARCHAR(30) NOT NULL UNIQUE,
     name VARCHAR(250) NOT NULL,
     release_date TIMESTAMP NOT NULL,
     manufacturer_id INT NOT NULL,
     FOREIGN KEY(manufacturer_id) REFERENCES manufacturer
);


INSERT INTO manufacturer (name, home_page, phone) VALUES ('Lego', 'https://www.lego.com/en-au', '02 1342 4343');
INSERT INTO manufacturer (name, home_page, phone) VALUES ('Sony', 'https://www.sony.com.au/', '07 5453 3645');

INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('75192', 'Millennium Falcon', {ts '2017-10-01 00:00:00'}, 1);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('75252', 'Star Destroyer', {ts '2019-09-18 00:00:00'}, 1);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('75159', 'Death Star', {ts '2016-09-15 00:00:00'}, 1);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('75244', 'Tantive IV', {ts '2019-05-03 00:00:00'}, 1);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('75181', 'Y-Wing Starfighter', {ts '2018-05-04 00:00:00'}, 1);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('75292', 'The Razor Crest', {ts '2020-09-01 00:00:00'}, 1);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('75256', 'Kylo Ren''s Shuttle', {ts '2019-10-04 00:00:00'}, 1);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('75291', 'Death Star Final Duel', {ts '2020-08-01 00:00:00'}, 1);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('75272', 'Sith TIE Fighter', {ts '2020-01-01 00:00:00'}, 1);

INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('SCPH-1000', 'Playstation', {ts '1994-12-03 00:00:00'}, 2);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('SCPH-90002', 'Playstation 2', {ts '2000-11-30 00:00:00'}, 2);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('CECHC01', 'Playstation 3', {ts '2007-03-23 00:00:00'}, 2);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('CUH-1115A', 'Playstation 4', {ts '2013-11-15 00:00:00'}, 2);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('Z9G', 'Master Series Z9G', {ts '2020-11-23 00:00:00'}, 2);
INSERT INTO inventory_item(inv_item_no, name, release_date, manufacturer_id) VALUES ('A8G', 'Bravia OLED', {ts '2019-12-04 00:00:00'}, 2);
