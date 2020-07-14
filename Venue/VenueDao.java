package com.techelevator.Venue;

import java.util.List;

public interface VenueDao {
	
	List<Venue> listAllVenues();
	List<String> getCategories(int venue_id);

}
