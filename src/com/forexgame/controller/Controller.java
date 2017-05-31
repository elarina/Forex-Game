package com.forexgame.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.TableItem;

import com.forexgame.model.News;
import com.forexgame.storage.FileDAOImpl;
import com.forexgame.storage.GlobalStorage;
import com.forexgame.storage.Storage;

public class Controller {
	public static Controller INSTANCE = new Controller();
	private Storage<News> newsStorage = GlobalStorage.INSTANCE.getNewsStorage();
	
	
	private Controller(){
		this.initialize();
	}

	private void initialize(){
		FileDAOImpl.INSTANCE.upload();
	}
	
	public List<News> getNews() {
		List<News> newsList = newsStorage.getEntities();
		return newsList;
	}
	
	public List<String> getNewsHeadings(){
		List<String> headings = new ArrayList<String>();
		List<News> news = getNews();
		for (News n : news) {
			headings.add(n.getHeading());
		}
		return headings;
	}
	
	public String getNewsHeading(Object news){
		return ((News)news).getHeading();
	}
	
	public String getNewsSource(Object news){
		return ((News)news).getAuthor();
	}
	
	public String getNewsDate(Object news){
		return ((News)news).getDate();
	}
	
	public String getNewsContent(Object news){
		return ((News)news).getContent();
	}
	
	public boolean isNewsRead(Object news){
		return ((News)news).isRead();
	}
	
	public List<String> getSources(){
		List<String> sources = new ArrayList<String>();
		List<News> news = getNews();
		for (News n : news) {
			sources.add(n.getAuthor());
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
				newsStorage.getEntities().remove(indexes[i++] - removedCount++);
			}
			if (removedCount > 0) FileDAOImpl.INSTANCE.save();
		} catch(IndexOutOfBoundsException e) {
			this.delete(indexes, i, --removedCount);
		} 
	}
		
	public void save (Object object, int oldId) {
		if(oldId != -1) {
			newsStorage.getEntities().set(oldId, (News)object);
		} else {
			newsStorage.getEntities().add((News)object);
		}
		
		FileDAOImpl.INSTANCE.save();
	}
	
	public void saveNewsList(TableItem[] items){
		List<News> newsList = new ArrayList<News>();
		for (TableItem item : items) {
			News news = (News)item.getData();
			newsList.add(news);
		}
		newsStorage.setEntities(newsList);
		FileDAOImpl.INSTANCE.save();
	}

	public void setNewsRead(Object news, boolean b) {
		((News)news).setRead(b);
	}
}
