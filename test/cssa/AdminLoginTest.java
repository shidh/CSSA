package cssa;

import org.junit.Test;

import play.mvc.Before;
import play.test.Fixtures;
import play.test.UnitTest;

public class AdminLoginTest extends UnitTest {
	@Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

	@Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }
}
