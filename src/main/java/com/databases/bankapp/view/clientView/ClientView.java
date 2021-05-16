package com.databases.bankapp.view.clientView;

import com.databases.bankapp.entity.Client;
import com.databases.bankapp.repository.ClientRepository;
import com.databases.bankapp.service.ClientService;
import com.databases.bankapp.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.data.provider.SortOrder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@Route(value="clients", layout = MainView.class)
@PageTitle("Bank App | Clients")
public class ClientView extends VerticalLayout {
    private TextField filterByName = new TextField("filter by full name");
    /*private TextField filterByGender = new TextField("filter by gender");
    private TextField filterByJobStatus = new TextField("filter by job status");
*/
    private final Grid<Client> grid;
    private final ClientService clientService;
    private final ClientForm clientForm;

    ComboBox<String> sort = new ComboBox<>();


    public ClientView(ClientService clientService)
    {
        this.clientService = clientService;
        this.grid = new Grid<>(Client.class);
        addClassName("client-view");
        setSizeFull();
        configureFilterByName();
        /*configureFilterByGender();
        configureFilterByJobStatus();*/
        configureGrid();

        clientForm = new ClientForm();
        clientForm.addListener(ClientForm.SaveEvent.class, this::saveClient);
        clientForm.addListener(ClientForm.DeleteEvent.class, this::deleteClient);
        clientForm.addListener(ClientForm.CloseEvent.class, e -> closeEditor());

        Div contentWindow = new Div(clientForm, grid);
        contentWindow.addClassName("contentWindow");
        contentWindow.setSizeFull();

        add(filterByName/*, filterByGender, filterByJobStatus*/, getToolbar(), contentWindow);
        updateList();

        closeEditor();

    }

    private void configureFilterByName() {
        filterByName.setPlaceholder("type name...");
        filterByName.setClearButtonVisible(true);
        filterByName.setValueChangeMode(ValueChangeMode.LAZY);
        filterByName.addValueChangeListener(e -> updateList());
    }

    /*private void configureFilterByGender() {
        filterByGender.setPlaceholder("type gender...");
        filterByGender.setClearButtonVisible(true);
        filterByGender.setValueChangeMode(ValueChangeMode.LAZY);
        filterByGender.addValueChangeListener(e -> updateList());
    }
    private void configureFilterByJobStatus() {
        filterByJobStatus.setPlaceholder("type job status...");
        filterByJobStatus.setClearButtonVisible(true);
        filterByJobStatus.setValueChangeMode(ValueChangeMode.LAZY);
        filterByJobStatus.addValueChangeListener(e -> updateList());
    }*/

    private void deleteClient(ClientForm.DeleteEvent event) {
        clientService.delete(event.getContact());
        updateList();
        closeEditor();
    }

    private void saveClient(ClientForm.SaveEvent event) {
        clientService.save(event.getContact());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        Button addClientButton = new Button("Add Сlient");
        addClientButton.addClickListener(click -> addClient());

        ArrayList<String> items = new ArrayList<>();
        items.add("asc");
        items.add("desc");

        sort.setItems(items);
        sort.setPlaceholder("sort");

        Button nameb = new Button("Sort By Name",
                click -> sortByName(sort.getValue()));

        Button genderb = new Button("Sort By Gender",
                click -> sortByGender(sort.getValue()));

        Button dateb = new Button("Sort By Date Of Birth",
                click -> sortByDate(sort.getValue()));

        Button jobb = new Button("Sort By Job Status",
                click -> sortByJob(sort.getValue()));

        Button phoneb = new Button("Sort By Phone Number",
                click -> sortByPhone(sort.getValue()));


        HorizontalLayout toolbar = new HorizontalLayout(addClientButton, sort,
                nameb, genderb, dateb, jobb, phoneb);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void sortByPhone(String sort) {
        if(sort.equals("asc")){
            List<Client> cards = null;

            cards = clientService.sortByPhoneAsc();
            if (cards.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(cards);

        }
        else if(sort.equals("desc")){
            List<Client> cards = null;

            cards = clientService.sortByPhoneDesc();
            if (cards.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(cards);

        }
        else {
            grid.setItems(Collections.emptyList());
        }
    }

    private void sortByJob(String sort) {
        if(sort.equals("asc")){
            List<Client> cards = null;

            cards = clientService.sortByJobAsc();
            if (cards.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(cards);

        }
        else if(sort.equals("desc")){
            List<Client> cards = null;

            cards = clientService.sortByJobDesc();
            if (cards.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(cards);

        }
        else {
            grid.setItems(Collections.emptyList());
        }
    }

    private void sortByDate(String sort) {
        if(sort.equals("asc")){
            List<Client> cards = null;

            cards = clientService.sortByDateAsc();
            if (cards.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(cards);

        }
        else if(sort.equals("desc")){
            List<Client> cards = null;

            cards = clientService.sortByDateDesc();
            if (cards.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(cards);

        }
        else {
            grid.setItems(Collections.emptyList());
        }
    }

    private void sortByGender(String sort) {
        if(sort.equals("asc")){
            List<Client> cards = null;

            cards = clientService.sortByGenderAsc();
            if (cards.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(cards);

        }
        else if(sort.equals("desc")){
            List<Client> cards = null;

            cards = clientService.sortByGenderDesc();
            if (cards.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(cards);

        }
        else {
            grid.setItems(Collections.emptyList());
        }
    }

    private void sortByName(String sort){
        if(sort.equals("asc")){
            List<Client> cards = null;

            cards = clientService.sortByNameAsc();
            if (cards.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(cards);

        }
        else if(sort.equals("desc")){
            List<Client> cards = null;

            cards = clientService.sortByNameDesc();
            if (cards.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(cards);

        }
        else {
            grid.setItems(Collections.emptyList());
        }
    }


    void addClient() {
        grid.asSingleSelect().clear();
        editClient(new Client());
    }

    private void configureGrid() {
        grid.addClassName("client-grid");
        grid.setSizeFull();
        grid.setColumns("id", "fullName", "gender", "dateOfBirth","jobStatus", "phoneNumber");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editClient(event.getValue()));
    }

    public void editClient(Client contact) {
        if (contact == null) {
            closeEditor();
        } else {
            clientForm.setClient(contact);
            clientForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        clientForm.setClient(null);
        clientForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(clientService.findAll(filterByName.getValue()));
        /*grid.setItems(clientService.findAll(filterByGender.getValue()));
        grid.setItems(clientService.findAll(filterByJobStatus.getValue()));*/
    }
}
