package com.beolnix.marvin.im.plugin;

import com.beolnix.marvin.im.api.IMSessionManager;
import com.beolnix.marvin.im.api.model.IMIncomingMessage;
import com.beolnix.marvin.im.api.model.IMOutgoingMessage;
import com.beolnix.marvin.im.api.model.IMOutgoingMessageBuilder;
import com.beolnix.marvin.plugins.api.IMPlugin;
import com.beolnix.marvin.plugins.api.IMPluginState;
import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;

import java.util.*;

/**
 * Created by DAtmakin on 11/9/2015.
 */
public class NewYearIMPlugin implements IMPlugin {

    private final Logger logger;
    private final CountDownService countDownService = new CountDownService();
    private IMSessionManager imSessionManager;
    private IMPluginState state = IMPluginState.NOT_INITIALIZED;

    private static final String COMMAND_NY = "ny";
    private static final String COMMAND_MONEY = "money";
    private static final String COMMAND_HELP = "help";
    private static final List<String> commandsList = Arrays.asList(COMMAND_NY, COMMAND_MONEY, COMMAND_HELP);

    public NewYearIMPlugin(BundleContext bundleContext) {
        logger = new PluginUtils().getLogger(bundleContext, getPluginName());
    }

    @Override
    public void setIMSessionManager(IMSessionManager imSessionManager) {
        this.imSessionManager = imSessionManager;
        this.state = IMPluginState.INITIALIZED;
    }

    @Override
    public String getPluginName() {
        logger.trace("getPluginName invoked");
        return "newYearPlugin";
    }

    @Override
    public boolean isProcessAll() {
        return false;
    }

    @Override
    public List<String> getCommandsList() {
        return commandsList;
    }

    @Override
    public boolean isCommandSupported(String s) {
        return commandsList.contains(s);
    }

    @Override
    public void process(IMIncomingMessage imIncomingMessage) {
        if (COMMAND_NY.equals(imIncomingMessage.getCommandName())) {
            imSessionManager.sendMessage(createOutMsg(imIncomingMessage, countDownService.getNewYearCountDown()));
        } else if (COMMAND_MONEY.equals(imIncomingMessage.getCommandName())) {
            imSessionManager.sendMessage(createOutMsg(imIncomingMessage, countDownService.getMoneyCountDown()));
        } else if (COMMAND_HELP.equals(imIncomingMessage.getCommandName())) {
            imSessionManager.sendMessage(
                    createOutMsges(imIncomingMessage,
                            imIncomingMessage.getCommandSymbol() + COMMAND_NY + " - сообщает сколько осталось до ближайшего нового года",
                            imIncomingMessage.getCommandSymbol() + COMMAND_MONEY + " - сообщает сколько осталось до 20-го числа текущего месяца"
                    )
            );
        }
    }

    private IMOutgoingMessage[] createOutMsges(IMIncomingMessage msg, String... answers) {
        List<IMOutgoingMessage> result = new ArrayList<>();
        for (String answer : answers) {
            result.add(createOutMsg(msg, answer));
        }
        return (IMOutgoingMessage[]) result.toArray();
    }

    private IMOutgoingMessage createOutMsg(IMIncomingMessage msg, String answer) {
        return new IMOutgoingMessageBuilder(msg)
                .withRawMessageBody(answer)
                .withFromPlugin(getPluginName())
                .build();
    }

    @Override
    public IMPluginState getPluginState() {
        return state;
    }

    @Override
    public String getErrorDescription() {
        return null;
    }

    @Override
    public Set<String> getSupportedProtocols() {
        return Collections.emptySet();
    }

    @Override
    public boolean isProtocolSupported(String s) {
        return true;
    }

    @Override
    public boolean isAllProtocolsSupported() {
        return true;
    }
}
