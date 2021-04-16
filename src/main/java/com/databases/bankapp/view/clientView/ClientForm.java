package com.databases.bankapp.view.clientView;

import com.databases.bankapp.entity.Client;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.GregorianCalendar;

public class ClientForm extends FormLayout {
    private TextField fullName = new TextField("full name");
    private ComboBox<Client.GenderEnum> gender = new ComboBox<>("gender");
    private DatePicker datePicker = new DatePicker();

    private ComboBox<Client.JobStatusEnum> jobStatus = new ComboBox<>("job status");
    private TextField phoneNumber = new TextField("phone number");

    private Button save = new Button("save");
    private Button delete = new Button("delete");
    private Button close = new Button("cancel");

    public ClientForm() {
        addClassName("client-form");
        datePicker.setLabel("date of birth");
        add(fullName, gender, datePicker, jobStatus, phoneNumber, createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        return new HorizontalLayout(save, delete, close);
    }
}
