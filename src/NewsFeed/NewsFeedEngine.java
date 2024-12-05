package NewsFeed;

import java.util.ArrayList;

import ContentCreation.ContentMedia;
import ContentCreation.Profile;
import FriendManagement.Friend;

public interface NewsFeedEngine {
    public ArrayList<ContentMedia> fetchContent();    
    public ArrayList<Friend> fetchFriends() ;
    public ArrayList<Profile> fetchFriendsSuggesstion() ;
}
