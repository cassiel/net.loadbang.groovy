package net.loadbang.groovy;

import static org.junit.Assert.assertEquals;
import java.util.ResourceBundle;
import org.junit.Test;

public class BundleTest {
	@Test
	public void getBundleItem() {
		ResourceBundle bundle = ResourceBundle.getBundle("net.loadbang.groovy.props.TestProps");
		
		assertEquals("HelloWorld", bundle.getObject("hello-world"));
	}
}
