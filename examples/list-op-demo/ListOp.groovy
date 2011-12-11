//	A binary list operation object which allows its operation to be
//	dynamically replaced.

tmp = []
main = []

//	This is Groovy being slightly smart: it resolves the listops.XXX classes
//	while this main script is being loaded, so
//	that it can still access them when we invoke the list() function, even
//	though the original interpreter instance is dead.
//	Let's see Python attempt something like that.

append = { operation = new listops.Append() }
intersect = { operation = new listops.Intersect() }
reverse = { operation = new listops.Reverse() }
register = { operation = new listops.Register() }

_list = { inlet, ... A ->
	if (inlet == 1) {
		tmp = A
	} else {
		main = A
		bang()
	}
}

bang = {
	//	Since we've used varargs, these values have
	//	come in as arrays, not lists.
	maxObject.outlet(0, operation.op(main.toList(), tmp.toList()))
}

//	append by default:
append()
