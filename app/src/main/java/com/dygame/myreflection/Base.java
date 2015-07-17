package com.dygame.myreflection;

/**
 * Created by Administrator on 2015/7/13.
 */
public abstract class Base
{
    String sDefualtName = "";//同一個package的class才可存取
    private int age = 15;
    private int iPrivateBasePassword = 1234 ;
    protected int iProtectedBaseVersionCode = 2 ;
    public String sPublicBaseVersionName = "1.0.0.2" ;

    abstract void getAbstractPhone();
    /**
     * constructor
     */
    public Base()
    {
        System.out.println("Base Constructor ");
        iProtectedBaseVersionCode = 3;
    }

    int getAge()
    {
        return age;
    }

    String getName()
    {
        return "Father";
    }

    public void setPublicBaseSun()
    {
        getAbstractPhone();
        setPrivateBaseMethod(25);
    }

    private void setPrivateBaseMethod(Integer a)
    {
        age = a;
        System.out.println("I am privateMethod , Dont call me! age " + age);
    }

    /**
     * @hide
     */
    public void setPublicBaseMethod(String name)
    {
        System.out.println("I am hideMethod , Dont call me! name:" + name + "   age:" + age);
    }

    protected String getProtectedBaseVersion() { return sPublicBaseVersionName ; }

    void setDefaultBaseString(String s) { sDefualtName = s ; }
}
