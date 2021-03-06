-- QUERY 1 --
CREATE INDEX hostIdIndex ON "Listing"(host_id);
DROP INDEX hostIdIndex;

-- QUERY 2 -- 
CREATE INDEX priceIndex ON "Price"(price);
DROP INDEX priceIndex;

-- QUERY 3 --
CREATE INDEX hostIdIndex ON "Listing"(host_id);
DROP INDEX hostIdIndex;


-- QUERY 4 --
CREATE INDEX  roomAccomIndex ON "Room"(accommodates);
DROP INDEX roomAccomIndex;

-- QUERY 5 -- 
CREATE INDEX priceIndex ON "Price"(price);
DROP INDEX priceIndex;


-- QUERY 6 --
CREATE INDEX maxNightsIndex ON "Price"(maximum_nights);
DROP INDEX maxNightsIndex;


-- QUERY 7 --
CREATE INDEX geoIndex ON "Geolocation"(properties_neighbourhood);
DROP INDEX geoIndex;
