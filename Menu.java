package com.techelevator;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.Reservation.Reservation;
import com.techelevator.Space.Space;
import com.techelevator.Venue.Venue;



public class Menu {

	private int endDateInt;
	
	
	private Scanner input = new Scanner(System.in);
	
		public int getUserMenuSelection (String[] menuOptions) {
		
			System.out.println();
		
			for (int i = 1; i < menuOptions.length + 1; i++ ) {
				System.out.println(menuOptions[i-1]);
			}
		
			return returnUserSelection();
		
		}
		
	
			
		public int listVenues (List<Venue> venue) {
			
			int count = venue.size();
			
			System.out.println("Which venue would you like to view?");
			
			for (int i = 1; i < venue.size() + 1; i++) {
			
					System.out.println("\t" + "(" + i + ") " + venue.get(i-1).getVenueName());
					
				}
				
			System.out.println("(R) Return to Previous Screen");
			
			int validUserInput = returnUserSelection();
			
			while ((validUserInput > count) || (validUserInput < 0)) {
				
				System.out.println("Invalid venue selection, please reselect");
				String validUserString = input.nextLine();
				validUserInput = Integer.parseInt(validUserString);
			}
			
			return validUserInput;
			
		}
			
			
		
		private int returnUserSelection() {
			
			String userChoice = input.nextLine();
			
			if (userChoice.equalsIgnoreCase("Q")) {
			
				return 99999;
			}
		
			else if (userChoice.equalsIgnoreCase("R")) {
				
				return 88888;
				
			} else {
				
				int userIntChoice = Integer.parseInt(userChoice);
				return userIntChoice;
				
			}
		}
			
		
		
		public int displayVenue(Venue venue, List<String> categories, String[] menuOptions) {
			
			System.out.println();
			System.out.println(venue.getVenueName());
			System.out.println("Location: " + venue.getCityName() + ", " + venue.getState());
			System.out.print("Categories: ");
			
			for (String item : categories) {
			System.out.print(item + " ");
			
			}
			
			System.out.println();
			System.out.println();
			System.out.println(venue.getVenueDescription());
			System.out.println();
			System.out.println();
			
			System.out.println("What would you like to do next?");
			return getUserMenuSelection(menuOptions);
			
		}
		
		//----------------------------------------------------------------------------------------
		

			

		

		
		
		
		
		
		
		
		
		
		
		//----------------------------------------------------------------------------------
		
		// TODO format text properly
		public int displayAllSpaces(String venueName, List<Space> spaces, String[] menuOptions) {
			
			System.out.println();
			System.out.println(venueName);
			
			System.out.println();
			System.out.println();
			
			System.out.println("\tName\t\t" + "Open\t" + " Close\t" + "   Daily Rate\t" + "  Max. Occupancy" );
			for (int i = 1; i < spaces.size() + 1; i++) {
				
				System.out.print("(" + i + ") " );
				System.out.printf("%-20s", spaces.get(i-1).getSpaceName());
				
				if (spaces.get(i-1).getOpenFrom() != null) {
				System.out.printf("%-10s", spaces.get(i-1).getOpenFrom());
				
				} else {
					
					System.out.printf("%-10s", "--");
					
				}
				
				if (spaces.get(i-1).getOpenTo() != null) {
					
					System.out.printf("%-10s", spaces.get(i-1).getOpenTo());
					
				} else {
					
					System.out.printf("%-10s", "--");
				}
	
				System.out.printf("%-15s", "$" + spaces.get(i-1).getDailyRate() + "\t");
				System.out.printf("%-10s", spaces.get(i-1).getMaxOccupancy());
			
				System.out.println();
				
			}
			
			System.out.println();
			System.out.println("What would you like to do next?");
			return getUserMenuSelection(menuOptions);
			
		}
		
		
	
