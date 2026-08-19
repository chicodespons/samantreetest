import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lawnpilot.Main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterTest {

    private static final String APPROVED_OUTPUT = """
            1 3 N
            5 1 E
            """;

    private final PrintStream originalOut = System.out;

    private ByteArrayOutputStream captured;


    @BeforeEach
    void captureStandardOut() {
        captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void restoreStandardOut() {
        System.setOut(originalOut);
    }


    @Test
    @DisplayName("prints the final position and orientation of every mower, in input order")
    void producesTheApprovedOutput() {

        Main.main(new String[0]);
        assertEquals(APPROVED_OUTPUT, actualOutput());
    }

    /**
     * {@code println} emits the platform line separator, so the captured text is normalised to
     * {@code \n} before comparison. Without this the test passes on Linux and macOS and fails on
     * Windows for a reason that has nothing to do with the mowers.
     */
    private String actualOutput() {
        return captured.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
    }
}
