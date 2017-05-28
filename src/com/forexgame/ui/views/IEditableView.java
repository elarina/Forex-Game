package com.forexgame.ui.views;

import org.eclipse.ui.IViewPart;

public interface IEditableView extends IViewPart {
	void setTextContent(String content);
	void setName(String name);
}
