

/* Ζήτημα Πρώτο */
/* Άσκηση 1 */



SET statistics IO ON;

CREATE INDEX movieIndex ON movies(pyear,title); 

DROP INDEX movieIndex ON movies;



BEGIN
select title from movies where pyear between 1990 and 2000

select pyear, title from movies where pyear between 1990 and 2000

select title, pyear from movies where pyear between 1990 and 2000
order by pyear, title
END


/* Άσκηση 2 */

checkpoint
dbcc dropcleanbuffers

SET statistics IO ON;

CREATE INDEX userMovieIndex ON user_movies(mid,rating,userid);

DROP INDEX userMovieIndex ON user_movies;

BEGIN
select mid, count(rating) 
from user_movies group by mid order by mid 

select userid, count(rating) 
from user_movies group by userid order by userid
END



/* Ζήτημα Δεύτερο */
/* Άσκηση 1*/


checkpoint
dbcc dropcleanbuffers

SET statistics IO ON;

CREATE INDEX titleIndex ON movies(mid,title);
CREATE INDEX genreIndex ON movies_genre(mid,genre);

DROP INDEX titleIndex ON movies;
DROP INDEX titleIndex ON movies_genre;
DROP INDEX genreIndex ON movies_genre;

SELECT title
FROM movies,movies_genre
WHERE movies.mid = movies_genre.mid AND (genre = 'Action' OR genre = 'Adventure')
GROUP BY title;

select title
from movies, movies_genre
where movies.mid=movies_genre.mid and genre = 'Adventure'
UNION
select title
from movies, movies_genre
where movies.mid=movies_genre.mid and genre = 'Action'


/* Άσκηση 2 */

checkpoint
dbcc dropcleanbuffers

SET statistics IO ON;

SELECT DISTINCT title,movies.mid
FROM movies,actors,roles
WHERE movies.mid = roles.mid AND actors.aid = roles.aid AND gender = 'M'
EXCEPT
SELECT DISTINCT title,movies.mid
FROM movies,roles,actors
WHERE movies.mid = roles.mid AND actors.aid = roles.aid AND gender = 'F';


SELECT DISTINCT title,movies.mid
FROM movies,actors,roles
WHERE movies.mid = roles.mid AND actors.aid = roles.aid AND gender = 'M' AND movies.mid NOT IN (
	SELECT DISTINCT movies.mid
	FROM movies,actors,roles
	WHERE movies.mid = roles.mid AND actors.aid = roles.aid AND gender = 'F'
);

CREATE INDEX titleIdIndex ON movies(title,mid);
CREATE INDEX genderIndex ON actors(gender);
CREATE INDEX roleIndex ON roles(aid);


DROP INDEX titleIdIndex ON movies;
DROP INDEX genderIndex ON actors;
DROP INDEX roleIndex ON roles;

/* 183657 */


/* Ζήτημα Τρίτο */
/* Άσκηση 1 και 2 */

checkpoint
dbcc dropcleanbuffers

SET statistics IO ON;


SELECT DISTINCT title
FROM movies,user_movies
WHERE movies.mid = user_movies.mid AND user_movies.rating > 3;


CREATE INDEX ratingIndex ON user_movies(rating,mid);
CREATE INDEX titleIndex ON movies(title,mid);

DROP INDEX ratingIndex ON user_movies;
DROP INDEX titleIndex ON movies;


SELECT DISTINCT directors.firstName,directors.lastname,movie_directors.mid
FROM movie_directors,directors,movies_genre,user_movies
WHERE movies_genre.mid = movie_directors.mid AND directors.did = movie_directors.did AND genre = 'Western';


CREATE INDEX userTitleIndex ON user_movies(rating);
CREATE INDEX movieDirIndex ON movie_directors(did);
CREATE INDEX directorIndex ON directors(firstName,lastname);
CREATE INDEX movieGenreIndex ON movies_genre(genre,mid);


DROP INDEX movieDirIndex ON movie_directors;
DROP INDEX directorIndex ON directors;
DROP INDEX movieGenreIndex ON movies_genre;
DROP INDEX userTitleIndex ON user_movies;