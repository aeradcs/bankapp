package com.databases.bankapp.view;

import com.databases.bankapp.entity.Client;
import com.databases.bankapp.entity.InvestmentAccount;
import com.databases.bankapp.repository.ClientRepository;
import com.databases.bankapp.service.ClientService;
import com.databases.bankapp.view.clientView.ClientForm;
import com.databases.bankapp.view.clientView.ClientView;
import com.databases.bankapp.view.investmentAccountView.InvestmentAccountView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "home")
public class MainView extends AppLayout {
    public MainView() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle());

        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setHeight("50px");
        header.addClassName("header");


        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink clientRL = new RouterLink("Clients", ClientView.class);
        clientRL.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink investmentAccountRL = new RouterLink("Investment Accounts", InvestmentAccountView.class);
        investmentAccountRL.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(clientRL, investmentAccountRL));
    }
}