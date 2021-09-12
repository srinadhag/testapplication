package testapplication;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty" }, glue = { "testapplication" }, tags = "@testSampleApplication", features = {
		"src/test/resources/features" })
public class RunCucumberTest {

}
