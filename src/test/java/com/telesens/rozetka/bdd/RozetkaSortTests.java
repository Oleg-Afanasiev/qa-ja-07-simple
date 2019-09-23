package com.telesens.rozetka.bdd;

import io.cucumber.testng.CucumberFeatureWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleEventWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

@CucumberOptions(
        features = {"src/main/resources/features"},
        glue = "com.telesens.rozetka.bdd.step",
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"}
)
public class RozetkaSortTests {
    private TestNGCucumberRunner runner;

    @BeforeClass
    public void setUpClass() throws IOException {
        runner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "bdd", description = "Runs Cucumber Feature",  dataProvider = "featureProvider")
    public void testProductSorting(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        runner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider
    public Object[][] featureProvider() {
        return runner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        runner.finish();
    }
}
