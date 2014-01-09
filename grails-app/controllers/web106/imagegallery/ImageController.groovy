package web106.imagegallery


import web106.site.Website

class ImageController {


    def index() {
        /*
		def activeWebsite
		
		def activeWebsiteSession = session.getAttribute('activeWebsite')
		activeWebsite = activeWebsiteSession
		Website website = Website.find {
			id == activeWebsite
		}
		
	
        //TODO refac

		String websitename = website.workGroup.name + "." + website.title + "-Images"
		print websitename


		String newmail = FileTypeConverter.replaceAT(websitename)
		print newmail
		
		//ImageService progress = new ImageService(newmail);
		List<String> imagelist = progress.getFileList();
		print imagelist;
		*/
		def model = [
		//	list:imagelist
        ]
		
		render view:"indexView", model : model
	}
}
