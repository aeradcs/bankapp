package com.databases.bankapp.view.metalView;


import com.databases.bankapp.entity.Metal;
import com.databases.bankapp.service.MetalService;
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

@Route(value="metals", layout = MainView.class)
@PageTitle("Bank App | Metals")
public class MetalView extends VerticalLayout {


    private TextField filter = new TextField("filter by name");

    private final Grid<Metal> grid;
    private final MetalService service;
    private final MetalForm form;

    public MetalView(MetalService service)
    {
        this.service = service;
        this.grid = new Grid<>(Metal.class);
        setSizeFull();
        configureFilter();
        configureGrid();

        form = new MetalForm();
        form.addListener(MetalForm.SaveEvent.class, this::save);
        form.addListener(MetalForm.DeleteEvent.class, this::delete);
        form.addListener(MetalForm.CloseEvent.class, e -> closeEditor());

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

    private void delete(MetalForm.DeleteEvent event) {
        service.delete(event.get());
        updateList();
        closeEditor();
    }

    private void save(MetalForm.SaveEvent event) {
        service.save(event.get());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        Button addClientButton = new Button("Add Metal");
        addClientButton.addClickListener(click -> add());

        HorizontalLayout toolbar = new HorizontalLayout(addClientButton);
        return toolbar;
    }

    void add() {
        grid.asSingleSelect().clear();
        edit(new Metal());
    }
    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("id", "name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                edit(event.getValue()));
    }

    public void edit(Metal v) {
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
