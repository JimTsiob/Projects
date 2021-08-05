
/* Ζήτημα Πρώτο */

CREATE TABLE campdata(
	custID int,
	fname varchar(30),
	lname varchar(30),
	cID int,
	country varchar(30),
	bookID int,
	bookDate datetime,
	campCode char(3),
	campName varchar(50),
	empno int,
	catCode char(1),
	category varchar(20),
	unitCost numeric(4,2),
	startDate datetime,
	overnights int,
	persons int
);

BULK INSERT campdata
FROM 'G:\AUEB\6thsemester\Database Design\Project B\CAMPDATA.TXT' /* adapted path */
WITH (FIRSTROW =2, FIELDTERMINATOR='|', ROWTERMINATOR = '\n');


DROP TABLE campdata;

/* Star Schema */

CREATE TABLE customer(
	custID int,
	fname varchar(30),
	lname varchar(30),
	cID int,
	country varchar(30),
	PRIMARY KEY(custID,cID)
);

CREATE TABLE Camp(
	campCode char(3) primary key,
	campName varchar(50)
);

CREATE TABLE Emplacement(
	empno int,
	catCode char(1),
	category varchar(20),
	PRIMARY KEY(empno,catCode)
);

CREATE TABLE timeinfo(
	time_key datetime primary key,
	t_year int,
	t_month int,
	t_dayofmonth int,
	t_hour int,
	t_quarter int,
	t_week int,
	t_dayofyear int,
	t_dayofweek int
);

CREATE TABLE Booking( /* Fact table */
	custID int,
	cID int,
	campCode char(3),
	empno int,
	time_key datetime,
	catCode char(1),
	bookID int,
	bookDate datetime,
	unitCost numeric(4,2),
	overnights int,
	persons int,

	PRIMARY KEY(custID,cID,campCode,empno,time_key,catCode,bookID),
	FOREIGN KEY(custID,cID) REFERENCES customer(custID,cID),
	FOREIGN KEY(campCode) REFERENCES Camp(campCode),
	FOREIGN KEY(empno,catCode) REFERENCES Emplacement(empno,catCode),
	FOREIGN KEY(time_key) REFERENCES timeinfo(time_key)
);

DROP TABLE Booking;

/* Gemisma pinakwn */

INSERT INTO customer 
SELECT DISTINCT custID,fname,lname,cID,country 
FROM campdata;

INSERT INTO Camp
SELECT DISTINCT campCode,campName
FROM campdata;


INSERT INTO Emplacement
SELECT DISTINCT empno,catCode,category
FROM campdata;

SET datefirst 1;

INSERT INTO timeinfo
SELECT DISTINCT startDate,DATEPART(year,startDate),DATEPART(month,startDate),
DATEPART(day,startDate),DATEPART(hour,startDate),DATEPART(quarter,startDate),
DATEPART(week,startDate),DATEPART(dayofyear,startDate),DATEPART(dw,startDate) 
FROM campdata;


INSERT INTO Booking
SELECT DISTINCT custID,cID,campCode,empno,startDate,catCode,bookID,bookDate,
unitCost,overnights,persons
FROM campdata;




/* Ζήτημα Δεύτερο */

/* Query 1 */
SELECT TOP 100 country,fname,lname,SUM(unitCost * overnights * persons) AS BookingCost
FROM customer,Booking 
WHERE customer.cID = Booking.cID AND customer.custID = Booking.custID
GROUP BY country,fname,lname
ORDER BY BookingCost DESC;


/* Query 2 */
SELECT campName,category,SUM(unitCost * overnights * persons) AS BookingCost
FROM Emplacement,Camp,Booking,timeinfo
WHERE Emplacement.empno = Booking.empno AND Emplacement.catCode = Booking.catCode AND Camp.campCode = Booking.campCode
AND timeinfo.time_key = Booking.time_key AND t_year = 2000
GROUP BY category,campName; 


/* Query 3 */
SELECT campName,t_month,SUM(unitCost * overnights * persons) AS BookingCost
FROM Camp,timeinfo,Booking
WHERE Camp.campCode = Booking.campCode AND timeinfo.time_key = Booking.time_key AND t_year = 2018
GROUP BY campName,t_month
ORDER BY campName,t_month;

/* Query 4 */
SELECT t_year,campName,category,COUNT(persons) AS totalPersonCount
FROM Booking,timeinfo,Camp,Emplacement
WHERE timeinfo.time_key = Booking.time_key AND Camp.campCode = Booking.campCode AND
Emplacement.empno = Booking.empno AND Emplacement.catCode = Booking.catCode
GROUP BY ROLLUP (t_year,campName,category);

/* Query 5 */
CREATE VIEW v1 AS
SELECT t_year,campName,COUNT(persons) AS totalPersonCount
FROM timeinfo , Booking,Camp
WHERE timeinfo.time_key = Booking.time_key AND Camp.campCode = Booking.campCode
AND t_year = 2017
GROUP BY t_year,campName;

CREATE VIEW v2 AS
SELECT t_year,campName,COUNT(persons) AS totalPersonCount
FROM timeinfo , Booking,Camp
WHERE timeinfo.time_key = Booking.time_key AND Camp.campCode = Booking.campCode
AND t_year = 2018
GROUP BY t_year,campName;



SELECT v2.campName 
FROM v1,v2 
WHERE v1.campName = v2.campName AND v2.totalPersonCount > v1.totalPersonCount
GROUP BY v2.campName;

SELECT DISTINCT country FROM customer;


/* Ζήτημα Τρίτο */


SELECT t_year,campName,category,SUM(unitCost * overnights * persons) AS totalBookingCosts
FROM timeinfo,Camp,Booking,Emplacement
WHERE timeinfo.time_key = Booking.time_key AND Camp.campCode = Booking.campCode
AND Emplacement.catCode = Booking.catCode
GROUP BY CUBE(t_year,campName,category);
