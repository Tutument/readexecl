package com.readexcel.readexcel.test;

import com.readexcel.readexcel.service.AsyncService;
import com.readexcel.readexcel.web.ReadExcel;
import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import java.lang.annotation.Native;
import java.util.*;


public class JavaTest {

    //JNA加密示例
   /* public interface Dll extends Library {


        Dll instance = (Dll)Native.loadLibrary("KSEnCode", Dll.class);

        public int CYHEncode(Pointer  source, String Key);
    }
    public static String PwdEncode(String source, String Key) {


        Pointer pointer = new Memory(source.length() + 1);
        pointer.setString(0, source);

        Dll.instance.CYHEncode(pointer, Key);

        return pointer.getString(0, "GBK");
    }*/
    public static void main(String[] args) {
        //System.out.println(System.getProperty("java.library.path"));
        //(0)JNA加密示例
        /*String s = PwdEncode("123456", "1880040003");
        System.out.println(s);*/


        //（1）方法参数缺失测试
        //  f(1,"q");
        // f(1,3);

        //（2）求根算法
        // float genHao = getGenHao(9);

        //（3）set与list转换
        //rongQi();

        //（4）ListIterator测试
        //ListIterator();

        //（5）LinkedList 模拟队列
      /*  DuiLie duiLie = new DuiLie();
        for (int i=0;i<10;i++){
            duiLie.myAdd("java"+i);
        }
        while (!duiLie.isKong()){
            System.out.println(duiLie.myGet());
        }*/

        //（6）LinkedList 模拟堆栈
     /*   DuiZhan<Integer > duiZhan = new DuiZhan<>();
        for (int i=0;i<10;i++){
            duiZhan.myAdd(i);
        }
        while (duiZhan.isKong()!=0){
            System.out.println(duiZhan.myGet());
        }*/

        //（7）Collections测试
        // doSet();

        //（8）测试hashmap及伪随机数
        //randMap();

        //（9）iterator实现
       /* CollentionSquence collentionSquence = new CollentionSquence();
        IteratorShow iteratorShow = new IteratorShow();
        iteratorShow.display(collentionSquence.iterator());*/

        //(11)系统环境变量
        // showEnviromentVariable();

        //(12)容器的一些方法测试
        // arrayToCollection();

        //（13）StringBuffer换行测试
        //springBufferTest();

        //（14）字母大小写转换及String转换字母大小写
        //atoA();

        //（15）String 的一些方法测试
        //stringFunction();

        //(16) 获取类路径和项目路径
        //getPath();

        //（17）测试String在编译器优化情况下与StringBuffer的差距,StringBuffer的顺序会影响时间
        //getStringTime();
        //getStringAndBufferTime();
        //getStringBufferTime();

        //(18)toString()方法测试及递归测试
        //testToString();

        //(19)测试构造函数中动态绑定的问题
        //new Zi(5);
        //new Zi();

        //(20) String类型存储在堆里吗？不是,类似常量池
      /*  String a = "0";
        doit(a);
        System.out.println(a);*/

        //（21）数组利用set去重，数组转set
        //collection();

        //（22）hashset默认hashcode与equals
        //hashSetDemo();
        //hashSetdemo2();

        //（23）String转double
        //doubleTest();

        //（24）测试||(双或的逻辑)的逻辑
        //huo("0");

        //（25）直接new接口的,并实现接口中的方法
        //jiekouTest();

        //(26)线程池（见demo中的例子）
        //myThread();

        //（27）map的values方法,entrySet方法
        //mapValueTest();

        //（28） 测试数组转list后，list可以增加元素吗（即list底层还是不变数组吗）
        //arrayToListTest();

        //(29)
        //TypesForSets.test(HashSet<HashSetType>,HashSetType);
       // TypesForSets typesForSets = new TypesForSets();
        //typesForSets.test(HashSet<HashSetType>,HashSetType);

        //(30)死循环
        //say(3);

        //（31）三元
       // sany("aa");

        //(32)foreach中用final关键字
        //doforeach();
        json();
    }

    /**
     *  （1）方法参数缺失测试
     * @param i
     * @param str
     */
    public static void f(int i,int ...str){
        System.out.println(i);
        for (int s:str) {
            System.out.println(s);
        }

    }

