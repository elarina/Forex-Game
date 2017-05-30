package com.forexgame.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
			Files.delete(path);
			OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE);
			ObjectOutputStream out = null;
			try{
				out = new ObjectOutputStream(outputStream);
				out.writeObject(news);
			}finally {
				closeOutputStream(out);
			}
		}catch (IOException e){
			e.printStackTrace(System.out);
		}
		
		System.out.println("FileDAO: saved");
	}	
	
	public void upload() {
		String filename = "News.out";
		Path path = Paths.get(filename); 
		List<News> oldNews = new ArrayList<News>();
		InputStream in = null;
		try {
			in = Files.newInputStream(path); 
			ObjectInputStream objectInputStream = new ObjectInputStream(in);
			oldNews = (ArrayList<News>) objectInputStream.readObject();
		} catch (NoSuchFileException e) {
			createFile(path, filename);
			handleException(e);
		} catch (IOException e){
			handleException(e);
		} catch(ClassNotFoundException e ) {
			handleException(e);
		} finally {
			closeInputStream(in);
		}
		
		List<News> newNews = new ArrayList<News>();
		newNews = NewsShaper.INSTANCE.shapeNews();
		newsStorage.setEntities(combine(oldNews, newNews));
		save();
	}

	private void closeInputStream(InputStream in) {
		if(in == null) return;
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<News> combine(List<News> oldNews, List<News> newNews) {
		List<News> newsList = new ArrayList<News>();
		newsList.addAll(oldNews); 		
		
		List<News> temp = new ArrayList<News>();
		temp.addAll(newNews);
		outerloop:
		for (News news : newNews) {
			for (News old : oldNews) {
				if(news.getHeading().equals(old.getHeading())){
					temp.remove(news);
					continue outerloop;
				}
			}
		}
		
		newsList.addAll(temp);
		return newsList;
	}

	private void createFile(Path path, String string) {
		OutputStream out = null;
		try {
			out = Files.newOutputStream(path, StandardOpenOption.CREATE);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			closeOutputStream(out);
		}
		
	}

	private void closeOutputStream(OutputStream out) {
		if(out != null) {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void handleException(Exception e){
		newsStorage.setEntities(new ArrayList<News>());
		e.printStackTrace(System.out);
	}
}
