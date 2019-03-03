package com.spotahome.rtest.front.pageObjects;

import org.apache.log4j.Logger;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/**
 * Created by jiglesias on 14/02/17.
 */
public class RoomSummaryPageObject extends BasePageObject {

    private static Logger log = Logger.getLogger(RoomSummaryPageObject.class);

    @FindBy(xpath = "//a[contains(@class,'button--book-now')]")
    private WebElement bookNowButton;

    @FindBy(className = "city-selector--date__from")
    private WebElement dateFromInput;

    @FindBy(className = "city-selector--date__to")
    private WebElement dateToInput;

    @FindBy(xpath = "//select[@class='pika-select pika-select-year']")
    private WebElement selectYearInElement;

    @FindBy(xpath = "//select[@class='pika-select pika-select-month']")
    private WebElement selectMonthInElement;

    @FindBy(className = "pika-table")
    private WebElement tableDaysInElement;

    @FindBy(xpath = "//div[@id='js-booknow-card']/div/div[2]/div[contains(@class,'body-dates')]/div[2]//select[@class='pika-select pika-select-year']")
    private WebElement selectYearOutElement;

    @FindBy(xpath = "//div[@id='js-booknow-card']/div/div[2]/div[contains(@class,'body-dates')]/div[2]//select[@class='pika-select pika-select-month']")
    private WebElement selectMonthOutElement;

    @FindBy(xpath = "//div[@id='js-booknow-card']/div/div[2]/div[contains(@class,'body-dates')]/div[2]//table")
    private WebElement tableDaysOutElement;

    @FindBy(xpath = "(//div[@class='availability-calendar__year'])[1]")
    private WebElement availabilityCurrentYear;

    @FindBy(xpath = "(//div[@class='availability-calendar__year'])[2]")
    private WebElement availabilityNextYear;

    @FindBy(xpath = "//div[@class='apartment-info--item']/div[contains(@class,'room--availability')]")
    private WebElement roomAvailableFrom;

    @FindBy(xpath = "(//div[@class='room--minmaxstay']/div[@class='apartment-info--item'])[1]")
    private WebElement minimunStay;

    public void clickOnBookNowButton() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start clickOnBookNowButton method");

        bookNowButton.click();

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End clickOnBookNowButton method");
    }

    public void isOnRoomSummaryPage() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start isOnRoomSummaryPage method");

        assertTrue("ERROR: Date in selector is not displayed", dateFromInput.isDisplayed());
        assertTrue("ERROR: Date out selector is not displayed", dateToInput.isDisplayed());
        assertTrue("ERROR: Book now button is not displayed", bookNowButton.isDisplayed());

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End isOnRoomSummaryPage method");
    }

    public void chooseRandomsDateInAndDateOutAvailables() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start chooseRandomsDateInAndDateOutAvailables method");

        openPikalendarDateInHomePage();

        selectYearMonthAndDayInCalendar(getRoomAvailableLocalDate(), selectYearInElement, selectMonthInElement, tableDaysInElement);

        LocalDate randomDateOut = getRoomAvailableLocalDate().plusDays(ThreadLocalRandom.current().nextInt(getMinimunStayInDays(), getMinimunStayInDays()+7));
        selectYearMonthAndDayInCalendar(randomDateOut, selectYearOutElement, selectMonthOutElement, tableDaysOutElement);

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End chooseRandomsDateInAndDateOutAvailables method");
    }

    private void openPikalendarDateInHomePage() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start openPikalendarDateInHomePage method");

        dateFromInput.click();

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End openPikalendarDateInHomePage method");
    }

    private LocalDate getRoomAvailableLocalDate() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start getRoomAvailableLocalDate method");

        LocalDate localDate;
        String roomAvailableFromText = roomAvailableFrom.getText();
        if(roomAvailableFromText.contains("Available now!")) {
            localDate = new LocalDate();
        } else {
            String[] splitedText = roomAvailableFromText.split(" ");
            String auxDay = splitedText[2];
            auxDay = auxDay.replace(",", "");

            Date date = null;
            try {
                date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(splitedText[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int mes = cal.get(Calendar.MONTH)+1;
            DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MM-yyyy");
            localDate = dtf.parseLocalDate(auxDay+"-"+String.valueOf(mes)+"-"+splitedText[3]);
        }

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End getRoomAvailableLocalDate method");
        return localDate;
    }

    private int getMinimunStayInDays() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - Start getMinimunStayInDays method");

        String minimunStayCad = minimunStay.getText();
        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(minimunStayCad);
        String days = "";
        while(m.find()) {
            days = m.group(1);
        }
        String[] daysSplited = days.split(" ");

        log.info("[log-PageObjects] " + this.getClass().getSimpleName() + " - End getMinimunStayInDays method");
        return Integer.valueOf(daysSplited[0]);
    }
}
