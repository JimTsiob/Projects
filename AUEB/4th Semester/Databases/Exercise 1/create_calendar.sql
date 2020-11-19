CREATE TABLE "Calendar"(
	listing_id int,
	date varchar(100),
	available char(1),
	price varchar(30),
	adjusted_price varchar(30),
	minimum_nights int,
	maximum_nights int,
	PRIMARY KEY (listing_id,date)
);

