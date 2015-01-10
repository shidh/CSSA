package controllers;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.Image;
import models.User;
import play.Play;
import play.mvc.Controller;
import play.mvc.results.Ok;
import play.mvc.results.Result;

public class MyProfile extends Controller {
	
	private static boolean updateSuccessFlag = false;
	private static boolean wrongOldPassFlag = false;
	private static boolean tooShortPassFlag = false;
	private static boolean wrongSecondPassFlag = false;

	public static Result saveIconImg(String userId, File file){
		// root path
		String projectRoot = Play.applicationPath.getAbsolutePath();

		// system separator
		String syetemSeperator = System.getProperty("file.separator");

		// check if upload folder exist, if not, create one
		File uploadFolder = new File(projectRoot + syetemSeperator + "public"
				+ syetemSeperator + "images" + syetemSeperator + "upload");
		if (!uploadFolder.exists()) {
			uploadFolder.mkdir();
		}

		// copy files from tmp directory to (app_root)/public/images/upload
		// using system seperator
		String imagePath = projectRoot + syetemSeperator + "public"
				+ syetemSeperator + "images" + syetemSeperator + "upload"
				+ syetemSeperator + file.getName();

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
			Admin.copyFileUsingStream(file, copyFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// stores image
		Image image = new Image(null);
		image.setUrl(imagePath);
		image.setFileName(file.getName());
		image.save();
		
		// link user image
		User user=User.find("byId", Long.parseLong(userId)).first();
		user.setImage(image);
		user.save();
		return new Ok();
	}
	
	public static void submitGeneral(String email, String password, String fullname,
			String major, String address, String gender,
			String birthday, String currentDegree,
			String mobileNo, String organization, String lastDegree) {

		System.out.println("here " + gender);
		String username = session.get("username");
		
		System.out.println("From MyProfile submit#current username: "+username);
		// System.out.println("follo:" + folloUserid);
		if (username != null) {
			User user = User.find("byEmail", username).first();
			if (user != null) {
				//System.out.println("Now name is:" + fullname);
				if (fullname != null) {
					user.fullname = fullname;
				}
				if (address != null) {
					user.address = address;
				}
				if (gender != null) {
					user.gender = gender;
				}
				if (birthday != null) {
					System.out.println("birthday"+birthday);
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd");
					try {
						user.birthday = formatter.parse(birthday);
						System.out.println(user.birthday);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				}
				if (major != null) {
					user.major = major;
				}
				if (currentDegree != null) {
					user.currentDegree = currentDegree;
				}
				if (mobileNo != null) {
					user.mobileNo = mobileNo;
				}
				if (organization != null) {
					user.organization = organization;
				}
				if (lastDegree != null) {
					user.lastDegree = lastDegree;
				}
				user.save();
			}

			MyProfile.page();
		}
	}
	
	public static void submitPassword(String oldPassword, String newPassword, String confirmed) {
		updateSuccessFlag = false;
		
		String username = session.get("username");
		
		System.out.println("From MyProfile submit#current username: "+username);
		// System.out.println("follo:" + folloUserid);
		if (username != null) {
			User user = User.find("byEmail", username).first();
			if (user != null) {
				//System.out.println("Now name is:" + fullname);

				if (oldPassword.equals(user.password)) {
					if (newPassword.equals(confirmed) ){
						if (newPassword.length() > 5) {
							updateSuccessFlag = true;
							user.password = newPassword;
							user.save();
						} else {
							tooShortPassFlag = true;
						}
					} else {
						wrongSecondPassFlag = true;
					}
				} else {
					wrongOldPassFlag = true;
				}
			}

			MyProfile.page();
		}
	}

	public static void page() {
		
		boolean flag_login = false;
		String email = null;

		String username = session.get("username");
		
		System.out.println("From MyProfile page#current username: "+username);
		if (username != null) {
			User user = null;
			User user1 = User.find("byEmail", username).first();
			User user2 = User.find("byUsername", username).first();

			if(user1 == null){
				user = user2; 
			}else if(user2 == null){
				user = user1;
			}
			if (user != null) {
				email = username;
				flag_login = true;
				// renderArgs.put("ajax", "true");
				// renderTemplate("tags/ajax.html");
				//String tagString = getTags(user.tags);
				System.out.println(user.cssaID);
				boolean updateSuc = false;
				boolean shortPass = false;
				boolean wrongSec = false;
				boolean wrongOld = false;
				if (updateSuccessFlag){
					updateSuc = true;
				}
				if (tooShortPassFlag){
					shortPass = true;
				}
				if (wrongSecondPassFlag){
					wrongSec = true;
				}
				if (wrongOldPassFlag){
					wrongOld = true;
				}
				updateSuccessFlag = false;
				tooShortPassFlag = false;
				wrongSecondPassFlag = false;
				wrongOldPassFlag = false;
				render(user, flag_login, email, updateSuc, shortPass, wrongSec, wrongOld);
			}
		}
		Application.index();
	}

	public static void forwardToMyProfile() {
		MyProfile.page();
	}

}
