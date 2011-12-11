//	Very simple lap timer

startTime = System.currentTimeMillis()

lap = {
	def now = System.currentTimeMillis()
	
	maxObject.outlet(0, now - startTime)
	startTime = now
}
