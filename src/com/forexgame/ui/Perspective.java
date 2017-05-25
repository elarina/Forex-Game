package com.forexgame.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.forexgame.ui.views.ChartView;
import com.forexgame.ui.views.InstrumentsView;
import com.forexgame.ui.views.NewsCategoriesView;
import com.forexgame.ui.views.NewsView;
import com.forexgame.ui.views.TransactionsView;

public class Perspective implements IPerspectiveFactory {
	
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		layout.addView(InstrumentsView.ID, IPageLayout.TOP, 0.5f, IPageLayout.ID_EDITOR_AREA);
		layout.addView(ChartView.ID, IPageLayout.RIGHT, 0.2f, InstrumentsView.ID);
		layout.addView(TransactionsView.ID, IPageLayout.RIGHT, 0.7f, ChartView.ID);
		layout.addView(NewsCategoriesView.ID, IPageLayout.BOTTOM, 0.6f, InstrumentsView.ID);
		layout.addView(NewsView.ID, IPageLayout.BOTTOM, 0.6f, ChartView.ID);	
	}

}
