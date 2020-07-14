package com.techelevator.Reservation;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ReservationJdbcDao implements ReservationDao {
	
	
	private JdbcTemplate jdbcTemplate;
	
	public ReservationJdbcDao (DataSource datasource) {
		
		jdbcTemplate = new JdbcTemplate(datasource);
	
	}

	
	@Override
	public void create(Reservation reservation) {
		
		String selectSql = "INSERT INTO reservation (reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for) VALUES (((SELECT MAX(reservation_id) FROM reservation)+1), ?, ?, ?, ?, ?) RETURNING reservation_id";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, reservation.getSpaceId(), reservation.getNumOfAttendees(), reservation.getStartDate(), reservation.getEndDate(), reservation.getReservedFor());
		
		rows.next();
		
		reservation.setReservationId(rows.getInt("reservation_id"));
	}

}
