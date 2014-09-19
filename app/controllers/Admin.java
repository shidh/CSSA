package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import models.Event;
import models.Image;
import models.Post;
import models.PostContent;
import models.User;
import play.Play;
import play.mvc.Controller;
import play.mvc.With;

import com.google.gson.Gson;

@With(SecureCssa.class)
public class Admin extends Controller {
	
	/**
	 * The main page of admin
	 */
	public static void index() {
		newPost(null);
	} 
	
	/**
	 * Statistics page
	 */
	public static void statistics(){
		// selected
		int selectedIndex = 4;
		
		render(selectedIndex);
	}
	
	/**
	 * Broadcast email page
	 */
	public static void broadcastEmail(){
		// selected
		int selectedIndex = 5;
		
		// find all user
		List<User> list = User.findAll();
		
		render(selectedIndex, list);
	}
	
	/**
	 * Setting page
	 */
	public static void setting(){
		// selected
		int selectedIndex = 6;
		
		render(selectedIndex);
	}
	
	/**
	 * Help page
	 */
	public static void help(){
		// selected
		int selectedIndex = 8;
		
		render(selectedIndex);
	}
	
	/**
	 * The main page
	 */
	public static void main(){
		// selected
		int selectedIndex = 7;
		
		render(selectedIndex);
	}
	
	/**
	 * Management of joining an event
	 * @param eventId - The event id
	 */
	public static void joinEvent(Long eventId){
		// selected
		int selectedIndex = 3;
		
		// find event
		Event event = Event.find("byId", eventId).first();
		
		render(selectedIndex, event);
	}
	
	/**
	 * Delete event 
	 * @param eventId - Event id
	 */
	public static void deleteEvent(Long eventId){
		// find
		Event event = Event.find("byId", eventId).first();
		
		// delete
		event.delete();
		
		// redirect to all event page
		events();
	}
	
	/**
	 * Show all events
	 */
	public static void events(){
		// selected index
		int selectedIndex = 3;
		
		// find all events
		List<Event> list = Event.findAll();
		
		render(selectedIndex, list);
	}
	
	/**
	 * Update a user
	 * @param userId - The user id
	 */
	public static void updateUser(String userId){
		// find
		User user = User.find("byId", Long.parseLong(userId)).first();
		
		// selected index
		int selectedIndex = 2;
		
		// render
		render(user, selectedIndex);
	}
	
	/**
	 * Delete user
	 * @param userId - The id to be delete
	 */
	public static void deleteUser(String userId){
		// find
		User user = User.find("byId", Long.parseLong(userId)).first();
		
		// delete
		user.delete();
		
		// redirect to all post 
		users();
	}
	
	/**
	 * Shows all users
	 */
	public static void users(){
		// selected index
		int selectedIndex = 2;
		
		// get all users
		List<User> list = User.findAll();
		
		// reverse
		Collections.reverse(list);
		
		render(list, selectedIndex);
	}
	
	/**
	 * New a post
	 * @param postId - The post id. If set it should update, if not insert a new post.
	 */
	public static void newPost(String postId){
		// get username
		String username = session.get("username");
		
		// selected index
		int selectedIndex = 0;
		
		// get system path separator
		String separator = System.getProperty("file.separator");
		
		// if post id --> update post
		if(postId != null){
			// find
			Post post = Post.find("byId", Long.parseLong(postId)).first();
			
			// get content
			String content = post.getPostContent();
			
			// get title
			String title = post.getTitle();
			
			// render
			render(postId, post, username, selectedIndex, separator);
			
			return;
		}
		
		render(username, selectedIndex, separator);
	}
	
	/**
	 * Show all posts
	 */
	public static void allPosts(){
		// find all post
		List<Post> list = Post.findAll();
		// reverse
		Collections.reverse(list);
		
		// selected index
		int selectedIndex = 1;
		
		render(list, selectedIndex);
	}
	
	public static void updatePost(String postId){
		// redirect to new post
		newPost(postId);
	}
	
	/**
	 * Delete a post
	 * @param postId - The id of the post
	 */
	public static void deletePost(String postId){
		// find
		Post post = Post.find("byId", Long.parseLong(postId)).first();
		
		// delete
		post.delete();
		
		// redirect to all post 
		allPosts();
	}
	
