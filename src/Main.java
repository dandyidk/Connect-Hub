
import ContentCreation.Content;
import ContentCreation.ContentMedia;
import ContentCreation.Post;
import ContentCreation.Profile;
import ContentCreation.User;
import ContentCreation.json;
import NewsFeed.NewsFeed;
import NewsFeed.NewsFeedPage;
import UserManagementSystem.Firstpage;
public class Main {
    public static void main(String[] args) {
        NewsFeedPage fp = new NewsFeedPage(json.readProfiles().get("1"));
        fp.setTitle("News Feed");
        fp.setVisible(true);
    }
}
