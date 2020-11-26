CREATE TABLE "Location" AS(
	SELECT DISTINCT id AS listing_id,
	street,
	neighbourhood,
	neighbourhood_cleansed,
	city,
	state,
	zipcode,
	market,
	smart_location,
	country_code,
	country,
	latitude,
	longtitude,
	is_location_exact
	FROM "Listings"
);

-- Foreign key from Location to Listings.

ALTER TABLE "Location"
ADD FOREIGN KEY (listing_id) REFERENCES "Listings"(id);

/*-- Using the following query , we're going to get the name of the FK constraint
SELECT CONSTRAINT_NAME
FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE TABLE_NAME = 'Listings'

It is "Listings_neighbourhood_cleansed_fkey" 
*/

-- Remove the relationship between Listings and Neighbourhoods.

ALTER TABLE "Listings"
DROP CONSTRAINT "Listings_neighbourhood_cleansed_fkey";

-- Foreign key from Location to Neighbourhoods.

ALTER TABLE "Location"
ADD FOREIGN KEY (neighbourhood_cleansed) REFERENCES "Neighbourhoods"(neighbourhood);

-- Drop Listings columns.
ALTER TABLE "Listings"
DROP COLUMN street,
DROP COLUMN neighbourhood,
DROP COLUMN neighbourhood_cleansed,
DROP COLUMN city,
DROP COLUMN state,
DROP COLUMN zipcode,
DROP COLUMN market,
DROP COLUMN smart_location,
DROP COLUMN country_code,
DROP COLUMN country,
DROP COLUMN latitude,
DROP COLUMN longtitude,
DROP COLUMN is_location_exact;
