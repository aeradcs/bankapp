package com.databases.bankapp.view.queries;

import com.databases.bankapp.service.ClientService;
import com.databases.bankapp.service.DepositService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;

@Route(value = "query_5", layout = MainView.class)
@PageTitle("Query №5")
public class FindClientsWhoHasntDepositAndHasntCard extends VerticalLayout {
    private final ClientService service;

    private final Grid<Object[]> grid;


    public FindClientsWhoHasntDepositAndHasntCard(ClientService service) {
        this.service = service;
        this.grid = new Grid<>();

        configureGrid();
        add(getToolBar(), grid);
        setSizeFull();

        grid.setItems(Collections.emptyList());
    }

    private HorizontalLayout getToolBar() {

        Button find = new Button("Find", click -> getClientsWhoHasntDepositAndHasntCard());

        HorizontalLayout toolbar = new HorizontalLayout(find);

        return toolbar;
    }



    private void getClientsWhoHasntDepositAndHasntCard() {
        List<Object[]> o = service.getClientsWhoHasntDepositAndHasntCard();
        if (o.isEmpty()) {
            grid.setItems(Collections.emptyList());
        } else grid.setItems(o);
    }


    private void configureGrid() {
        grid.addColumn(objects -> objects[0]).setHeader("Invest Account Id");
        grid.addColumn(objects -> objects[1]).setHeader("Currency Id");
        grid.addColumn(objects -> objects[2]).setHeader("Currency Name");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();
    }
}
