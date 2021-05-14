package com.databases.bankapp.view.investmentAccountView;

import com.databases.bankapp.entity.Card;
import com.databases.bankapp.entity.Client;
import com.databases.bankapp.entity.InvestmentAccount;
import com.databases.bankapp.entity.Share;
import com.databases.bankapp.view.clientView.ClientForm;
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
import com.vaadin.flow.data.validator.DoubleRangeValidator;
import com.vaadin.flow.shared.Registration;
import org.vaadin.gatanaso.MultiselectComboBox;

import java.util.List;

public class InvestmentAccountForm extends FormLayout {
    ComboBox<Client> client = new ComboBox<>("client");

    DatePicker dateOfOpening = new DatePicker();
    NumberField moneySum = new NumberField("money sum");

    MultiselectComboBox<Share> shares = new MultiselectComboBox<>("share");

    Button save = new Button("save");
    Button delete = new Button("delete");
    Button close = new Button("cancel");

    Binder<InvestmentAccount> binder = new Binder<>(InvestmentAccount.class);
    InvestmentAccount investmentAccount;

    public InvestmentAccountForm(List<Client> clients, List<Share> shares_) {
        addClassName("invest-account-form");
        dateOfOpening.setLabel("date of opening");

        binder.forField(moneySum)
                .withValidator(new DoubleRangeValidator("Can be only between 0 and 1000000000000", 0.0, 1000000000000.0))
                .bind(InvestmentAccount::getMoneySum, InvestmentAccount::setMoneySum);
        binder.setBean(investmentAccount);

        binder.bindInstanceFields(this);

        client.setItems(clients);
        client.setItemLabelGenerator(Client::getIdStr);

        shares.setItems(shares_);
        shares.setItemLabelGenerator(Share::getNameOfCompany);

        add(dateOfOpening, moneySum, client, shares, createButtonsLayout());

    }

    public void setInvestmentAccount(InvestmentAccount investmentAccount){
        /*this.investmentAccount = investmentAccount;
        binder.readBean(investmentAccount);
*/

        binder.setBean(investmentAccount);
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
            fireEvent(new InvestmentAccountForm.SaveEvent(this, binder.getBean()));
        }
    }

    public static abstract class InvestAccountFormEvent extends ComponentEvent<InvestmentAccountForm> {
        private InvestmentAccount investmentAccount;

        protected InvestAccountFormEvent(InvestmentAccountForm source, InvestmentAccount account) {
            super(source, false);
            this.investmentAccount = account;
        }

        public InvestmentAccount getInvestmentAccount() {
            return investmentAccount;
        }
    }

    public static class SaveEvent extends InvestAccountFormEvent {
        SaveEvent(InvestmentAccountForm source, InvestmentAccount account) {
            super(source, account);
        }
    }

    public static class DeleteEvent extends InvestAccountFormEvent {
        DeleteEvent(InvestmentAccountForm source, InvestmentAccount account) {
            super(source, account);
        }

    }

    public static class CloseEvent extends InvestAccountFormEvent {
        CloseEvent(InvestmentAccountForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
