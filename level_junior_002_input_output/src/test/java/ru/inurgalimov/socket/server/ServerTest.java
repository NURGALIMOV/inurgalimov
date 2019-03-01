package ru.inurgalimov.socket.server;

        import org.junit.Test;

        import java.io.IOException;

        import static org.junit.Assert.*;

public class ServerTest {
    @Test
    public void serverStart() {
        try {
            String[] args = {"\\", "5000"};
            Server.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}