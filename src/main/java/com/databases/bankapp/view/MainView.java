package com.databases.bankapp.view;

import com.databases.bankapp.view.bondView.BondView;
import com.databases.bankapp.view.cardView.CardView;
import com.databases.bankapp.view.clientView.ClientView;
import com.databases.bankapp.view.currencyView.CurrencyView;
import com.databases.bankapp.view.depositView.DepositView;
import com.databases.bankapp.view.investmentAccountView.InvestmentAccountView;
import com.databases.bankapp.view.metalView.MetalView;
import com.databases.bankapp.view.queries.FindCardByDiffParams;
import com.databases.bankapp.view.queries.FindClientsWhoHasInvestAcc;
import com.databases.bankapp.view.queries.FindDepositCountForEveryClientWhoHasIt;
import com.databases.bankapp.view.queries.GetShareByDiffParams;
import com.databases.bankapp.view.shareView.ShareView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

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

        RouterLink shareRL = new RouterLink("Shares", ShareView.class);
        shareRL.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink depositRL = new RouterLink("Deposits", DepositView.class);
        depositRL.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink cardRL = new RouterLink("Cards", CardView.class);
        cardRL.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink bondRL = new RouterLink("Bonds", BondView.class);
        bondRL.setHighlightCondition(HighlightConditions.sameLocation());

        /*RouterLink currencyRL = new RouterLink("Currencies", CurrencyView.class);
        currencyRL.setHighlightCondition(HighlightConditions.sameLocation());*/

        /*RouterLink metalRL = new RouterLink("Metals", MetalView.class);
        metalRL.setHighlightCondition(HighlightConditions.sameLocation());*/

        RouterLink q1 = new RouterLink("Get Shares By Diff Params", GetShareByDiffParams.class);
        q1.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink q2 = new RouterLink("Get Cards By Diff Params", FindCardByDiffParams.class);
        q2.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink q3 = new RouterLink("Get Clients Who Has Invest Acc", FindClientsWhoHasInvestAcc.class);
        q3.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink q4 = new RouterLink("Get Clients Who Has Deposit Acc And Count", FindDepositCountForEveryClientWhoHasIt.class);
        q4.setHighlightCondition(HighlightConditions.sameLocation());


        addToDrawer(new VerticalLayout(clientRL, investmentAccountRL, shareRL, depositRL, cardRL, bondRL, /*currencyRL, metalRL,*/
                q1, q2, q3, q4));
    }
}