    /**
     * （2）求根算法
     * @param nummber
     * @return
     */
    public static float getGenHao(float nummber){
        long i;
        float x2,y;
        final float threehalfs = 1.5f;

        x2 = nummber * 0.5f;
        y = nummber;

        i = (long) y;
        System.out.println(i);
        i = 0x5f3759df - (i >> 1);
        System.out.println(i);
        y = (float) i;
        //y = y * (threehalfs - (x2 * y * y));
        return y;

    }

    /**
     * （3）set与list转换
     */
    public static void rongQi(){
        ArrayList<String > arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("1");
        System.out.println(arrayList);
        HashSet<String> hashSet = new HashSet(arrayList);
        System.out.println(hashSet);
    }

    /**
     *（4） ListIterator测试
     */
    public static void ListIterator(){
        ArrayList<String > arrayList = new ArrayList<>();
        for(int i=0;i<10;i++){
            arrayList.add(i+"");
        }
        ListIterator<String> it = arrayList.listIterator();
        while (it.hasNext()){
            System.out.println(it.next()+","+it.nextIndex()+","+it.previousIndex());
        }
        System.out.println();
        while (it.hasPrevious()){
            System.out.println(it.previous()+","+it.nextIndex()+","+it.previousIndex());
        }
    }

    /**
     * （5）LinkedList 模拟队列
     */
    public static class DuiLie{
        private LinkedList<Object> linkedList;
        DuiLie(){
            linkedList = new LinkedList<>();
        }
        //进入队列
        public void myAdd(Object obj){
            linkedList.addFirst(obj);
        }
        //出队列
        public Object myGet(){
            return linkedList.removeLast();
        }

        public boolean isKong(){
            return linkedList.isEmpty();
        }
    }

    /**
     * （6）LinkedList 模拟堆栈
     */
    public static class DuiZhan<T>{
        private LinkedList<T> linkedList;
        DuiZhan(){
            linkedList = new LinkedList<>();
        }
        //进栈（压栈）
        public void myAdd(T obj){
            linkedList.addFirst(obj);
        }
        //出栈
        public T myGet(){
            return linkedList.removeFirst();
        }

        /* public boolean isKong(){
             return linkedList.isEmpty();
         }*/
        public int isKong(){
            return linkedList.size();
        }
    }

    /**
     * （7）Collections测试
     */
    public static void doSet(){
        HashSet<String> set = new HashSet<>();
        Collections.addAll(set,"A B C D E F".split(" "));
        TreeSet<String> set1 = new TreeSet<>();
        Collections.addAll(set1,"M H G K L N".split(" "));
        LinkedHashSet<String> set2 = new LinkedHashSet<>();
        Collections.addAll(set2,"O P Q R S T".split(" "));
        LinkedHashSet<String> set3 = new LinkedHashSet<>(set1);
        System.out.println(set);
        System.out.println(set1);
        System.out.println(set2);
        System.out.println(set3);
    }

