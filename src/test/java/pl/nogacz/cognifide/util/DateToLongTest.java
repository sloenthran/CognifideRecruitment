package pl.nogacz.cognifide.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Dawid Nogacz on 16.06.2019
 */
public class DateToLongTest {
    private DateToLong dateToLong = new DateToLong();

    @Test
    public void convert() {
        //Given
        String dateWithYearDayAndMonth = "2019-06-16";
        String dateWithYearAndMonth = "2019-06";
        String dateWithYear = "2019";

        //When
        long longDateWithYearDayAndMonth = dateToLong.convert(dateWithYearDayAndMonth);
        long longDateWithYearAndMonth = dateToLong.convert(dateWithYearAndMonth);
        long longDateWithYear = dateToLong.convert(dateWithYear);

        //Then
        Assert.assertEquals(1560636000, longDateWithYearDayAndMonth);
        Assert.assertEquals(1559340000, longDateWithYearAndMonth);
        Assert.assertEquals(1546297200, longDateWithYear);
    }
}