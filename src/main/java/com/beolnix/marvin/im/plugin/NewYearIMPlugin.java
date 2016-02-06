package com.beolnix.marvin.im.plugin;

import com.beolnix.marvin.im.api.IMSessionManager;
import com.beolnix.marvin.im.api.model.IMIncomingMessage;
import com.beolnix.marvin.im.api.model.IMOutgoingMessage;
import com.beolnix.marvin.im.api.model.IMOutgoingMessageBuilder;
import com.beolnix.marvin.plugins.api.IMPlugin;
import com.beolnix.marvin.plugins.api.IMPluginState;
import com.beolnix.marvin.plugins.api.PluginConfig;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by DAtmakin on 11/9/2015.
 */
public class NewYearIMPlugin implements IMPlugin {

    private Logger logger;
    private final CountDownService countDownService = new CountDownService();
    private IMSessionManager imSessionManager;
    private IMPluginState state = IMPluginState.NOT_INITIALIZED;

    private static final String COMMAND_NY = "ny";
    private static final String COMMAND_HELP = "help";
    private static final List<String> commandsList = Arrays.asList(COMMAND_NY, COMMAND_HELP);

    @Override
    public void init(PluginConfig pluginConfig, IMSessionManager imSessionManager) {
        PluginUtils pluginUtils = new PluginUtils();
        this.logger = pluginUtils.getLogger(pluginConfig.getLogsDirPath(), getPluginName());
        this.imSessionManager = imSessionManager;
        this.state = IMPluginState.INITIALIZED;
    }

    @Override
    public String getPluginName() {
        if (logger != null) {
            logger.trace("getPluginName invoked");
        }
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
        } else if (COMMAND_HELP.equals(imIncomingMessage.getCommandName())) {
            imSessionManager.sendMessage(
                    createOutMsg(
                            imIncomingMessage,
                            imIncomingMessage.getCommandSymbol() + COMMAND_NY + " - сообщает сколько осталось до ближайшего нового года"
                    )
            );

        }
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
