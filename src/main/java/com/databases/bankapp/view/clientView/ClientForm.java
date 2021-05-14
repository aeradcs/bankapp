package com.databases.bankapp.view.clientView;

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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.DateRangeValidator;
import com.vaadin.flow.data.validator.DoubleRangeValidator;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.shared.Registration;

import java.time.LocalDate;
import java.util.*;

public class ClientForm extends FormLayout {
    TextField fullName = new TextField("full name");
    //TextField gender = new TextField("gender");
    ComboBox<String> gender = new ComboBox<>();
    DatePicker dateOfBirth = new DatePicker();
    //TextField jobStatus = new TextField("job status");
    ComboBox<String> jobStatus = new ComboBox<>();
    TextField phoneNumber = new TextField("phone number");

    Button save = new Button("save");
    Button delete = new Button("delete");
    Button close = new Button("cancel");

    Binder<Client> binder = new Binder<>(Client.class);
    Client client;

    public ClientForm() {
        addClassName("client-form");
        dateOfBirth.setLabel("date of birth");
        gender.setLabel("gender");
        jobStatus.setLabel("job status");

        ArrayList<String> genderItems = new ArrayList<>();
        genderItems.add("мужской");
        genderItems.add("женский");

        gender.setItems(genderItems);

        ArrayList<String> jobStatusItems = new ArrayList<>();
        jobStatusItems.add("работает");
        jobStatusItems.add("безработный");

        jobStatus.setItems(jobStatusItems);

        binder.forField(phoneNumber)
                .withValidator(new RegexpValidator("Not a valid phone number",
                        "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$"))
                .bind(Client::getPhoneNumber, Client::setPhoneNumber);
        binder.setBean(client);

        binder.forField(dateOfBirth)
                .withValidator(new DateRangeValidator(
                        "Date of birth must be between 1900-01-01 and 2003-01-01",
                        LocalDate.of(1900, 1, 1),
                        LocalDate.of(2003, 1, 1)))
                .bind(Client::getDateOfBirth, Client::setDateOfBirth);
        binder.setBean(client);


        binder.bindInstanceFields(this);

        add(fullName, gender, dateOfBirth, jobStatus, phoneNumber, createButtonsLayout());
    }

    public void setClient(Client client){
        this.client = client;
        binder.readBean(client);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, client)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(client);
            fireEvent(new SaveEvent(this, client));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class ClientFormEvent extends ComponentEvent<ClientForm> {
        private Client client;

        protected ClientFormEvent(ClientForm source, Client contact) {
            super(source, false);
            this.client = contact;
        }

        public Client getContact() {
            return client;
        }
    }

    public static class SaveEvent extends ClientFormEvent {
        SaveEvent(ClientForm source, Client contact) {
            super(source, contact);
        }
    }

    public static class DeleteEvent extends ClientFormEvent {
        DeleteEvent(ClientForm source, Client contact) {
            super(source, contact);
        }

    }

    public static class CloseEvent extends ClientFormEvent {
        CloseEvent(ClientForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}