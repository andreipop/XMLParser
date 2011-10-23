package com.android.bbcxmlfeed;

import java.util.List;
import java.util.Vector;

/**
 * This class handles the XML feed by creating a list of all the news items 
 * with their titles and descriptions.
 * 
 * @author Andrei
 *
 */
public class XMLFeed {
	// The fields.
	private String title = null;
	private String description = null;
	
	// The vector array.
	private List<XMLItem> itemList;
	
	/**
	 * A list of items is created and assigned to a constructor with an initial empty initial size.   
	 */
	public XMLFeed(){
		itemList = new Vector<XMLItem>(0);
	}
	
	/**
	 * Adds a news item to the list of news items.
	 * @param item News item.
	 */
	public void addItem(XMLItem item){
		itemList.add(item);
	}
	
	/**
	 * Gets and returns the news item location.
	 * @param location News item array location.
	 * @return The location in the array. 
	 */
	public XMLItem getItem(int location){
		return itemList.get(location);
	}
	
	/**
	 * Returns the list of new items.
	 * @return News items. 
	 */
	public List<XMLItem> getList(){
		return itemList;
	}
	
	/**
	 * Assigns string value to the news item title. 
	 * @param value The title.
	 */
	public void setTitle(String value){
		title = value;
	}
	
	/**
	 * Assigns a string value to the news item description.
	 * @param value The Description. 
	 */
	public void setDescription(String value)
	{
		description = value;
	}
	
	/**
	 * Returns the title string. 
	 * @return The title.
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Returns the description string.
	 * @return The description.
	 */
	public String getDescription()
	{
		return description;
	}
}
