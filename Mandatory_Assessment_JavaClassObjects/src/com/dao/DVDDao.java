package com.dao;

import java.util.List;

import com.model.DVD;

public interface DVDDao {

	public List<DVD> getAllDvds();
	public DVD getDvd(int dvdId);
	public boolean addDvd(DVD dvd);
	public boolean editDvd();
	public boolean removeDvd(DVD dvd);
	
}
