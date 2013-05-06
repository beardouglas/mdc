package com.facebook.android.fbreader;


import java.util.List;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;

/*
 * This Activity hosts a StoryListFragment that
 * displays the static list of stories, and has
 * a LoginButton at the top that will take care
 * of creating and opening a Session. We will add
 * extra functionality to manage Session state.
 */

public class StoryListActivity extends FragmentActivity
        implements StoryListFragment.Callbacks {
	
    // add the UiLifecycleHelper to manage Session
	
   /*private UiLifecycleHelper uiHelper;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };*/

    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_list);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        
      // uiHelper = new UiLifecycleHelper(this, callback);
      // uiHelper.onCreate(savedInstanceState);
        
        
        if (findViewById(R.id.story_detail_container) != null) {
            ((StoryListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.story_list))
                    .setActivateOnItemClick(true);
        }
        
       
    }

    @Override
    public void onItemSelected(String id) {
            Intent detailIntent = new Intent(this, StoryDetailActivity.class);
            detailIntent.putExtra(StoryDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
    }
    
    
    // Monitor changes in Session state
    /*private void onSessionStateChange(Session session, 
    		SessionState state, Exception exception) {
            if (state.equals(SessionState.OPENED)) {
            	
            	// Now we have a logged-in user. Let's get some information
            	// about them!
            	
                Request firstRequest = Request.newMeRequest(Session.getActiveSession(), 
                		new Request.GraphUserCallback() {
					@Override
					public void onCompleted(GraphUser user, Response response) {
						Toast.makeText(getApplicationContext(), 
								"Hello, " + user.getName(), 
								Toast.LENGTH_SHORT)
								.show();
					}
				});
                firstRequest.executeAsync();
 
            } else if (state.isClosed()) {
                // Don't make any API calls!
            }
        
    }*/
    
    // Explicitly call UiLifecycleHelper methods in Activity
    // lifecycle methods:
    
   /*@Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }*/

}
