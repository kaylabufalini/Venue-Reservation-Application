package com.techelevator.Space;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class SpaceJdbcDao implements SpaceDao {

	private JdbcTemplate jdbcTemplate;
	
	public SpaceJdbcDao (DataSource datasource) {
		
		jdbcTemplate = new JdbcTemplate(datasource);
	
	}
	
	
	@Override
	public List<Space> list (int venueId) {
		
		List <Space> spaces = new ArrayList<Space>();
		
		String selectSql = "SELECT id, venue_id, name, is_accessible, open_from, open_to, CAST (daily_rate AS decimal), max_occupancy FROM space WHERE venue_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, venueId);
		
		while(rows.next()) {
			Space space = mapRowToSpace (rows);
			spaces.add(space);
		}
		return spaces;
	}


	
	
	@Override
	public List<Space> getAvailableSpaces(int maxOccupancy, int venueId, LocalDate startDate, LocalDate endDate) {
				
		List <Space> spaces = new ArrayList<Space>();
	
		String selectSql = "SELECT id, venue_id, name, is_accessible, open_from, open_to, CAST (daily_rate AS decimal), max_occupancy FROM space WHERE max_occupancy >= ? AND venue_id = ? AND id NOT IN (SELECT space_id FROM reservation WHERE CAST (? AS DATE) <= end_date AND CAST (? AS DATE) >= start_date) LIMIT 5";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, maxOccupancy, venueId, "'" + startDate + "'", "'" + endDate + "'");
		
		while(rows.next()) {
			
			Space space = mapRowToSpace (rows);
			spaces.add(space);
		}
		
		return spaces;
			
	}
		
	private Space mapRowToSpace ( SqlRowSet row) {
		Space space = new Space();
		
		space.setSpaceId(row.getInt("id"));
		space.setSpaceName(row.getString("name"));
		space.setAccessible(row.getBoolean("is_accessible"));
		if (row.getObject("open_from") != null) {
			space.setOpenFrom(row.getInt("open_from"));
		}
		if (row.getObject("open_to") != null) {
			space.setOpenTo(row.getInt("open_to"));
		}
		space.setDailyRate(row.getBigDecimal("daily_rate"));
		space.setMaxOccupancy(row.getInt("max_occupancy"));
		
		return space;
	}
	
	
	

}
