package com.techelevator;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.Reservation.Reservation;
import com.techelevator.Reservation.ReservationDao;
import com.techelevator.Reservation.ReservationJdbcDao;
import com.techelevator.Space.Space;
import com.techelevator.Space.SpaceDao;
import com.techelevator.Space.SpaceJdbcDao;
import com.techelevator.Venue.Venue;
import com.techelevator.Venue.VenueDao;
import com.techelevator.Venue.VenueJdbcDao;

public class ExcelsiorCLI {
	
	private String[] mainMenu = {"1) List Venues", "Q) Quit"};
	private String[] venueDetails = {"1) View Spaces", "R) Return to Previous Screen" };
	private String[] reserveSpaceMenu = {"1) Reserve a Space", "R) Return to Previous Screen"};
	private Menu menu = new Menu();
	private Reservation reservation;
	
	private ReservationDao reservationDao; 
	private SpaceDao spaceDao;
	private VenueDao venueDao;
	
	
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
	}
	
	public ExcelsiorCLI(DataSource datasource) {
		
		reservationDao = new ReservationJdbcDao (datasource);
		spaceDao = new SpaceJdbcDao (datasource);
		venueDao = new VenueJdbcDao (datasource);

	}

	public void run() {

		while (true) {
			
			int firstMenuUserChoice = menu.getUserMenuSelection(mainMenu);
			
			if (firstMenuUserChoice == 99999) {
				
				break;
				
			}
				while (firstMenuUserChoice == 1) {
					
					firstMenuUserChoice = menu.listVenues(venueDao.listAllVenues());
					
					
					if (firstMenuUserChoice == 88888) {
						break;
					}
					
					if ((firstMenuUserChoice - 1) < 0) {
						
						menu.displayErrorMessage("Venue selection invalid, please try again");
						firstMenuUserChoice = 1;
						break;
					}
					
					Venue venue = handleVenues(firstMenuUserChoice);
					
					int secondMenuUserChoice = menu.displayVenue(venue, venueDao.getCategories(venue.getVenueId()), venueDetails);
					
					if (secondMenuUserChoice == 88888) {
						firstMenuUserChoice = 1;
						secondMenuUserChoice = 0;
					}

					while (secondMenuUserChoice == 1) {

						int thirdMenuUserChoice = menu.displayAllSpaces(venue.getVenueName(), spaceDao.list(venue.getVenueId()) , reserveSpaceMenu);
						
						if (thirdMenuUserChoice == 88888) {
							firstMenuUserChoice = 1;
							secondMenuUserChoice = 0;
							thirdMenuUserChoice = 0;
						}
						
						while (thirdMenuUserChoice == 1) {
							
							reservation  = menu.reservationMenu();
							 
							List<Space> spaces = spaceDao.getAvailableSpaces(reservation.getNumOfAttendees(), venue.getVenueId(), reservation.getStartDate(), reservation.getEndDate());
	
												
							reservation = menu.displayAvailableSpaces(reservation, spaces);
							
							 if (reservation.getSpaceId() == 0) {
								 
								 thirdMenuUserChoice = 0;
								 break;
							 }
							
							reservationDao.create(reservation);
							menu.getReservationDetails(reservation, venue);
							
							
							firstMenuUserChoice = 0;
							secondMenuUserChoice = 0;
							thirdMenuUserChoice = 0;
							break;
							
						}
					}						
				}	
				
		}
	}
	
		private Venue handleVenues(int userChoice) {
			
			
			Venue venue;
			
			venue = venueDao.listAllVenues().get(userChoice - 1);
			
			return venue;
		}
}
