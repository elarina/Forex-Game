package com.forexgame.dummy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.forexgame.model.News;

public class DummyNews {
	public static final DummyNews INSTANCE= new DummyNews();
	
	public List<News> news = new ArrayList<News>();
	
	private DummyNews(){
		this.initialize();
	}
	
	public void initialize(){
		for (int i = 0; i < 20; i++) {
			news.add(new News("Test News " + i, "Test News Content " + i, new Date(), "Investing.com"));
		}
	}
	
	public List<News> getNews() {
		return news;
	}
	
}
