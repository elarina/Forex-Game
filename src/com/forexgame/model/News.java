package com.forexgame.model;

import java.util.Date;

public class News {
	public String heading;
	public String content;
	public Date date;
	public String source;
	
	public News(String heading, String content, Date date, String source) {
		super();
		this.heading = heading;
		this.content = content;
		this.date = date;
		this.source = source;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	@Override
	public String toString() {
		return this.heading;
	}
}