		public Reservation reservationMenu() {
			
			Reservation reservation = new Reservation();
			
			String startDate;
		
			while (reservation.getStartDate() == null) {
				
				try {
				
					System.out.println("When do you need the space?");
					startDate = input.nextLine();
					reservation.setStartDate(startDate);
				
				} catch (Exception e) {
				
//					We didn't want to bury an exception but the CLI is also the user interface
//					System.out.println("Your date is formatted incorrectly, please retry (yyyy/MM/d)");
				
				}			
			}
			
			while (reservation.getStartDate().compareTo(LocalDate.now()) < 0) {
				
				System.out.println("You cannot set a reservation for a date prior to today");
				System.out.println("Please reselect a start date");
				startDate = input.nextLine();
				reservation.setStartDate(startDate);
		
			}
			
			System.out.println("How many days will you need the space?");
			String endDate = input.nextLine();
			endDateInt = Integer.parseInt(endDate);
			reservation.setEndDate(endDateInt);
			
			System.out.println("How many people will be in attendance?");
			String numOfAttendees = input.nextLine();
			int numOfAttendeesInt = Integer.parseInt(numOfAttendees);
			
			while (numOfAttendeesInt <= 0) {
				
				System.out.println("You need at least one attendee, please reenter number of people in attendance");
				numOfAttendees = input.nextLine();
				numOfAttendeesInt = Integer.parseInt(numOfAttendees);
				
			}
			
			reservation.setNumOfAttendees(numOfAttendeesInt);
			
			return reservation;
			
		}
		
		public Reservation displayAvailableSpaces(Reservation reservation, List<Space> spaces) {
			
			List<Integer> spaceIds = new ArrayList<Integer>();
			
			if (spaces.size() == 0) {
				
				System.out.println();
				
				System.out.println("There are no spaces that fit your needs!");
				System.out.println();
				return reservation;
			}
			
			System.out.println();
			System.out.println("The following spaces are available based on your needs: ");
			System.out.println();
			
			System.out.println("Space #\t\t" + "Name\t" + "\tDaily Rate\t" + "Max Occup.\t" + "Accessible?\t" + "Total Cost");
			System.out.println();
			
			for (int i = 0; i < spaces.size(); i++ ) {
				
				System.out.printf("%-10s", spaces.get(i).getSpaceId());
					spaceIds.add(spaces.get(i).getSpaceId());
					
				System.out.printf("%-22s", spaces.get(i).getSpaceName());
				System.out.printf("%-17s", "$" + spaces.get(i).getDailyRate());
				System.out.printf("%-16s", spaces.get(i).getMaxOccupancy());
				
				if (spaces.get(i).isAccessible() == true) {
					System.out.printf("%-15s", "Yes");
				} else {
					System.out.printf("%-15s", "No");
				}
				
				System.out.printf("%-15s", "$" + BigDecimal.valueOf(endDateInt).multiply(spaces.get(i).getDailyRate()));
				
				System.out.println();
			}
			
			System.out.println();
			System.out.println("Which space would you like to reserve (enter 0 to cancel) ?");
			String userInput = input.nextLine();
			int userInputInt = Integer.parseInt(userInput);
			
			if (userInputInt == 0) {
				
				return reservation;
			}
			
			while (!spaceIds.contains(userInputInt)) {
			
				System.out.println("Invalid spaceId, please reselect");
				userInput = input.nextLine();
				userInputInt = Integer.parseInt(userInput);
			}
			
			reservation.setSpaceId(userInputInt);
			
			
			for (int i = 0; i < spaces.size(); i++ ) {
				if (userInputInt == spaces.get(i).getSpaceId()) {
					
					reservation.setSpaceName(spaces.get(i).getSpaceName());
					reservation.setDailyRate(BigDecimal.valueOf(endDateInt).multiply(spaces.get(i).getDailyRate()));
					
				}
					
			}
			
			
			System.out.println("Who is this reservation for?");
			String reservedFor = input.nextLine();
			
			reservation.setReservedFor(reservedFor);
			
			return reservation;
		}
		
		
		public void getReservationDetails(Reservation reservation, Venue venue) {
			
			System.out.println();
			
			double randomNum = (Math.random()*(1000000)+1) + 9000000;
			System.out.println("Confirmation: " + (int) randomNum);
			
			System.out.println("Venue: " + venue.getVenueName() );
			System.out.println("Space: " + reservation.getSpaceName());
			System.out.println("Reserved For: " + reservation.getReservedFor());
			System.out.println("Attendees: " + reservation.getNumOfAttendees());
			System.out.println("Arrival Date: " + reservation.getStartDate());
			System.out.println("Depart Date: " + reservation.getEndDate());
			System.out.println("Total Cost: $" + BigDecimal.valueOf(endDateInt).multiply(reservation.getDailyRate()));
			
		}
		
		
		public void displayErrorMessage(String message) {
			
			System.out.println();
			System.out.println(message);
			System.out.println();
		}
		
		
}
			
	
		
			
			
			
			
			