    /**
     * （8）测试hashmap及伪随机数
     */
    public static void randMap(){
        Random random = new Random(47);

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int i=0;i<10000;i++){
            int anInt = random.nextInt(10);
            Integer integer = hashMap.get(anInt);
            hashMap.put(anInt,integer == null ? 1 : integer+1);
        }
        System.out.println(hashMap);
    }

    /**
     *（9）iterator实现继承
     */
    public static class CollentionSquence extends AbstractCollection<String >{

        private String[] strs = new String[]{"a","b","c"};

        @Override
        public Iterator<String> iterator() {

            return new Iterator<String>() {
                private int index;
                @Override
                public boolean hasNext() {
                    return index<strs.length;
                }
                @Override
                public String next() {
                    return strs[index++];

                }
                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public int size() {
            return strs.length;
        }
    }
    public static class IteratorShow{
        public void display(Iterator<String> iterator){
            String str ="";
            while(iterator.hasNext()){
                str+=iterator.next()+";";
            }
            str =  str.substring(0,str.length()-1);
            System.out.println(str);
        }
    }

    /**
     * （10）iterator实现自己实现
     */
    public static class FuIteratorShow{
        public String[] strs = new String[]{"a","b","c"};
    }
    public static class ZiIteratorShow extends FuIteratorShow{
        public Iterator<String> iterator(){
            return new Iterator<String>() {
                private int index;
                @Override
                public boolean hasNext() {
                    return index<strs.length;
                }

                @Override
                public String next() {
                    return strs[index++];
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    /**
     * （11）系统环境变量 foreach对于实现Iterator接口的类都可以使用
     */

    public static void showEnviromentVariable(){
        for(Map.Entry entry:System.getenv().entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    /**
     * （12）数组转集合，集合转数组
     */
    public static void arrayToCollection(){
        String[] strs = new String[]{"a","b","c"};

        String[] strs1 = new String[]{"a","b","d"};
        String[] strs2 = new String[]{"a","b"};
        List<String> list = Arrays.asList(strs);
        ArrayList<String> arrayList = new ArrayList<>(list);
        List<String> list1 = Arrays.asList(strs1);
        ArrayList<String> arrayList1 = new ArrayList<>(list1);
        List<String> list2 = Arrays.asList(strs2);
        //equals方法比较内容
        boolean equals = list1.equals(list);
        //removeAll方法移除内容，若包含返回ture
        boolean b = arrayList1.removeAll(arrayList);
        System.out.println(b+"__"+arrayList1);
        System.out.println(equals);
        list.toArray();

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("1","wo");
        stringStringHashMap.put("2","ji");
        Collection<String> values = stringStringHashMap.values();

        for (String collection:values){
            System.out.println(collection);
        }
    }

    /**
     * （13）StringBuffer换行测试
     */
    public static void springBufferTest(){
        StringBuffer sb = new StringBuffer();
       /* sb.append("hi");
        sb.append("\r\n");
        sb.append("boy");*/

        if(sb.length()==0){
            System.out.println("===");
        }
        System.out.println(sb.toString());
    }

    /**
     * （14）字母大小写转换及String转换字母大小写
     */
    public static void atoA(){
        char d = Character.toUpperCase('d');
        System.out.println(d);

        String str = "abc";
        int i = str.codePointAt(0);
        char j = (char)i;

        char c = Character.toUpperCase(j);
        String s = c+"";
        String s1 = str.replaceFirst(j+"", s);
        System.out.println(s1);
    }

    /**
     * (15)String 的一些方法测试
     */
    public static void stringFunction(){
        String excelPath = "D:\\jar\\ESB对外接口文档1.2.1.xlsx";
        String[] splits = excelPath.split("\\\\");
        boolean esb = splits[splits.length - 1].startsWith("ESB");
        System.out.println(esb);
        //charAt()
        String abc = "abc";
        char c = abc.charAt(2);
        System.out.println(c);

        //getChars(),getBytes()
        byte[] bytes = abc.getBytes();
        ArrayList<Byte> bytes1 = new ArrayList<>();
        for (int i=0;i<bytes.length;i++){
            bytes1.add(bytes[i]);
        }
        System.out.println(bytes);
        System.out.println(bytes1);

        //toCharArray()
        char[] chars = abc.toCharArray();
        System.out.println(chars);

        //compareTo()
        abc="Nsa";
        int ab = abc.compareTo("N");
        System.out.println(ab);
    }

    /**
     *(16) 获取类路径和项目路径
     */
    public static void getPath(){
        String path = Class.class.getClass().getResource("/").getPath();
        String property = System.getProperty("user.dir");
        System.out.println(path);
        System.out.println(property);

    }
    /**
     * （17）测试String在编译器优化情况下与tringBuffer的差距
     */
    public static void getStringTime(){
        long start = System.currentTimeMillis();
        String s = "";
        for(int i=0; i<100000;i++){
            s+="a";
        }
        long end = System.currentTimeMillis();
        System.out.println("String "+(end-start));
    }
    public static void getStringBufferTime(){
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<100000;i++){
            sb.append("a");
        }
        long end = System.currentTimeMillis();
        System.out.println("StringBuffer "+(end-start));
    }
    public static void getStringAndBufferTime(){
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<100000;i++){
            sb.append("a"+"b");
        }
        long end = System.currentTimeMillis();
        System.out.println("StringAndBuffer "+(end-start));
    }

    /**
     * (18)toString()方法测试
     */
    public static void testToString(){
        List<Integer> integers = new ArrayList<>();
        for (int i=0;i<10;i++){
            integers.add(i);
        }
        System.out.println(integers);

        //递归测试
        DiGui diGui = new DiGui();
        System.out.println(diGui);

    }
    public static class DiGui{
        public String toString(){
            return "对象地址"+this;
            //return "对象地址"+super.toString();
        }
    }

    /**
     * (19)测试构造函数中动态绑定的问题
     */
    public static class Fu{
        void f(){
            System.out.println("Fu.f()");
        }
        Fu(){
            System.out.println("before f()");
            f();
            System.out.println("after f()");
        }
    }
    public static class Zi extends Fu{
        int count = 1 ;
        void f(){
            System.out.println("Zi.f() "+count);
        }
        Zi(int i ){
            count = i;
            System.out.println("Zi f():controller "+count);
        }
        Zi(){
            System.out.println("Zi f():controller "+count);
        }
    }

    /**
     * (20) String类型存储在堆里吗？不是
     * @param a
     */
    public static void doit(String a){
        a = "1";
        System.out.println(a+"__doit");
    }

    /**
     * （21）数组利用set去重，数组转set
     */
    public static void collection() {
        String[] strings = new String[2];
        strings[0] = "a";
        strings[1] = "a";
        Set<String> set = new HashSet<>();
        Collections.addAll(set, strings);
        String[] sset = new String[set.size()];
        set.toArray(sset);
        /*ArrayList<String> strings1 = new ArrayList<>();
        Collections.addAll(set,strings1 );*/
        for (int i=0;i<sset.length;i++)
            System.out.println(sset[i]);
    }

    /**
     * （22）hashset默认hashcode与equals
     */
    public static void hashSetDemo(){
        ReadExcel readExcel = new ReadExcel();
        ReadExcel readExcel1 = new ReadExcel();
        HashSet<ReadExcel> hashSet = new HashSet<>();
        hashSet.add(readExcel);
        hashSet.add(readExcel1);
        System.out.println(hashSet);
        ArrayList<ReadExcel> arrayList = new ArrayList<>();
        arrayList.add(readExcel);
        int i = arrayList.indexOf(readExcel1);
        System.out.println(i);
    }
    static class Order{
        private int id;
        private int age;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void hashSetdemo2(){
        HashSet<Order> hashSet = new HashSet<>();
        ArrayList<Order> arrayList = new ArrayList<>();
        for (int i=0;i<2;i++){
            Order readExcel = new Order();
            hashSet.add(readExcel);
            //hashSet.hashCode();
            //hashSet.equals(readExcel);

            if(arrayList.indexOf(readExcel)==-1) {
                System.out.println("haha");
                arrayList.add(readExcel);
            }
        }

        ArrayList<Order> orderArrayList = new ArrayList<>();
        for (Order order:arrayList){
            if(orderArrayList.indexOf(order)==-1) {
                orderArrayList.add(order);
            }
        }
        System.out.println(hashSet);
        System.out.println(arrayList);
    }

    /**
     * （23）String转double
     */
    public static void doubleTest(){
        String i = "1";
        double v = Double.parseDouble(i);

        if(v>0){
            System.out.println("haha");
        }
        System.out.println(v);
    }

    /**
     * （24）测试||的逻辑，好蠢啊,不能这样写（!"0".equals(str)||null!=str）他有三个可能，类似四象限
     * @param str
     */
    public static void huo(String str){
        if(!"0".equals(str)||null!=str){
            System.out.println("keng keng");
        }

    }

    /**
     * （25）直接new接口的,并实现接口中的方法
     */
    public static void jiekouTest(){
       new MyInterface(){
            @Override
            public void hello() {
                System.out.println("haha");
            }
        };
    }

    /**
     * (26)线程池（见demo中的例子）
     */
    //这个注解无效，因为JavaTest类没有没spring管理
    @Autowired
    static TaskExecutor taskExecutor;

    public static void myThread(){
        for (int i=0;i<100;i++){
           taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"run---");
                }
            });
        }

    }

    /**
     * (27)map的values方法,entrySet方法
     */
    public static void mapValueTest(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1","A");
        hashMap.put("2","B");
        hashMap.put("3","B");
        //values方法
        Collection<String> values = hashMap.values();
        for (String string :values)
            System.out.println(string);

        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        hashMap.put("3","C");
        for(Map.Entry<String, String> entry :entries){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

    /**
     * (28)测试数组转list后，list可以增加元素吗（即list底层还是不变数组吗）
     */
    public static void arrayToListTest(){
        String[] strs =new String[]{"1","2","3"};
        List<String> list = Arrays.asList(strs);
        //不可添加，因为底层还是不变数组
        //list.add("4");
        System.out.println(list);

    }

    /**
     * (29)set的模拟
     */
    static class SetType{
        int i ;
        public SetType(int n){
            i=n;
        }

        public boolean equeals(Object o){
            return o instanceof SetType &&(i == ((SetType)o).i);
        }

        public String toString(){
            return Integer.toString(i);
        }
    }

    static class HashSetType extends SetType{
        public HashSetType(int n){
            super(n);
        }

        public int hashCode(){
            return i;
        }
    }

    static class TreeType extends SetType implements Comparable<TreeType>{
        public TreeType(int n){
            super(n);
        }

        @Override
        public int compareTo(TreeType o) {
            return o.i < i ? -1:(i == o.i ? 0 : 1);
        }

    }

    static class TypesForSets{
        static <T> Set<T> fill(Set<T> set , Class<T> type){
            try {
                for(int i=0;i<10;i++)
                    set.add(type.getConstructor(int.class).newInstance(i));
            }catch (Exception e){
                e.printStackTrace();
            }
            return set;
        }
        static void test(Set<T> set,Class<T> type){
            fill(set,type);
            fill(set,type);
            fill(set,type);
            System.out.println(set);
        }
    }

    /**
     * (30)测试死循环
     * @param i
     * @return
     */
    public static String say(int i){
        if(i == 3)
            return say(4);
        return "1";
    }

    /**
     * 三元运算符与数组去重
     */
    public static void sany(String failInstituName){
        //failInstituName = null;
       String str =  !"".equals(failInstituName)&&null!=failInstituName ? "海通证券股份有限公司".equals(failInstituName)?"true":"false":"false";
       String str1 =  !"".equals(failInstituName)&&null!=failInstituName && "海通证券股份有限公司".equals(failInstituName)?"true":"false";

        //System.out.println(str1);
        "aa".indexOf("aa");
       // Object

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(",666");
        stringBuffer.append(",666");
        stringBuffer.append(",667");
        stringBuffer.append(",667");
        stringBuffer.append(",669");
        String string = stringBuffer.toString();
        String[] array = string.split(",");



        if(string.length()>0) {
            String[] split = string.split(",");
            Set<String> set = new HashSet<>();
            Collections.addAll(set, split);
            String[] sset = new String[set.size()];
            //Collections.addAll(set, split);
            //String[] sset = new String[set.size()];

           /* List<String> resultList= new ArrayList<>(Arrays.asList(array));
            Set<String> set = new HashSet<>(resultList);
            String[] sset = new String[set.size()];*/
            set.toArray(sset);
            stringBuffer = new StringBuffer();
            for (int i=1;i<sset.length;i++){
                stringBuffer.append(","+sset[i]);
                System.out.println(sset[i]);
            }
            System.out.println(stringBuffer.toString());
        }
        //System.out.println(set);

    }


    /**
     * Code shared by String and StringBuffer to do searches. The
     * source is the character array being searched, and the target
     * is the string being searched for.
     *
     * @param   source       the characters being searched.
     * @param   sourceOffset offset of the source string.
     * @param   sourceCount  count of the source string.
     * @param   target       the characters being searched for.
     * @param   targetOffset offset of the target string.
     * @param   targetCount  count of the target string.
     * @param   fromIndex    the index to begin searching from.
     */
    static int indexOf(char[] source, int sourceOffset, int sourceCount,
                       char[] target, int targetOffset, int targetCount,
                       int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }

        char first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first);
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j]
                        == target[k]; j++, k++);

                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }

    /**
     * (32)foreach中用final关键字
     */
    public static void doforeach(){
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("1");
//        arrayList.add("2");
//        arrayList.add("3");
        ArrayList<ForeachTest> arrayList1 = new ArrayList<>();
        arrayList1.add(new ForeachTest("小明"));
        arrayList1.add(new ForeachTest("小华"));
        arrayList1.add(new ForeachTest("小丽"));
        final ForeachTest foreachTest1 = new ForeachTest("图图");
        for (final ForeachTest str :arrayList1){
            //在for的()中类似在后边的{}中,
            final ForeachTest foreachTest = str;
            System.out.println(str.name);
            System.out.println(foreachTest.name);
        }
    }

    public static class ForeachTest{
        String name ;
        ForeachTest(String name){
            this.name = name;
        }
    }

    public static void json(){
        //JSONObject jsonObject = JSONObject.fromObject("");
        String s = "adc";
        String[] split = s.split(",");
        for (int i=0;i<split.length;i++)
            System.out.println(split[i]);

    }

    public void insert(){
        //逗号分隔字符串
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuffer sb= new StringBuffer();
        for(int i = 0; i < arrayList.size(); ++i) {
            sb.append(i == 0 ? "" : ", ").append(arrayList.get(i));
        }
    }
}