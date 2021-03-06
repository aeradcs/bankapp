package com.databases.bankapp.view.queries;

import com.databases.bankapp.entity.Card;
import com.databases.bankapp.entity.Share;
import com.databases.bankapp.service.CardService;
import com.databases.bankapp.service.ShareService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route(value = "query_2", layout = MainView.class)
@PageTitle("Query №2")
public class FindCardByDiffParams extends VerticalLayout{
    private final CardService cardService;

    private final Grid<Card> grid;

    private final IntegerField less = new IntegerField();
    private final IntegerField more = new IntegerField();

    ComboBox<String> sort = new ComboBox<>();

    public FindCardByDiffParams(CardService cardService) {
        this.cardService = cardService;
        this.grid = new Grid<>(Card.class);

        configureGrid();
        add(getToolBar(), grid);
        setSizeFull();

        grid.setItems(Collections.emptyList());
    }

    private HorizontalLayout getToolBar() {


        less.setPlaceholder("Less than...");
        less.setClearButtonVisible(true);

        more.setPlaceholder("More than...");
        more.setClearButtonVisible(true);

        Button qbetweeen = new Button("Find between these",
                click -> findBetween(more.getValue(), less.getValue(), sort.getValue()));


        ArrayList<String> items = new ArrayList<>();
        items.add("asc");
        items.add("desc");

        sort.setItems(items);
        sort.setPlaceholder("sort");

        HorizontalLayout toolbar = new HorizontalLayout(more, less, sort, qbetweeen);

        return toolbar;
    }

    private void findBetween(Integer param1, Integer param2, String sort) {
        if(sort.equals("asc")){
            List<Card> cards = null;
            if(param1 != null && param2 != null){
                cards = cardService.getCardByMoneySumBetweenAsc(param1, param2);
                if (cards.isEmpty()) {
                    grid.setItems(Collections.emptyList());
                } else grid.setItems(cards);
            }
            else {
                grid.setItems(Collections.emptyList());
            }
        }
        else if(sort.equals("desc")){
            List<Card> cards = null;
            if(param1 != null && param2 != null){
                cards = cardService.getCardByMoneySumBetweenDesc(param1, param2);
                if (cards.isEmpty()) {
                    grid.setItems(Collections.emptyList());
                } else grid.setItems(cards);
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
        grid.setColumns("id", "expiryDate", "moneySum", "client");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();
    }
}
