package dev.rohitahuja.views;

import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dev.rohitahuja.tools.ToolsService;
import org.vaadin.firitin.components.messagelist.MarkdownMessage;

@PageTitle("Tool Calling")
@Menu(order = 1)
@Route(value = "tools", layout = MainLayout.class)
public class ToolsView extends VerticalLayout {

    public ToolsView(ToolsService toolsService) {
        var messageList = new VerticalLayout();
        var scroller = new Scroller(messageList);
        var messageInput = new MessageInput();
        messageInput.setWidthFull();
        scroller.setWidthFull();
        messageList.setWidthFull();

        messageInput.addSubmitListener(e -> {
            var userMessage = e.getValue();
            messageList.add(new MarkdownMessage(userMessage, "You"));
            var assistantMessage = new MarkdownMessage("", "Assistant");
            messageList.add(assistantMessage);

            toolsService
                    .callTool(userMessage)
                    .subscribe(assistantMessage::appendMarkdownAsync);
        });

        addAndExpand(scroller);
        add(messageInput);
    }
}
