package com.databases.bankapp.view.clientView;

import com.databases.bankapp.entity.Client;
import com.databases.bankapp.repository.ClientRepository;
import com.databases.bankapp.service.ClientService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

@Route("")
public class ClientView extends VerticalLayout {
    private Grid<Client> grid = new Grid<>(Client.class);
    private ClientService clientService;
    private ClientForm clientForm;

    public ClientView(ClientService clientService)
    {
        this.clientService = clientService;
        addClassName("list-view-client");
        setSizeFull();
        configureGrid();

        clientForm = new ClientForm();

        Div contentWindow = new Div(clientForm, grid);
        contentWindow.addClassName("contentWindow");
        contentWindow.setSizeFull();

        add(contentWindow);
        updateList();

    }

    private void updateList() {
        grid.setItems(clientService.findAll());

    }

    private void configureGrid() {
        grid.addClassName("client-grid");
        grid.setSizeFull();
        grid.setColumns("id", "fullName", "gender", "dateOfBirth","jobStatus", "phoneNumber");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
