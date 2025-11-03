/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

/**
 *
 * @author srigowri.n
 */
public class LinearSerachExample {
    public static void main(String[] args) {
       int arr[] = {10,30,50,22,28,15};
       int x = 28;
       int res = search(arr, x);
       if(res == -1){
           System.out.println("Element not found");
       }else{
           System.out.println("Element present at index: " + res);
       }
    }
    
    public static int search(int arr[], int x){
        int n = arr.length;
        for(int i=0; i<n; i++){
            if(arr[i] == x){
                return i;
            }
        }
        return -1;
    }
}
