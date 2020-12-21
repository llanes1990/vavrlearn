import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleTests {
    @Test
    public void givenValue_whenNullCheckNeeded_thenCorrect() {
        Object possibleNullObj = null;
        if (possibleNullObj == null) {
            possibleNullObj = "someDefaultValue";
        }
        assertNotNull(possibleNullObj);
    }

    @Test
    public void givenValue_whenNullCheckNeeded_thenCorrect2() {
        Object possibleNullObj = null;
        assertThrows(NullPointerException.class, () -> {
            possibleNullObj.toString();
        });
    }

    @Test
    public void givenValue_whenCreatesOption_thenCorrect() {
        Option<Object> noneOption = Option.of(null);
        Option<Object> someOption = Option.of("val");

        assertEquals(Option.none(), noneOption);
        assertEquals("None", noneOption.toString());
        assertEquals("DefaultVal", noneOption.getOrElse("DefaultVal"));

        assertEquals(Option.some("val"), someOption);
        assertEquals("val", someOption.get());
        assertEquals("Some(val)", someOption.toString());
    }

    @Test
    public void whenCreatesTuple_thenCorrect1() {
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        String element1 = java8._1;
        int element2 = java8._2();

        assertEquals("Java", element1);
        assertEquals(8, element2);
    }

    @Test
    public void whenCreatesTuple_thenCorrect2() {
        Tuple3<String, Integer, Double> java8 = Tuple.of("Java", 8, 1.8);
        String element1 = java8._1;
        int element2 = java8._2();
        double element3 = java8._3();

        assertEquals("Java", element1);
        assertEquals(8, element2);
        assertEquals(1.8, element3, 0.1);
    }
}
