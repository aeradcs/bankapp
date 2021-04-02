package com.databases.bankapp.view;

import com.databases.bankapp.entity.Client;
import com.databases.bankapp.repository.ClientRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route
public class MainView extends VerticalLayout {

    private final ClientRepository clientRepository;
    private Grid<Client> grid = new Grid<>(Client.class);

    @Autowired
    public MainView(ClientRepository clientRepository)
    {
        this.clientRepository = clientRepository;


        add(grid);

        grid.setItems(clientRepository.findAll());
    }

}
