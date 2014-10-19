package controllers;

import java.io.File;
import java.util.Date;

import models.*;
import play.db.jpa.Blob;
import play.mvc.*;

public class MyProfile extends Controller {
	
	private static boolean updateSuccessFlag;
	private static boolean wrongOldPassFlag = false;
	private static boolean tooShortPassFlag = false;
	private static boolean wrongSecondPassFlag = false;

	public static void submitGeneral(String email, String password, String fullname,
			String major, String address, String gender,
			Date birthday, String currentDegree,
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
					user.birthday = birthday;
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
			User user = User.find("byEmail", username).first();
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
