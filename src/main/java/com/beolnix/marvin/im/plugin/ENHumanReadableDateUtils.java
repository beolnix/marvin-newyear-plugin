package com.beolnix.marvin.im.plugin;

/**
 * Created by DAtmakin on 11/9/2015.
 */
public class ENHumanReadableDateUtils {
    public String getSeconds(long seconds) {
        return getSmartEnd(seconds, "second", "seconds", "seconds");
    }

    public String getMinutes(long minutes) {
        return getSmartEnd(minutes, "minute", "minutes", "minutes");
    }

    public String getHours(long hours) {
        return getSmartEnd(hours, "hour", "hours", "hours");
    }

    public String getDays(long days) {
        return getSmartEnd(days, "day", "days", "days");
    }

    private String getSmartEnd(long number, String end1, String end2, String end3) {
        if (number <= 0)
            return "";
        String end = "";
        if (number > 4 && number < 21)
            end = end3;
        else {
            String numberStr = number + "";
            String endOfNumberStr = new String(new char[]{numberStr.charAt(numberStr.length() - 1)});
            Integer endOfnumber = Integer.parseInt(endOfNumberStr);
            switch (endOfnumber) {
                case 1:
                    end = end1;
                    break;
                case 2:
                case 3:
                case 4:
                    end = end2;
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 0:
                    end = end3;
                    break;

                default:
                    break;
            }
        }
        return number + " " + end;
    }
}
