SELECT venue.id AS venue_id, venue.name AS venue_name, venue.description, city.id AS city_id, city.name AS city_name, city.state_abbreviation FROM venue
JOIN city ON venue.city_id = city.id
ORDER BY venue_id;


SELECT category.name
FROM category_venue
JOIN category ON category_venue.category_id = category.id
WHERE category_venue.venue_id = ?;

SELECT id, venue_id, name, is_accessible, open_from, open_to, CAST (daily_rate AS decimal), max_occupancy FROM space WHERE max_occupancy >= ? AND venue_id = ? AND id NOT IN (SELECT space_id FROM reservation WHERE CAST (? AS DATE) <= end_date AND CAST (? AS DATE) >= start_date) LIMIT 5


SELECT id FROM space WHERE max_occupancy >= 5 AND venue_id = 1 AND id NOT IN (SELECT space_id FROM reservation WHERE CAST ('2020-06-19' AS DATE) <= end_date AND CAST ('2020-06-22' AS DATE) >= start_date);


SELECT id, venue_id, name, is_accessible, open_from, open_to, daily_rate, max_occupancy FROM space WHERE venue_id = 1;


SELECT venue.id AS venue_id, venue.name AS venue_name, venue.description, city.id AS city_id, city.name AS city_name, city.state_abbreviation FROM venue JOIN city ON venue.city_id = city.id ORDER BY venue_name;


SELECT id, venue_id, name, is_accessible, open_from, open_to, CAST (daily_rate AS decimal), max_occupancy FROM space WHERE venue_id = 1;


INSERT INTO reservation (reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for) VALUES (DEFAULT, 41, 10, CAST ('2020-06-18' AS DATE), CAST ('2020-06-22' AS DATE), 'Test')  RETURNING reservation_id;







SELECT space.id, space.venue_id, space.name AS space_name, space.is_accessible, space.open_from, space.open_to, CAST (daily_rate AS decimal), space.max_occupancy, reservation.number_of_attendees, reservation.start_date, reservation.end_date, reservation.reserved_for FROM space 
JOIN reservation ON space.id = reservation.space_id
WHERE space.venue_id = 1 AND space.max_occupancy >= 5;


 AND CAST('2020-06-16' AS DATE) < reservation.start_date AND CAST('2020-06-18' AS DATE) > reservation.end_date;





SELECT id, name AS space_name, is_accessible, CAST (daily_rate AS decimal), max_occupancy FROM space 
WHERE venue_id = 20 AND max_occupancy >= 5;

-- campground = space
-- site = venue
"join campground on site.campground_id = campground.campground_id " + 
				"where site.campground_id = ? " + 
				"and site_id not in " + 
				"(select site.site_id from site " + 
				"JOIN reservation ON reservation.site_id = site.site_id " + 
				"WHERE ? > reservation.from_date and ? < reservation.to_date) " + 
				"order by daily_fee " + 
				"LIMIT 5";

SELECT distinct * FROM space
JOIN reservation ON;

SELECT * FROM space 
JOIN reservation ON space.id = reservation.space_id;


SELECT * FROM space 
WHERE max_occupancy >= 50 AND venue_id = 1 AND id NOT IN (SELECT space_id FROM reservation WHERE CAST('2020-06-19' AS DATE) <= end_date AND CAST('2020-06-22' AS DATE) >= start_date); 

SELECT id FROM space WHERE max_occupancy >= 5 AND venue_id = 1 AND id NOT IN (SELECT space_id FROM reservation WHERE CAST ('2020-06-19' AS DATE) <= end_date AND CAST ('2020-06-22' AS DATE) >= start_date);


SELECT * FROM space
JOIN reservation ON space.id = reservation.space_id
WHERE venue_id = 1 AND max_occupancy >= 5 AND WHERE NOT EXISTS (CAST('2020-06-19' AS DATE) <= end_date AND CAST('2020-06-22' AS DATE) >= start_date);


SELECT * FROM space 
WHERE max_occupancy >= 5 AND venue_id = 1 AND id NOT IN (SELECT space_id FROM reservation WHERE CAST('2020-06-19' AS DATE) <= end_date AND CAST('2020-06-22' AS DATE) >= start_date); 

SELECT *
FROM reservation;


SELECT space.id FROM space 
JOIN reservation ON space.id = reservation.space_id
WHERE space.venue_id =1 AND (space.id NOT IN (SELECT reservation.space_id WHERE space.max_occupancy >= 5 AND CAST('2020-06-19' AS DATE) <= reservation.end_date AND CAST('2020-06-22' AS DATE) >= reservation.start_date)); 

INSERT INTO reservation (reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for) VALUES (DEFAULT, 41, 10, CAST ('2020-06-18' AS DATE), CAST ('2020-06-22' AS DATE), 'Test')  RETURNING reservation_id;

 

UPDATE reservation SET reservation_id = 0, space_id = 0, number_of_attendees = 0, start_date = '', end_date = '', reserved_for = '' WHERE reservation_id = <condition>;

INSERT INTO reservation (reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for) VALUES (0, 0, 0, '', '', '');
