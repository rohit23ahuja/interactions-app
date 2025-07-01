package dev.rohitahuja.views;

import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dev.rohitahuja.mcp.McpService;
import org.vaadin.firitin.components.messagelist.MarkdownMessage;

@PageTitle("MCP")
@Menu(order = 0)
@Route(value = "mcp", layout = MainLayout.class)
public class McpView extends VerticalLayout {

    public McpView(McpService mcpService) {
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

            mcpService
                    .mcp(userMessage)
                    .subscribe(assistantMessage::appendMarkdownAsync);
        });

        addAndExpand(scroller);
        add(messageInput);
    }
}
