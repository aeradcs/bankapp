package com.databases.bankapp.view.queries;


import com.databases.bankapp.entity.Share;
import com.databases.bankapp.service.ShareService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;

@Route(value = "query_1", layout = MainView.class)
@PageTitle("Query №1")
public class GetShareByStockQuery extends VerticalLayout {
    private final ShareService shareService;

    private final Grid<Share> grid;

    private final TextField stockName = new TextField();

    public GetShareByStockQuery(ShareService shareService) {
        this.shareService = shareService;
        addClassName("query_1_view");
        this.grid = new Grid<>(Share.class);

        configureGrid();
        add(getToolBar(), grid);
        setSizeFull();

        grid.setItems(Collections.emptyList());
    }

    private HorizontalLayout getToolBar() {
        stockName.setPlaceholder("Enter name of stock...");
        stockName.setClearButtonVisible(true);

        System.out.println("======================================================" + stockName.getValue());

        Button queryButton = new Button("Query", click -> listEvents(stockName.getValue()));
        HorizontalLayout toolbar = new HorizontalLayout(stockName, queryButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("query_2_grid");
        grid.setColumns("id", "country", "nameOfCompany", "capitalization", "stock");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();
    }



    private void listEvents(String param) {
        List<Share> shares = shareService.getShareByStock(param);
        if (shares.isEmpty()) {
            grid.setItems(Collections.emptyList());
        } else grid.setItems(shares);
    }
}