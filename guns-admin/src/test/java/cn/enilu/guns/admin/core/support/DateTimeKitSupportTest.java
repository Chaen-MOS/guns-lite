package cn.enilu.guns.admin.core.support;

import cn.enilu.guns.admin.core.support.exception.ToolBoxException;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DateTimeKitSupportTest {

    @Test
    public void dateTimeFormatsParsesAndConvertsDates() {
        DateTime dateTime = DateTimeKit.parse("2020-05-06 07:08:09");

        assertEquals("2020-05-06 07:08:09", DateTimeKit.formatDateTime(dateTime));
        assertEquals("2020-05-06", DateTimeKit.formatDate(dateTime));
        assertEquals("07:08:09", DateTimeKit.format(dateTime, DateTimeKit.NORM_TIME_PATTERN));
        assertEquals("2020-05-06 07:08:09", dateTime.toString());
        assertEquals("2020/05/06", dateTime.toString("yyyy/MM/dd"));
        assertEquals("2020-05-06 07:08:09.000", dateTime.toMsStr());
        assertEquals(dateTime.getTime(), dateTime.toDate().getTime());
        assertEquals(dateTime.getTime(), DateTime.parse(dateTime).getTime());
        assertNotNull(DateTimeKit.formatHttpDate(dateTime));
        assertEquals(null, DateTimeKit.formatDateTime(null));
        assertEquals(null, DateTimeKit.formatDate(null));
        assertEquals(null, DateTimeKit.formatHttpDate(null));
    }

    @Test
    public void dateTimeCalculatesCalendarFieldsOffsetsAndRanges() {
        DateTime date = DateTimeKit.parseDate("2020-05-06");

        assertEquals(2020, DateTimeKit.year(date));
        assertEquals(5, DateTimeKit.month(date));
        assertEquals(2, DateTimeKit.season(date));
        assertEquals("20202", DateTimeKit.yearAndSeason(date));
        assertEquals("2020-05-06 00:00:00", DateTimeKit.formatDateTime(DateTimeKit.getBeginTimeOfDay(date)));
        assertEquals("2020-05-06 23:59:59", DateTimeKit.format(DateTimeKit.getEndTimeOfDay(date), DateTimeKit.NORM_DATETIME_PATTERN));
        assertEquals("2020-05-07", DateTimeKit.formatDate(DateTimeKit.offsiteDay(date, 1)));
        assertEquals("2020-05-13", DateTimeKit.formatDate(DateTimeKit.offsiteWeek(date, 1)));
        assertEquals("2020-06-06", DateTimeKit.formatDate(DateTimeKit.offsiteMonth(date, 1)));

        LinkedHashSet<String> seasons = DateTimeKit.yearAndSeasons(DateTimeKit.parseDate("2020-01-01"), DateTimeKit.parseDate("2020-07-01"));
        assertTrue(seasons.contains("20201"));
        assertTrue(seasons.contains("20202"));
        assertTrue(seasons.contains("20203"));
        assertTrue(DateTimeKit.yearAndSeasons(null, date).isEmpty());
    }

    @Test
    public void dateTimeParsesAllNormalPatternsAndRejectsInvalidValues() {
        assertEquals("2020-05-06 07:08:09", DateTimeKit.formatDateTime(DateTimeKit.parse("2020-05-06 07:08:09")));
        assertEquals("2020-05-06", DateTimeKit.formatDate(DateTimeKit.parse("2020-05-06")));
        assertEquals("07:08:09", DateTimeKit.format(DateTimeKit.parse("07:08:09"), DateTimeKit.NORM_TIME_PATTERN));
        assertEquals("2020-05-06 07:08", DateTimeKit.format(DateTimeKit.parse("2020-05-06 07:08"), DateTimeKit.NORM_DATETIME_MINUTE_PATTERN));
        assertEquals("2020-05-06 07:08:09.123", DateTimeKit.format(DateTimeKit.parse("2020-05-06 07:08:09.123"), DateTimeKit.NORM_DATETIME_MS_PATTERN));
        assertEquals(null, DateTimeKit.parse(null));
    }

    @Test(expected = ToolBoxException.class)
    public void dateTimeRejectsUnknownFormat() {
        DateTimeKit.parse("not-a-date");
    }

    @Test
    public void dateTimeCalculatesAgeDiffWeekAndTimer() {
        DateTime birthday = DateTimeKit.parseDate("2000-05-06");
        DateTime comparison = DateTimeKit.parseDate("2020-05-05");
        DateTime later = DateTimeKit.parseDate("2020-05-06");

        assertEquals(19, DateTimeKit.age(birthday, comparison));
        assertEquals(20, DateTimeKit.age(birthday, later));
        assertEquals(2, DateTimeKit.diff(birthday, DateTimeKit.offsiteDay(birthday, 2), DateTimeKit.DAY_MS));
        assertEquals(1, DateTimeKit.weekCount(DateTimeKit.parseDate("2020-05-03"), DateTimeKit.parseDate("2020-05-09")));
        assertEquals(5060000, DateTimeKit.toIntSecond(birthday));
        assertTrue(DateTimeKit.current(false) > 0);
        assertTrue(DateTimeKit.current(true) > 0);
        assertNotNull(DateTimeKit.now());
        assertNotNull(DateTimeKit.today());
        assertTrue(DateTimeKit.thisMonth() >= 1);
        assertTrue(DateTimeKit.thisYear() >= 2020);
        assertNotEquals(DateTimeKit.date().getTime(), 0L);
        assertEquals(1000L, DateTimeKit.date(1000L).getTime());

        DateTimeKit.Timer timer = DateTimeKit.timer();
        assertTrue(timer.duration() >= 0);
        assertTrue(timer.durationRestart() >= 0);
        assertTrue(timer.start() > 0);
        assertTrue(DateTimeKit.spendMs(System.currentTimeMillis()) >= 0);
        assertTrue(DateTimeKit.spendNt(System.nanoTime()) >= 0);
    }

    @Test
    public void ageCalculatesRelativeToComparisonDateBeforeBirthdayYear() {
        assertEquals(-10, DateTimeKit.age(DateTimeKit.parseDate("2030-01-01"), DateTimeKit.parseDate("2020-01-01")));
    }

    @Test
    public void toCalendarUsesProvidedDate() {
        Date date = DateTimeKit.parseDate("2020-12-31");
        Calendar calendar = DateTimeKit.toCalendar(date);

        assertEquals(2020, calendar.get(Calendar.YEAR));
        assertEquals(Calendar.DECEMBER, calendar.get(Calendar.MONTH));
    }
}
