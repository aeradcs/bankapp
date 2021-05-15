package com.databases.bankapp.view.currencyView;

import com.databases.bankapp.entity.Currency;
import com.databases.bankapp.service.CurrencyService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="currencies", layout = MainView.class)
@PageTitle("Bank App | Currencies")
public class CurrencyView extends VerticalLayout {

    private TextField filter = new TextField("filter by name");

    private final Grid<Currency> grid;
    private final CurrencyService service;
    private final CurrencyForm form;

    public CurrencyView(CurrencyService service)
    {
        this.service = service;
        this.grid = new Grid<>(Currency.class);
        setSizeFull();
        configureFilter();
        configureGrid();

        form = new CurrencyForm();
        form.addListener(CurrencyForm.SaveEvent.class, this::save);
        form.addListener(CurrencyForm.DeleteEvent.class, this::delete);
        form.addListener(CurrencyForm.CloseEvent.class, e -> closeEditor());

        Div contentWindow = new Div(form, grid);
        contentWindow.setSizeFull();

        add(filter, getToolbar(), contentWindow);

        updateList();

        closeEditor();

    }

    private void configureFilter() {
        filter.setPlaceholder("type name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList());
    }

    private void delete(CurrencyForm.DeleteEvent event) {
        service.delete(event.get());
        updateList();
        closeEditor();
    }

    private void save(CurrencyForm.SaveEvent event) {
        service.save(event.get());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        Button addClientButton = new Button("Add Currency");
        addClientButton.addClickListener(click -> add());

        HorizontalLayout toolbar = new HorizontalLayout(addClientButton);
        return toolbar;
    }

    void add() {
        grid.asSingleSelect().clear();
        edit(new Currency());
    }
    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("id", "country", "name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                edit(event.getValue()));
    }

    public void edit(Currency v) {
        if ( v == null) {
            closeEditor();
        } else {
            form.set(v);
            form.setVisible(true);
        }
    }

    private void closeEditor() {
        form.set(null);
        form.setVisible(false);
    }

    private void updateList() {
        grid.setItems(service.findAll(filter.getValue()));

    }
}
