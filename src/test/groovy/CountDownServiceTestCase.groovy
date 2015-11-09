import com.beolnix.marvin.im.plugin.CountDownService
import org.junit.Test

import java.time.LocalDateTime

import static org.junit.Assert.assertEquals;

/**
 * Created by beolnix on 11/9/2015.
 */
public class CountDownServiceTestCase {

    @Test
    public void testGetHumanReadableCountDownTo() {

        LocalDateTime fromDateTime = LocalDateTime.of(2015, 11, 2, 00, 1, 1);
        LocalDateTime toDateTime = LocalDateTime.of(2015, 12, 31, 23, 59, 59);

        assertEquals("59 дней 23 часа 58 минут и 58 секунд.", new CountDownService().getHumanReadableCountDownTo(fromDateTime, toDateTime))
    }

    @Test
    public void testGetHumanReadableCountDownTo2() {

        LocalDateTime fromDateTime = LocalDateTime.of(2015, 11, 2, 23, 2, 1);
        LocalDateTime toDateTime = LocalDateTime.of(2015, 12, 31, 23, 59, 59);

        assertEquals("59 дней 57 минут и 58 секунд.", new CountDownService().getHumanReadableCountDownTo(fromDateTime, toDateTime))
    }

    @Test
    public void testGetHumanReadableCountDownTo3() {

        LocalDateTime fromDateTime = LocalDateTime.of(2015, 12, 31, 23, 58, 56);
        LocalDateTime toDateTime = LocalDateTime.of(2015, 12, 31, 23, 59, 59);

        assertEquals("1 минута и 3 секунды.", new CountDownService().getHumanReadableCountDownTo(fromDateTime, toDateTime))
    }

    @Test
    public void testGetHumanReadableCountDownTo4() {

        LocalDateTime fromDateTime = LocalDateTime.of(2015, 12, 31, 23, 59, 56);
        LocalDateTime toDateTime = LocalDateTime.of(2015, 12, 31, 23, 59, 59);

        assertEquals("3 секунды.", new CountDownService().getHumanReadableCountDownTo(fromDateTime, toDateTime))
    }

    @Test
    public void testGetHumanReadableCountDownTo5() {

        LocalDateTime fromDateTime = LocalDateTime.of(2015, 12, 31, 23, 59, 59);
        LocalDateTime toDateTime = LocalDateTime.of(2015, 12, 31, 23, 59, 59);

        assertEquals("- нисколько не осталось.", new CountDownService().getHumanReadableCountDownTo(fromDateTime, toDateTime))
    }

    @Test
    public void testGetHumanReadableCountDownTo6() {

        LocalDateTime fromDateTime = LocalDateTime.of(2016, 1, 1, 23, 59, 59);
        LocalDateTime toDateTime = LocalDateTime.of(2015, 12, 31, 23, 59, 59);

        assertEquals("- уже прошло: 1 день с того момента.", new CountDownService().getHumanReadableCountDownTo(fromDateTime, toDateTime))
    }

    @Test
    public void testGetHumanReadableCountDownTo7() {

        LocalDateTime fromDateTime = LocalDateTime.of(2016, 1, 2, 3, 16, 45);
        LocalDateTime toDateTime = LocalDateTime.of(2015, 12, 31, 23, 59, 59);

        assertEquals("- уже прошло: 1 день 3 часа 16 минут и 46 секунд с того момента.", new CountDownService().getHumanReadableCountDownTo(fromDateTime, toDateTime))
    }
}
