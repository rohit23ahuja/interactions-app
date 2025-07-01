package dev.rohitahuja.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Welcome")
@Route(value = "", layout = MainLayout.class)
public class WelcomeView extends VerticalLayout {

    public WelcomeView() {
        setWidthFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setPadding(true);
        setSpacing(true);

        H1 title = new H1("Welcome to Interactions App");
        Paragraph intro = new Paragraph(
                "The idea behind Interactions App is to provide a single interface or platform that allows you to interact with your Application using natural language. " +
                        "The App aims to solves various use cases such as: chat with your knowledge base, Chat with your database, execute your business logic, etc."
        );

        // The image path is relative to the public resources folder
        Image image = new Image("images/Scaled3x.png", "Interactions App");

        image.setMaxWidth("900px");
        image.setWidthFull();
        image.getStyle().set("margin-top", "2em");

        add(title, intro, image);

    }
}
