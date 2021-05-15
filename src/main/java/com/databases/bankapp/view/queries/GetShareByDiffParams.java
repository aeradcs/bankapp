package com.databases.bankapp.view.queries;


import com.databases.bankapp.entity.Share;
import com.databases.bankapp.service.ShareService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;

@Route(value = "query_1", layout = MainView.class)
@PageTitle("Query №1")
public class GetShareByDiffParams extends VerticalLayout {
    private final ShareService shareService;

    private final Grid<Share> grid;

    private final TextField stockName = new TextField();
    private final TextField country = new TextField();
    private final TextField nameOfCompany = new TextField();
    private final IntegerField less = new IntegerField();
    private final IntegerField more = new IntegerField();

    public GetShareByDiffParams(ShareService shareService) {
        this.shareService = shareService;
        addClassName("query_1_view");
        this.grid = new Grid<>(Share.class);

        configureGrid();
        add(getToolBar(), grid);
        setSizeFull();

        grid.setItems(Collections.emptyList());
    }

    private HorizontalLayout getToolBar() {
        stockName.setPlaceholder("Stock...");
        stockName.setClearButtonVisible(true);

        country.setPlaceholder("Country...");
        country.setClearButtonVisible(true);

        nameOfCompany.setPlaceholder("Company...");
        nameOfCompany.setClearButtonVisible(true);

        less.setPlaceholder("Less than...");
        less.setClearButtonVisible(true);

        more.setPlaceholder("More than...");
        more.setClearButtonVisible(true);

        Button qstock = new Button("Find by stock", click -> findByStock(stockName.getValue()));
        Button qcountry = new Button("Find by country", click -> findByCountry(country.getValue()));
        Button qcompany = new Button("Find by company", click -> findByCompany(nameOfCompany.getValue()));
        Button qbetweeen = new Button("Find between these", click -> findBetween(more.getValue(), less.getValue()));


        VerticalLayout v1 = new VerticalLayout(country, qcountry);
        VerticalLayout v2 = new VerticalLayout(nameOfCompany, qcompany);
        VerticalLayout v3 = new VerticalLayout(less);
        VerticalLayout v4 = new VerticalLayout(more, qbetweeen);
        VerticalLayout v5 = new VerticalLayout(stockName, qstock);
        HorizontalLayout toolbar = new HorizontalLayout(v1, v2, v4, v3, v5);

        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void findBetween(Integer param1, Integer param2) {
        List<Share> shares = null;
        if(param1 != null && param2 != null){
            shares = shareService.getShareByCapitalizationBetween(param1, param2);
            if (shares.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(shares);
        }
        else {
            grid.setItems(Collections.emptyList());
        }

    }


    private void findByStock(String param) {
        List<Share> shares = shareService.getShareByStock(param);
        if (shares.isEmpty()) {
            grid.setItems(Collections.emptyList());
        } else grid.setItems(shares);
    }

    private void findByCountry(String param) {
        List<Share> shares = shareService.getShareByCountry(param);
        if (shares.isEmpty()) {
            grid.setItems(Collections.emptyList());
        } else grid.setItems(shares);
    }

    private void findByCompany(String param) {
        List<Share> shares = shareService.getShareByNameOfCompany(param);
        if (shares.isEmpty()) {
            grid.setItems(Collections.emptyList());
        } else grid.setItems(shares);
    }





    private void configureGrid() {
        grid.addClassName("query_2_grid");
        grid.setColumns("id", "country", "nameOfCompany", "capitalization", "stock");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();
    }

}