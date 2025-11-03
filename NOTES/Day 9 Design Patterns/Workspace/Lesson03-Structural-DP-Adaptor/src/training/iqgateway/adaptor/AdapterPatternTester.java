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
//public class AdapterPatternTester {
//
//    public static void main(String[] args) {
//        testClassAdapter();
//        testObjectAdapter();
//
//    }
//
//    public static void testObjectAdapter() {
//        SocketAdapter sockAdapterRef = new SocketObjectAdapterImpl();
//        Volt v3 = getVolt(sockAdapterRef, 3);
//        Volt v12 = getVolt(sockAdapterRef, 3);
//        Volt v120 = getVolt(sockAdapterRef, 3);
//
//        System.out.println("V3 Volts using object Adapter = " + v3.getVolt());
//
//        System.out.println("V12 Volts using object Adapter = " + v12.getVolt());
//
//        System.out.println("V120 Volts using object Adapter = " + v120.getVolt());
//    }
//
//    public static void testClassAdapter() {
//        SocketAdapter sockAdapterRef = new SocketClassAdapterImpl();
//        Volt v3 = getVolt(sockAdapterRef, 3);
//        Volt v12 = getVolt(sockAdapterRef, 3);
//        Volt v120 = getVolt(sockAdapterRef, 3);
//
//        System.out.println("V3 Volts using object Adapter = " + v3.getVolt());
//
//        System.out.println("V12 Volts using object Adapter = " + v12.getVolt());
//
//        System.out.println("V120 Volts using object Adapter = " + v120.getVolt());
//    }
//
//}
