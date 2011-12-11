# net.loadbang.groovy

This package which supports the Groovy scripting/programming language
within MXJ for [MaxMSP][max].

Groovy is an agile, dynamic language for the Java Virtual Machine
(JVM) which builds upon Java by providing features such as closures
and support for domain-specific programming (such as XML processing
and database access). It integrates seamlessly with Java and is very
similar in syntax.

For more information on Groovy, see the [Codehaus][codehaus] page.

The prebuilt JAR files are in the sub-directory `distribution`, or the
library can be built from the enclosed sources using Maven. (For the
Maven build, clone and build [net.loadbang.scripting][scripting]
first, since our libraries are not yet in a central repository.)

See the README for [net.loadbang.lib][lib] for installation details.

## Documentation

The JavaDocs can be generated from Maven by

	mvn javadoc:javadoc

The documentation is written to `target/site/apidocs`.

## License

Distributed under the [GNU General Public License][gpl].

Copyright (C) 2011 Nick Rothwell.

[max]: http://cycling74.com/products/max/
[codehaus]: http://groovy.codehaus.org
[scripting]: https://github.com/cassiel/net.loadbang.scripting
[lib]: https://github.com/cassiel/net.loadbang.lib
[gpl]: http://www.gnu.org/copyleft/gpl.html
