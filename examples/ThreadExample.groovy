//	Simple thread example.

t = new Thread()

running = true

start = {
	t.start {
		while (running) {
			println("Hello World")
			Thread.sleep(2000)
		}
	}
}

//	Add a closure which stops the thread. This is triggered
//	when the MXJ instance is deleted, or when the binding
//	environment is cleared.

engine.addCleanup {
	println("Cleaning up")
	stop()
}

stop = {
	running = false
}
