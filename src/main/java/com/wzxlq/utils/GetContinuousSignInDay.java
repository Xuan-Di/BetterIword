package com.wzxlq.utils;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 李倩
 */
public class GetContinuousSignInDay {
    public static int getContinuousSignInDay(List<LocalDate> signInList) {
        //continuousDay 连续签到数
        int continuousDay = 1;
        boolean todaySignIn = false;
        LocalDate today = LocalDate.now();
        for (int i = 0; i < signInList.size(); i++) {
            LocalDate signIn = signInList.get(i);
            int intervalDay = distanceDay(today, signIn);
            //当天已签到
            if (intervalDay == 0 && i == 0) {
                todaySignIn = true;
            } else if (intervalDay == continuousDay) {
                continuousDay++;
            } else {
                //不连续，终止判断
                break;
            }
        }
        if (!todaySignIn) {
            continuousDay--;
        }
        return continuousDay;
    }

    public static int distanceDay(LocalDate today, LocalDate signIn) {
        int distanceDay = (int) (today.toEpochDay() - signIn.toEpochDay());
        return distanceDay;
    }
}
