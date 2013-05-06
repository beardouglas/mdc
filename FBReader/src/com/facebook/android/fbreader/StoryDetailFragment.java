package com.facebook.android.fbreader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestBatch;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.android.fbreader.dummy.DummyContent;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

/*
 * The StoryDetailFragment shows details on the selected
 * content, a button for sharing a story back to 
 * Facebook, a button for selecting friends, and a button
 * that gets a list of friends using Android devices and
 * the music they've been listening to lately. Initially,
 * these buttons do not do anything. 
 * 
 */

public class StoryDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    DummyContent.DummyItem mItem;
    
    private Button shareButton;
    private Button pickFriendsButton;
    private Button androidFriendsButton;
    private Button sendRequestsButton;
    
    /*private UiLifecycleHelper uiHelper;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(final Session session, final SessionState state, final Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };
    
    private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
    	    Log.i("StoryDetailFragment", "Session updated: "+ state.toString());
	} */
    
   // private static final int REAUTH_ACTIVITY_CODE = 100;


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
        	  
        	   sendRequestsButton = ((Button) rootView.findViewById(R.id.sendRequestsButton));
       	   /*sendRequestsButton.setOnClickListener( new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (Session.getActiveSession().isOpened()) {
						sendRequest(mItem);
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
        	   
        	   androidFriendsButton = ((Button) rootView.findViewById(R.id.androidFriendsButton));
        	   /*androidFriendsButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (Session.getActiveSession().isOpened()) {
						getAndroidFriends();
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
    
    // Very similar to the Feed Dialog, this uses the WebDialogBuilder
    // to build a Requests dialog. 
    /*private void sendRequest(DummyContent.DummyItem item) {
    	    Bundle params = new Bundle();
        params.putString("message", "Learn how to make your Android apps social");
        
        // Only show people who aren't already using the app 
        params.putString("filters", "app_non_users");
        
        // Track which page this request was generated from
        params.putString("data", item.content);

        WebDialog requestsDialog = (
            new WebDialog.RequestsDialogBuilder(getActivity(),
                Session.getActiveSession(),
                params))
                .setOnCompleteListener(new OnCompleteListener() {

                   // Add logging here to see when people choose
                	   // not to share 
                	   @Override
                    public void onComplete(Bundle values,
                        FacebookException error) {
                        if (error != null) {
                            if (error instanceof FacebookOperationCanceledException) {
                                Toast.makeText(getActivity().getApplicationContext(), 
                                    "Request cancelled", 
                                    Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), 
                                    "Network Error", 
                                    Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            final String requestId = values.getString("request");
                            if (requestId != null) {
                                Toast.makeText(getActivity().getApplicationContext(), 
                                    "Request sent",  
                                    Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), 
                                    "Request cancelled", 
                                    Toast.LENGTH_SHORT).show();
                            }
                        }   
                    }

                })
                .build();
        requestsDialog.show();
    	
    }*/
    
    
    // Sends an intent to the FriendPicker Activity to
    // pop up a FriendPickerFragment and save the list
    // of selected people in the Application data.
    /*private void pickFriends(Uri data, int requestCode) {
    	     Intent intent = new Intent();
    	     intent.setData(data);
    	     intent.setClass(getActivity(), FriendPicker.class);
    	     startActivityForResult(intent, requestCode);
    }*/
    
    // Handle the result of the friend picker
   /* @Override
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
    }*/
    
   
    // Gets Android friend data in two steps-- make FQL
    // call to get IDs, pass those ids to a function that
    // will create Requests to fetch the songs that 
    // friend has listened to
   /* private void getAndroidFriends() {
    	     requestAndroidFriendIds();
    }*/
    
    // Make an FQL call using the user and friend
    // tables to return a list of friends that only
    // includes people using at least one Android
    // device, alphabetized by name
   /* private void requestAndroidFriendIds() {
    	      String fqlQuery = "SELECT uid, devices FROM user WHERE uid IN" + 
                  "(SELECT uid2 FROM friend WHERE uid1 = me() LIMIT 100) " +
                  "AND \"Android\" IN devices " +
                  "ORDER BY name";
          Bundle params = new Bundle();
          
          // Put the query text into param "q"
          params.putString("q", fqlQuery);
          Session session = Session.getActiveSession();
          
          // Construct a request using the /fql graph path
          // end point and pass in parameter "q"
          Request request = new Request(session,
              "/fql",                         
              params,                         
              HttpMethod.GET,                 
              new Request.Callback(){         
                  public void onCompleted(Response response) {
                      if (response!=null) {
                    	     // if this call returned friends,
                    	     // parse the response for UIDs
                    	     ArrayList<String> androidIds = getIdsFromResponse(response);
                    	     handleGetMusic(androidIds);
                      }
                  }                  
          }); 
          request.executeAsync();
         
    }*/
    
    // Pull uids from response data. Quick and dirty,
    // a proper implementation should probably model
    // the response
    /*private ArrayList<String> getIdsFromResponse(Response response) {
    	     int uidStart;
    	     int uidEnd;
    	     Log.i("Response was", response.toString());
    	     String responseText = response.toString();
    	     ArrayList<String>androidIds = new ArrayList<String>();
    	     for (int i=0; i<responseText.length(); i++) {
    	    	      uidStart = responseText.indexOf("uid", i) + 5;
    	    	      uidEnd = responseText.indexOf("}", uidStart);
    	    	      String id = responseText.substring(uidStart, uidEnd);
    	    	      if (!androidIds.contains(id)) {
    	    	         androidIds.add(id);
    	    	      }
    	     }
    	     return androidIds;
    }*/
    
    // Asking for friends' music activity requires new
    // permissions. Ask for them in context!
   /*private void handleGetMusic(ArrayList<String> androidIds) {
    	   if (Session.getActiveSession().getPermissions()
  			  .contains("friends_actions.music")) {
    		   requestFriendDataFromIds(androidIds);
    	   } else {
    		   Session.NewPermissionsRequest newPermsRequest = 
    				   new Session.NewPermissionsRequest(
    					   getActivity(),
    					   Arrays.asList("friends_actions.music"));
    		   Session.getActiveSession()
    		          .requestNewReadPermissions(newPermsRequest);
    		   if (Session.getActiveSession().getState() == 
    				   SessionState.OPENED_TOKEN_UPDATED) {
    			   requestFriendDataFromIds(androidIds);
    		   }
    	   }
    }*/
    
    // Get the latest song each friend using an Android
    // device listened to
    /*private void requestFriendDataFromIds(ArrayList<String> androidIds) {
    	   if (Session.getActiveSession().getPermissions()
  			  .contains("friends_actions.music")) {
    		   
    	     RequestBatch requestBatch = new RequestBatch();
 
         for (String id : androidIds) {
            Request musicRequest = Request.newGraphPathRequest(
            		Session.getActiveSession(), 
                id+"/music.listens", 
                new Request.Callback() {
                    
            	        public void onCompleted(Response response) {
            	        	  if (response != null) {
            	        	    Log.i("Story Detail Music", response.toString());
            	        	  }
            	        }
                });
            requestBatch.add(musicRequest);
         }
   
         requestBatch.executeAsync();	
    	  }
    }*/
}


