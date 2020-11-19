BEGIN;
ALTER TABLE "Listings"
ADD FOREIGN KEY (neighbourhood_cleansed) REFERENCES "Neighbourhoods"(neighbourhood);

ALTER TABLE "Neighbourhoods"
ADD FOREIGN KEY (neighbourhood) REFERENCES "Geolocation"(properties_neighbourhood);

ALTER TABLE "Calendar"
ADD FOREIGN KEY (listing_id) REFERENCES "Listings"(id);

ALTER TABLE "Reviews"
ADD FOREIGN KEY (listing_id) REFERENCES "Listings"(id);

ALTER TABLE "Listings_summary"
ADD FOREIGN KEY (id) REFERENCES "Listings"(id);
COMMIT;