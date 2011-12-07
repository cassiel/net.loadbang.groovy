//	$Id$
//	$Source$

package net.loadbang.groovy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.loadbang.scripting.Engine;
import net.loadbang.scripting.MaxObjectProxy;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.cycling74.max.Atom;

@RunWith(JMock.class)
public class GroovyEngineImplTest {
	private Mockery itsContext = new JUnit4Mockery();
	
	@Test
	public void canSetAndRetrieveListVariable() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, Arrays.asList(1, 2));
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.setVariable(new Atom[] { Atom.newAtom("X"), Atom.newAtom(1), Atom.newAtom(2) });
		engine.getVariable("X");
	}
	
	@Test
	public void canExecuteOneLinerToBindVariable() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);
		
		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, Arrays.asList((Object) 5, "X", 7));
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.exec("X = [5, \"X\", 7]");
		
		engine.getVariable("X");
	}
	
	@Test
	public void canEvaluateToAResult() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, Arrays.asList((Object) 5, "X", 7, "A"));
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.eval("[5, \"X\", 7] << \"A\"");
	}
	
	@Test
	public void willOutputNull() {
		//	This is actually an anti-test! We cannot differentiate None/null from
		//	undefined as far as I can tell. (To be investigated.)

		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);
		
		itsContext.checking(new Expectations() {{
			one(proxy).error("no such variable: X");
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.exec("X = null");
		
		engine.getVariable("X");
	}
	
	@Test
	public void bindsMaxObject() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);
		
		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, "hello-world");
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.exec("maxObject.outlet(0, 'hello-world')");
	}
	
	@Test
	public void convertsGroovyListForOutput() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			//	Cascaded call to the MaxObject outlet, once the list has been converted:
			one(proxy).outlet(with(any(int.class)), with(any(List.class)));
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.exec("maxObject.outlet(0, [5, 'X', 7])");
	}
	
	//	We can't really test what comes out when writing "0.0" in Groovy, since we don't
	//	know the scale of the BigDecimal. (The real problem is that our test coverage isn't
	//	complete - we want to test the Atom conversion.)
	
	@Test
	public void outputsNumericList() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);
		
		itsContext.checking(new Expectations() {{
			//	Cascaded call to the MaxObject outlet, once the list has been converted:
			one(proxy).outlet(0, Arrays.asList((Object) 1, (Integer) 1000, (Float) 0.0f));
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.exec("maxObject.outlet(0, [1, 1000, 0.0f])");
	}
	
	@Test
	public void mapsMessageToClosureCall() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, "Hello Dolly");
			//one(proxy).outlet(with(any(int.class)), with(any(String.class)));
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		//engine.exec("doit = { _, who -> maxObject.outlet(0, \"Hello ${who}\") }");	// TODO Groovy bug?
		engine.exec("doit = { _, who -> maxObject.outlet(0, 'Hello ' + who) }");
		engine.invoke("doit", 0, new Atom[] { Atom.newAtom("Dolly") });
	}
	
	@Test
	public void canInvokeFunctionWithSingleArg() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, -100);
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.exec("doit = { maxObject.outlet(0, -it) }");
		engine.invoke("doit", null, new Atom[] { Atom.newAtom(100) });
	}
	
	@Test
	public void canInvokeFunctionWithVarArgs() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, 1);
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.exec("doit = { _, ... L -> maxObject.outlet(0, Arrays.asList(L).min()) }");
		engine.invoke("doit", 0, new Atom[] { Atom.newAtom(1), Atom.newAtom(22), Atom.newAtom(3) });
	}
	
	@Test
	public void survivesInvocationOfUnknownFunction() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).error("function not found: doit");
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.invoke("doit", 0, new Atom[] { Atom.newAtom(1), Atom.newAtom(22), Atom.newAtom(3) });
	}
	
	@Test
	public void survivesInvocationOfNonFunction() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).error("not a function: doit");
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.exec("doit = 456");
		engine.invoke("doit", 0, new Atom[] { Atom.newAtom(1), Atom.newAtom(22), Atom.newAtom(3) });
	}
	
	@Test
	public void canInvokeFunctionWithMultipleArgs() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, 6);
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.exec("doit = { i, j, k -> maxObject.outlet(0, i + j + k) }");
		engine.invoke("doit", null, new Atom[] { Atom.newAtom(1), Atom.newAtom(2), Atom.newAtom(3) });
	}
	
	@Test
	public void actuallyDoesClear() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).error("no such variable: X");
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.setVariable(new Atom[] { Atom.newAtom("X"), Atom.newAtom(1), Atom.newAtom(2) });
		engine.clear();
		engine.getVariable("X");
	}
	
	@Test
	public void survivesCleanupWithNonFunction() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).error("addCleanup: not a function");
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.exec("engine.addCleanup(1234)");
		engine.exec("engine.clear()");
	}
	
	@Test
	public void callsCleanupCallback() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, 999);
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.exec("engine.addCleanup { maxObject.outlet(0, 999) }");
		engine.exec("engine.clear()");
	}
	
	@Test
	public void execCanBindVariables() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, 999);
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.exec("x = 999");
		engine.getVariable("x");
	}
	
	@Test
	public void canRunScriptGivenDirectory() throws Exception {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, 999);
		}});

		Engine engine = new GroovyEngineImpl(proxy);

		engine.runScript("test-data/script", "hello.groovy");
	}
	
	@Test
	public void canRunScriptUsingPlaceHolder() throws Exception {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, 2001);
		}});

		Engine engine = new GroovyEngineImpl(proxy);
		
		engine.setupEngineOnPlaceHolder("test-data/place");
		engine.runUsingPlaceHolder("myscript.groovy");
	}
}
