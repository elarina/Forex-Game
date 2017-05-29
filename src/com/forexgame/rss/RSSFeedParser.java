package com.forexgame.rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.forexgame.model.News;

public class RSSFeedParser {	
	static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";
    static final String CREATOR = "creator";

    final URL url;
    
    public RSSFeedParser(String feedUrl){
    	try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
    }
    
    public List<Feed> readFeed(){
    	List<Feed> feeds = new ArrayList<Feed>();
    	XMLInputFactory inputFactory = 	XMLInputFactory.newInstance();
    	InputStream in = read();
	    try{
    		XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
	    	feeds = handleEvents(eventReader, feeds);
	    } catch (XMLStreamException e) {
	    	e.printStackTrace();
	    }
    	return feeds;
    }

	private List<Feed> handleEvents(XMLEventReader eventReader, List<Feed> feeds) throws XMLStreamException {
		if(!eventReader.hasNext())
			return feeds;
		
		XMLEvent event = eventReader.nextEvent();
		
		if(event.isStartElement()){
			handleEventElement(event, eventReader, feeds);
		} else {
			handleEvents(eventReader, feeds);
		}
				
		return feeds;
	}

	private void handleEventElement(XMLEvent event, XMLEventReader eventReader, List<Feed> feeds) {
		// Set header values intial to the empty string
        StartElement startElement = event.asStartElement();
		QName name = startElement.getName();
		String localPart = name.getLocalPart();
		Feed feed = new Feed();
		
		try{
			if(localPart.equals(ITEM)){
				event = eventReader.nextEvent();
				localPart = getLocalPart(event);
				while(localPart != ITEM && eventReader.hasNext()) {
					if(event.isStartElement()){
				        handleProperties(event, eventReader, feed);
					} 
					
			        event = eventReader.nextEvent();
			        localPart = getLocalPart(event);
				}
				feeds.add(feed);
			}
			handleEvents(eventReader, feeds);
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getLocalPart(XMLEvent event) {
		EndElement endElement;
		Characters characters;
		StartElement startElement;
		QName name;
		String localPart;
		if(event.isStartElement()){
			startElement = event.asStartElement();
		    name = startElement.getName();
			localPart = name.getLocalPart();
		} else if(event.isEndElement()){
			endElement = event.asEndElement();
		    name = endElement.getName();
			localPart = name.getLocalPart();
		} else {
			characters = event.asCharacters();
			localPart = characters.toString();
		}
		return localPart;
	}

	private void handleProperties(XMLEvent event, XMLEventReader eventReader, Feed feed) throws XMLStreamException  {
	    StartElement startElement = event.asStartElement();
	    QName name = startElement.getName();
		String localPart = name.getLocalPart();
	
		switch(localPart){
			case TITLE:
				feed.setTitle(getCharacterData(event, eventReader));
				break;
			case AUTHOR:
				feed.setAuthor(getCharacterData(event, eventReader));
				break;
			case COPYRIGHT:
				feed.setAuthor(getCharacterData(event, eventReader));
				break;
			case CREATOR:
				feed.setAuthor(getCharacterData(event, eventReader));
				break;
			case DESCRIPTION:
				feed.setDescription(getCharacterData(event, eventReader));
			break;
			case PUB_DATE:
				feed.setPubdate(getCharacterData(event, eventReader));
			break;
			case LINK:
				feed.setLink(getCharacterData(event, eventReader));
			break;
			case GUID:
				feed.setGuid(getCharacterData(event, eventReader));
			break;
			case LANGUAGE:
				feed.setLanguage(getCharacterData(event, eventReader));
			break;
		}
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if(event instanceof Characters){
			result = event.asCharacters().getData();
		}
		return result;
	}
	
	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
   
}