	/**
	 * Save a post
	 * @param title - The title of the post
	 * @param postEditor - The identifier of the ckeditor
	 * @param username - The username
	 * @param imgUrls - The urls of image in this post
	 */
	public static void savePost(
			String postType,
			String postId,
			String title, 
			String postEditor, 
			String username, 
			String imgUrls,
			int capacity,
			String location,
			String time){
		
		// find user
		User user = User.find("byEmail", username).first();
		
		// post content
		PostContent postContent = null;
		
		// handle image urls
		if(imgUrls != null){
			// JSON
			Gson gson = new Gson();
			
			// Deserialization
			String[] strArray = gson.fromJson(imgUrls, String[].class);
			
			// image list
			ArrayList<Image> imageList = new ArrayList<Image>();
			
			// root path
			String projectRoot = Play.applicationPath.getAbsolutePath();
			
			for(int i = 0; i < strArray.length; i++){
				// find and push
				Image img = (Image) Image.find("byUrl", projectRoot + strArray[i]).first();
				
				// push
				imageList.add(img);
			}
			
			// post content
			postContent = new PostContent();
			postContent.setPictures(imageList);
			
			// save 
			postContent.save();
			
		}
		
		if(postType.equals("news")){ // news
			// if post id not null --> update
			if(postId != null && !postId.trim().equals("")){
				// post
				Post post = null;
				// find
				post = Post.find("byId", Long.parseLong(postId)).first();
				// set type
				post.setPostType("news");
				// update title
				post.setTitle(title);
				// set date
				post.setPostingDate(new Date());
				// set sender
				post.setPostContent(postEditor);
				// set images etc.
				post.setContent(postContent);
				// set sender
				post.setSender(user);
				// save
				post.save();
			}else{
				// post
				Post post = null;
				// new post
				post = new Post(title, new Date(), postEditor, postContent, user);
				// set type
				post.setPostType("news");
				// save
				post.save();
			}
		}else if(postType.equals("event")){// event
			// if post id not null --> update
			if(postId != null && !postId.trim().equals("")){
				// event
				Event event = null;
				// find
				event = Post.find("byId", Long.parseLong(postId)).first();
				// set type
				event.setPostType("event");
				// update title
				event.setTitle(title);
				// set date
				event.setPostingDate(new Date());
				// set sender
				event.setPostContent(postEditor);
				// set images etc.
				event.setContent(postContent);
				// set sender
				event.setSender(user);
				// update capacity
				event.setCapacity(capacity);
				// update time
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM hh:mm");
				try {
					event.setTime(formatter.parse(time));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// update location
				event.setLocation(location);
				// save
				event.save();
			}else{
				// post
				Event event = null;
				// new event
				event = new Event();
				// set type
				event.setPostType("event");
				// set title
				event.setTitle(title);
				// set date
				event.setPostingDate(new Date());
				// set sender
				event.setPostContent(postEditor);
				// set images etc.
				event.setContent(postContent);
				// set sender
				event.setSender(user);
				// set capacity
				event.setCapacity(capacity);
				// set time
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM hh:mm");
				try {
					event.setTime(formatter.parse(time));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// set location
				event.setLocation(location);
				// save
				event.save();
			}
		}
		
		allPosts();
	}
	
	/**
	 * Browser image in server
	 * @param CKEditorFuncNum - The identifier of the ckeditor callback function
	 */
	public static void imageBrowseUrl(String CKEditorFuncNum){
		// find all images
		List<Image> images = Image.findAll();
		render(images,CKEditorFuncNum);
	}
	
	/**
	 * Handler of image upload
	 * @param upload - The file to be upload
	 */
	public static void uploadUrl(File upload){
		// root path
		String projectRoot = Play.applicationPath.getAbsolutePath();
		
		// system separator
		String syetemSeperator = System.getProperty("file.separator");
		
		// check if upload folder exist, if not, create one
		File uploadFolder = new File(projectRoot + syetemSeperator + 
				"public" + syetemSeperator +
				"images" + syetemSeperator +
				"upload");
		if(!uploadFolder.exists()){
			uploadFolder.mkdir();
		}
		
		// copy files from tmp directory to (app_root)/public/images/upload using system seperator
		String imagePath = projectRoot + 
				syetemSeperator +
				"public" +
				syetemSeperator + 
				"images" +
				syetemSeperator + 
				"upload" + 
				syetemSeperator + 
				upload.getName();
		
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
	 * @param source - The source path
	 * @param dest - The destination path
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
