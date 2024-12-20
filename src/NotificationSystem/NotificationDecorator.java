package NotificationSystem;

public class NotificationDecorator implements NotificationInterface {
    protected NotificationInterface decoratedNotification;
    public NotificationDecorator(NotificationInterface decoratedNotification){
        this.decoratedNotification = decoratedNotification;
    }
    @Override
    public String display() {
        return decoratedNotification.display();
    }
    
}
