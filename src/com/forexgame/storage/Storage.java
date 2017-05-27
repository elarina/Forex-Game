package com.forexgame.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Storage <T extends Object > implements Serializable {

	private List<T> entities = new ArrayList<T>();
	
	public List<T> getEntities(){
		return entities;
	}
	
	public void setEntities(List<T> entities){
		this.entities = entities;
	}
}
