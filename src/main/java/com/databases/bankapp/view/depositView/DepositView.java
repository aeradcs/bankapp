package com.databases.bankapp.view.depositView;

import com.databases.bankapp.entity.Deposit;
import com.databases.bankapp.service.ClientService;
import com.databases.bankapp.service.DepositService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="deposits", layout = MainView.class)
@PageTitle("Bank App | Deposits")
public class DepositView extends VerticalLayout {
    private final Grid<Deposit> grid;
    private final DepositService depositService;
    private final DepositForm depositForm;

    //private final TextField filterText = new TextField();

    public DepositView(DepositService depositService,
                       ClientService clientService)
    {
        this.depositService = depositService;
        this.grid = new Grid<>(Deposit.class);
        addClassName("deposit-view");
        setSizeFull();
        configureGrid();

        depositForm = new DepositForm(clientService.findAll());
        depositForm.addListener(DepositForm.SaveEvent.class, this::saveDeposit);
        depositForm.addListener(DepositForm.DeleteEvent.class, this::deleteDeposit);
        depositForm.addListener(DepositForm.CloseEvent.class, e -> closeEditor());

        Div contentWindow = new Div(depositForm, grid);
        contentWindow.addClassName("contentWindow");
        contentWindow.setSizeFull();

        add(getToolbar(), contentWindow);

        updateList();

        closeEditor();

    }

    private void deleteDeposit(DepositForm.DeleteEvent event) {
        depositService.delete(event.getDeposit());
        updateList();
        closeEditor();
    }

    private void saveDeposit(DepositForm.SaveEvent event) {
        depositService.save(event.getDeposit());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        /*filterText.setPlaceholder("Filter by ...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());*/

        Button addInvestAccountButton = new Button("Add Deposit");
        addInvestAccountButton.addClickListener(click -> addDeposit());

        HorizontalLayout toolbar = new HorizontalLayout(/*filterText, */addInvestAccountButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    void addDeposit() {
        grid.asSingleSelect().clear();
        editDeposit(new Deposit());
    }

    private void configureGrid() {
        grid.addClassName("deposit-grid");
        grid.setSizeFull();

        grid.setColumns("id", "client", "dateOfOpening", "dateOfEnding",
                "moneySum", "percentPerYear", "isReplenish", "isWithdraw");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editDeposit(event.getValue()));
    }

    public void editDeposit(Deposit deposit) {
        if (deposit == null) {
            closeEditor();
        } else {
            depositForm.setDeposit(deposit);
            depositForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        depositForm.setDeposit(null);
        depositForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(depositService.findAll(/*filterText.getValue()*/));

    }
}
