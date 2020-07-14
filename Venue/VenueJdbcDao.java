package com.techelevator.Venue;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class VenueJdbcDao implements VenueDao {

	private JdbcTemplate jdbcTemplate;
	
	public VenueJdbcDao (DataSource datasource) {
		
		jdbcTemplate = new JdbcTemplate(datasource);
		
	}
	

	
	@Override
	public List<Venue> listAllVenues() {
		List <Venue> venues = new ArrayList<Venue>();
		
		String selectSql = "SELECT venue.id AS venue_id, venue.name AS venue_name, venue.description, city.id AS city_id, city.name AS city_name, city.state_abbreviation FROM venue JOIN city ON venue.city_id = city.id ORDER BY venue_name";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);
		
		while (rows.next()) {
			Venue venue = mapRowToVenue (rows);
			venues.add(venue);
		}
	
		return venues;
	}

	@Override
	public List<String> getCategories(int venue_id) {
		List<String> categories = new ArrayList<>();
		
		String selectSql = "SELECT category.name FROM category_venue JOIN category ON category_venue.category_id = category.id WHERE category_venue.venue_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, venue_id);

		while (rows.next()) {
			categories.add(rows.getString("name"));
		}
		
		return categories;
	}
	
	
	private Venue mapRowToVenue( SqlRowSet row ) {
		Venue venue = new Venue();
		
		venue.setVenueId( row.getInt("venue_id") );
		venue.setVenueName(row.getString("venue_name"));
		venue.setCityId(row.getInt("city_id"));
		venue.setVenueDescription(row.getString("description"));
		venue.setCityName(row.getString("city_name"));
		venue.setState(row.getString("state_abbreviation"));

		return venue;
	}
	
	

}
