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

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.function.Supplier;

@SpringUI(path = "wx")
@Title("DailySummary")
@Theme("valo")
public class WxDailySummaryUI extends UI {
    TextField filter = new TextField();
    Button newDailySummary = new Button("New daily summary");
    // ContactService is a in-memory mock DAO that mimics
    // a real-world datasource. Typically implemented for
    // example as EJB or Spring Data based service.
    @Autowired
    WxDailySummaryClient wxDailySummaryClient;

    private Grid dailySummaryList;
    private Button showLastYear;

    private Grid getDailySummaryList() {
        if (dailySummaryList == null) {
            if (true) {
                dailySummaryList = new Grid() {
                    {
                        setContainerDataSource(new MyBeanItemContainer());
                        setColumnOrder("date", "precip", "maxTemperature", "minTemperature");
//        dailySummaryList.removeColumn("birthDate");
                        setSelectionMode(Grid.SelectionMode.SINGLE);
                    }
                };
            } else {
                dailySummaryList = new Grid();
                //        dailySummaryList.setContainerDataSource(new BeanItemContainer<>(WxDailySummary.class));
                dailySummaryList.setContainerDataSource(new MyBeanItemContainer());
                dailySummaryList.setColumnOrder("date", "prcp", "tmax", "tmin");
//        dailySummaryList.removeColumn("birthDate");
                dailySummaryList.setSelectionMode(Grid.SelectionMode.SINGLE);

            }

        }
        return dailySummaryList;
    }

    private Button getShowLastYear() {
        if (showLastYear == null) {
            showLastYear = new Button("Show last year");
//            showLastYear.addClickListener(e -> refreshContacts(() -> wxDailySummaryClient.findAll().getContent()));
            showLastYear.addClickListener(e -> refreshContacts(() -> {
                Date lastYear = new Date();
                lastYear.setYear(lastYear.getYear() - 1);
                return Collections.singleton(wxDailySummaryClient.findByDate(lastYear));
            }));
        }
        return showLastYear;
    }

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
//        newDailySummary.addClickListener(e -> contactForm.edit(new User()));

        filter.setInputPrompt("Filter contacts...");
        filter.addTextChangeListener(e -> refreshContacts(e.getText()));
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
        HorizontalLayout actions = new HorizontalLayout(filter, newDailySummary, getShowLastYear());
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);

        VerticalLayout left = new VerticalLayout(actions, getDailySummaryList());
        left.setSizeFull();
        getDailySummaryList().setSizeFull();
        left.setExpandRatio(getDailySummaryList(), 1);


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

    void refreshContacts(Supplier<Collection<WxDailySummary>> summaryAccessor) {
        getDailySummaryList().setContainerDataSource(new BeanItemContainer<>(WxDailySummary.class, summaryAccessor.get()));
    }

    private void refreshContacts(String stringFilter) {

        Date lastYear = new Date();
        lastYear.setYear(lastYear.getYear() - 1);

        getDailySummaryList().setContainerDataSource(new BeanItemContainer<>(
                WxDailySummary.class, wxDailySummaryClient.findAll().getContent()));
//        dailySummaryList.setContainerDataSource(new BeanItemContainer<>(
        //               WxDailySummary.class, Collections.singleton(wxDailySummaryClient.findByDate(lastYear))));
//        contactForm.setVisible(false);
    }

    private static class MyBeanItemContainer extends BeanItemContainer<WxDailySummary> {
        public MyBeanItemContainer() {
            super(WxDailySummary.class);
        }
    }
}
