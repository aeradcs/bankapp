package com.databases.bankapp.view.depositView;

import com.databases.bankapp.entity.Client;
import com.databases.bankapp.entity.Deposit;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.DoubleRangeValidator;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class DepositForm extends FormLayout {
    ComboBox<Client> client = new ComboBox<>("client");

    DatePicker dateOfOpening = new DatePicker();
    DatePicker dateOfEnding = new DatePicker();

    NumberField moneySum = new NumberField("money sum");
    NumberField percentPerYear = new NumberField("percent per year");

    IntegerField isReplenish = new IntegerField("is replenish");
    IntegerField isWithdraw = new IntegerField("is withdraw");

    Button save = new Button("save");
    Button delete = new Button("delete");
    Button close = new Button("cancel");

    Binder<Deposit> binder = new Binder<>(Deposit.class);
    Deposit deposit;

    public DepositForm(List<Client> clients) {
        addClassName("invest-account-form");
        dateOfOpening.setLabel("date of opening");
        dateOfEnding.setLabel("date of ending");

        binder.forField(isReplenish)
                .withValidator(new IntegerRangeValidator("Can be only 1 or 0", 0, 1))
                .bind(Deposit::getIsReplenish, Deposit::setIsReplenish);
        binder.setBean(deposit);

        binder.forField(isWithdraw)
                .withValidator(new IntegerRangeValidator("Can be only 1 or 0", 0, 1))
                .bind(Deposit::getIsWithdraw, Deposit::setIsWithdraw);
        binder.setBean(deposit);

        binder.forField(percentPerYear)
                .withValidator(new DoubleRangeValidator("Can be only from 0.1 to 10", 0.1, 10.0))
                .bind(Deposit::getPercentPerYear, Deposit::setPercentPerYear);
        binder.setBean(deposit);

        binder.bindInstanceFields(this);

        client.setItems(clients);
        client.setItemLabelGenerator(Client::getIdStr);

        add(client, dateOfOpening, dateOfEnding, moneySum, percentPerYear,
                isReplenish, isWithdraw, createButtonsLayout());

    }

    public void setDeposit(Deposit deposit){
        /*this.investmentAccount = investmentAccount;
        binder.readBean(investmentAccount);
*/

        binder.setBean(deposit);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        /*try {
            binder.writeBean(investmentAccount);
            fireEvent(new SaveEvent(this, investmentAccount));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
*/

        if(binder.isValid()){
            fireEvent(new DepositForm.SaveEvent(this, binder.getBean()));
        }
    }

    public static abstract class DepositFormEvent extends ComponentEvent<DepositForm> {
        private Deposit deposit;

        protected DepositFormEvent(DepositForm source, Deposit deposit) {
            super(source, false);
            this.deposit = deposit;
        }

        public Deposit getDeposit() {
            return deposit;
        }
    }

    public static class SaveEvent extends DepositFormEvent {
        SaveEvent(DepositForm source, Deposit deposit) {
            super(source, deposit);
        }
    }

    public static class DeleteEvent extends DepositFormEvent {
        DeleteEvent(DepositForm source, Deposit deposit) {
            super(source, deposit);
        }

    }

    public static class CloseEvent extends DepositFormEvent {
        CloseEvent(DepositForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
