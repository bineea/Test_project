package test_project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class Test_LinqCollection<T> extends java.util.ArrayList<T> {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5042194313851746204L;

    public Test_LinqCollection(){
        super();
    }

    public Test_LinqCollection(Collection<? extends T> c){
        super(c);
    }

    /**
     * 查找首条符合条件的记录
     * @param predicate 条件
     * @return
     */
    public T find(Predicate<T> predicate){
        for(T item: this){
            if(predicate.evaluate(item)){
                return item;
            }
        }
        return null;
    }

    /**
     * 按条件删除
     * @param predicate
     */
    public void remove(Predicate<T> predicate){
        ListIterator<T> i = this.listIterator();
        while(i.hasNext()){
            T c = i.next();
            if(predicate.evaluate(c)){
                i.remove();
            }
        }
    }

    class ComparableItem<T> implements Comparable{

        T data;
        Fun<T,  ?> keySelect =null;
        public  <T2 extends Comparable<? super T2>> ComparableItem(T item,Fun<T,T2> keySelect){
            this.keySelect = keySelect;
            this.data = item;
        }

        public int compareTo(Object o) {
            return ((Comparable)(this.keySelect.process(this.data))).compareTo((Comparable)(this.keySelect.process(((ComparableItem<T>)o).data)));
        }

    }

    /**
     * 选择
     * @param keySelect
     * @return
     */
    public <T2> List<T2> select(Fun<T,T2> keySelect){
        Set<T2> result = new HashSet<T2>();
        for(T item : this){
            T2 t2=keySelect.process(item);
            if(null!=t2){
                result.add(t2);
            }
        }
        return new ArrayList<T2>(result);
    }

    public void foreach(Predicate1<T> predicate){
        for(T item : this){
            predicate.foreach(item);
        }
    }

    public <T2> T2 sum(Fun<T,T2> keySelect){
        Object object=0;
        T2 t2=(T2) object;
        for(T item : this){
            T2 tmp=keySelect.process(item);
            if(tmp instanceof BigDecimal){
                t2=(T2)new BigDecimal(t2.toString()).add((BigDecimal)tmp);
            }
            if(tmp instanceof Integer){
                t2=(T2)(Integer)(((Integer)t2)+((Integer)tmp));
            }
        }
        return t2;
    }

    /**
     * 按指定字段排序
     * @param keySelect（选择排序的字段）
     */
    public <T2 extends Comparable<? super T2>> void sort(Fun<T,T2> keySelect){
        List<ComparableItem<T>> items = new ArrayList<>();
        for(T item : this){
            items.add(new ComparableItem<T>(item, keySelect));
        }
        Collections.sort(items);
        ListIterator i = this.listIterator();
        for (int j=0; j<items.size(); j++) {
            i.next();
            i.set(items.get(j).data);
        }
    }

    /**
     * 查找所有符合条件的记录
     * @param predicate
     * @return
     */
    public Test_LinqCollection<T> findAll(Predicate<T> predicate){
    	Test_LinqCollection<T> result = new Test_LinqCollection<T>();
        for(T item: this){
            if(predicate.evaluate(item)){
                result.add(item);
            }
        }
        return result;
    }

    public List<T> findAllArrayList(Predicate<T> predicate){
        List<T> result = new ArrayList<>();
        for(T item: this){
            if(predicate.evaluate(item)){
                result.add(item);
            }
        }
        return result;
    }

    /**
     * 是否存在
     * @param predicate
     * @return
     */
    public boolean exist(Predicate<T> predicate){
        return this.find(predicate)!=null;
    }

    public static void main(String[] args){
        List<Map<String,Object>> mapList=new ArrayList<Map<String, Object>>();
        Map<String,Object> map;
        for(int i=0;i<10;i++){
            map=new HashMap<String, Object>();
            map.put("username","username");
            map.put("age",i);
            map.put("age1",1);
            map.put("kg",new BigDecimal("3.22").add(new BigDecimal(i)));
            mapList.add(map);
        }
        List<Integer> stringList=new Test_LinqCollection<Map<String,Object>>(mapList).select(new Fun<Map<String,Object>, Integer>() {
            public Integer process(Map<String,Object> item) {
                if((Integer)item.get("age")>5){
                    return Integer.parseInt(item.get("age1").toString()) ;
                }
                return null;
            }
        });
        int count=new Test_LinqCollection<Map<String,Object>>(mapList).findAll(new Predicate<Map<String, Object>>() {
            public boolean evaluate(Map<String, Object> item) {
                return (Integer)item.get("age")>=5;
            }
        }).size();

        int ageSum=new Test_LinqCollection<Map<String,Object>>(mapList).sum(new Fun<Map<String, Object>, Integer>() {
            public Integer process(Map<String, Object> item) {
                return (Integer)item.get("age");
            }
        });

        BigDecimal kgSum=new Test_LinqCollection<Map<String,Object>>(mapList).sum(new Fun<Map<String, Object>, BigDecimal>() {
            public BigDecimal process(Map<String, Object> item) {
                return new BigDecimal(item.get("kg").toString());
            }
        });
        System.out.println("岁数大于等于5岁的人数为"+count);
        System.out.println("所有人的岁数相加为"+ageSum);
        System.out.println("所有人的岁数相加为"+kgSum.toString());
    }
}

interface Predicate<T> {
    /**
     * 是否满足
     * @param item
     * @return
     */
    public boolean evaluate(T item);
}

interface Fun<T1,T2> {
    public T2 process(T1 item);
}

interface Predicate1<T> {

    /**
     * for循环
     * @param item
     */
    public void foreach(T item);
}

