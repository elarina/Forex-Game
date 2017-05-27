package com.forexgame.storage;

import com.forexgame.model.News;

public class GlobalStorage {
	public static final GlobalStorage INSTANCE = new GlobalStorage();
	private Storage<News> newsStorage = new Storage<News>();
	
	private GlobalStorage(){}
	
	public Storage<News> getNewsStorage(){
		return newsStorage;
	}
	
}
