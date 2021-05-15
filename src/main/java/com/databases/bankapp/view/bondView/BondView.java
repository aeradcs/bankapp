package com.databases.bankapp.view.bondView;

import com.databases.bankapp.entity.Bond;
import com.databases.bankapp.service.BondService;
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

@Route(value="bonds", layout = MainView.class)
@PageTitle("Bank App | Bonds")
public class BondView extends VerticalLayout {
    private TextField filter = new TextField("filter by name");

    private final Grid<Bond> grid;
    private final BondService service;
    private final BondForm form;

    public BondView(BondService service)
    {
        this.service = service;
        this.grid = new Grid<>(Bond.class);
        setSizeFull();
        configureFilter();
        configureGrid();

        form = new BondForm();
        form.addListener(BondForm.SaveEvent.class, this::save);
        form.addListener(BondForm.DeleteEvent.class, this::delete);
        form.addListener(BondForm.CloseEvent.class, e -> closeEditor());

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

    private void delete(BondForm.DeleteEvent event) {
        service.delete(event.getBond());
        updateList();
        closeEditor();
    }

    private void save(BondForm.SaveEvent event) {
        service.save(event.getBond());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        Button addClientButton = new Button("Add Bond");
        addClientButton.addClickListener(click -> add());

        HorizontalLayout toolbar = new HorizontalLayout(addClientButton);
        return toolbar;
    }

    void add() {
        grid.asSingleSelect().clear();
        edit(new Bond());
    }
    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("id", "country", "name", "percentPerYear", "amountOfYears");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                edit(event.getValue()));
    }

    public void edit(Bond v) {
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
