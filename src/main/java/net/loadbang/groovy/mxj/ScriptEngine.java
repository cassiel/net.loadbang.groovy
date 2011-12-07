//	$Id$
//	$Source$

//	Copyright (C) 2008 Nick Rothwell, nick@cassiel.com / nick@loadbang.net

//	This program is free software: you can redistribute it and/or modify
//	it under the terms of the GNU General Public License as published by
//	the Free Software Foundation, either version 3 of the License, or
//	(at your option) any later version.
//
//	This program is distributed in the hope that it will be useful,
//	but WITHOUT ANY WARRANTY; without even the implied warranty of
//	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//	GNU General Public License for more details.
//
//	You should have received a copy of the GNU General Public License
//	along with this program.  If not, see <http://www.gnu.org/licenses/>.

package net.loadbang.groovy.mxj;

import net.loadbang.groovy.GroovyEngineImpl;
import net.loadbang.scripting.Engine;
import net.loadbang.scripting.MaxObjectProxy;
import net.loadbang.scripting.ScriptEngineBase;

import com.cycling74.max.Atom;

/**	A simple MXJ object for running Groovy scripts from source files in Max's
 	search path. Almost everything is delegated to an Engine object (to allow
 	mocking and unit testing).
 	
 	@author Nick Rothwell, nick@cassiel.com / nick@loadbang.net
 */

public class ScriptEngine extends ScriptEngineBase {
	ScriptEngine(Atom[] args) {
		super("$Id$",
			  "net.loadbang.groovy",
			  ScriptEngine.class,
			  args,
			  ".groovy"
			 );
	}

	@Override
	protected Engine buildEngine(MaxObjectProxy proxy) {
		return new GroovyEngineImpl(proxy);
	}
}
