package com.android.bbcxmlfeed;

/**
 * This class handles the mutator and accessors methods of a news item. 
 * For each news item the title and the description is assigned as a string value.
 * 
 * @author Andrei
 *
 */
public class XMLItem{
	// class fields
	private String title = null;
	private String description = null;
	
	/**
	 * Empty constructor. 
	 */
	public XMLItem(){
		
	}
	
	/**
	 * Assign the news title to the title field. 
	 * @param value Set item title.
	 */
	public void setTitle(String value){
		title = value;
	}
	
	/**
	 * Assign the news description to the description field. 
	 * @param value
	 */
	public void setDescription(String value){
		description = value;
	}
	
	/**
	 * This method returns the news item title. 
	 * @return News item title.
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * This method returns the news item description.
	 * @return News item description.
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Create a toString which appends the news item title with the news item description.
	 */
	@Override
	public String toString(){
		return ("Title: "+title+"."+"\n"+"Description: "+description+"\n");
	}
}

	
