package dev.rohitahuja.views;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Welcome")
@Route(value = "", layout = MainLayout.class)
public class WelcomeView extends VerticalLayout {

    public WelcomeView() {
        add(
                new H1("About Interactions App"),
                new Html("""
                        <div>
                            <p>
                                The idea behind Interactions App is to provide a single interface or platform that allows you to interact with your Application using natural language.
                            </p>
                            <p>
                                The App aims to solves various use cases such as: chat with your knowledge base, Chat with your database, execute your business logic, etc.
                            </p>
                        </div>
                        """)
        );
    }
}
