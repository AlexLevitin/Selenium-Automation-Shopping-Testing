import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.setProperty("webdriver.chrome.driver", "E:\\New folder\\q2\\q2\\lib\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        WebElement loginIdBox = driver.findElement(By.id("user-name"));
        WebElement passwordBox = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        WebElement passDiv = driver.findElement(By.className("login_password")); // extract password
        String passTxt = passDiv.getText();
        String password = passTxt.replace("Password for all users:", "").trim();

        WebElement usersDiv = driver.findElement(By.id("login_credentials"));  // extract usernames
        String usersTxt = usersDiv.getText();
        String[] users = usersTxt.split("\\n");
        users[0] = "wrong_user";
        for(String s : users){
        System.out.println(s);}

        loginIdBox.sendKeys(users[2]);  //locked_out
        passwordBox.sendKeys(password);
        loginBtn.click();
        WebElement errorLocked = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String errorMsgLocked = errorLocked.getText();
        System.out.println("For user: " + users[2] + "\nError is: " + errorMsgLocked + "\n\n");
        loginIdBox.clear();
        Thread.sleep(100);

        loginIdBox.sendKeys(users[0]);  //wrong_user
        loginBtn.click();
        WebElement errorWrong = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String errorMsgWrong = errorWrong.getText();
        System.out.println("For user: " + users[0] + "\nError is: " + errorMsgWrong + "\n\n");
        loginIdBox.clear();

        Thread.sleep(100);


        loginIdBox.sendKeys(users[1]);  //standard_user
        loginBtn.click();
        WebElement dropDown = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(dropDown);
        select.selectByValue("lohi");            //sort low2high
        List<WebElement> productsName = driver.findElements(By.className("inventory_item_name"));
        System.out.println("*************************************\nList of items:\n");
        for (WebElement p : productsName)
        {
            System.out.println(p.getText());
        }
        List<WebElement> products = driver.findElements(By.className("inventory_item"));
        WebElement mostExpensive = products.get(products.size()-1);
        WebElement mostExpensiveBtn = mostExpensive.findElement(By.tagName("button"));
        mostExpensiveBtn.click();

        WebElement leastExpensive = products.get(0);
        WebElement leastExpensiveBtn = leastExpensive.findElement(By.tagName("button"));
        leastExpensiveBtn.click();

        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        String cartBadgetxt = cartBadge.getText();
        System.out.println("Cart has "+cartBadgetxt+" items");

        WebElement cartBtn = driver.findElement(By.className("shopping_cart_link"));
        cartBtn.click();
        List<WebElement> cartProducts = driver.findElements(By.className("cart_item"));
        WebElement secondProduct = cartProducts.get(1);  // get second item
        WebElement removeItemBtn = secondProduct.findElement(By.tagName("button"));
        removeItemBtn.click();

        WebElement dropBtn = driver.findElement(By.id("react-burger-menu-btn"));
        dropBtn.click();
        Thread.sleep(100);
        WebElement resetBtn = driver.findElement(By.id("reset_sidebar_link"));
        resetBtn.click();
        Thread.sleep(100);
        WebElement xBtn = driver.findElement(By.id("react-burger-cross-btn"));
        xBtn.click();
        Thread.sleep(100);
        cartBtn = driver.findElement(By.className("shopping_cart_link"));
        cartBtn.click();
        List<WebElement> updatedCartProducts = driver.findElements(By.className("cart_item"));
        if(updatedCartProducts.isEmpty())
        {System.out.println("Cart has 0 items");}

        WebElement logoutBtn = driver.findElement(By.id("logout_sidebar_link"));
        logoutBtn.click();
        if(!driver.findElements(By.className("login_container")).isEmpty())
        {
            System.out.println("Back to login page");
        }



        driver.quit();
        System.out.println("Thank you and have a nice day! ");

    }
}
