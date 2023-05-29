package com.controller;

import java.util.List;

import com.dao.DVDDao;
import com.model.DVD;
import com.view.DVDView;

public class DVDController {

	private DVDView view;
	private DVDDao dao;

	public DVDController(DVDView view, DVDDao dao) {
		this.view = view;
		this.dao = dao;
	}

	public void run() {
		int selection;
		do {
			view.displayMenu();
			selection = view.getMenuSelection();
			
			switch (selection) {
				case 1:
					listAllDvds();
					break;
				case 2:
					displayDvd();
					break;
				case 3:
					addDvd();
					break;
				case 4:
					editDVD();
					break;
				case 5:
					removeDVD();
					break;
			}
		} while (selection != 6);
	}
	
	private void listAllDvds() {
		List<DVD> dvds = dao.getAllDvds();
		view.listAllDvds(dvds);
	}

	private void displayDvd() {
		int dvdId = view.getDvdId();
		if (dvdId < 0) return;
		
		DVD dvd = dao.getDvd(dvdId);			
		if (dvd == null) return;
		
		view.displayDVD(dvd);
	}

	private void addDvd() {
		DVD dvd = view.getNewDvdDetails();	
		if (dvd == null) return;

		dao.addDvd(dvd);

		System.out.println("DVD successfully added to library.\n");
	}
	
	private void editDVD() {
		int dvdId = view.getDvdId();
		if (dvdId < 0) return;
		
		DVD dvd = dao.getDvd(dvdId);			
		if (dvd == null) return;
		
		boolean validDvd = view.getEditedDvdDetails(dvd);
		if (validDvd == false) return;
		
		dao.editDvd();
		
		System.out.println("DVD successfully edited.\n");
	}

	private void removeDVD() {
		int dvdId = view.getDvdId();
		if (dvdId < 0) return;
		
		DVD dvd = dao.getDvd(dvdId);			
		if (dvd == null) return;
		
		dao.removeDvd(dvd);
		
		System.out.println("DVD successfully removed.\n");
	}

}
