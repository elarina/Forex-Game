package com.forexgame.model;

import java.io.Serializable;

public class News implements Serializable{
	private static final long serialVersionUID = 1L;
	private String heading;
	private String content;
	private String date;
	private String author;
	private String link;
		
	public News(){
		
	}
	
	public News(String heading, String content, String date, String source, String link) {
		super();
		this.heading = heading;
		this.content = content;
		this.date = date;
		this.author = source;
		this.link = link;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return this.heading;
	}
}
