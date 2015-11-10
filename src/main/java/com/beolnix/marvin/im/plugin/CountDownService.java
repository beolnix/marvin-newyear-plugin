package com.beolnix.marvin.im.plugin;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by beolnix on 11/9/2015.
 */
public class CountDownService {
    private final static int SECS_IN_MINUTE_FACTOR = 60;
    private final static int SECS_IN_HOUR_FACTOR = SECS_IN_MINUTE_FACTOR * 60;
    private final static int SECS_IN_DAY_FACTOR = SECS_IN_HOUR_FACTOR * 24;

    private final static int SALARY_DAY_IN_MONTH = 5;

    private HumanReadableDateUtils hrDateUtils = new HumanReadableDateUtils();


    public String getNewYearCountDown() {
        int year = LocalDateTime.now().getYear();

        LocalDateTime fromDateTime = LocalDateTime.now();
        LocalDateTime toDateTime = LocalDateTime.of(year, 12, 31, 23, 59, 59);

        return "до нового года осталось " + getHumanReadableCountDownTo(fromDateTime, toDateTime);
    }

    public String getMoneyCountDown() {
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonth().getValue();

        LocalDateTime fromDateTime = LocalDateTime.now();

        if (fromDateTime.getDayOfMonth() > SALARY_DAY_IN_MONTH) {
            month += 1;
        }

        LocalDateTime toDate = LocalDateTime.of(year, month, 5, 19, 0, 1);

        return "до зарплаты осталось " + getHumanReadableCountDownTo(fromDateTime, toDate);
    }

    private String getHumanReadableCountDownTo(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        Duration duration = Duration.between(fromDateTime, toDateTime);
        long secondsTotal = duration.getSeconds();

        long days = howManyDaysIn(secondsTotal);
        long seconds_without_days = reduceSecondsOn(days, SECS_IN_DAY_FACTOR, secondsTotal);

        long hours = howManyHoursIn(seconds_without_days);
        long seconds_without_hours = reduceSecondsOn(hours, SECS_IN_HOUR_FACTOR, seconds_without_days);

        long minutes = howManyMinutesIn(seconds_without_hours);
        long seconds_without_minutes = reduceSecondsOn(minutes, SECS_IN_MINUTE_FACTOR, seconds_without_hours);

        long seconds = seconds_without_minutes;

        return prepareStringAnswer(days, hours, minutes, seconds);
    }

    private String prepareStringAnswer(long days, long hours, long minutes, long seconds) {
        LinkedList<String> answersList = new LinkedList<>();
        StringBuilder resultBuilder = new StringBuilder();
        int pastFactor = 1;
        String ending = ".";
        if ((days + hours + minutes + seconds) < 0) {
            resultBuilder.append("- уже прошло: ");
            ending = " с того момента.";
            pastFactor *= -1;
        }

        if (days != 0) {
            answersList.add(hrDateUtils.getDays(days * pastFactor));
        }

        if (hours != 0) {
            answersList.add(hrDateUtils.getHours(hours * pastFactor));
        }

        if (minutes != 0) {
            answersList.add(hrDateUtils.getMinutes(minutes * pastFactor));
        }

        if (seconds != 0) {
            answersList.add(hrDateUtils.getSeconds(seconds * pastFactor));
        }

        if (answersList.size() == 0) {
            resultBuilder.append("- нисколько не осталось");
        } else if (answersList.size() == 1) {
            resultBuilder.append(answersList.get(0));
        } else {
            int numberOfLastMember = answersList.size();
            int currentMember = 1;
            for (String result : answersList) {
                if (currentMember == numberOfLastMember) {
                    resultBuilder
                            .append(" и ")
                            .append(result);
                } else if (currentMember == 1) {
                    resultBuilder.append(result);
                } else {
                    resultBuilder
                            .append(" ")
                            .append(result);
                }
                currentMember += 1;
            }
        }

        resultBuilder.append(ending);
        return resultBuilder.toString();

    }

    private long howManyDaysIn(long seconds) {
        return seconds / (SECS_IN_DAY_FACTOR);
    }

    private long howManyHoursIn(long seconds) {
        return seconds / (SECS_IN_HOUR_FACTOR);
    }

    private long howManyMinutesIn(long seconds) {
        return seconds / (SECS_IN_MINUTE_FACTOR);
    }

    private long reduceSecondsOn(long amount, int factor, long seconds) {
        return seconds - (amount * factor);
    }
}
