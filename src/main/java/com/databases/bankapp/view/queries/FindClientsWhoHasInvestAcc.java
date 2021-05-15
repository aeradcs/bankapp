package com.databases.bankapp.view.queries;

import com.databases.bankapp.entity.Card;
import com.databases.bankapp.entity.Client;
import com.databases.bankapp.entity.InvestmentAccount;
import com.databases.bankapp.service.CardService;
import com.databases.bankapp.service.ClientService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;

@Route(value = "query_3", layout = MainView.class)
@PageTitle("Query №3")
public class FindClientsWhoHasInvestAcc extends VerticalLayout {
    private final ClientService clientService;

    private final Grid<Client> grid;

//    /*private final TextField stockName = new TextField();
//    private final TextField country = new TextField();
//    private final TextField nameOfCompany = new TextField();*/
//    private final IntegerField less = new IntegerField();
//    private final IntegerField more = new IntegerField();

    public FindClientsWhoHasInvestAcc(ClientService clientService) {
        this.clientService = clientService;
        this.grid = new Grid<>(Client.class);

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


        Button find = new Button("Find", click -> getClientsWhoHasInvestAcc());


        /*VerticalLayout v1 = new VerticalLayout(country, qcountry);
        VerticalLayout v2 = new VerticalLayout(nameOfCompany, qcompany);*/
        /*VerticalLayout v3 = new VerticalLayout(less);
        VerticalLayout v4 = new VerticalLayout(more, qbetweeen);*/
        //VerticalLayout v5 = new VerticalLayout(stockName, qstock);
        HorizontalLayout toolbar = new HorizontalLayout(find);

        return toolbar;
    }



    private void getClientsWhoHasInvestAcc() {
        List<Client> shares = clientService.getClientsWhoHasInvestAcc();
        if (shares.isEmpty()) {
            grid.setItems(Collections.emptyList());
        } else grid.setItems(shares);
    }
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
        grid.setColumns("id", "fullName");
        //grid.addColumn(InvestmentAccount::getMoneySum).setHeader("Sum");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();
    }
}
