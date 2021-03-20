package com.databases.bankapp.view;

import com.databases.bankapp.service.GreetService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("")


public class MainView extends VerticalLayout {

    public MainView()
    {
        TextField textField = new TextField("hello world aao");

        add(textField);

    }

}
