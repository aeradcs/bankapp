package com.databases.bankapp.view.investmentAccountView;

import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value="investmentaccounts", layout = MainView.class)
@PageTitle("InvestmentAccounts")
public class InvestmentAccountView extends VerticalLayout {


    public InvestmentAccountView(){

        addClassName("invest-account-view");
    }

}
