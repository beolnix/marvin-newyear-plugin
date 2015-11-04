package com.beolnix.marvin.im.plugin

/**
 * Author: Atmakin Danila 
 * Email: beolnix@gmail.com
 * Date: 26.12.11
 * Time: 21:04
 */

class DateUtils {

    public static String getSecundes(Integer seconds) {
        return getSmartEnd(seconds, 'секунда', 'секунды', 'секунд')
    }

    public static String getMinutes(Integer minutes) {
        return getSmartEnd(minutes, 'минута', 'минуты', 'минут')
    }

    public static String getHours(Integer hours) {
        return getSmartEnd(hours, 'час', 'часа', 'часов')
    }

    public static String getDays(Integer days) {
        return getSmartEnd(days, 'день', 'дня', 'дней')
    }

    private static String getSmartEnd(Integer number, String end1, String end2, String end3) {
        if (number <= 0)
            return ''
        String end = ''
        if (number > 4 && number < 21)
            end = end3
        else {
            String numberStr = number.toString()
            String endOfNumberStr = numberStr[-1..-1]
            Integer endOfnumber = Integer.parseInt(endOfNumberStr)
            switch (endOfnumber) {
                case 1:
                    end = end1
                    break
                case 2:
                case 3:
                case 4:
                    end = end2
                    break
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 0:
                    end = end3
                    break

                default:
                    break
            }
        }
        return " ${number} ${end}"
    }
}
