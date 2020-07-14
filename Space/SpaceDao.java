package com.techelevator.Space;

import java.time.LocalDate;
import java.util.List;

public interface SpaceDao {
	
	List<Space> list(int venueId);
	List<Space> getAvailableSpaces (int maxOccupancy, int venueId, LocalDate startDate, LocalDate endDate);
	
}
