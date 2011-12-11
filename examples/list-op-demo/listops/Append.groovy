package listops

class Append {
	def op(L1, L2) {
		def L = L1.clone()	//	Let's not side-effect!
		L.addAll(L2)
		L
	}
}
