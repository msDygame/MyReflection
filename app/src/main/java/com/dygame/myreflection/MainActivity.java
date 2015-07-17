package com.dygame.myreflection;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity
{
    protected Button quitButton ;
    protected Button runButton ;
    protected ListView lvReflectionListView ;
    protected ListViewAdapter lvReflectionListViewAdapter ;
    protected static String TAG ;
    protected static ArrayList<String> sReflectionArray = new ArrayList<String>();
    protected static Derived pReflection = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Uncaught Exception Handler(Crash Exception)
        MyCrashHandler pCrashHandler = MyCrashHandler.getInstance();
        pCrashHandler.init(getApplicationContext());
        TAG = pCrashHandler.getTag() ;
        //
        runButton = (Button)findViewById(R.id.button) ;
        quitButton = (Button)findViewById(R.id.button1) ;
        lvReflectionListView = (ListView)findViewById(R.id.listView) ;
        //
        runButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i(TAG, "Start of Base Method -----------");
                callSpuerMethod();
                Log.i(TAG, "Start of Derived Method -----------");
                callCurrentMethod();
                Log.i(TAG, "Start of new Instance -----------");
                callOtherMethod();
                Log.i(TAG, "End of -----------");
                //
                resetArrayList() ;
                lvReflectionListViewAdapter.notifyDataSetChanged();
            }
        });
        //
        pReflection = new Derived();
        getInfo();
        quitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        //listview
        lvReflectionListViewAdapter = new ListViewAdapter(this,sReflectionArray) ;
        lvReflectionListView.setAdapter(lvReflectionListViewAdapter);
        lvReflectionListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.e(TAG, "ListView OnItemClickListene "+position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     *
     */
    static void getInfo()
    {
        Class<?> temp = pReflection.getClass();
        try
        {
            int iCount = 0 ;
            Field[] fb = temp.getFields();
            for (int j = 0; j < fb.length; j++)
            {
                Class<?> cl = fb[j].getType();
                String s = "反射類中所有公有(public)的屬性(Field):" + cl + "___" + fb[j].getName() ;
                Log.e(TAG, iCount + "/" + fb.length + s);
                sReflectionArray.add(s) ;
                iCount++ ;
            }
            //
            iCount = 0 ;
            Field[] fa = temp.getDeclaredFields();
            for (int j = 0; j < fa.length; j++)
            {
                Class<?> cl = fa[j].getType();
                String s = "反射類中所有(Declared)的屬性:" + cl + "___" + fa[j].getName() ;
                Log.e(TAG, iCount + "/" + fa.length + s);
                sReflectionArray.add(s) ;
                iCount++ ;
            }
            //
            iCount = 0 ;
            Method[] fm = temp.getMethods();
            for (int i = 0; i < fm.length; i++)
            {
                String s = "反射類中所有的方法(Method):" + fm[i].getName() + "___return " + fm[i].getReturnType().getName() ;
                Log.e(TAG, iCount+"/"+fm.length+s);
                sReflectionArray.add(s) ;
                iCount++ ;
            }
            //
            iCount = 0 ;
            Class<?>[] fi = temp.getInterfaces();
            for (int i = 0; i < fi.length; i++)
            {
                String s = "反射類中所有的接口(Interfaces):" + fi[i].getName() ;
                Log.e(TAG, iCount+"/"+fi.length+s);
                sReflectionArray.add(s) ;
                iCount++ ;
            }
            //
            iCount = 0 ;
            Field[] sb = temp.getSuperclass().getFields();
            for (int j = 0; j < sb.length; j++)
            {
                Class<?> cl = sb[j].getType();
                String s = "反射類中父類別的所有公有(public)的屬性(Field):" + cl + "___" + sb[j].getName() ;
                Log.e(TAG, iCount + "/" + sb.length + s);
                sReflectionArray.add(s) ;
                iCount++ ;
            }
            //
            iCount = 0 ;
            Field[] sa = temp.getSuperclass().getDeclaredFields();
            for (int j = 0; j < sa.length; j++)
            {
                Class<?> cl = sa[j].getType();
                String s = "反射類中父類別的所有(Declared)的屬性:" + cl + "___" + sa[j].getName() ;
                Log.e(TAG, iCount + "/" + sa.length + s);
                sReflectionArray.add(s) ;
                iCount++ ;
            }
            //
            iCount = 0 ;
            Method[] sm = temp.getSuperclass().getMethods();
            for (int i = 0; i < sm.length; i++)
            {
                String s = "反射類中父類別的所有的方法(Method):" + sm[i].getName() + "___return " + sm[i].getReturnType().getName() ;
                Log.e(TAG, iCount+"/"+sm.length+s);
                sReflectionArray.add(s) ;
                iCount++ ;
            }
            //返回直接存在於此元素上的所有註釋。
            Annotation[] annotations =temp.getDeclaredAnnotations();
            for(Annotation ant:annotations)
            {
                String s = "反射類中所有註釋:" + ant.toString() ;
                Log.e(TAG, s);
                sReflectionArray.add(s) ;
            }
            //獲取包信息
            Package package_1=temp.getPackage();
            System.out.println(package_1.toString());
            //獲取內部類
            Class [] cla_1=temp.getDeclaredClasses();
            for(Class clazz : cla_1){
                System.out.println(clazz.toString());
            }
            //返回 Constructor 對象的一個數組，這些對像反映此 Class 對像表示的類聲明的所有構造方法。
            Constructor[] cs = temp.getDeclaredConstructors() ;
            //-------------------
            setArrayList() ;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 修復父類變量，調用父類方法
     */
    static void callSpuerMethod()
    {
        try
        {
            Random pRandom = new Random() ;
            int i = pRandom.nextInt(100)+1 ;//1~100
            // 修改私有變量；
            Field f = pReflection.getClass().getSuperclass().getDeclaredField("age");
            f.setAccessible(true);
            f.set(pReflection, i);

            i = pRandom.nextInt(200)+1 ;//1~200
            // 調用私有方法,必須要用getDeclaredMethod，而不能用getMethod；
            Method mp = pReflection.getClass().getSuperclass().getDeclaredMethod("setPrivateBaseMethod", Integer.class);
            mp.setAccessible(true);
            mp.invoke(pReflection, i);

            // 調用私有屬性
            Field fs = pReflection.getClass().getSuperclass().getDeclaredField("iProtectedBaseVersionCode");
            fs.setAccessible(true);
            int is = fs.getInt(pReflection) ;
            is++ ;
            fs.set(pReflection, is);

            // 調用隱藏方法
            Method m = pReflection.getClass().getSuperclass().getMethod("setPublicBaseMethod", String.class);
            m.setAccessible(true);
            m.invoke(pReflection, "WTF");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 修復子類變量，調用子類方法
     */
    static void callCurrentMethod()
    {
        try
        {
            Random pRandom = new Random() ;
            int i = pRandom.nextInt(100)+1 ;//1~100
            // 修改私有變量；
            Field f = pReflection.getClass().getDeclaredField("age");
            f.setAccessible(true);
            f.set(pReflection, i);

            // 調用私有方法,必須要用getDeclaredMethod，而不能用getMethod；
            Method mp = pReflection.getClass().getDeclaredMethod("getPrivateDerivedName");
            mp.setAccessible(true);
            mp.invoke(pReflection);

            // 調用隱藏私有方法
            Method m = pReflection.getClass().getDeclaredMethod("getPrivateDerivedAge");
            m.setAccessible(true);
            m.invoke(pReflection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 用Class.forName加載類及實例化
     */
    static void callOtherMethod()
    {
        try
        {
            // Class.forName(xxx.xx.xx) 返回的是一個類, .newInstance() 後才創建一個對像
            // Class.forName(xxx.xx.xx) 的作用是要求JVM查找並加載指定的類，也就是說JVM會執行該類的靜態代碼段
            Class<?> cl = Class.forName("com.dygame.myreflection.Derived");
            Object r = cl.newInstance();

            // 修改私有變量；
            Field f = cl.getDeclaredField("age");
            f.setAccessible(true);
            f.set(r, 20);

            // 調用私有方法,必須要用getDeclaredMethod，而不能用getMethod；
            Method mp = cl.getDeclaredMethod("getPrivateDerivedName");
            mp.setAccessible(true);
            mp.invoke(r);

            // 調用隱藏私有方法
            Method m = cl.getDeclaredMethod("getPrivateDerivedAge");
            m.setAccessible(true);
            m.invoke(r);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     *
     */
    public static void setArrayList()
    {
        try
        {
            //取值 Derived class
            String sTarget = "sPrivateDerivedName";
            Field field = pReflection.getClass().getDeclaredField(sTarget);
            field.setAccessible(true);
            Object str = field.get(pReflection);
            String s = "FIELD:反射類中私有(private)的屬性的值:" + sTarget + "___" + str.toString();
            Log.e(TAG, s);
            sReflectionArray.add(s);
            //
            sTarget = "iDefualtDerivedPerClassMaxField";
            field = pReflection.getClass().getDeclaredField(sTarget);
            field.setAccessible(true);
            int i = field.getInt(pReflection);
            s = "FIELD:反射類中預設(default)的屬性的值:" + sTarget + "___" + i;
            Log.e(TAG, s);
            sReflectionArray.add(s);
            //
            sTarget = "iProtectedDerivedCount";
            field = pReflection.getClass().getDeclaredField(sTarget);
            field.setAccessible(true);
            i = field.getInt(pReflection);
            s = "FIELD:反射類中保護(protected)的屬性的值:" + sTarget + "___" + i;
            Log.e(TAG, s);
            sReflectionArray.add(s);
            //
            sTarget = "iPublicDerivedIndex";
            s = "FIELD:反射類中公有(public)的屬性的值:" + sTarget + "___" + pReflection.iPublicDerivedIndex;
            Log.e(TAG, s);
            sReflectionArray.add(s);
            //
            sTarget = "age";
            field = pReflection.getClass().getDeclaredField(sTarget);
            field.setAccessible(true);
            i = field.getInt(pReflection);
            s = "FIELD:反射類中私有(private)的屬性的值:" + sTarget + "___" + i;
            Log.e(TAG, s);
            sReflectionArray.add(s);
            //---------------
            //取值 Base class
            sTarget = "sPublicBaseVersionName";
            s = "FIELD:反射類中父類別的公有(public)的屬性的值:" + sTarget + "___" + pReflection.sPublicBaseVersionName;
            Log.e(TAG, s);
            sReflectionArray.add(s);
            //
            sTarget = "sDefualtName";
            field = pReflection.getClass().getSuperclass().getDeclaredField(sTarget);
            field.setAccessible(true);
            str = field.get(pReflection);
            s = "FIELD:反射類中父類別的預設(default)的屬性的值:" + sTarget + "___" + str.toString();
            Log.e(TAG, s);
            sReflectionArray.add(s);
            //
            sTarget = "iProtectedBaseVersionCode";
            field = pReflection.getClass().getSuperclass().getDeclaredField(sTarget);
            field.setAccessible(true);
            i = field.getInt(pReflection);
            s = "FIELD:反射類中父類別的保護(protected)的屬性的值:" + sTarget + "___" + i;
            Log.e(TAG, s);
            sReflectionArray.add(s);
            //
            sTarget = "iPrivateBasePassword";
            field = pReflection.getClass().getSuperclass().getDeclaredField(sTarget);
            field.setAccessible(true);
            i = field.getInt(pReflection);
            s = "FIELD:反射類中父類別的私有(private)的屬性的值:" + sTarget + "___" + i;
            Log.e(TAG, s);
            sReflectionArray.add(s);
            //
            sTarget = "age";
            field = pReflection.getClass().getSuperclass().getDeclaredField(sTarget);
            field.setAccessible(true);
            i = field.getInt(pReflection);
            s = "FIELD:反射類中父類別的私有(private)的屬性的值:" + sTarget + "___" + i;
            Log.e(TAG, s);
            sReflectionArray.add(s);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void resetArrayList()
    {
        int iResetIndex = sReflectionArray.size() ;
        for (int i = (iResetIndex-1) ; i > 0 ; i--)//由0往max , remove會出現outOfIndex
        {
            if (sReflectionArray.get(i).contains("FIELD:")) sReflectionArray.remove(i) ;
        }
        setArrayList() ;
    }
    /**
     * 獲得包的大小
     */
/*
    public void queryPacakgeSize(String pkgName) throws Exception
    {
        if (pkgName != null)
        {
            // 使用放射機制得到PackageManager類的隱藏函數getPackageSizeInfo
            PackageManager pm = getPackageManager(); // 得到pm對像
            try
            {
                // 通過反射機制獲得該隱藏函數
                Method getPackageSizeInfo = pm.getClass().getDeclaredMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);//error? @20150713
                // 調用該函數，並且給其分配參數 ，待調用流程完成後會回調PkgSizeObserver類的函數
                getPackageSizeInfo.invoke(pm, pkgName, new PkgSizeObserver());//error? @20150713
                // 停止主線程讓PackageStats對像獲得數據
                Thread.sleep(8);
            }
            catch (Exception ex)
            {
                Log.e("TAG", "NoSuchMethodException") ;
                ex.printStackTrace();
                throw ex;// 拋出異常
            }
        }
    }
*/
}
