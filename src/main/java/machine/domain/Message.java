package machine.domain;

import java.util.Optional;

public class Message {
    public static final Message EMPTY = new Message();

    private final String content;

    private Message(String content) {
        this.content = content;
    }

    private Message() {
        this(null);
    }

    public static Message create(String content) {
        return new Message(content);
    }

    public Optional<String> getContent() {
        return Optional.ofNullable(content);
    }
}
