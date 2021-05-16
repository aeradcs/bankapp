package com.databases.bankapp.view.queries;

import com.databases.bankapp.entity.Card;
import com.databases.bankapp.service.DepositService;
import com.databases.bankapp.service.InvestmentAccountService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route(value = "query_6", layout = MainView.class)
@PageTitle("Query №6")
public class FindInvAccCountBetweenForEveryClientWhoHasIt extends VerticalLayout {
    private final InvestmentAccountService service;

    private final Grid<Object[]> grid;

    private final IntegerField less = new IntegerField();
    private final IntegerField more = new IntegerField();

    ComboBox<String> sort = new ComboBox<>();

    public FindInvAccCountBetweenForEveryClientWhoHasIt(InvestmentAccountService service) {
        this.service = service;
        this.grid = new Grid<>();

        configureGrid();
        add(getToolBar(), grid);
        setSizeFull();

        grid.setItems(Collections.emptyList());
    }

    private HorizontalLayout getToolBar() {


        Button find = new Button("Find",
                click -> getInvAccCountBetweenForEveryClientWhoHasIt(more.getValue(), less.getValue(), sort.getValue()));

        less.setPlaceholder("Less than...");
        less.setClearButtonVisible(true);

        more.setPlaceholder("More than...");
        more.setClearButtonVisible(true);

        ArrayList<String> items = new ArrayList<>();
        items.add("asc");
        items.add("desc");

        sort.setItems(items);
        sort.setPlaceholder("sort");

        HorizontalLayout toolbar = new HorizontalLayout(more, less, sort, find);

        return toolbar;
    }



    private void getInvAccCountBetweenForEveryClientWhoHasIt(Integer param1, Integer param2, String sort) {
        if(sort.equals("asc")){
            List<Object[]> v = null;
            if(param1 != null && param2 != null){
                v = service.getInvAccCountBetweenForEveryClientWhoHasItAsc(param1, param2);
                if (v.isEmpty()) {
                    grid.setItems(Collections.emptyList());
                } else grid.setItems(v);
            }
            else {
                grid.setItems(Collections.emptyList());
            }
        }
        else if(sort.equals("desc")){
            List<Object[]> v = null;
            if(param1 != null && param2 != null){
                v = service.getInvAccCountBetweenForEveryClientWhoHasItDesc(param1, param2);
                if (v.isEmpty()) {
                    grid.setItems(Collections.emptyList());
                } else grid.setItems(v);
            }
            else {
                grid.setItems(Collections.emptyList());
            }
        }
        else {
            grid.setItems(Collections.emptyList());
        }

    }


    private void configureGrid() {
        grid.addColumn(objects -> objects[0]).setHeader("Client Id");
        grid.addColumn(objects -> objects[1]).setHeader("Client Full Name");
        grid.addColumn(objects -> objects[2]).setHeader("Count Invest Accounts");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();
    }
}
