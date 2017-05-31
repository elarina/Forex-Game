package com.forexgame.controller;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import com.forexgame.model.News;

public class NewsViewerComparator extends ViewerComparator {
	private final int DESCENDING = 1;
	private int direction = DESCENDING;
	private int propertyIndex = -1 ;
	
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		return compare(propertyIndex, e1, e2);
	}
	
	public int getDirection(){
		return direction == 1 ? SWT.DOWN : SWT.UP;
	}
		
	public void setColumn(int column){
		if(column == this.propertyIndex){
			direction = 1 - direction;
		} else {
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}
	
	public int compare(int propertyIndex, Object e1, Object e2) {
		News news1 = (News)e1;
		News news2 = (News)e2;
		
		int result = 0;
		switch(propertyIndex) {
			case 1: 
				result = news1.getHeading().compareTo(news2.getHeading());
				break;
			case 2: 
				result = news1.getAuthor().compareTo(news2.getAuthor());
				break;
			case 3:
				result = news1.getDate().compareTo(news2.getDate());
				break;
		}
		
		if(direction == DESCENDING){
			result = -result;
		}
		
		return result;
	}


}
