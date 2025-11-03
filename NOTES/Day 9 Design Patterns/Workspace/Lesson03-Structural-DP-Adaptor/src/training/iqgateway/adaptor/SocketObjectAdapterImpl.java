///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package training.iqgateway.adaptor;
//
///**
// *
// * @author srigowri.n
// */
//public class SocketObjectAdapterImpl implements SocketAdapter {
//    
//    public Volt get120Volt(){
//        return getVolt();
//    }
//    
//     public Volt get12Volt(){
//        Volt v = getVolt();
//        return convertVolt(v, 10);
//    }
//    
//     public Volt get3Volt(){
//        Volt v = getVolt();
//        return convertVolt(v, 40);    
//    }
//     
//     private Volt convertVolt(Volt v, int i){
//         return new Volt(v.getVolt()/i);
//     }
//}
