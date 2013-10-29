package de.sixfourpixel.web106.site

class Menu {
	
	String title

	static hasMany = [pages : Page] 
	

	static constraints = {
	        title blank: false
		
	}
}
