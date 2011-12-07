//	$Id$
//	$Source$

package net.loadbang.groovy;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;
import groovy.lang.MissingPropertyException;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import net.loadbang.scripting.EngineImpl;
import net.loadbang.scripting.MaxObjectProxy;
import net.loadbang.scripting.util.Converters;
import net.loadbang.scripting.util.Manifest;
import net.loadbang.scripting.util.exn.DataException;

import com.cycling74.max.Atom;

/**	The actual implementation of the Groovy Script Engine for MXJ, abstracted against a proxy
	for MaxObject.
 	
 	@author Nick Rothwell, nick@cassiel.com / nick@loadbang.net
 */

public class GroovyEngineImpl extends EngineImpl {
	private Stack<Closure> itsCleanupCallbacks00 = null;
	private Converters itsConverters;
	private GroovyScriptEngine itsGroovyEngine00 = null;
	private Binding itsBinding;
	
	//	We always create a shell, regardless of whether we have the setup for a file-reading
	//	engine or not. (We recreate it if/when we change binding.)
	//	TODO: this means that an "exec" into an instance with a place-holder does not
	//	benefit from the place-holder's contribution to the search path. This is irritating,
	//	but minor: if you want the search path, you might as well put the 'exec' string into
	//	a file and run it (and/or call a function).

	private GroovyShell itsShell;

	public GroovyEngineImpl(MaxObjectProxy proxy) {
		super(proxy);
		itsConverters = new Converters();
		clear();
	}
	
	@Override
	protected Converters getConverters() {
		return itsConverters;
	}
	
	public void clear() {
		unwindCallbacks();

		itsBinding = new Binding();	//	Clear env for this engine.
		
		itsBinding.setVariable(Manifest.Vars.MAX_OBJECT, getProxy());
		itsBinding.setVariable(Manifest.Vars.ENGINE, this);
		
		//	We create a new shell on this environment. (We don't need to do this
		//	for the place-holder engine because it's always called with the
		//	binding explicitly. TODO: test that!)
		
		itsShell = new GroovyShell(itsBinding);
	}
	
	/**	Cleanup machinery, called from Groovy. Add a cleanup closure. */
	
	public void addCleanup(Object obj) {
		if (obj instanceof Closure) {
			if (itsCleanupCallbacks00 == null) {
				itsCleanupCallbacks00 = new Stack<Closure>();
			}
	
			itsCleanupCallbacks00.push((Closure) obj);
		} else {
			getProxy().error("addCleanup: not a function");
		}
	}
	
	/**	Unwind all callbacks, in reverse order. */

	@Override
	public void unwindCallbacks() {
		if (itsCleanupCallbacks00 != null) {
			while (!itsCleanupCallbacks00.empty()) {
				itsCleanupCallbacks00.pop().call();
			}
		}
	}
	
	/**	Run the script. We create a new GroovyScriptEngine
	 	for the script, rooted on the script's directory, which means that package access
	 	will work in the same way as for place-holders. runScript() prints errors
	 	via the proxy; it doesn't signal.
	  */
	
	public void runScript(String directory, String filename) {
		MaxObjectProxy proxy = getProxy();

		try {
			new GroovyScriptEngine(directory).run(filename, itsBinding);
		} catch (ResourceException exn) {
			proxy.error("resource exception: " + exn);
			exn.printStackTrace();
		} catch (ScriptException exn) {
			proxy.error("script exception: " + exn);
			exn.printStackTrace();
		} catch (IOException exn) {
			proxy.error("I/O error: " + exn);
		}
	}
	
	/**	Set a value in the binding environment. */

	@Override
	public void setVar(String id, Object args) {
		itsBinding.setVariable(id, args);
	}
	
	/**	Get and output a value from the binding environment. */

	@Override
	protected Object getVar00(String id) {
		try {
			return itsBinding.getVariable(id);
		} catch (MissingPropertyException _) {
			return null;
		}
	}
	
	@Override
	public void setupEngineOnPlaceHolder(String directory) throws IOException {
		itsGroovyEngine00 = new GroovyScriptEngine(directory);
	}

	/**	Run a script using an interpreter rooted on a place-holder directory.

 		@see net.loadbang.scripting.Engine#runUsingPlaceHolder(java.lang.String)
	 */

	public void runUsingPlaceHolder(String name) {
		if (itsGroovyEngine00 != null) {
			try {
				itsGroovyEngine00.run(name, itsBinding);
			} catch (ScriptException exn) {
				getProxy().error("script exception: " + exn);
			} catch (ResourceException exn) {
				getProxy().error("resource exception: " + exn);
			}
		} else {
			getProxy().error("engine not loaded: place-holder problem?");
		}
	}

	/** Execute a Groovy expression.

		@param groovyStatement the statement to execute
	 */

	@Override
	public void exec(String groovyStatement) {
		itsShell.evaluate(groovyStatement);
	}
	
	/** Evaluate a Groovy expression, outputting the result to the leftmost outlet.

		@param groovyStatement the statement to execute
	 */

	@Override
	public void eval(String groovyStatement) {
		getProxy().outlet(0, itsShell.evaluate(groovyStatement));
	}
	
	/**	Invoke a named function with arguments. The first argument is the inlet number, or null
		for single-inlet wrappers. */

	@Override
	public void invoke(String fn, Integer inlet00, Atom[] args) {
		try {
			Object val = itsBinding.getVariable(fn);
			
			if (val instanceof Closure) {
				try {
					Closure cl = (Closure) val;
					Atom[] objArray = args;
					
					if (inlet00 != null) {
						objArray = prepend(Atom.newAtom(inlet00), args);
					}
					
					List<Object> objList = getConverters().atomsToObjects(objArray, 0);
					cl.call(objList.toArray());
				} catch (DataException e) {
					getProxy().error("cannot convert from Atoms: " + e);
				}
			} else {
				getProxy().error("not a function: " + fn);
			}
		} catch (MissingPropertyException _) {
			getProxy().error("function not found: " + fn);
		}
	}
}
