package com.forexgame.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.forexgame.dummy.DummyNews;
import com.forexgame.model.News;
import com.forexgame.storage.FileDAOImpl;
import com.forexgame.storage.GlobalStorage;
import com.forexgame.storage.Storage;

public class Controller {
	public static Controller INSTANCE = new Controller();
	private Storage<News> storage = GlobalStorage.INSTANCE.getNewsStorage();
	
	
	private Controller(){
		this.initialize();
	}

	private void initialize(){
//		FileDAOImpl.INSTANCE.upload();
	}
	
	public List<News> getNews() {
		return DummyNews.INSTANCE.getNews();
	}
	
	public List<String> getNewsHeadings(){
		List<String> headings = new ArrayList<String>();
		List<News> news = DummyNews.INSTANCE.getNews();
		for (News n : news) {
			headings.add(n.getHeading());
		}
		return headings;
	}
	
	public String getNewsHeading(Object news){
		return ((News)news).getHeading();
	}
	
	public String getNewsSource(Object news){
		return ((News)news).getSource();
	}
	
	public Date getNewsDate(Object news){
		return ((News)news).getDate();
	}
	
	public String getNewsContent(Object news){
		return ((News)news).getContent();
	}
	
	public List<String> getSources(){
		List<String> sources = new ArrayList<String>();
		List<News> news = DummyNews.INSTANCE.getNews();
		for (News n : news) {
			sources.add(n.getSource());
		}
		return sources;

	}
	
	public void delete (int[] indexes){
		if (indexes.length == 0) return;
		delete(indexes, 0, 0);
	}
	
	private void delete(int[] indexes, int i, int removedCount) {
		try{
			while (i < indexes.length){
				storage.getEntities().remove(indexes[i++] - removedCount++);
			}
			if (removedCount > 0) FileDAOImpl.INSTANCE.save();
		} catch(IndexOutOfBoundsException e) {
			this.delete(indexes, i, --removedCount);
		} 
	}
		
	public void save (Object object, int oldId) {
		if(oldId != -1) {
			storage.getEntities().set(oldId, (News)object);
		} else {
			storage.getEntities().add((News)object);
		}
		
		FileDAOImpl.INSTANCE.save();
	}
}
