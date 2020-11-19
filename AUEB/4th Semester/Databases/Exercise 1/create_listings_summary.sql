CREATE TABLE "Listings_summary"(
	id int,
	name varchar(100),
	host_id int,
	host_name varchar(100),
	neighbourhood_group varchar(100),
	neighbourhood varchar(100),
	latitude float,
	longtitude float,
	room_type varchar(100),
	price int,
	minimum_nights int,
	number_of_reviews int,
	last_review varchar(100),
	reviews_per_month float,
	calculated_host_listings_count int,
	availability_365 int,
	PRIMARY KEY(id)
);



