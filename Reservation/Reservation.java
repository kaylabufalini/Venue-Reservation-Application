package com.techelevator.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
	
	private int confirmation;
	private int reservationId;
	private int numOfAttendees;
	private LocalDate startDate;
	private LocalDate endDate;
	private String reservedFor;
	private int spaceId = 0;
	private String spaceName;
	private BigDecimal dailyRate;
	
	
	
	public BigDecimal getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(BigDecimal dailyRate) {
		this.dailyRate = dailyRate;
	}
	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	public int getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}
	public int getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(int confirmation) {
		this.confirmation = confirmation;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getNumOfAttendees() {
		return numOfAttendees;
	}
	public void setNumOfAttendees(int numOfAttendees) {
		this.numOfAttendees = numOfAttendees;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	
	
	public void setStartDate(String startDate) {
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
			
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			this.startDate = localDate;
			
		} catch (Exception e) {
			System.out.println("Your date is formatted incorrectly, please retry (yyyy/MM/d)");
		}

	}
	
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	
	public void setEndDate(int endDate) {
		
		LocalDate startLocalDate = getStartDate();
		LocalDate endOfReservation = LocalDate.of(startLocalDate.getYear(), startLocalDate.getMonth(), startLocalDate.getDayOfMonth()).plusDays(endDate);
		
		this.endDate = endOfReservation;
	}
	
	public String getReservedFor() {
		return reservedFor;
	}
	
	public void setReservedFor(String reservedFor) {
		this.reservedFor = reservedFor;
	}
	

}
