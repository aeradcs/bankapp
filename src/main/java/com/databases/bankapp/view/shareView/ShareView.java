package com.databases.bankapp.view.shareView;

import com.databases.bankapp.entity.Share;
import com.databases.bankapp.service.ShareService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="shares", layout = MainView.class)
@PageTitle("Bank App | Shares")
public class ShareView extends VerticalLayout {
    private TextField filterByName = new TextField("filter by name");

    private final Grid<Share> grid;
    private final ShareService shareService;
    private final ShareForm shareForm;

    public ShareView(ShareService shareService)
    {
        this.shareService = shareService;
        this.grid = new Grid<>(Share.class);
        addClassName("client-view");
        setSizeFull();
        configureFilterByName();
        configureGrid();

        shareForm = new ShareForm();
        shareForm.addListener(ShareForm.SaveEvent.class, this::saveShare);
        shareForm.addListener(ShareForm.DeleteEvent.class, this::deleteShare);
        shareForm.addListener(ShareForm.CloseEvent.class, e -> closeEditor());

        Div contentWindow = new Div(shareForm, grid);
        contentWindow.addClassName("contentWindow");
        contentWindow.setSizeFull();

        add(filterByName, getToolbar(), contentWindow);
        updateList();

        closeEditor();

    }

    private void configureFilterByName() {
        filterByName.setPlaceholder("type name...");
        filterByName.setClearButtonVisible(true);
        filterByName.setValueChangeMode(ValueChangeMode.LAZY);
        filterByName.addValueChangeListener(e -> updateList());
    }

    private void deleteShare(ShareForm.DeleteEvent event) {
        shareService.delete(event.getShare());
        updateList();
        closeEditor();
    }

    private void saveShare(ShareForm.SaveEvent event) {
        shareService.save(event.getShare());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        Button addClientButton = new Button("Add Share");
        addClientButton.addClickListener(click -> addShare());

        HorizontalLayout toolbar = new HorizontalLayout(addClientButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    void addShare() {
        grid.asSingleSelect().clear();
        editShare(new Share());
    }

    private void configureGrid() {
        grid.addClassName("client-grid");
        grid.setSizeFull();
        grid.setColumns("id", "country", "nameOfCompany", "capitalization","stock");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editShare(event.getValue()));
    }

    public void editShare(Share share) {
        if (share == null) {
            closeEditor();
        } else {
            shareForm.setShare(share);
            shareForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        shareForm.setShare(null);
        shareForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(shareService.findAll(filterByName.getValue()));

    }
}
