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
		news.add(new News("������ ���: �������������� ���� Bitcoin", 
				"������� �������, ��� ������� ������ ����� � ��������. ��� ���� ����������, ��� ����� ����� �� Forex �������� ��� �������� ������ � �������������. ���������� ��������� ����� 2015, ����� ���� ���������� � ��������� ������ �� ����������� ������ \n"
						+ "�� ������� ���������� FOMC � 2016-� ������� ������ ��� �����, ������ ���������� ��������� ������ ����. ��� ���������� �������-��������� �������� ���� � �������, � ������������ ������ �� �������� ����� ������������ � �������� ����������. ���� �� �� ������� �����. � ����� �� �������� ��� ������. \n"
						+ "�������� �� ������ ����������� ������� ��������� ��� �� 3-4% ��������� ������ ������� USD � ������� 14-������ �����. ������ ���� ��� �����, ��� ��������� ������ ��������� ���� ������������ ��������. \n"
						+ "� ��� ����� ��������� ������? ��������, � ��������� ������� Citigroup (NYSE:C) �������� ������� ����� �������� ������������� ���������, ������� ���� ����������, ������������ ������������ �����, ���� �� �� ��������� ���������������� ���� ��������� � ���������������, \n"
						+ "���������� � ������������������ �����������. � ����� ����� ����� ��������������, �������������� ����� EUR/USD � ������� ����������� ����������. � ���������, ������ ������������� ��������� ��� ���� � ����������� �������� �� ��������� 15 �������, � ��� ������ �� �������� ��������� ���� ��������� ������ 6-������ �����.", 
				new Date(), "Investing.com"));
		news.add(new News("��������� ����: ����������� ���", "Test News Content ", new Date(), "Investing.com"));
		news.add(new News("������ ��� ���� ��-�� ��������� ���", "Test News Content ", new Date(), "Investing.com"));
		news.add(new News("�������� ����� ������ ���� ������������� �����  ", "Test News Content ", new Date(), "Investing.com"));
		news.add(new News("������ ���� ����������� �����", "Test News Content ", new Date(), "Investing.com"));
		news.add(new News("��������� ������� ������� � ���������� ������ ���������� ���", "Test News Content ", new Date(), "Investing.com"));
		news.add(new News("������� ����� ������ �� 26.05.2017  ", "Test News Content ", new Date(), "Investing.com"));
		news.add(new News("������� ����� ������ �� 25.05.2017  ", "Test News Content ", new Date(), "Investing.com"));
	}
	
	public List<News> getNews() {
		return news;
	}
	
}
