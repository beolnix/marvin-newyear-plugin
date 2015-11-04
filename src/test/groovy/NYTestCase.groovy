import org.junit.Test
import com.beolnix.marvin.im.plugin.DateUtils
import com.beolnix.marvin.im.plugin.NewYearIMPlugin

/**
 * Author: Atmakin Danila 
 * Email: datmakin@dh-lab.ru
 * Date: 26.12.11
 * Time: 20:45
 */

class NYTestCase {

    @Test
    public void testDateUtils() {
        println DateUtils.getMinutes(12)
    }

    @Test
    public void testNyDate() {
        println new NewYearIMPlugin(null).getNyAnswer()
    }

    @Test
    public void testMoneyDate() {
        println new NewYearIMPlugin(null).getMoneyAnswer()
    }
    
}
