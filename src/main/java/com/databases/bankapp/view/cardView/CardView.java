package com.databases.bankapp.view.cardView;

import com.databases.bankapp.entity.Card;
import com.databases.bankapp.service.CardService;
import com.databases.bankapp.service.ClientService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="cards", layout = MainView.class)
@PageTitle("BankApp | Cards")
public class CardView extends VerticalLayout {
    private final Grid<Card> grid;
    private CardService cardService;
    private final CardForm cardForm;

    public CardView(CardService cardService,
                    ClientService clientService)
    {
        this.cardService = cardService;
        this.grid = new Grid<>(Card.class);
        this.cardService = cardService;
        addClassName("card-view");
        setSizeFull();
        configureGrid();

        cardForm = new CardForm(clientService.findAll());
        cardForm.addListener(CardForm.SaveEvent.class, this::saveCard);
        cardForm.addListener(CardForm.DeleteEvent.class, this::deleteCard);
        cardForm.addListener(CardForm.CloseEvent.class, e -> closeEditor());

        Div contentWindow = new Div(cardForm, grid);
        contentWindow.addClassName("contentWindow");
        contentWindow.setSizeFull();

        add(getToolbar(), contentWindow);
        updateList();

        closeEditor();


    }

    private void deleteCard(CardForm.DeleteEvent event) {
        cardService.delete(event.getCard());
        updateList();
        closeEditor();
    }

    private void saveCard(CardForm.SaveEvent event) {
        cardService.save(event.getCard());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        Button addCardButton = new Button("Add card");
        addCardButton.addClickListener(click -> addCard());

        HorizontalLayout toolbar = new HorizontalLayout(addCardButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    void addCard() {
        grid.asSingleSelect().clear();
        editCard(new Card());
    }

    private void configureGrid() {
        grid.addClassName("card-grid");
        grid.setSizeFull();
        grid.setColumns("id", "expiryDate", "moneySum", "client");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editCard(event.getValue()));
    }

    public void editCard(Card card) {
        if (card == null) {
            closeEditor();
        } else {
            cardForm.setCard(card);
            cardForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        cardForm.setCard(null);
        cardForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(cardService.findAll());
    }





}
