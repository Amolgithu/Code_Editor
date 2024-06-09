package com.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class mouselistener implements MouseListener{

    private window w;
    private componentset component;
    private boolean entered,pressed,released;
    char array[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public mouselistener(window ww,componentset c){
        this.w=ww;
        component=c;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        boolean flag=false;
//        String s = component.textArea[component.tp.getSelectedIndex()].getText();
//
//        // System.out.println(s);
//        for(int i =0;i<array.length;i++){
//            if(s.contains(array[i]+":")){
//                flag=true;
//                break;
//            }
//        }
//        if(flag){
//            component.textArea[component.tp.getSelectedIndex()].setText("");
//            if(component.tp.getTitleAt(component.tp.getSelectedIndex())!="Untitled"){
//                component.addtab();
//            }
//            try {
//                    component.mce.writeingcomtentfromfile(s);
//                } catch (IOException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }
//        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exit");
    }
    
}
