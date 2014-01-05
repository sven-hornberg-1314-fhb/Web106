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

    /**
     * e.g. http://localhost:8080/Web106/live/testexportbucket-webseite/index.html
     *      Path: /live/testexportbucket-website/index.html
     */
    def live() {

        def workgroupAndWebsite
        def workgroupName
        def websiteName
        def pageName = params.website

        workgroupAndWebsite = params.workgroup

        if(workgroupAndWebsite==null || workgroupAndWebsite==''){
            //error no website for live view
        }

        if(pageName == null || pageName == ''){
            //error no page for live view
        }

        workgroupName = workgroupAndWebsite.substring(0, workgroupAndWebsite.indexOf('-'))

        websiteName =  workgroupAndWebsite.substring(workgroupAndWebsite.indexOf('-')+1)

        def url = 'https://s3-eu-west-1.amazonaws.com/'+workgroupName+'-'+websiteName+'/'+pageName

        redirect(url: url)

    }
	
}
