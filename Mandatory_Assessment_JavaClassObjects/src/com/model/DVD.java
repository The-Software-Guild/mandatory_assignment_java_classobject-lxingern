package com.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DVD implements Serializable {
	
	private static final long serialVersionUID = 1411166884280847044L;
	private static int nextId;
	private int id;
	private String title;
	private LocalDate releaseDate;
	private String mpaaRating;
	private String directorName;
	private String studio;
	private String notes;
	
	public DVD(String title, LocalDate releaseDate, String mpaaRating, String directorName, String studio, String notes) {
		this.id = nextId;
		nextId++;
		this.title = title;
		this.releaseDate = releaseDate;
		this.mpaaRating = mpaaRating;
		this.directorName = directorName;
		this.studio = studio;
		this.notes = notes;
	}

	public static int getNextId() {
		return nextId;
	}

	public static void setNextId(int nextId) {
		DVD.nextId = nextId;
	}

	public int getId() {
		return id;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getMpaaRating() {
		return mpaaRating;
	}

	public void setMpaaRating(String mpaaRating) {
		this.mpaaRating = mpaaRating;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public String getNotes() {
		return notes;
	}

	@Override
	public String toString() {
		return "Title: " + title + "\nRelease date: " + releaseDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nMPAA Rating: " + mpaaRating + "\nDirector's name: " + directorName + "\nStudio: " + studio + "\nNotes: " + notes;
	}
	
}
