package com.databases.bankapp.view.bondView;

import com.databases.bankapp.entity.Bond;
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

public class BondForm extends FormLayout {
    TextField country = new TextField("country");
    TextField name = new TextField("name");
    NumberField cost = new NumberField("cost");
    IntegerField amountOfYears = new IntegerField("amount of years");
    NumberField percentPerYear = new NumberField("percent per year");

    Button save = new Button("save");
    Button delete = new Button("delete");
    Button close = new Button("cancel");

    Binder<Bond> binder = new Binder<>(Bond.class);
    //Bond bond;

    public BondForm(){
        binder.forField(amountOfYears)
                .withValidator(new IntegerRangeValidator("Can be only between 1 and 50", 1, 50))
                .bind(Bond::getAmountOfYears, Bond::setAmountOfYears);
        //binder.setBean(bond);

        binder.forField(percentPerYear)
                .withValidator(new DoubleRangeValidator("Can be only between 0.1 and 100", 0.1, 100.0))
                .bind(Bond::getPercentPerYear, Bond::setPercentPerYear);
        //binder.setBean(bond);

        binder.forField(cost)
                .withValidator(new DoubleRangeValidator("Can be only more than 0", 0.0, 100000000.0))
                .bind(Bond::getCost, Bond::setCost);
        //binder.setBean(bond);

        binder.bindInstanceFields(this);

        add(country, name, cost, amountOfYears, percentPerYear, createButtonsLayout());
    }

    public void set(Bond bond){
        //this.bond = bond;
        //binder.readBean(bond);
        binder.setBean(bond);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean()/*bond*/)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        /*try {
            binder.writeBean(bond);
            fireEvent(new SaveEvent(this, bond));
        } catch (ValidationException e) {
            e.printStackTrace();
        }*/
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    public static abstract class BondFormEvent extends ComponentEvent<BondForm> {
        private Bond bond;

        protected BondFormEvent(BondForm source, Bond bond) {
            super(source, false);
            this.bond = bond;
        }

        public Bond getBond() {
            return bond;
        }
    }

    public static class SaveEvent extends BondFormEvent {
        SaveEvent(BondForm source, Bond bond) {
            super(source, bond);
        }
    }

    public static class DeleteEvent extends BondFormEvent {
        DeleteEvent(BondForm source, Bond bond) {
            super(source, bond);
        }

    }

    public static class CloseEvent extends BondFormEvent {
        CloseEvent(BondForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
