package one;
import java.util.Arrays;
import java.util.Random;

/**
 * 选择问题是指从N个数当中，按升序（降序也可以）排列，找出第k个数。
 * 编写一个程序解决选择问题。令k=N/2，画出表格显示程序对于N种不同的值的运行时间。
 * Created by ydx on 2016/8/7.
 */
public class Practice1_1 {

    enum  SortMode{
        all,
        group
    }

    public static void main(String [] args){
        Practice1_1 practice=new Practice1_1();
        int n[]= practice.createN(new Random().nextInt(10000));
        int k=n.length/2;
        practice.printResult(n,k,SortMode.all);
        practice.printResult(n,k,SortMode.group);
    }

    /**
     *  sort
     * @param n
     * @param k
     * @return
     */
    private int getK(int[] n,int k,SortMode mode ){
        int result=0;
        switch (mode) {
            case all:
                sortN(n);
                result= n[k-1];
                break;
            case group:
                //分割前k个
                int[] m = Arrays.copyOf(n, k);
                // 2200 122 21 333 2 23 443 5676  32  10
                sortN(m);
                //2  21 122 333 2200 23 443 5676  32  10
                //遍历分割后剩余数组
                for (int i = k; i < n.length; i++) {
                    //如果当前元素小于 已排序的最后一个元素 将其插入到已排序数组的相应位置
                    if (m[k - 1] > n[k]) {
                        int z = n[k];
                        for (int j = k - 2; j > 0; j--) {
                            m[j + 1] = m[j];//移位
                            if (z > m[j]) {
                                m[j + 1] = z;//插入
                                break;
                            }
                        }
                    }
                }
                result= m[k-1];
                break;
        }
       return result;
    }

    private void printResult(int[] n,int k,SortMode mode){
        System.out.println("mode:"+mode);
        System.out.println("length:"+n.length);
        long startMs=System.currentTimeMillis();
        System.out.println("resust:"+getK(n,k,mode));
        long time=System.currentTimeMillis()-startMs;
        System.out.println("cost:"+time+"ms");
        System.out.println("=============================================");
    }

    /**
     * 升序  冒泡
     * @param n
     */
    private void sortN(int [] n){
        for(int j=0;j<n.length-1;j++) {
            for (int i = 0; i < n.length - 1; i++) {
                if (n[i + 1] - n[i] < 0) {
                    int m = n[i + 1];
                    n[i + 1] = n[i];
                    n[i] = m;
                }
            }
        }
    }

    /**
     * 创建随机数组
     * @param length
     * @return
     */
    private int [] createN(int length){
        int[] n=new int[length];
        for (int i=0;i<length;i++){
            n[i]=new Random().nextInt(length*2);
        }
        return n;
    }

}
