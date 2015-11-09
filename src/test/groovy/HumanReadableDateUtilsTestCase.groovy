import com.beolnix.marvin.im.plugin.CountDownService
import com.beolnix.marvin.im.plugin.HumanReadableDateUtils
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
        assertEquals("12 минут", new HumanReadableDateUtils().getMinutes(12))
    }

    @Test
    public void testGetSeconds() {
        assertEquals("51 секунда", new HumanReadableDateUtils().getSeconds(51))
    }

    @Test
    public void testGetHours() {
        assertEquals("2 часа", new HumanReadableDateUtils().getHours(2))
    }

    @Test
    public void testGetDays() {
        assertEquals("47 дней", new HumanReadableDateUtils().getDays(47))
    }
}
