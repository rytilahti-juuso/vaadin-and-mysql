package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
public class VaadinUI extends UI {

    @Autowired
    private CustomerService service;

    private Customer customer;
    private Binder<Customer> binder = new Binder<>(Customer.class);

    private Grid<Customer> grid = new Grid(Customer.class);
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private Button save = new Button("Save", e -> saveCustomer());
//    omaa shaissee
    private Button add =  new Button("add", e -> setAddFormVisible(true));
    private TextField firstNameAdd = new TextField("First name");
    private TextField lastNameAdd = new TextField("Last name");
    private Button saveNewCustomer = new Button("save");
    @Override
    protected void init(VaadinRequest request) {
        updateGrid();
        grid.setColumns("firstName", "lastName");
        grid.addSelectionListener(e -> updateForm());

        binder.bindInstanceFields(this);

        VerticalLayout layout = new VerticalLayout(add, grid, firstName, lastName, save);
        VerticalLayout addForm = new VerticalLayout(firstNameAdd, lastNameAdd, saveNewCustomer);
        HorizontalLayout main = new HorizontalLayout(layout, addForm);
        setContent(main);
    }

    private void updateGrid() {
        List<Customer> customers = service.findAll();
        grid.setItems(customers);
        setEditFormVisible(false);
        setAddFormVisible(false);
    }

    private void updateForm() {
        if (grid.asSingleSelect().isEmpty()) {
            setEditFormVisible(false);
        } else {
            customer = grid.asSingleSelect().getValue();
            binder.setBean(customer);
            setEditFormVisible(true);
        }
    }

    private void setEditFormVisible(boolean visible) {
        firstName.setVisible(visible);
        lastName.setVisible(visible);
        save.setVisible(visible);
    }
    private void setAddFormVisible(boolean visible) {
        firstNameAdd.setVisible(visible);
        lastNameAdd.setVisible(visible);
        saveNewCustomer.setVisible(visible);
    }

    private void saveCustomer() {
        service.update(customer);
        updateGrid();
    }}
