package mk.finki.ukim.wp.balloonShop.selenium;


import mk.finki.ukim.wp.balloonShop.model.Manufacturer;
import mk.finki.ukim.wp.balloonShop.model.User;
import mk.finki.ukim.wp.balloonShop.model.enums.TYPE;
import mk.finki.ukim.wp.balloonShop.service.BalloonService;
import mk.finki.ukim.wp.balloonShop.service.ManufacturerService;
import mk.finki.ukim.wp.balloonShop.service.OrderService;
import mk.finki.ukim.wp.balloonShop.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    BalloonService balloonService;

    @Autowired
    OrderService orderService;

    private HtmlUnitDriver driver;

    private static TYPE t1;
    private static TYPE t2;
    private static Manufacturer m1;
    private static Manufacturer m2;
    private static User regularUser;
    private static User adminUser;

    private static String user = "user";
    private static String admin = "admin";

    private static boolean dataInitialized = false;


    @BeforeEach
    public void setup(){
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy(){
        if (this.driver != null){
            this.driver.close();
        }
    }

    public void initData(){
        if (!dataInitialized){
            t1 = TYPE.OVAL;
            t2 = TYPE.HEART;

            m1 = manufacturerService.save("m1","m1","m1").get();
            m2 = manufacturerService.save("m2", "m2", "m2").get();

            regularUser = userService.register(user, user, user, user, user, user);
            adminUser = userService.register(admin, admin, admin, admin, admin, admin);
            dataInitialized = true;
        }
    }


    @Test
    public void testScenario() throws Exception {
      BalloonsPage balloonsPage = BalloonsPage.to(this.driver);
      balloonsPage.assertElements(0, 0, 0, 0);
      //LoginPage loginPage = LoginPage.openLogin(this.driver);

      // balloonsPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), admin);
      // balloonsPage.assertElements(0, 0, 0, 1);

      // balloonsPage = AddOrEditBalloon.addProduct(this.driver, "test", "test Balloon", m2.getName(), t1.name());
      // balloonsPage.assertElements(1, 1, 1, 1);

      // balloonsPage = AddOrEditBalloon.addProduct(this.driver, "test1", "test1 Balloon",  m2.getName(), t1.name());
      // balloonsPage.assertElements(2, 2, 2, 1);

      // balloonsPage.getDeleteButtons().get(1).click();
      // balloonsPage.assertElements(1, 1, 1, 1);

      // balloonsPage = AddOrEditBalloon.editProduct(this.driver, balloonsPage.getEditButtons().get(0), "test1", "test1 Balloon",  m2.getName(), t1.name());
      // balloonsPage.assertElements(1, 1, 1, 1);

      // loginPage = LoginPage.logout(this.driver);
      // balloonsPage = LoginPage.doLogin(this.driver, loginPage, regularUser.getUsername(), user);
      // balloonsPage.assertElements(1, 0, 0, 0);

    }





}
