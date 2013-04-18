package com.facebook.android.fbreader;

import java.util.List;

import android.app.Application;

import com.facebook.model.GraphUser;

/*
 * In this app, we are using the Application class
 * only to store the list of selected friends that
 * is picked in the FriendPicker. FBReaderApplication
 * has a "get friend names" method that returns just
 * the names of selected friends.
 */

public class FBReaderApplication extends Application {
	
	private List<GraphUser> selectedFriends;
	
	public List<GraphUser> getSelectedFriends() {
	    return selectedFriends;
	}

	public void setSelectedFriends(List<GraphUser> friends) {
	    selectedFriends = friends;
	}
	
	// Returns a string of selected friends' names
	public String getFriendNames() {
		String result = "";
		if (selectedFriends.size()==0) {
			result = "No friends selected";
		} else {
			result = "You picked: ";
			for (GraphUser friend : selectedFriends) {
				result = result + friend.getName() + " ";
			}
		}
		return result;
	}

}
