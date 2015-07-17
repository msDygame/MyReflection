package com.dygame.myreflection;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 2015/7/13.
 */
public class Derived extends Base implements Observer
{
    int iDefualtDerivedPerClassMaxField = 5;//同一個package的class才可存取
    private int age = 16 ;
    private String sPrivateDerivedName = "HelloWorld.";
    protected int iProtectedDerivedCount = 0 ;
    public int iPublicDerivedIndex = 20 ;

    /**
     * constructor
     */
    public Derived()
    {
        System.out.println("Derived Constructor ");
        age = 18;
    }

    @Override
    void getAbstractPhone()
    {
        System.out.println("Derived class , age is " + age + "___" + iPublicDerivedIndex);
    }

    @Override
    public void update(Observable o, Object arg)
    {

    }

    public int getAge()
    {
        return 28;
    }

    public String getName()
    {
        return "Jerome";
    }
    //private method
    private void getPrivateDerivedName()
    {
        System.out.println("I am Jerome");
    }

    /**
     *@hide
     */
    private void getPrivateDerivedAge()
    {
        System.out.println("I am "+age);
    }
    //protected method
    protected void setProtectedDerivedCount(int i) { iProtectedDerivedCount = i ; }

    void setDefaultDerivedMax(int i) { iDefualtDerivedPerClassMaxField = i ; }
    //public method
    public void setPublicDerivedIndex(int i) { iPublicDerivedIndex = i ; }
}
