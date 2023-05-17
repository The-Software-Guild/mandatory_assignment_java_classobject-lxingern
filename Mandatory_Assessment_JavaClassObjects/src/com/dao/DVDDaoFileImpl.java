package com.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.UnableToLoadDvdsException;
import com.model.DVD;

public class DVDDaoFileImpl implements DVDDao {

	private List<DVD> allDvds;
	private String fileName;
	
	public DVDDaoFileImpl(String fileName) {
		this.fileName = fileName;
		boolean loadSuccessful = loadDvdsFromFile();
		if (!loadSuccessful) throw new UnableToLoadDvdsException();
	}
	
	public List<DVD> getAllDvds() {
		return allDvds;
	}
	
	public void setAllDvds(List<DVD> allDvds) {
		this.allDvds = allDvds;
	}

	@Override
	public boolean addDvd(DVD dvd) {
		allDvds.add(dvd);
		return saveDvdsToFile();
	}
	
	@Override
	public boolean editDvd() {
		return saveDvdsToFile();
	}

	@Override
	public boolean removeDvd(DVD dvd) {
		allDvds.remove(dvd);
		return saveDvdsToFile();
	}

	@Override
	public DVD getDvd(int dvdId) {
		for (DVD dvd : allDvds) {
			if (dvd.getId() == dvdId)
				return dvd;
		}
		
		System.out.println("Unable to find DVD: DVD with the given ID does not exist.\n");
		return null;
	}

	private boolean loadDvdsFromFile() {
		List<DVD> dvds = new ArrayList<>();
				
		DVD dvd;
		int nextId = 1;
		
		try (FileInputStream fis = new FileInputStream(fileName);
			 ObjectInputStream ois = new ObjectInputStream(fis)) {	
			nextId = ois.readInt();
			while ((dvd = (DVD) ois.readObject()) != null)
				dvds.add(dvd);
		} catch(EOFException e) {
			DVD.setNextId(nextId);
			allDvds = dvds;
			return true;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e + ": " + e.getMessage());
			System.out.println("Error occurred: Changes not persisted.\n");
			return false;
		} 
		
		return true;
	}
	
	private boolean saveDvdsToFile() {		
		try (FileOutputStream fos = new FileOutputStream(fileName);
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {			
			oos.writeInt(DVD.getNextId());
			for (DVD dvd : allDvds)
				oos.writeObject(dvd);
			return true;
		} catch (IOException e) {
			System.out.println(e + ": " + e.getMessage());
			System.out.println("Error occurred: Changes not persisted.\n");
			return false;
		} 
	}
}
