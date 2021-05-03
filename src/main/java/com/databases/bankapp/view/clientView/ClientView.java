package com.databases.bankapp.view.clientView;

import com.databases.bankapp.entity.Client;
import com.databases.bankapp.repository.ClientRepository;
import com.databases.bankapp.service.ClientService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;


@Route(value="clients", layout = MainView.class)
@PageTitle("Bank App | Clients")
public class ClientView extends VerticalLayout {
    private final Grid<Client> grid;
    private final ClientService clientService;
    private final ClientForm clientForm;

    public ClientView(ClientService clientService)
    {
        this.clientService = clientService;
        this.grid = new Grid<>(Client.class);
        addClassName("client-view");
        setSizeFull();
        configureGrid();

        clientForm = new ClientForm();
        clientForm.addListener(ClientForm.SaveEvent.class, this::saveClient);
        clientForm.addListener(ClientForm.DeleteEvent.class, this::deleteClient);
        clientForm.addListener(ClientForm.CloseEvent.class, e -> closeEditor());

        Div contentWindow = new Div(clientForm, grid);
        contentWindow.addClassName("contentWindow");
        contentWindow.setSizeFull();

        add(getToolbar(), contentWindow);
        updateList();

        closeEditor();

    }

    private void deleteClient(ClientForm.DeleteEvent event) {
        clientService.delete(event.getContact());
        updateList();
        closeEditor();
    }

    private void saveClient(ClientForm.SaveEvent event) {
        clientService.save(event.getContact());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        Button addClientButton = new Button("Add client");
        addClientButton.addClickListener(click -> addClient());

        HorizontalLayout toolbar = new HorizontalLayout(addClientButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    void addClient() {
        grid.asSingleSelect().clear();
        editClient(new Client());
    }

    private void configureGrid() {
        grid.addClassName("client-grid");
        grid.setSizeFull();
        grid.setColumns("id", "fullName", "gender", "dateOfBirth","jobStatus", "phoneNumber");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editClient(event.getValue()));
    }

    public void editClient(Client contact) {
        if (contact == null) {
            closeEditor();
        } else {
            clientForm.setClient(contact);
            clientForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        clientForm.setClient(null);
        clientForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(clientService.findAll());

    }

}
