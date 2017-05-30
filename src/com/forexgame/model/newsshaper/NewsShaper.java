package com.forexgame.model.newsshaper;

import java.util.ArrayList;
import java.util.List;

import com.forexgame.model.News;
import com.forexgame.rss.Feed;
import com.forexgame.rss.RSSFeedParser;
import com.forexgame.storage.GlobalStorage;

public class NewsShaper {
	
	public static NewsShaper INSTANCE = new NewsShaper();
	
	private NewsShaper(){}
	
	public List<News> shapeNews(){
		String emptyString = "";
		RSSFeedParser parser = new RSSFeedParser("https://habrahabr.ru/rss/interesting/");
//		RSSFeedParser parser = new RSSFeedParser("https://ru.investing.com/rss/market_overview_Fundamental.rss");
		List<News> newsList = new ArrayList<News>();
		List<Feed> feeds = parser.readFeed();
		for (Feed feed : feeds) {
			News news = new News();
			if(feed.getTitle() == null) continue;
			
			//set title
			news.setHeading(feed.getTitle());
			
			//set author
			String author = feed.getAuthor();
			String copyright = feed.getCopyright();
			if(author != null) {
				news.setAuthor(author);
			} else if(copyright != null ){
					news.setAuthor(copyright);
			} else {
				news.setAuthor(emptyString);
			}
			
			//set description
			String description = feed.getDescription();
			if(description != null){
				news.setContent(description);
			} else {
				String content = getContentFromHTML(feed.getLink());
				news.setContent(content);
			}
			
			//set date
			if(feed.getPubdate() != null){
				news.setDate(feed.getPubdate());
			} else {
				news.setDate(emptyString);
			}
			
			//set link
			if(feed.getLink() != null) {
				news.setLink(feed.getLink());
			} else {
				news.setLink(emptyString);
			}
			
			GlobalStorage.INSTANCE.getNewsStorage().setEntities(newsList);
			newsList.add(news);
		}
		
		return newsList;
	}

	private String getContentFromHTML(String link) {
		String content = "";
		
		if(content.isEmpty()) {
			return link;
		}
		
		return content;
	}
	
}
