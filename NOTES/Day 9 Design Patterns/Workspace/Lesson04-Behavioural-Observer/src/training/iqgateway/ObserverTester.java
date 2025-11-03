/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

/**
 *
 * @author srigowri.n
 */
public class ObserverTester {
    public static void main(String[] args) {
        MyTopic topic = new MyTopic();
        
        Observer obs1 = new MyTopicSubscribe("obs1");
        Observer obs2 = new MyTopicSubscribe("obs2");
        Observer obs3 = new MyTopicSubscribe("obs3");
        
        topic.register(obs1);
        topic.register(obs2);
        topic.register(obs3);
        
        obs1.setSubject(topic);
        obs2.setSubject(topic);
        obs3.setSubject(topic);
        
        obs1.update();
        
        topic.postMessage("Hello Everyone...");
    }
}
