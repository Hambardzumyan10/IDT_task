import Models.ExercisesLogics;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAuthPractic {

    @Test
    public void GetSaleProDucts() throws InterruptedException {
        ExercisesLogics logics = new ExercisesLogics();
        logics.GetAllSaleProducts();
    }
}