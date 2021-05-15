package com.databases.bankapp.view.queries;

import com.databases.bankapp.service.ClientService;
import com.databases.bankapp.service.DepositService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;

@Route(value = "query_4", layout = MainView.class)
@PageTitle("Query №4")
public class FindDepositCountForEveryClientWhoHasIt extends VerticalLayout {
    private final DepositService depositService;

    private final Grid<Object[]> grid;

    //    /*private final TextField stockName = new TextField();
//    private final TextField country = new TextField();
//    private final TextField nameOfCompany = new TextField();*/
    /*private final NumberField less = new NumberField();
    private final NumberField more = new NumberField();
*/
    public FindDepositCountForEveryClientWhoHasIt(DepositService depositService) {
        this.depositService = depositService;
        this.grid = new Grid<>();

        configureGrid();
        add(getToolBar(), grid);
        setSizeFull();

        grid.setItems(Collections.emptyList());
    }

    private HorizontalLayout getToolBar() {
        /*stockName.setPlaceholder("Stock...");
        stockName.setClearButtonVisible(true);

        country.setPlaceholder("Country...");
        country.setClearButtonVisible(true);

        nameOfCompany.setPlaceholder("Company...");
        nameOfCompany.setClearButtonVisible(true);*/

        /*less.setPlaceholder("Less than...");
        less.setClearButtonVisible(true);

        more.setPlaceholder("More than...");
        more.setClearButtonVisible(true);*/

        /*Button qstock = new Button("Find by stock", click -> findByStock(stockName.getValue()));
        Button qcountry = new Button("Find by country", click -> findByCountry(country.getValue()));
        Button qcompany = new Button("Find by company", click -> findByCompany(nameOfCompany.getValue()));*/
        //Button qbetweeen = new Button("Find between these", click -> findBetween(more.getValue(), less.getValue()));


        //Button find = new Button("Find", click -> getClientsWhoHasInvestAcc());
        Button find = new Button("Find", click -> getDepositCountForEveryClientWhoHasIt());


        /*VerticalLayout v1 = new VerticalLayout(country, qcountry);
        VerticalLayout v2 = new VerticalLayout(nameOfCompany, qcompany);*/
        /*VerticalLayout v3 = new VerticalLayout(less);
        VerticalLayout v4 = new VerticalLayout(more, qbetweeen);*/
        //VerticalLayout v5 = new VerticalLayout(stockName, qstock);
        HorizontalLayout toolbar = new HorizontalLayout(find);

        return toolbar;
    }



    private void getDepositCountForEveryClientWhoHasIt() {
        List<Object[]> o = depositService.getDepositCountForEveryClientWhoHasIt();
        if (o.isEmpty()) {
            grid.setItems(Collections.emptyList());
        } else grid.setItems(o);
    }

   /* private void getClientsWhoHasInvestAccAndSumBetween(Double param1, Double param2) {
        List<Object[]> o = clientService.getClientsWhoHasInvestAccAndSumBetween(param1, param2);
        if(param1 != null && param2 != null){
            o = clientService.getClientsWhoHasInvestAccAndSumBetween(param1, param2);
            if (o.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(o);
        }
        else {
            grid.setItems(Collections.emptyList());
        }

    }
*/
/*
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


*/


    private void configureGrid() {
        grid.addColumn(objects -> objects[0]).setHeader("Client Id");
        grid.addColumn(objects -> objects[1]).setHeader("Client Full Name");
        grid.addColumn(objects -> objects[2]).setHeader("Count Deposits");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();
    }
}
