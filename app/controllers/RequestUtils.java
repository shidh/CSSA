package controllers;


import models.Image;
import models.Post;
import models.User;
import play.mvc.Controller;

public class RequestUtils extends Controller
{
	public static void renderImage(Long imageId){
		Image image = Image.findById(imageId);
		response.setContentTypeIfNotSet(image.imageDate.type());
		renderBinary(image.imageDate.get());
	}
	
}
