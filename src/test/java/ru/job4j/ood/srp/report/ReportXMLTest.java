package ru.job4j.ood.srp.report;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReportXMLTest {

    @Test
    public void generate() {
        MemStore store = new MemStore();
        Calendar now = new GregorianCalendar(2022, Calendar.JANUARY, 18);
        Employee worker = new Employee("Ivan", null, null, 100);
        store.add(worker);
        Report report = new ReportXML(store);
        String ln = "\n";
        String expected =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + ln
                + "<employees>"
                + ln
                + "    <employees>"
                + ln
                + "        <name>Ivan</name>"
                + ln
                + "        <salary>100.0</salary>"
                + ln
                + "    </employees>"
                + ln
                + "</employees>"
                + ln;
        String actual = report.generate(employee -> true);
        Assert.assertEquals(expected, actual);
    }

}