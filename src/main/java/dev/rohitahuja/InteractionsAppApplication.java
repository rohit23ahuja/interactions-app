package dev.rohitahuja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.page.AppShellConfigurator;

@SpringBootApplication
public class InteractionsAppApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(InteractionsAppApplication.class, args);
    }
}
