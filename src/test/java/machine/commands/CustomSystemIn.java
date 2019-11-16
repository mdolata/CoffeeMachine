package machine.commands;

import java.io.IOException;
import java.io.InputStream;

public class CustomSystemIn extends InputStream {

    private int[] bytes = new int[] {49, 48};
    private int position = 0;

    @Override
    public int read() throws IOException {
        if (position >= bytes.length) {
            position = 0;
            return 10;
        }
        return bytes[position++] & 0xff;
    }

    public void setResponses(int[] responses) {
        this.bytes = responses;
    }
}
