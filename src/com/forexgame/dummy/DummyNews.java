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
		news.add(new News("График дня: ошеломительный рост Bitcoin", 
				"Принято считать, что рынками правят страх и жадность. Мой опыт показывает, что курсы валют на Forex меняются под влиянием надежд и разочарований. Достаточно вспомнить конец 2015, когда вера инвесторов в повышение ставки по федеральным фондам \n"
						+ "на четырех заседаниях FOMC в 2016-м толкала доллар США вверх, однако реальность оказалась совсем иной. ФРС ужесточила денежно-кредитную политику лишь в декабре, а американская валюта из фаворита могла превратиться в главного аутсайдера. Если бы не Дональд Трамп. И здесь не обошлось без Надежд. \n"
						+ "Оптимизм по поводу возможности разгона экономики США до 3-4% обернулся ростом индекса USD к области 14-летних пиков. Сейчас мало кто верит, что президент сможет выполнить свои предвыборные обещания. \n"
						+ "И где нынче находится доллар? Возможно, в недалеком будущем Citigroup (NYSE:C) расширит линейку своих индексов экономических сюрпризов, включив туда индикаторы, улавливающие политические риски, пока же мы вынуждены довольствоваться лишь надеждами и разочарованиями, \n"
						+ "связанными с макроэкономической статистикой. И здесь имеют место закономерности, обосновывающие ралли EUR/USD к области полугодовых максимумов. В частности, индекс экономических сюрпризов США упал к минимальным отметкам за последние 15 месяцев, а его аналог по еврозоне комфортно себя чувствует вблизи 6-летних пиков.", 
				new Date(), "Investing.com"));
		news.add(new News("Заседание ОПЕК: расстановка сил", "Test News Content ", new Date(), "Investing.com"));
		news.add(new News("Доллар США упал из-за протокола ФРС", "Test News Content ", new Date(), "Investing.com"));
		news.add(new News("Нефтяная гроза прошла мимо американского рынка  ", "Test News Content ", new Date(), "Investing.com"));
		news.add(new News("Саммит ОПЕК разочаровал рынки", "Test News Content ", new Date(), "Investing.com"));
		news.add(new News("Инвесторы скупают доллары в преддверии выхода протоколов ФРС", "Test News Content ", new Date(), "Investing.com"));
		news.add(new News("Прогноз рынка форекс на 26.05.2017  ", "Test News Content ", new Date(), "Investing.com"));
		news.add(new News("Прогноз рынка форекс на 25.05.2017  ", "Test News Content ", new Date(), "Investing.com"));
	}
	
	public List<News> getNews() {
		return news;
	}
	
}
