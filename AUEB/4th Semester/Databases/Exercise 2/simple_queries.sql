/*
Find all hosts with 100% acceptance rate. This could be searched by users in order to find the friendliest hosts
so they can know who to choose. Name sorting is for easier reading by the user.
Output : 6442 rows
*/

SELECT host_name
FROM "Listings"
WHERE host_acceptance_rate = '100%'
ORDER BY host_name;

/*
Find the id's of the houses with the biggest amount of positive reviews based on two words (good and clean). 
People always search for houses with the most positive reviews because this indicates that this house is worth renting.
Output : 6464 rows
*/

SELECT listing_id,COUNT(comments) AS positive_reviews
FROM "Reviews" 
WHERE comments LIKE '%good%' OR comments LIKE '%clean%'
GROUP BY (listing_id)
ORDER BY positive_reviews DESC;

/*
Find all houses with 1 bedroom and 1 bathroom which are available for renting between 20-03-2020 and 23-03-2020.
This could be searched by a user who wants to rent a house alone in a specific date.
Output : 5157 rows
*/

SELECT DISTINCT cal.listing_id,l.bedrooms,l.bathrooms
FROM "Listings" l
JOIN "Calendar" cal ON cal.listing_id = l.id
WHERE cal.available = 't' AND cal.date BETWEEN '20-03-2020' AND '23-03-2020' AND l.bedrooms = 1 AND l.bathrooms = 1
GROUP BY cal.listing_id,l.bedrooms,l.bathrooms;

/*
Find all houses and their renting prices in the Ampelokipoi area. 
Users can search this if they want to stay in a specific area.
Output : 367 rows
*/

SELECT n.neighbourhood,l.id,l.price
FROM "Neighbourhoods" n
JOIN "Listings" l
ON n.neighbourhood = l.neighbourhood_cleansed
WHERE n.neighbourhood = 'ΑΜΠΕΛΟΚΗΠΟΙ';

/*
Find 100 houses,their square feet and their areas.
This could be a search criteria for a user.
Output : 100 rows
*/

SELECT DISTINCT cal.listing_id,l.square_feet,l.neighbourhood_cleansed
FROM "Listings" l
LEFT OUTER JOIN "Calendar" cal
ON cal.listing_id = l.id
LIMIT 100;


/*
Find all houses available for renting between 20/03 and 27/03 and their weekly prices.
This could be searched by someone who wants to spend a week or more on specific dates.
Output : 10622 rows
*/

SELECT DISTINCT cal.listing_id, l.weekly_price
FROM "Listings" l
LEFT OUTER JOIN "Calendar" cal
ON cal.listing_id = l.id
WHERE cal.available = 't' AND cal.date BETWEEN '20-03-2020' AND '27-03-2020';


/*
Find the house with the biggest acceptable (from the host) amount of accommodates and the least cleaning fee.
This could possibly be searched by a travel agent for a big tourist group to save money.
Output : 1 row
*/

SELECT id
FROM "Listings"
WHERE accommodates = (SELECT MAX(accommodates)
					 FROM "Listings") AND cleaning_fee = (SELECT MIN(cleaning_fee)
														 FROM "Listings");
	

/*
Find the average number of bedrooms in each area's houses.
A person could search this to see which area has bigger houses than others in order to narrow down his search of houses in that area.
Output : 45 rows
*/


SELECT ROUND(AVG(bedrooms)),n.neighbourhood
FROM "Listings" l
JOIN "Neighbourhoods" n
ON n.neighbourhood = l.neighbourhood_cleansed
GROUP BY n.neighbourhood;
				 
				 

/*
Find all houses that are contained in the area specified by the coordinates.
This could be searched by someone who wants to rent a house at a very specific location inside an area.
Output : 4614 rows
*/

SELECT l.id , geo.properties_neighbourhood
FROM "Listings" l
JOIN "Geolocation" geo
ON geo.properties_neighbourhood = l.neighbourhood_cleansed
WHERE geo.geometry_coordinates_0_0_29_0 BETWEEN '23.724727' AND '23.744308' 
AND geo.geometry_coordinates_0_0_29_1 BETWEEN '37.968134' AND '38.015347';


/*
Find all houses and their respective neighbourhoods which can accommodate 4 or more people.
This could be searched by a family who wants to rent a house in a specific area.
Output : 6769 rows
*/

SELECT l.id , n.neighbourhood
FROM "Listings" l
JOIN "Neighbourhoods" n
ON n.neighbourhood = l.neighbourhood_cleansed
WHERE l.accommodates >= 4;

/*
Find 100 houses and their respective locations which are available for renting and have Wifi.
This could be searched by someone who needs wifi in his house.
Output : 100 rows
*/

SELECT DISTINCT l.id,l.neighbourhood_cleansed
FROM "Listings" l
JOIN "Calendar" cal 
ON l.id = cal.listing_id
WHERE l.amenities LIKE '%Wifi%' AND cal.available = 't'
LIMIT 100;


/*
Find all the hosts , their id's and their photographs, who have the greatest review score rating of all.
This could be searched by users because if a host has the maximum review score rating that means he's hospitable , friendly and kind
to his guests.
Output : 2353 rows
*/

SELECT host_name,host_id,host_picture_url
FROM "Listings"
WHERE review_scores_rating = (SELECT MAX(review_scores_rating)
							 FROM "Listings");