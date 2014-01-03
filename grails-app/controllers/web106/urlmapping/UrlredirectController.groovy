package web106.urlmapping

import web106.auth.WorkGroup
import web106.site.Website

class UrlredirectController {

    def index() { }
	
	def mapping() {
		def workgroupid
		def websitename
		
		workgroupid = params.wgid
		websitename = params.website
		
		if (websitename.contains("www.")){
			websitename = websitename.replace("www.", '')
		}
		
		if (websitename.contains(".de")){
			websitename = websitename.replace(".de", '')
		}
		
		if (websitename.contains(".com")){
			websitename = websitename.replace(".com", '')
		}
		
		def aworkGroup = WorkGroup.find(){
            id == workgroupid
        }
		
		def website = Website.find(){
			workGroup == aworkGroup;
			title == websitename
			
		}
		if (website != null){
			redirect(uri: website.websiteurl)
		}
		
	}

    def live() {

    }
	
}
