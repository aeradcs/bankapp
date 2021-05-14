package com.databases.bankapp.view.cardView;

import com.databases.bankapp.entity.Card;
import com.databases.bankapp.entity.Client;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class CardForm extends FormLayout {
    ComboBox<Client> client = new ComboBox<>("client");
    DatePicker expiryDate = new DatePicker();
    NumberField moneySum = new NumberField("money sum");

    Button save = new Button("save");
    Button delete = new Button("delete");
    Button close = new Button("cancel");

    Binder<Card> binder = new Binder<>(Card.class);
    Card card;

    public CardForm(List<Client> clients){
        addClassName("card-form");
        expiryDate.setLabel("expiry date");

        binder.bindInstanceFields(this);

        client.setItems(clients);
        client.setItemLabelGenerator(Client::getIdStr);
        add(client, expiryDate, moneySum, createButtonsLayout());
    }

    public void setCard(Card card){
        this.card = card;
        binder.readBean(card);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, card)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(card);
            fireEvent(new SaveEvent(this, card));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class CardFormEvent extends ComponentEvent<CardForm> {
        private Card card;

        protected CardFormEvent(CardForm source, Card card) {
            super(source, false);
            this.card = card;
        }

        public Card getCard() {
            return card;
        }
    }

    public static class SaveEvent extends CardFormEvent {
        SaveEvent(CardForm source, Card card) {
            super(source, card);
        }
    }

    public static class DeleteEvent extends CardFormEvent {
        DeleteEvent(CardForm source, Card card) {
            super(source, card);
        }

    }

    public static class CloseEvent extends CardFormEvent {
        CloseEvent(CardForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
