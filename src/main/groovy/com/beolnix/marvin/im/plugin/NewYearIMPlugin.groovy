package com.beolnix.marvin.im.plugin

import com.beolnix.marvin.im.api.IMSessionManager
import com.beolnix.marvin.im.api.model.IMIncomingMessage
import com.beolnix.marvin.im.api.model.IMOutgoingMessage
import com.beolnix.marvin.im.api.model.IMOutgoingMessageBuilder
import com.beolnix.marvin.plugins.api.IMPlugin
import com.beolnix.marvin.plugins.api.IMPluginState
import org.apache.log4j.Logger
import org.osgi.framework.BundleContext

/**
 * Author: beolnix
 * Email: beolnix@gmail.com
 * Date: 11/14/11
 * Time: 11:05 PM
 */
class NewYearIMPlugin implements IMPlugin {

    private final Logger logger;
    private IMSessionManager imSessionManager

    private IMPluginState state = IMPluginState.NOT_INITIALIZED
    private String errMsg
    private PluginUtils

    public static final String COMMAND_NY = 'ny'
    public static final String COMMAND_MONEY = 'money'
    public static final String COMMAND_HELP = 'help'

    private Calendar ny

    public NewYearIMPlugin(BundleContext bundleContext) {
        logger = new PluginUtils().getLogger(bundleContext, getPluginName())
    }

    @Override
    public String getPluginName() {
        if (logger != null)
            logger.info('getPluginName invoked')
        return 'newYearPlugin'
    }

    @Override
    public List<String> getCommandsList() {
        return [COMMAND_NY, COMMAND_MONEY, COMMAND_HELP]
    }

    @Override
    boolean isCommandSupported(String s) {
        return getCommandsList().contains(s)
    }

    @Override
    IMPluginState getPluginState() {
        return state
    }

    @Override
    public boolean isProcessAll() {
        return false
    }

    private void sendAnwer(IMIncomingMessage msg, String answer) {
        IMOutgoingMessage outMsg = new IMOutgoingMessageBuilder(msg)
                .withRecipient(msg.getAuthor())
                .withRawMessageBody(answer)
                .withFromPlugin(getPluginName())
                .build();
        imSessionManager.sendMessage(outMsg)
    }

    @Override
    public void process(IMIncomingMessage msg) {
        try {
            if (msg.getCommandName().equals(COMMAND_NY)) {
                sendAnwer(msg, getNyAnswer())
            } else if (msg.commandName.equals(COMMAND_MONEY)) {
                sendAnwer(msg, getMoneyAnswer())
            } else if (msg.getCommandName().equals(COMMAND_HELP)) {
                sendAnwer(msg, msg.commandSymbol + "$COMMAND_NY - сообщает сколько осталось до ближайшего нового года")
                sendAnwer(msg, msg.commandSymbol + "$COMMAND_MONEY - сообщает сколько осталось до 20-го числа текущего месяца")
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e)
        }
    }

    public String getMoneyAnswer() {
        Calendar moneyDate = Calendar.getInstance();
        moneyDate.set(Calendar.DAY_OF_MONTH, 5)
        moneyDate.set(Calendar.HOUR_OF_DAY, 19)
        moneyDate.set(Calendar.MINUTE, 0)
        moneyDate.set(Calendar.SECOND, 0)

        Answer answer = getAnswer(moneyDate)
        String and = ''
        if (answer.days > 0 || answer.hours - 1 > 0 || answer.minutes > 0)
            and = ' и'
        return "До обещанной зарплаты${answer.daysStr}${answer.hoursStr}" +
                "${answer.minutesStr}${and}${answer.secundesStr}"
    }

    public String getNyAnswer() {
        Calendar nyDate = Calendar.getInstance();
        nyDate.set(Calendar.MONTH, Calendar.DECEMBER)
        nyDate.set(Calendar.DAY_OF_MONTH, 31)
        nyDate.set(Calendar.HOUR_OF_DAY, 24)
        nyDate.set(Calendar.MINUTE, 0)
        nyDate.set(Calendar.SECOND, 0)
        Answer answer = getAnswer(nyDate)

        String and = ''
        if (answer.days > 0 || (answer.hours - 1) > 0 || answer.minutes > 0)
            and = ' и'
        return "До Нового ${nyDate.get(Calendar.YEAR)} года осталось${answer.daysStr}${answer.hoursStr}" +
                "${answer.minutesStr}${and}${answer.secundesStr}"

    }

    public Answer getAnswer(Calendar date) {
        Calendar pr = Calendar.getInstance();

        Date datePr = pr.getTime()
        Date dateNy = date.getTime()

        use(groovy.time.TimeCategory) {
            def diff = dateNy - datePr
            Answer answer = new Answer()
            answer.days = diff.days
            answer.hours = diff.hours
            answer.minutes = diff.minutes
            answer.secundes = diff.seconds
            return answer
        }
    }

    @Override
    void setIMSessionManager(IMSessionManager imSessionManager) {
        this.imSessionManager = imSessionManager
        this.state = IMPluginState.INITIALIZED
    }

    @Override
    String getErrorDescription() {
        return errMsg
    }

    class Answer {
        Integer days = 0
        Integer hours = 0
        Integer minutes = 0
        Integer secundes = 0

        public String getDaysStr() {
            return DateUtils.getDays(days)
        }

        public String getHoursStr() {
            return DateUtils.getHours(hours)
        }

        public String getMinutesStr() {
            return DateUtils.getMinutes(minutes)
        }

        public String getSecundesStr() {
            DateUtils.getSecundes(secundes)
        }
    }

    @Override
    public Set<String> getSupportedProtocols() {
        return Collections.emptySet()
    }

    @Override
    public boolean isProtocolSupported(String protocol) {
        return true
    }

    @Override
    public boolean isAllProtocolsSupported() {
        return true
    }
}
