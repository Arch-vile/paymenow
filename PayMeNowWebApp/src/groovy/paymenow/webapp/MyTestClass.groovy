package paymenow.webapp

import java.security.SecureRandom

class MyTestClass {

	def say2() {
		print "Say something"
	}
	
	def calculate(){
		
		for(i in 1..10){
			def random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			random.nextBytes(new byte[512]);
			print i + ": "
			println random.nextInt(100)
		}
		
	}
	
	
}
