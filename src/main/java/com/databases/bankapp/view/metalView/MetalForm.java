package com.databases.bankapp.view.metalView;

import com.databases.bankapp.entity.Metal;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class MetalForm extends FormLayout {
    TextField name = new TextField("name");

    Button save = new Button("save");
    Button delete = new Button("delete");
    Button close = new Button("cancel");

    Binder<Metal> binder = new Binder<>(Metal.class);
    Metal v;

    public MetalForm(){
        binder.bindInstanceFields(this);

        add(name, createButtonsLayout());
    }

    public void set(Metal v){
        this.v = v;
        binder.readBean(v);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new MetalForm.DeleteEvent(this, v)));
        close.addClickListener(event -> fireEvent(new MetalForm.CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(v);
            fireEvent(new MetalForm.SaveEvent(this, v));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class MetalFormEvent extends ComponentEvent<MetalForm> {
        private Metal v;

        protected MetalFormEvent(MetalForm source, Metal v) {
            super(source, false);
            this.v = v;
        }

        public Metal get() {
            return v;
        }
    }

    public static class SaveEvent extends MetalForm.MetalFormEvent {
        SaveEvent(MetalForm source, Metal v) {
            super(source, v);
        }
    }

    public static class DeleteEvent extends MetalForm.MetalFormEvent {
        DeleteEvent(MetalForm source, Metal v) {
            super(source, v);
        }

    }

    public static class CloseEvent extends MetalForm.MetalFormEvent {
        CloseEvent(MetalForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
