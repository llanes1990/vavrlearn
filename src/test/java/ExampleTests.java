import io.vavr.*;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

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

    @Test
    public void givenBadCode_whenThrowsException_thenCorrect() {
        assertThrows(ArithmeticException.class, () -> {
            int a = 1 / 0;
        });
    }

    @Test
    public void givenBadCode_whenTryHandles_thenCorrect() {
        Try<Integer> result = Try.of(() -> 1 / 0);

        assertTrue(result.isFailure());
    }

    @Test
    public void givenBadCode_whenTryHandles_thenCorrect2() {
        Try<Integer> result = Try.of(() -> 1 / 0);
        int errorSentinel = result.getOrElse(-1);

        assertEquals(-1, errorSentinel);
    }

    @Test
    public void givenBadCode_whenTryHandles_thenCorrect3() {
        Try<Integer> result = Try.of(() -> 1 / 0);

        assertThrows(ArithmeticException.class, () -> {
            //result.getOrElseThrow(ArithmeticException::new);
            result.getOrElseThrow(() -> new ArithmeticException());
        });
    }

    @Test
    public void givenJava8Function_whenWorks_thenCorrect() {
        Function<Integer, Integer> square = (num) -> num * num;
        int result = square.apply(2);

        assertEquals(4, result);
    }

    @Test
    public void givenJava8BiFunction_whenWorks_thenCorrect() {
        BiFunction<Integer, Integer, Integer> sum =
                (num1, num2) -> num1 + num2;
        int result = sum.apply(5, 7);

        assertEquals(12, result);
    }

    @Test
    public void givenVavrFunction_whenWorks_thenCorrect() {
        Function1<Integer, Integer> square = (num) -> num * num;
        int result = square.apply(2);

        assertEquals(4, result);
    }

    @Test
    public void givenVavrBiFunction_whenWorks_thenCorrect() {
        Function2<Integer, Integer, Integer> sum =
                (num1, num2) -> num1 + num2;
        int result = sum.apply(5, 7);

        assertEquals(12, result);
    }

    @Test
    public void whenCreatesFunction_thenCorrect0() {
        Function0<String> getClazzName = () -> this.getClass().getName();
        String clazzName = getClazzName.apply();

        assertEquals("com.baeldung.vavr.VavrTest", clazzName);
    }

    @Test
    public void whenCreatesFunction_thenCorrect5() {
        Function5<String, String, String, String, String, String> concat =
                (a, b, c, d, e) -> a + b + c + d + e;
        String finalString = concat.apply(
                "Hello ", "world", "! ", "Learn ", "Vavr");

        assertEquals("Hello world! Learn Vavr", finalString);
    }

    public int sum(int a, int b) {
        return a + b;
    }

    @Test
    public void whenCreatesFunctionFromMethodRef_thenCorrect() {
        Function2<Integer, Integer, Integer> sum = Function2.of(this::sum);
        int summed = sum.apply(5, 6);

        assertEquals(11, summed);
    }
}
