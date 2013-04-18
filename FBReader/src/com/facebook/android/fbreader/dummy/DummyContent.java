package com.facebook.android.fbreader.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {
	
	/*
	 * This file defines the content for the
	 * items in the ListView. It adds a few
	 * extra fields to the standard template's
	 * DummyItem, and adds static content that
	 * we can share back to Facebook
	 */

    public static class DummyItem {

        public String id;
        public String title;
        public String content;
        public String url;
        public String pictureLink;
        

        public DummyItem(String id, String title, 
        		String content, String url, 
        		String pictureLink) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.url = url;
            this.pictureLink = pictureLink;
        }

        @Override
        public String toString() {
            return title;
        }
        
    }

    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        addItem(new DummyItem("1", "Latest Facebook Developer News",
        		"Read the blog for the latest news",
        		"https://developers.facebook.com/blog", 
        		"https://raw.github.com/beardouglas/mdc/master/Images/oper_dev_love.png"));
        addItem(new DummyItem("2", "Resources for Android",
        		"How-tos and reference docs are here!",
        		"https://developers.facebook.com/android/",
        		"https://raw.github.com/beardouglas/mdc/master/Images/android.png"));
        addItem(new DummyItem("3", "Resources for iOS",
        		"How-tos and reference docs are here!",
        		"https://developers.facebook.com/ios/",
        		"https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png"));
        addItem(new DummyItem("4", "Get your questions answered",
        		"The community on StackOverflow is here to help!",
        		"http://stackoverflow.com/questions/tagged/facebook",
        		"https://raw.github.com/beardouglas/mdc/master/Images/android.png"));
        addItem(new DummyItem("5", "Report a bug",
        		"Is something broken? Tell us by filing a bug!",
        		"https://developers.facebook.com/bugs",
        		"http://www.shadesofgreensa.com/Images/Beneficials/ladybug.jpg"));
        addItem(new DummyItem("6", "Try out the API",
        		"Use Graph API Explorer to test API calls",
        		"https://developers.facebook.com/tools/explorer",
        		"https://raw.github.com/beardouglas/mdc/master/Images/web.png"));
        addItem(new DummyItem("7", "Watch video tutorials",
        		"Developers Live! is a new video resource",
        		"https://apps.facebook.com/developerslive/",
        		"https://raw.github.com/beardouglas/mdc/master/Images/like.png"));
        addItem(new DummyItem("8", "Awesome social integrations",
        		"Check out the app showcase for ideas",
        		"https://developers.facebook.com/showcase/apps/",
        		"https://raw.github.com/beardouglas/mdc/master/Images/like.png"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
}
