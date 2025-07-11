package dev.rohitahuja.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;


public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE);

        addToNavbar(false, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Interactions");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.Vertical.MEDIUM,
                LumoUtility.Margin.Horizontal.MEDIUM);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();
        nav.addClassNames(LumoUtility.Margin.SMALL, LumoUtility.Margin.Top.NONE);

        nav.addItemAtIndex(0, new SideNavItem("Overview", OverviewView.class, LineAwesomeIcon.ATOM_SOLID.create()));
        nav.addItemAtIndex(1, new SideNavItem("Document Chat", DocsChatView.class, LineAwesomeIcon.COMMENTS_SOLID.create()));
        nav.addItemAtIndex(2, new SideNavItem("Document Ingestion", DocumentIngestionView.class, LineAwesomeIcon.FILE_ALT_SOLID.create()));
        nav.addItemAtIndex(3, new SideNavItem("Database Chat", DatabaseChatView.class, LineAwesomeIcon.DATABASE_SOLID.create()));
        nav.addItemAtIndex(4, new SideNavItem("Data Visualisation", ChartView.class, LineAwesomeIcon.CHART_BAR_SOLID.create()));
        nav.addItemAtIndex(5, new SideNavItem("Tools", ToolsView.class, LineAwesomeIcon.CODE_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
