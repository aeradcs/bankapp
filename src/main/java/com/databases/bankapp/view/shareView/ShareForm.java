package com.databases.bankapp.view.shareView;

import com.databases.bankapp.entity.Card;
import com.databases.bankapp.entity.Share;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.DoubleRangeValidator;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import com.vaadin.flow.shared.Registration;

import java.util.ArrayList;

public class ShareForm extends FormLayout {

    TextField country = new TextField("country");
    TextField name = new TextField("name");
    NumberField cost = new NumberField("cost");
    IntegerField capitalization = new IntegerField("capitalization");
    ComboBox<String> stock = new ComboBox<>();

    Button save = new Button("save");
    Button delete = new Button("delete");
    Button close = new Button("cancel");

    Binder<Share> binder = new Binder<>(Share.class);
    Share share;

    public ShareForm() {
        addClassName("client-form");
        stock.setLabel("stock");

        ArrayList<String> stockItems = new ArrayList<>();
        stockItems.add("MSK");
        stockItems.add("SPB");

        stock.setItems(stockItems);

        binder.forField(capitalization)
                .withValidator(new IntegerRangeValidator("Can be only between 0 and 1000000000000", 0, 1000000000))
                .bind(Share::getCapitalization, Share::setCapitalization);
        binder.setBean(share);
        binder.forField(cost)
                .withValidator(new DoubleRangeValidator("Can be only more than 0", 0.0, 100000000.0))
                .bind(Share::getCost, Share::setCost);
        binder.setBean(share);

        binder.forField(name).bind(Share::getName, Share::setName);
        binder.bindInstanceFields(this);

        add(country, name, cost, capitalization, stock, createButtonsLayout());
    }

    public void setShare(Share share){
        /*this.share = share;
        binder.readBean(share);*/
        binder.setBean(share);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, share)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        /*try {
            binder.writeBean(share);
            fireEvent(new SaveEvent(this, share));
        } catch (ValidationException e) {
            e.printStackTrace();
        }*/
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    public static abstract class ShareFormEvent extends ComponentEvent<ShareForm> {
        private Share share;

        protected ShareFormEvent(ShareForm source, Share share) {
            super(source, false);
            this.share = share;
        }

        public Share getShare() {
            return share;
        }
    }

    public static class SaveEvent extends ShareFormEvent {
        SaveEvent(ShareForm source, Share share) {
            super(source, share);
        }
    }

    public static class DeleteEvent extends ShareFormEvent {
        DeleteEvent(ShareForm source, Share share) {
            super(source, share);
        }

    }

    public static class CloseEvent extends ShareFormEvent {
        CloseEvent(ShareForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
