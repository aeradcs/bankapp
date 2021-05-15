package com.databases.bankapp.view.currencyView;

import com.databases.bankapp.entity.Bond;
import com.databases.bankapp.entity.Currency;
import com.databases.bankapp.view.bondView.BondForm;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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

public class CurrencyForm extends FormLayout {
    TextField country = new TextField("country");
    TextField name = new TextField("name");
    NumberField cost = new NumberField("cost");

    Button save = new Button("save");
    Button delete = new Button("delete");
    Button close = new Button("cancel");

    Binder<Currency> binder = new Binder<>(Currency.class);
    Currency v;

    public CurrencyForm(){
        binder.bindInstanceFields(this);

        binder.forField(cost)
                .withValidator(new DoubleRangeValidator("Can be only more than 0", 0.0, 100000000.0))
                .bind(Currency::getCost, Currency::setCost);
        binder.setBean(v);

        add(country, name, cost, createButtonsLayout());
    }

    public void set(Currency v){
        this.v = v;
        binder.readBean(v);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new CurrencyForm.DeleteEvent(this, v)));
        close.addClickListener(event -> fireEvent(new CurrencyForm.CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(v);
            fireEvent(new CurrencyForm.SaveEvent(this, v));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class CurrencyFormEvent extends ComponentEvent<CurrencyForm> {
        private Currency v;

        protected CurrencyFormEvent(CurrencyForm source, Currency v) {
            super(source, false);
            this.v = v;
        }

        public Currency get() {
            return v;
        }
    }

    public static class SaveEvent extends CurrencyForm.CurrencyFormEvent {
        SaveEvent(CurrencyForm source, Currency v) {
            super(source, v);
        }
    }

    public static class DeleteEvent extends CurrencyForm.CurrencyFormEvent {
        DeleteEvent(CurrencyForm source, Currency v) {
            super(source, v);
        }

    }

    public static class CloseEvent extends CurrencyForm.CurrencyFormEvent {
        CloseEvent(CurrencyForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
