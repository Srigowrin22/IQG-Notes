/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

/**
 *
 * @author srigowri.n
 */
public class ChatClient {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediatorImpl();
        User user1 = new UserImpl(mediator, "Roopesh");
        User user2 = new UserImpl(mediator, "Gurudeep");
        User user3 = new UserImpl(mediator, "Madan");
        User user4 = new UserImpl(mediator, "Chirag");
        
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("Hello Guys!.. ;)");
        user2.send("Hey man!! Whatsup? ");
    }
}
