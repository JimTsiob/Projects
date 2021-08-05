BULK INSERT actors
 FROM 'G:\AUEB\6thsemester\Database Design\Project A\movieData\actors.txt'
WITH (FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT directors
 FROM 'G:\AUEB\6thsemester\Database Design\Project A\movieData\directors.txt'
WITH ( FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT movies
 FROM 'G:\AUEB\6thsemester\Database Design\Project A\movieData\movies.txt'
WITH ( FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT movie_directors
 FROM 'G:\AUEB\6thsemester\Database Design\Project A\movieData\movie_directors.txt'
WITH ( FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT movies_genre
 FROM 'G:\AUEB\6thsemester\Database Design\Project A\movieData\movies_genre.txt'
WITH ( FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT roles
 FROM 'G:\AUEB\6thsemester\Database Design\Project A\movieData\roles.txt'
WITH ( FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT users
 FROM 'G:\AUEB\6thsemester\Database Design\Project A\movieData\users.txt'
WITH (FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT user_movies
 FROM 'G:\AUEB\6thsemester\Database Design\Project A\movieData\user_movies.txt'
WITH (FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

