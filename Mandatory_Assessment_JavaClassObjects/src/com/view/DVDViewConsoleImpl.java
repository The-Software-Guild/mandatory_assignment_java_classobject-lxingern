package com.view;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.exceptions.InvalidInputException;
import com.model.DVD;

public class DVDViewConsoleImpl implements DVDView {
	
	private Scanner sc;
	
	public DVDViewConsoleImpl() {
		this.sc = new Scanner(System.in);
	}

	@Override
	public void displayMenu() {
		System.out.println("What would you like to do? Enter the corresponding number.");
		System.out.println("1. List all DVDs");
		System.out.println("2. Display info for a DVD");
		System.out.println("3. Add a DVD");
		System.out.println("4. Edit info for a DVD");
		System.out.println("5. Remove a DVD");
		System.out.println("6. Exit");
		System.out.println();
	}

	@Override
	public int getMenuSelection() {
		int selection = 0;
		boolean validInput = false;
		while (!validInput) {
			System.out.print("Your selection: ");
			try {
				selection = Integer.parseInt(sc.nextLine());
				if (selection < 0 || selection > 6) throw new InvalidInputException();
				validInput = true;
			} catch (NumberFormatException | InvalidInputException e) {
				System.out.println("Invalid input: Please enter an integer from 1 to 6.");
			} 
			System.out.println();			
		}
		return selection;
	}
	
	@Override
	public void listAllDvds(List<DVD> dvds) {
		if (dvds.isEmpty()) System.out.println("No DVDs to display.");
		for (DVD dvd : dvds) {
			System.out.println("ID: " + dvd.getId() + " - " + dvd.getTitle() + " (" + dvd.getReleaseDate().getYear() + ")");
		}
		System.out.println();
	}
	
	@Override
	public void displayDVD(DVD dvd) {
		System.out.println(dvd);
		System.out.println();
	}

	@Override
	public DVD getNewDvdDetails() {
		String title, mpaaRating, directorName, studio, notes;
		LocalDate releaseDate;
		
		try {
			System.out.println("Enter the details of the DVD: ");

			System.out.print("Title (required): ");
			title = sc.nextLine().trim();
			if (title.length() == 0) throw new InvalidInputException();
			
			System.out.print("Release date in the format dd/mm/yyyy (required): ");
			String releaseDateStr = sc.nextLine().trim();
			releaseDate = LocalDate.parse(releaseDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
			
			System.out.print("MPAA rating: ");
			mpaaRating = sc.nextLine().trim();
			
			System.out.print("Director's name: ");
			directorName = sc.nextLine().trim();
			
			System.out.print("Studio: ");
			studio = sc.nextLine().trim();
			
			System.out.print("Notes: ");
			notes = sc.nextLine().trim();
			
			System.out.println();
		} catch (InvalidInputException e) {
			System.out.println(e + " field cannot be blank.\n");
			return null;
		} catch (DateTimeException e) {
			System.out.println("Invalid input: invalid date provided.\n");
			return null;
		}
		
		return new DVD(title, releaseDate, mpaaRating, directorName, studio, notes);
	}
	
	@Override
	public int getDvdId() {
		System.out.print("What is the ID of the DVD? ");
		int id = -1;
		
		try {
			id = Integer.parseInt(sc.nextLine());
			if (id < 0) throw new InvalidInputException();
			System.out.println();
		} catch (NumberFormatException e) {
			System.out.println("Invalid input: ID has to be an integer.\n");
		} catch (InvalidInputException e) {
			System.out.println(e + "ID has to be a positive integer.\n");
		}
		
		return id;
	}

	@Override
	public boolean getEditedDvdDetails(DVD dvdToEdit) {	
		String title, mpaaRating, directorName, studio, notes;
		LocalDate releaseDate;
		
		try {
			System.out.println("Enter the details of the DVD: \n");
			
			System.out.println("Old title: " + dvdToEdit.getTitle());
			System.out.print("New title (required): ");
			title = sc.nextLine().trim();
			if (title.length() == 0) throw new InvalidInputException();
			System.out.println();
			
			System.out.println("Old release date: " + dvdToEdit.getReleaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			System.out.print("New release date in the format dd/mm/yyyy (required): ");
			String releaseDateStr = sc.nextLine().trim();
			releaseDate = LocalDate.parse(releaseDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
			System.out.println();
			
			System.out.println("Old MPAA rating: " + dvdToEdit.getMpaaRating());
			System.out.print("New MPAA rating: ");
			mpaaRating = sc.nextLine().trim();
			System.out.println();
			
			System.out.println("Old director's name: " + dvdToEdit.getDirectorName());
			System.out.print("New director's name: ");
			directorName = sc.nextLine().trim();
			System.out.println();
			
			System.out.println("Old studio: " + dvdToEdit.getStudio());
			System.out.print("New studio: ");
			studio = sc.nextLine().trim();
			System.out.println();
			
			System.out.println("Old notes: " + dvdToEdit.getNotes());
			System.out.print("New notes: ");
			notes = sc.nextLine().trim();
			System.out.println();
		} catch (InvalidInputException e) {
			System.out.println(e + "field cannot be blank.\n");
			return false;
		} catch (DateTimeException e) {
			System.out.println("Invalid input: invalid date provided.\n");
			return false;
		}
		
		dvdToEdit.setTitle(title);
		dvdToEdit.setReleaseDate(releaseDate);
		dvdToEdit.setMpaaRating(mpaaRating);
		dvdToEdit.setDirectorName(directorName);
		dvdToEdit.setStudio(studio);
		dvdToEdit.setNotes(notes);
		return true;
	}

}
