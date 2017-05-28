package application.ui;

/**
 * Created by troy on 5/26/17.
 */

import application.clients.WxDailySummaryClient;
import application.models.WxDailySummary;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@SpringUI(path = "wx")
@Title("DailySummary")
@Theme("valo")
public class WxDailySummaryUI extends UI {

    TextField filter = new TextField();
    Grid contactList = new Grid();
    Button newContact = new Button("New daily summary");

    // ContactService is a in-memory mock DAO that mimics
    // a real-world datasource. Typically implemented for
    // example as EJB or Spring Data based service.
    @Autowired
    WxDailySummaryClient wxDailySummaryClient;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        /** Synchronous event handling.
         *
         * Receive user interaction events on the server-side. This allows you
         * to synchronously handle those events. Vaadin automatically sends
         * only the needed changes to the web page without loading a new page.
         */
//        newContact.addClickListener(e -> contactForm.edit(new User()));

        filter.setInputPrompt("Filter contacts...");
        filter.addTextChangeListener(e -> refreshContacts(e.getText()));

        contactList.setContainerDataSource(new BeanItemContainer<>(WxDailySummary.class));
        contactList.setColumnOrder("date", "prcp", "tmax", "tmin");
//        contactList.removeColumn("birthDate");
        contactList.setSelectionMode(Grid.SelectionMode.SINGLE);
//        contactList.addSelectionListener(e
//                -> contactForm.edit((User) contactList.getSelectedRow()));
        refreshContacts();
    }

    /**
     * Robust layouts.
     * <p>
     * Layouts are components that contain other components.
     * HorizontalLayout contains TextField and Button. It is wrapped
     * with a Grid into VerticalLayout for the left side of the screen.
     * Allow user to resize the components with a SplitPanel.
     * <p>
     * In addition to programmatically building layout in Java,
     * you may also choose to setup layout declaratively
     * with Vaadin Designer, CSS and HTML.
     */
    private void buildLayout() {
        HorizontalLayout actions = new HorizontalLayout(filter, newContact);
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);

        VerticalLayout left = new VerticalLayout(actions, contactList);
        left.setSizeFull();
        contactList.setSizeFull();
        left.setExpandRatio(contactList, 1);


        HorizontalLayout mainLayout = new HorizontalLayout(left);
//        HorizontalLayout mainLayout = new HorizontalLayout(left, contactForm);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);

        // Split and allow resizing
        setContent(mainLayout);
    }

    /**
     * Choose the design patterns you like.
     * <p>
     * It is good practice to have separate data access methods that
     * handle the back-end access and/or the user interface updates.
     * You can further split your code into classes to easier maintenance.
     * With Vaadin you can follow MVC, MVP or any other design pattern
     * you choose.
     */
    void refreshContacts() {
        refreshContacts(filter.getValue());
    }

    private void refreshContacts(String stringFilter) {

        Date lastYear = new Date();
        lastYear.setYear(lastYear.getYear() - 1);

//        contactList.setContainerDataSource(new BeanItemContainer<>(
//                WxDailySummary.class, wxDailySummaryClient.findAll().getContent()));
        contactList.setContainerDataSource(new BeanItemContainer<>(
                WxDailySummary.class, Collections.singleton(wxDailySummaryClient.findByDate(lastYear))));
//        contactForm.setVisible(false);
    }
}
