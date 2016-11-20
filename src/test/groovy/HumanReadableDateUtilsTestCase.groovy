import com.beolnix.marvin.im.plugin.RUHumanReadableDateUtils
import org.junit.Test

import static org.junit.Assert.assertEquals;

/**
 * Author: Atmakin Danila 
 * Email: beolnix@gmail.com
 * Date: 26.12.11
 * Time: 20:45
 */

class HumanReadableDateUtilsTestCase {

    @Test
    public void testGetMinutes() {
        assertEquals("12 минут", new RUHumanReadableDateUtils().getMinutes(12))
    }

    @Test
    public void testGetSeconds() {
        assertEquals("51 секунда", new RUHumanReadableDateUtils().getSeconds(51))
    }

    @Test
    public void testGetHours() {
        assertEquals("2 часа", new RUHumanReadableDateUtils().getHours(2))
    }

    @Test
    public void testGetDays() {
        assertEquals("47 дней", new RUHumanReadableDateUtils().getDays(47))
    }
}
