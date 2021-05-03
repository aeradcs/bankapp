package com.databases.bankapp.view.investmentAccountView;

import com.databases.bankapp.entity.Client;
import com.databases.bankapp.entity.InvestmentAccount;
import com.databases.bankapp.service.ClientService;
import com.databases.bankapp.service.InvestmentAccountService;
import com.databases.bankapp.view.MainView;
import com.databases.bankapp.view.clientView.ClientForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;


@Route(value="investmentaccounts", layout = MainView.class)
@PageTitle("Bank App | Investment Accounts")
public class InvestmentAccountView extends VerticalLayout {

    private final Grid<InvestmentAccount> grid;
    private final InvestmentAccountService investmentAccountService;
    //private final InvestmentAccountForm investmentAccountForm;

    public InvestmentAccountView(InvestmentAccountService investmentAccountService)
    {
        this.investmentAccountService = investmentAccountService;
        this.grid = new Grid<>(InvestmentAccount.class);
        addClassName("invest-account-view");
        setSizeFull();
        configureGrid();

        //clientForm = new ClientForm();
        /*clientForm.addListener(ClientForm.SaveEvent.class, this::saveClient);
        clientForm.addListener(ClientForm.DeleteEvent.class, this::deleteClient);
        clientForm.addListener(ClientForm.CloseEvent.class, e -> closeEditor());*/

        Div contentWindow = new Div(/*clientForm,*/ grid);
        contentWindow.addClassName("contentWindow");
        contentWindow.setSizeFull();

        add(/*getToolbar(),*/ contentWindow);
        updateList();

        //closeEditor();

    }

    private void configureGrid() {
        grid.addClassName("invest-account-grid");
        grid.setSizeFull();
        grid.setColumns("id", "dateOfOpening", "moneySum");
        grid.addColumn(InvestmentAccount::getClient).setHeader("Client Id");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        /*grid.asSingleSelect().addValueChangeListener(event ->
                editClient(event.getValue()));*/
    }

    private void updateList() {
        grid.setItems(investmentAccountService.findAll());

    }

}
