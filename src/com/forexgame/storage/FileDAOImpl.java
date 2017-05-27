package com.forexgame.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.forexgame.model.News;

public class FileDAOImpl implements FileDao {

	public static final FileDAOImpl INSTANCE = new FileDAOImpl();
	private FileDAOImpl(){}
	private Storage<News> newsStorage = GlobalStorage.INSTANCE.getNewsStorage();
	
	public void save() {		
		List <News> news = newsStorage.getEntities();
		try{
			Path path = Paths.get("News.out");
			if(!Files.exists(path)){
				File f = new File("News.out");
				f.setReadable(true);
			}
			ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("News.out"));
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
		File f = new File("News.out");
		f.setReadable(true);
		f.setWritable(true);
		System.out.println(f.getAbsolutePath());
		System.out.println(f.canRead());
		System.out.println(f.canWrite());
		System.out.println(f.exists());
		System.out.println(System.getProperty("user.dir"));
		try{	
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream(f));
			try{
				newsStorage.setEntities((ArrayList<News>) in.readObject());
			}
			catch(ClassNotFoundException e){
				newsStorage.setEntities(new ArrayList<News> ());
				e.printStackTrace(System.out);
			}
			finally {
				in.close();
			}	
		}catch(IOException e){
			e.printStackTrace(System.out);
		}		
	}

}
