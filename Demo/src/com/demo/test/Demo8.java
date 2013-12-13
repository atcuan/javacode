package com.demo.test;

import java.util.concurrent.Executors;

public class Demo8 {
        public static void main(String[] args) {
                Ticket ticket = Ticket.getInstance();
                ticket.setNumber(100); 
                new SealWindow("1号窗口").start();  
                new SealWindow("2号窗口").start(); 
                new SealWindow("3号窗口").start();  
                new SealWindow("4号窗口").start();  
        }
}  
class Ticket { 
        private static Ticket ticket = new Ticket(); 
        private Ticket() { }  
        public static Ticket getInstance() {  
                return ticket; 
        }  
        private int number;  

        public boolean isHasTicket() { 
                if (number > 0) 
                        return true;
                return false; 
        }  
        public void sealTicket() {
                number--; 
        } 
        
        public void setNumber(int number) { 
                this.number = number;
        }   
        public int getNumber() {  
                return number;
        }         
} 
class SealWindow {  
        private String name;  
        public SealWindow(String name) { 
                this.name = name;
        }  
        public void start() { 
                Executors.newScheduledThreadPool(1).execute(new Runnable() {
                        Ticket ticket = Ticket.getInstance(); 
                        @Override  
                        public void run() {  
                                while (ticket.isHasTicket()) { 
                                        synchronized (Ticket.class) {
                                                if(!ticket.isHasTicket())continue; 
                                                try { 
                                                        Thread.sleep(10); 
                                                } catch (InterruptedException e) { 
                                                        e.printStackTrace();
                                                } 
                                                ticket.sealTicket(); 
                                                System.out.println(name + "售出" + (ticket.getNumber() + 1) + "号票");
                                        } 
                                }  
                        } 
                }); 
        } 
        }   
class TicketSealCenter { }



