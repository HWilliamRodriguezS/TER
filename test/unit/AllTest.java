package unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({JugadorTest.class,
				TableroTest.class,
				TurnoTest.class, 
				CoordenadaTest.class})
public class AllTest {

}