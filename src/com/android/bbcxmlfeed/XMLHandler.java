package com.android.bbcxmlfeed;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class handles the SAX parsing. 
 * 
 * @author Andrei
 *
 */
public class XMLHandler extends DefaultHandler {

	static final int state_unknown = 0;
	static final int state_title = 1;
	static final int state_description = 2;
	
	private int currentState = state_unknown;

	private XMLFeed feed;
	private XMLItem item;

	private boolean itemFound = false;
	
	/**
	 * Class constructor method. 
	 */
	public XMLHandler(){
	
	}
	
	/**
	 * Gets the XML feed items. 
	 * @return Feed items. 
	 */
	public XMLFeed getFeed(){
		return feed;
	}
	
	// SAX parsing elements from here
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		feed = new XMLFeed();
		item = new XMLItem();
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
	}

	@Override
	public void startElement(String uri, String localName, String qName,
	Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub

		if (localName.equalsIgnoreCase("item")){
			itemFound = true;
			item = new XMLItem();
			currentState = state_unknown;
		}
		else if (localName.equalsIgnoreCase("title")){
			currentState = state_title;
		}
		else if (localName.equalsIgnoreCase("description")){
			currentState = state_description;
		}
		else{
			currentState = state_unknown;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
	throws SAXException {
	// TODO Auto-generated method stub
		if (localName.equalsIgnoreCase("item")){
			feed.addItem(item);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
	throws SAXException {
	// TODO Auto-generated method stub
		String strCharacters = new String(ch,start,length);

		if (itemFound==true){
			//If a news item is found, set it to the item parameter
			switch(currentState){
			case state_title:
				item.setTitle(strCharacters);
				break;
			case state_description:
				item.setDescription(strCharacters);
				break;
			default:
				break;
		}
	}
		else{
			// No news items found, it a feeds parameter
			switch(currentState){
			case state_title:
				feed.setTitle(strCharacters);
				break;
			case state_description:
				feed.setDescription(strCharacters);
				break;
			default:
				break;
			}
		}

		currentState = state_unknown;
	}
}
