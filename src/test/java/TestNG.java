import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class TestNG {
    WebDriver driver;

    @BeforeMethod
    public void login() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://railwayb1.somee.com/Page/HomePage.cshtml");

    }
    @AfterTest
    public void close() throws InterruptedException{
        Thread.sleep(1000);
        driver.quit();
    }

    @Test
    //User can log into Railway with valid username and password
    public void tc01() {
        driver.findElement(By.xpath("//a[@href='/Account/Login.cshtml']")).click();
        WebElement email = driver.findElement(By.id("username"));
        email.sendKeys("trankhanhduyen304@gmail.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("naunau1234");
        WebElement button = driver.findElement(By.xpath("//input[@type='submit']"));
        button.click();
        driver.quit();
    }

    //User can't login with blank "Username" textbox
    @Test
    public void tc02() {
        driver.findElement(By.xpath("//a[@href='/Account/Login.cshtml']")).click();
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("naunau1234");
        WebElement button = driver.findElement(By.xpath("//input[@type='submit']"));
        button.click();
        driver.quit();
    }

    //User cannot log into Railway with invalid password
    @Test
    public void tc03() {
        driver.findElement(By.xpath("//a[@href='/Account/Login.cshtml']")).click();
        WebElement email = driver.findElement(By.id("username"));
        email.sendKeys("trankhanhduyen304@gmail.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("naunau1234uu");
        WebElement button = driver.findElement(By.xpath("//input[@type='submit']"));
        button.click();
        driver.quit();
    }

    //Login page displays when un-logged User clicks on "Book ticket" tab
    @Test
    public void tc04() {
        driver.get("http://railwayb1.somee.com/Page/HomePage.cshtml");
        driver.findElement(By.xpath("//a[@href='/Page/BookTicketPage.cshtml']")).click();

    }

    //System shows message when user enters wrong password several times
    @Test
    public void tc05() {
        driver.findElement(By.xpath("//a[@href='/Account/Login.cshtml']")).click();
        for (int i = 0; i < 5; i++) {
            WebElement email = driver.findElement(By.id("username"));
            email.sendKeys("trankhanhduyen304@gmail.com1");
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("11111111111111111dddddddddddd");
            WebElement button = driver.findElement(By.xpath("//input[@type='submit']"));
            button.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                driver.quit();
            }

        }
    }
    //Additional pages display once user logged in
    @Test
    public void tc06(){
        WebElement ticket = driver.findElement(By.xpath("//a[@href='/Page/ManageTicket.cshtml']"));
        ticket.click();

        //Kiểm tra xem đã vào đúng trang ticket chưa
        boolean headerpost = driver.findElement(By.xpath("//h1[normalize-space()='Manage ticket']")).isDisplayed();
        if (headerpost == true) {
            System.out.println("Đã đến trang My ticket");
        } else {
            System.out.println("Không đến trang My ticket");
        }
        // Click "Chane password"
        WebElement change_password = driver.findElement(By.xpath("//span[normalize-space()='Change password']"));
        change_password.click();
        boolean headerpost2 = driver.findElement(By.xpath("//h1[normalize-space()='Change password']")).isDisplayed();
        if (headerpost2 == true) {
            System.out.println("Đã đến trang change password");
        } else {
            System.out.println("Không đến trang change password");
            driver.quit();
        }
    }
    //User can create new account
    @Test
    public void tc07(){
        driver.findElement(By.xpath("//span[normalize-space()='Register']")).click();
        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("tranductai123@gmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("taitai12345");
        WebElement repassword = driver.findElement(By.xpath("//input[@id='confirmPassword']"));
        repassword.sendKeys("taitai12345");
        WebElement passwordnumber = driver.findElement(By.xpath("//input[@id='pid']"));
        passwordnumber.sendKeys("taitai12345");
        ((JavascriptExecutor) driver).executeScript ("arguments[0].scrollIntoView();", passwordnumber);
        WebElement register = driver.findElement(By.xpath("//input[@title='Register']"));
        register.click();
        driver.quit();
    }
    //User can't login with an account hasn't been activated
    @Test
    public void tc08(){
        driver.findElement(By.xpath("//a[@href='/Account/Login.cshtml']")).click();
        WebElement email = driver.findElement(By.id("username"));
        email.sendKeys("trankhanhduyen30422@gmail.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("naun123");
        //Click Login button
        WebElement button = driver.findElement(By.xpath("//input[@type='submit']"));
        button.click();
        driver.quit();
    }
    //User can't create account with "Confirm password" is not the same with "Password"
    @Test
    public void tc09(){
        driver.findElement(By.xpath("//span[normalize-space()='Register']")).click();
        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("trankhanhduyen@gmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("naunau123");
        WebElement repassword = driver.findElement(By.xpath("//input[@id='confirmPassword']"));
        repassword.sendKeys("naunau12345");
        WebElement passwordnumber = driver.findElement(By.xpath("//input[@id='pid']"));
        passwordnumber.sendKeys("naunau123");
        ((JavascriptExecutor) driver).executeScript ("arguments[0].scrollIntoView();", passwordnumber);
        WebElement register = driver.findElement(By.xpath("//input[@title='Register']"));
        driver.quit();
    }
    //User can't create account while password and PID fields are empty
    @Test
    public void tc010(){
        driver.findElement(By.xpath("//span[normalize-space()='Register']")).click();
        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("trankhanhduyen78@gmail.com");

        ((JavascriptExecutor) driver).executeScript ("arguments[0].scrollIntoView();", email);
        WebElement register = driver.findElement(By.xpath("//input[@title='Register']"));
        register.click();
        driver.quit();
    }
    //User can book 1 ticket at a time
    @Test
    public void tc011(){
        driver.findElement(By.xpath("//a[@href='/Account/Login.cshtml']")).click();
        WebElement bookticket = driver.findElement(By.xpath("//span[normalize-space()='Book ticket']"));
        bookticket.click();
        WebElement bookticketform = driver.findElement(By.xpath("//legend[normalize-space()='Book ticket form']"));
        ((JavascriptExecutor) driver).executeScript ("arguments[0].scrollIntoView();", bookticketform);
        //handle dropdown tĩnh
        //lấy element của thẻ depart date

        WebElement element = driver.findElement(By.xpath("//select[@name='Date']"));
        Select depart_date = new Select(element);
        depart_date.selectByVisibleText("12/11/2023");

        WebElement element2 = driver.findElement(By.xpath("//select[@name='DepartStation']"));
        Select depart_from1 = new Select(element2);
        depart_from1.selectByVisibleText("Quãng Ngãi");


        WebElement element3 = driver.findElement(By.xpath("//select[@name='ArriveStation']"));
        Select arrive_at = new Select(element3);
        arrive_at.selectByVisibleText("Nha Trang");

        WebElement element4 = driver.findElement(By.xpath("//select[@name='SeatType']"));
        Select seat_type = new Select(element4);
        seat_type.selectByIndex(2);

        WebElement element5 = driver.findElement(By.xpath("//select[@name='TicketAmount']"));
        Select ticket_amount = new Select(element5);
        ticket_amount.selectByVisibleText("1");

        WebElement button1 = driver.findElement(By.xpath("//input[@value='Book ticket']"));
        driver.quit();
    }
    //User can change password
    @Test
    public void tc012(){
        driver.findElement(By.xpath("//a[@href='/Account/Login.cshtml']")).click();
        WebElement email = driver.findElement(By.id("username"));
        email.sendKeys("tranductai123@gmail.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("taitai12345");
        driver.findElement(By.xpath("//a[@href='/Account/Login.cshtml']")).click();
        driver.findElement(By.xpath("//a[@href='/Account/ChangePassword.cshtml']")).click();
        WebElement cpassword = driver.findElement(By.xpath("//input[@id='currentPassword']"));
        cpassword.sendKeys("naunau1234");
        WebElement newpass = driver.findElement(By.xpath("//input[@id='newPassword']"));
        newpass.sendKeys("naunau12345");
        WebElement confirmpass = driver.findElement(By.xpath("//input[@id='confirmPassword']"));
        confirmpass.sendKeys("naunau12345");
        WebElement changepass = driver.findElement(By.xpath("//input[@title='Change password']"));
        changepass.click();
        driver.quit();
    }
    //User can cancel a ticket
    @Test
    public void tc013(){
        driver.findElement(By.xpath("//a[@href='/Account/Login.cshtml']")).click();
        WebElement email = driver.findElement(By.id("username"));
        email.sendKeys("trankhanhduyen304@gmail.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("naunau1234");
        //Click Login button
        WebElement button = driver.findElement(By.xpath("//input[@type='submit']"));
        button.click();

        WebElement book_ticket = driver.findElement(By.xpath("//span[normalize-space()='My ticket']"));
        book_ticket.click();

        WebElement manage_ticket = driver.findElement(By.xpath("//h1[normalize-space()='Manage ticket']"));
        manage_ticket.click();
        ((JavascriptExecutor) driver).executeScript ("arguments[0].scrollIntoView();",manage_ticket);

        WebElement deleted_ticket = driver.findElement(By.xpath("//body[1]/div[1]/div[2]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr[4]/td[11]/input[1]"));
        deleted_ticket.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.quit();
    }
    //User can open "Book ticket" page by clicking on "Book ticket" link in "Train timetable" page
    @Test
    public void tc014() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='/Account/Login.cshtml']")).click();
        WebElement email = driver.findElement(By.id("username"));
        email.sendKeys("trankhanhduyen304@gmail.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("naunau1234");
        //Click Login button
        WebElement button = driver.findElement(By.xpath("//input[@type='submit']"));
        button.click();

        WebElement timetable = driver.findElement(By.xpath("//a[@href='TrainTimeListPage.cshtml']"));
        timetable.click();


        WebElement train_timetable = driver.findElement(By.xpath("//h1[normalize-space()='Train Timetable']"));
        ((JavascriptExecutor) driver).executeScript ("arguments[0].scrollIntoView();",train_timetable);

        boolean Check_price = driver.findElement(By.xpath("a[href='TicketPricePage.cshtml?id1=1&id2=3']")).isDisplayed();
        Thread.sleep(2000);
        if (Check_price  == true) {
            driver.findElement(By.xpath("a[href='TicketPricePage.cshtml?id1=4&id2=6']")).click();
            System.out.println("Đã click Check_price ");
        } else {
            System.out.println("Không tìm thấy Check_price ");

        }

    }
}
