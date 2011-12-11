//	A simple demo of script switching. (There are obviously neater
//	ways to achieve this.)

println "[max]"

tmp = 0
main = 0

_int = { inlet, x ->
	if (inlet == 1) {
		tmp = x
	} else {
		main = x
		bang()
	}
}

bang = {
	maxObject.outlet(0, Math.max(main, tmp))
}
