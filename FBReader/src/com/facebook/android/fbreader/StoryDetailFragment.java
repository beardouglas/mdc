package com.facebook.android.fbreader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.android.fbreader.dummy.DummyContent;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

/*
 * The StoryDetailFragment shows details on the selected
 * content, a button for sharing a story back to 
 * Facebook, and a button for selecting friends. Initially,
 * these buttons do not do anything. 
 * 
 */

public class StoryDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    DummyContent.DummyItem mItem;
    
    private Button shareButton;
    private Button pickFriendsButton;
    
    /*private UiLifecycleHelper uiHelper;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(final Session session, final SessionState state, final Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };
    
    private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		Toast.makeText(getActivity(), "Nothing to see here!", Toast.LENGTH_SHORT).show();
		
	} */
    
  //private static final int REAUTH_ACTIVITY_CODE = 100;


    public StoryDetailFragment() {
    }

    // Nothing changed in onCreate
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }
    

    // Wire up the buttons, which start out not doing anything
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_story_detail, container, false);
        if (mItem != null) {
        	   shareButton = ((Button) rootView.findViewById(R.id.shareButton));
        	   /*shareButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (Session.getActiveSession().isOpened()) {
						publishFeedDialog(mItem);
					} else {
						Toast.makeText(getActivity(), 
								"You must log in to publish a story", 
								Toast.LENGTH_SHORT)
								.show();
					}
				}
			});*/
        	   
        	   pickFriendsButton = ((Button) rootView.findViewById(R.id.friendsButton));
        	   /*pickFriendsButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (Session.getActiveSession().isOpened()) {
						pickFriends(FriendPicker.FRIEND_PICKER, 100);
					} else {
						Toast.makeText(getActivity(), 
								"You must log in to pick friends", 
								Toast.LENGTH_SHORT)
								.show();
					}
				}
			});*/
        	   
           ((TextView) rootView.findViewById(R.id.story_detail)).setText(mItem.title);
           ((TextView) rootView.findViewById(R.id.story_description)).setText(mItem.content);
        }
        
        return rootView;
    }
    
    // Take the currently selected item and populate a Feed
    // Dialog with its content to be posted to Facebook
    
    /*private void publishFeedDialog(DummyContent.DummyItem item) {
        Bundle params = new Bundle();
        
        // Add details about the clicked item to the
        // story params Bundle
        params.putString("name", item.title);
        params.putString("description", item.content);
        params.putString("link", item.url);
        params.putString("picture", item.pictureLink);

        WebDialog feedDialog = (
            new WebDialog.FeedDialogBuilder(getActivity(),
                Session.getActiveSession(),
                params))
            .setOnCompleteListener(new OnCompleteListener() {

                @Override
                public void onComplete(Bundle values,
                    FacebookException error) {
                    if (error == null) {
                        // When the story is posted, echo the success
                        // and the post Id.
                        final String postId = values.getString("post_id");
                        if (postId != null) {
                            Toast.makeText(getActivity(),
                                "Posted story, id: "+postId,
                                Toast.LENGTH_SHORT).show();
                        } else {
                            // User clicked the Cancel button
                            Toast.makeText(getActivity()
                            .getApplicationContext(), 
                                "Publish cancelled", 
                                Toast.LENGTH_SHORT).show();
                        }
                    } else if (error instanceof 
                    FacebookOperationCanceledException) {
                        // User clicked the "x" button
                        Toast.makeText(getActivity().getApplicationContext(), 
                            "Publish cancelled", 
                            Toast.LENGTH_SHORT).show();
                    } else {
                        // Generic, ex: network error
                        Toast.makeText(getActivity().getApplicationContext(), 
                            "Error posting story", 
                            Toast.LENGTH_SHORT).show();
                    }
                }

            })
            .build();
        feedDialog.show();
    }*/
    
    
    // Sends an intent to the FriendPicker Activity to
    // pop up a FriendPickerFragment and save the list
    // of selected people in the Application data
    
    /*private void pickFriends(Uri data, int requestCode) {
    	     Intent intent = new Intent();
    	     intent.setData(data);
    	     intent.setClass(getActivity(), FriendPicker.class);
    	     startActivityForResult(intent, requestCode);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (resultCode == Activity.RESULT_OK && 
                requestCode >= 0){
        	   FBReaderApplication app = (FBReaderApplication) 
        			   getActivity().getApplication();
        	   String pickedFriends = app.getFriendNames();
           Toast.makeText(getActivity(), pickedFriends, Toast.LENGTH_SHORT)
           .show();
        } else if (requestCode == REAUTH_ACTIVITY_CODE) {
          uiHelper.onActivityResult(requestCode, resultCode, data);
        }
    } */
    
}


