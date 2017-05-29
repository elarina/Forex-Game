package com.forexgame.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.forexgame.model.News;
import com.forexgame.model.newsshaper.NewsShaper;

public class FileDAOImpl implements FileDao {

	public static final FileDAOImpl INSTANCE = new FileDAOImpl();
	private FileDAOImpl(){}
	private Storage<News> newsStorage = GlobalStorage.INSTANCE.getNewsStorage();
	
	public void save() {		
		List <News> news = newsStorage.getEntities();
		try{
			Path path = Paths.get("News.out");
			OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE);
			ObjectOutputStream out = new ObjectOutputStream(
					outputStream);
			try{
				out.writeObject(news);
			}finally {
				out.close();
			}
		}catch (IOException e){
			e.printStackTrace(System.out);
		}
		
		System.out.println("FileDAO: saved");
	}	
	
	public void upload() {
		String filename = "News.out";
		Path path = Paths.get(filename); 
		List<News> oldNews = null;
		try {
			final InputStream in = Files.newInputStream(path); 
			ObjectInputStream objectInputStream = new ObjectInputStream(in);
			oldNews = (ArrayList<News>) objectInputStream.readObject();
		} catch (NoSuchFileException e) {
			createFile(path, filename);
			handleException(e);
		} catch (IOException e){
			handleException(e);
		} catch(ClassNotFoundException e ) {
			handleException(e);
		}
		
		List<News> newNews = new ArrayList<News>();
		newNews = NewsShaper.INSTANCE.shapeNews();
		newsStorage.setEntities(combine(oldNews, newNews));
	}

	private List<News> combine(List<News> oldNews, List<News> newNews) {
		List<News> newsList = new ArrayList<News>();
		newsList.addAll(oldNews);
		for (News news : newsList) {
			if(!oldNews.contains(news)){
				newsList.add(news);
			}
		}
		
		return newsList;
	}

	private void createFile(Path path, String string) {
		try {
			Files.newOutputStream(path, StandardOpenOption.CREATE);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	private void handleException(Exception e){
		newsStorage.setEntities(new ArrayList<News>());
		e.printStackTrace(System.out);
	}
}
