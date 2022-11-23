insert into airport(code,name,location) values (101,'Indira Gandhi International Airport','Delhi');
insert into airport(code,name,location) values (102,'Chhatrapati Shivaji International Airport','Mumbai');
insert into airport(code,name,location) values (103,'Kempegowda International Airport','Bangalore');
insert into airport(code,name,location) values (104,'Madras International Airport','Chennai');
insert into airport(code,name,location) values (105,'Rajiv Gandhi International Airport','Hyderabad');
insert into airport(code,name,location) values (106,'Netaji Subhash Chandra Bose International Airport','Kolkata');
insert into airport(code,name,location) values (107,'Sri Guru Ram Dass Jee International Airport','Amritsar');
insert into airport(code,name,location) values (108,'Chandigarh International Airport','Chandigarh');
insert into airport(code,name,location) values (109,'Jaipur International Airport','Jaipur');
insert into airport(code,name,location) values (110,'Cochin International Airport','Kochi');
insert into airport(code,name,location) values (111,'Veer Savarkar International Airport','Port Blair');
insert into airport(code,name,location) values (112,'Biju Patnaik International Airport','Bhubaneswar');
insert into airport(code,name,location) values (113,'Calicut International Airport','Kozhikode');
insert into airport(code,name,location) values (114,'Trivandrum International Airport','Thiruvananthapuram');
insert into airport(code,name,location) values (115,'Sheikh ul-Aalam International Airport','Srinagar');

insert into flight(fno, cname, fmodel, seatcap) values (10001,'Indigo', 'Jet', 100);
insert into flight(fno, cname, fmodel, seatcap) values (10002,'Spicejet', 'Airbus', 120);
insert into flight(fno, cname, fmodel, seatcap) values (10003,'Air India', 'Jet', 150);

insert into user(id, email, name, pwd, phno, type) values (11, 'hemant@gmail.com', 'Hemant', 'hemant@1129', 7738684418, 'Admin');
insert into user(id, email, name, pwd, phno, type) values (12, 'sahil@gmail.com', 'Sahil', 'sahil@0307', 7032716763, 'Admin');
insert into user(id, email, name, pwd, phno, type) values (13, 'harshita@gmail.com', 'Harshita', 'hrathore8', 9672716925, 'Admin');
insert into user(id, email, name, pwd, phno, type) values (14, 'simran@gmail.com', 'Simran', 'simran@1357', 9079162206, 'Admin');
insert into user(id, email, name, pwd, phno, type) values (15, 'harsh@gmail.com', 'Harsh', 'Harsh@123', 9068967542, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (16, 'amit123@gmail.com', 'Amit', 'Amity@567', 7623568178, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (17, 'mansiii12@gmail.com', 'Mansi', 'Mansi06@', 9769616719, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (18, 'sakshii89@gmail.com', 'Sakshi', 'saxxi@12345', 9079167656, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (19, 'aakash654@gmail.com', 'Akash', 'Akash@321', 8765456776, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (20, 'yogeshY@gmail.com', 'Yogesh', 'Yogesh@987', 7654568976, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (21, 'sarveshTiwari@gmail.com', 'Sarvesh', 'sarvesh@tiwari3', 9079166543, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (22, 'palakNayak@gmail.com', 'Palak', 'Palak@1129', 9076512456, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (23, 'janvi123@gmail.com', 'Janvi', 'JanviTAwde@12', 8978675406, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (24, 'namrata1234@gmail.com', 'Namrata', 'NamrataJ56', 9656545676, 'Admin');
insert into user(id, email, name, pwd, phno, type) values (25, 'ishika@gmail.com', 'Ishika', 'Ishika@45', 9452342206, 'Admin');
insert into user(id, email, name, pwd, phno, type) values (26, 'dhirajYadav@gmail.com', 'Dhiraj', 'Dhiraj345@', 9779162206, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (27, 'hy3588211@gmail.com', 'Hritik', 'hy@125675', 9765432456, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (28, 'akashsky@gmail.com', 'Akash', 'Akash@sky', 9564322206, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (29, 'parshant@gmail.com', 'Parshant', 'Parshant9065@', 9206763452, 'Customer');
insert into user(id, email, name, pwd, phno, type) values (30, 'ashuSingh1305@gmail.com', 'Ashish', 'Ashu@1287', 9876512456, 'Customer');

insert into schedule(sid, atime, dtime, destination_code, source_code) values (100,'2022-12-26 11:00:00','2022-12-26 09:00:00',109,101);

insert into scheduled_flight(sfid, avalseat, flight_fno, schedule_sid) values (100,90,10001,100);

insert into booking(booking_id, date, nop, cost, sfid, user_id) values (1000, '2022-12-24 12:00:00', 1, 6000, 100, 12);

insert into passenger(pnr, luggage, age, name, uin, booking_id) values (1, 12, 22, 'Sahil', 836484251673, 1000);