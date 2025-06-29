package dev.rohitahuja;

import com.vaadin.flow.component.page.Push;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.page.AppShellConfigurator;

@Push
@SpringBootApplication
@Theme("doc-chat")
public class InteractionsAppApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(InteractionsAppApplication.class, args);
    }
}
