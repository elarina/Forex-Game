package com.forexgame.ui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewLayout;

import com.forexgame.ui.views.ChartView;
import com.forexgame.ui.views.InstrumentsView;
import com.forexgame.ui.views.NewsCategoriesView;
import com.forexgame.ui.views.NewsContentView;
import com.forexgame.ui.views.NewsView;
import com.forexgame.ui.views.TransactionsView;

public class Perspective implements IPerspectiveFactory {
	
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);

		layout.addView(InstrumentsView.ID, IPageLayout.TOP, 0.3f, IPageLayout.ID_EDITOR_AREA);
		IFolderLayout chartFolder = layout.createFolder("chart", IPageLayout.RIGHT, 0.2f, "instruments");
		chartFolder.addView(ChartView.ID);
		chartFolder.addPlaceholder(NewsContentView.ID);

		layout.addView(NewsCategoriesView.ID, IPageLayout.BOTTOM, 0.65f, InstrumentsView.ID);
		layout.addView(NewsView.ID, IPageLayout.BOTTOM, 0.65f, ChartView.ID);	
		layout.addView(TransactionsView.ID, IPageLayout.RIGHT, 0.7f, ChartView.ID);
		
		makeViewUncloseable(layout, InstrumentsView.ID);
		makeViewUncloseable(layout, TransactionsView.ID);
		makeViewUncloseable(layout, NewsCategoriesView.ID);
		makeViewUncloseable(layout, NewsView.ID);
		
//		String editorArea = layout.getEditorArea();
//		IFolderLayout instrumentsFolder = layout.createFolder("instruments", IPageLayout.LEFT, 0.5f, editorArea);
//		instrumentsFolder.addView(InstrumentsView.ID);
//		
//		
//		IFolderLayout transactionsFolder = layout.createFolder("transactions", IPageLayout.RIGHT, 0.7f, editorArea);
//		transactionsFolder.addView(TransactionsView.ID);
//		
//		IFolderLayout newsCategoriesFolder = layout.createFolder("newsCategories", IPageLayout.BOTTOM, 0.6f, "instruments");
//		newsCategoriesFolder.addView(NewsCategoriesView.ID);
//		
//		IFolderLayout newsFolder = layout.createFolder("news", IPageLayout.BOTTOM, 0.8f, "chart");
//		newsFolder.addView(NewsView.ID);
	}
	
	private void makeViewUncloseable(IPageLayout layout, String viewId){
		IViewLayout viewLayout = layout.getViewLayout(viewId);
		viewLayout.setCloseable(false);
	}

}
