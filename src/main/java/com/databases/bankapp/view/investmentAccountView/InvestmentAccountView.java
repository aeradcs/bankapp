package com.databases.bankapp.view.investmentAccountView;

import com.databases.bankapp.entity.Client;
import com.databases.bankapp.entity.InvestmentAccount;
import com.databases.bankapp.service.ClientService;
import com.databases.bankapp.service.InvestmentAccountService;
import com.databases.bankapp.service.ShareService;
import com.databases.bankapp.view.MainView;
import com.databases.bankapp.view.clientView.ClientForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;


@Route(value="investmentaccounts", layout = MainView.class)
@PageTitle("Bank App | Investment Accounts")
public class InvestmentAccountView extends VerticalLayout {

    private final Grid<InvestmentAccount> grid;
    private final InvestmentAccountService investmentAccountService;
    private final InvestmentAccountForm investmentAccountForm;

    private final TextField filterText = new TextField();

    public InvestmentAccountView(InvestmentAccountService investmentAccountService,
                                 ClientService clientService,
                                 ShareService shareService)
    {
        this.investmentAccountService = investmentAccountService;
        this.grid = new Grid<>(InvestmentAccount.class);
        addClassName("invest-account-view");
        setSizeFull();
        configureGrid();

        investmentAccountForm = new InvestmentAccountForm(clientService.findAll(), shareService.findAll(null));
        investmentAccountForm.addListener(InvestmentAccountForm.SaveEvent.class, this::saveInvestAccount);
        investmentAccountForm.addListener(InvestmentAccountForm.DeleteEvent.class, this::deleteInvestAccount);
        investmentAccountForm.addListener(InvestmentAccountForm.CloseEvent.class, e -> closeEditor());

        Div contentWindow = new Div(investmentAccountForm, grid);
        contentWindow.addClassName("contentWindow");
        contentWindow.setSizeFull();

        add(getToolbar(), contentWindow);

        updateList();

        closeEditor();

    }

    private void deleteInvestAccount(InvestmentAccountForm.DeleteEvent event) {
        investmentAccountService.delete(event.getInvestmentAccount());
        updateList();
        closeEditor();
    }

    private void saveInvestAccount(InvestmentAccountForm.SaveEvent event) {
        investmentAccountService.save(event.getInvestmentAccount());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by ...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addInvestAccountButton = new Button("Add Investment Account");
        addInvestAccountButton.addClickListener(click -> addInvestAccount());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addInvestAccountButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    void addInvestAccount() {
        grid.asSingleSelect().clear();
        editInvestAccount(new InvestmentAccount());
    }

    private void configureGrid() {
        grid.addClassName("invest-account-grid");
        grid.setSizeFull();

        grid.setColumns("id", "dateOfOpening", "moneySum", "client");

        grid.addColumn(InvestmentAccount::getSharesStr).setHeader("Shares");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editInvestAccount(event.getValue()));
    }

    public void editInvestAccount(InvestmentAccount investmentAccount) {
        if (investmentAccount == null) {
            closeEditor();
        } else {
            investmentAccountForm.setInvestmentAccount(investmentAccount);
            investmentAccountForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        investmentAccountForm.setInvestmentAccount(null);
        investmentAccountForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(investmentAccountService.findAll(filterText.getValue()));

    }

}
