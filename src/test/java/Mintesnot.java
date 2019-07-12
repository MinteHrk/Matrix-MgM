import io.github.bonigarcia.wdm.WebDriverManager;
import javafx.scene.layout.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Mintesnot {
   WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.mgmresorts.com");
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void entertainmentFunctionality() {
        WebElement popUp = driver.findElement(By.xpath("//button[@class='msg-close']"));
        popUp.click();
        WebElement entertainmentButton = driver.findElement(By.id("nav-entertainment-2"));
        entertainmentButton.click();
        Assert.assertTrue(driver.getTitle().contains("Entertainment"));
    }

    @Test
    public void searchEntertainmentFunctionality() throws InterruptedException {

        WebElement searchEvents = driver.findElement(By.xpath("//input[@id='es-search-term']"));
        searchEvents.sendKeys("Concerts");
//
//        Thread.sleep(2000);

        WebElement cityDropDown = driver.findElement(By.xpath("//div[@id='search-location-drop']"));
        cityDropDown.click();
        WebElement lasVegas = driver.findElement(By.xpath("//li[@aria-label='Mississippi']/../li[1]"));
        lasVegas.click();
        String expectedCityName = "Las Vegas";
        Assert.assertTrue(lasVegas.getText().contains(expectedCityName), "city name failed");
        WebElement subCityAllLasVegasFilter = driver.findElement(By.xpath("//li[@aria-label='Mississippi']/../li[1]//li[@aria-label='All Las Vegas']"));
        subCityAllLasVegasFilter.click();
        WebElement durationFilterSearch = driver.findElement(By.xpath("//div[@class='duration-search ']"));
        durationFilterSearch.click();
       Assert.assertTrue(durationFilterSearch.getText().contains("View All Dates"), "date failed");
        WebElement thisWeekendSearch = driver.findElement(By.xpath("//li[@aria-label=\"This Weekend\"]"));
        thisWeekendSearch.click();
        Thread.sleep(2000);

        Assert.assertTrue(durationFilterSearch.getText().contains("This Weekend"), "this week failed");
        WebElement search = driver.findElement(By.xpath("//div[@class='search-btn']"));
        search.click();

        Assert.assertTrue(driver.getTitle().contains("Concerts"), "Concert failed");

    }
    @Test
    public void showsEntertainmentFunctionality(){
        WebElement hughJackmanShow=driver.findElement(By.xpath("//div[@ng-show='searchResult.showSearchResult']/div[2]"));
        WebElement pentatonixConcertDiscription=driver.findElement(By.xpath("//div[@ng-show='searchResult.showSearchResult']/div[2]/div[2]"));
        String availableDatesOfTheShow="Available Dates";

        Assert.assertTrue(pentatonixConcertDiscription.getText().contains(availableDatesOfTheShow), "available date failed");

        String ticketPrice="Tickets starting at";
        Assert.assertTrue(pentatonixConcertDiscription.getText().contains(ticketPrice), "Price display failed");

        String buyTicket="BUY TICKETS";
        Assert.assertTrue(pentatonixConcertDiscription.getText().contains(buyTicket), "Buy TICKET failed");

        WebElement buyTicketButton= driver.findElement(By.xpath("//a[@href='https://www.mgmresorts.com/en/booking/show-booking.html#/show-listing&showFilter=postmodern-jukebox-1oak&arrive=07/05/2019']"));
        buyTicketButton.sendKeys(Keys.ENTER);
        String bookingPage="Show Booking - MGM Resorts";
        Assert.assertTrue(driver.getTitle().contains(bookingPage), "Booking page failed");
        driver.close();

    }

}
