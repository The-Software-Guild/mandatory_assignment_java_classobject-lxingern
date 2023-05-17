package com.view;

import java.util.List;

import com.model.DVD;

public interface DVDView {
	
	public void displayMenu();
	public int getMenuSelection();
	public void listAllDvds(List<DVD> dvds);
	public void displayDVD(DVD dvd);
	public DVD getNewDvdDetails();
	public int getDvdId();
	public boolean getEditedDvdDetails(DVD dvdToEdit);
	
}
