import com.beolnix.marvin.im.plugin.NewYearIMPlugin
import org.junit.Test
import org.osgi.framework.BundleContext

/**
 * Created by beolnix on 10/11/15.
 */
class NewYearIMPluginTestCase {

    @Test
    public void test() {
        def bundleContext = [] as BundleContext
        NewYearIMPlugin plugin = new NewYearIMPlugin(bundleContext)

        plugin.createOutMsges
    }
}
