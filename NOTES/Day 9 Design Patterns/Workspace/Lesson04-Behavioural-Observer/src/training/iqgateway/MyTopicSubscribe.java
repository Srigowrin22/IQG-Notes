/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

/**
 *
 * @author srigowri.n
 */
public class MyTopicSubscribe implements Observer{
    private String name;
    private Subject topic;
    
    public MyTopicSubscribe(String givenName){
        this.name = givenName;
    }
    
    @Override
    public void update(){
        String msg = (String) topic.getUpdate(this);
        if(msg == null){
            System.out.println(name + " : No new message ");
        }else{
            System.out.println(name + " : Consuming message " + msg);
        }
    }
    
    @Override
    public void setSubject(Subject sub){
        this.topic=sub;
    }
}
