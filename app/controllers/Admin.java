package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import models.Image;
import models.Post;
import models.User;
import play.Play;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Admin extends Controller {
	
	public static void index() {
		newPost();
	} 
	
	public static void newPost(){
		// get username
		String username = session.get("username");
		
		int selectedIndex = 0;// selected index
		render(username,selectedIndex);
	}
	
	public static void allPosts(){
		// find all post
		List<Post> list = Post.findAll();
		// reverse
		Collections.reverse(list);
		
		render(list);
	}
	
	public static void savePost(String title, String postEditor, String username){
		// find user
		User user = User.find("byEmail", username).first();
		
		Post post =new Post(title, new Date(), postEditor, null, user);
		// save
		post.save();
		
		allPosts();
	}
	
	public static void imageBrowseUrl(String CKEditorFuncNum){
		// find all images
		List<Image> images = Image.findAll();
		render(images,CKEditorFuncNum);
	}
	
	public static void uploadUrl(File upload){
		// copy files from tmp directory to (app_root)/public/images/upload
		String projectRoot = Play.applicationPath.getAbsolutePath();
		String imagePath = projectRoot + "\\public\\images\\upload\\" + upload.getName();
		
		// new empty file for copy
		File copyFile = new File(imagePath);
		try {
			copyFile.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// copy 
		try {
			copyFileUsingStream(upload, copyFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// stores image
		Image image = new Image(null);
		image.setUrl(imagePath);
		image.setFileName(upload.getName());
		image.save();
		
		render();
	}
	
	/**
	 * copy file using stream
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
}
