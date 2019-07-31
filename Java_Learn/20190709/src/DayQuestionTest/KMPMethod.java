package DayQuestionTest;

/**
 * @Classname DayQuestionTest.KMPMethod
 * @Description TODO
 * @Date 2019/7/22 17:13
 * @Created by AppleTree
 */
public class KMPMethod {

    public static void prefix_table(String pattern, int[] prefix){
        prefix[0] = 0;
        int len = 0;
        int i = 1;
        while (i < prefix.length){
            if(pattern.charAt(i) == pattern.charAt(len)){
                len += 1;
                prefix[i] = len;
                i += 1;
            }else {
                if( len > 0) {
                    len = prefix[len - 1];
                }
                else {
                    prefix[i] = len;
                    i ++;
                }
            }
        }
    }

    public static  void move_table(int[] prefix){
        for (int i = prefix.length - 1; i > 0 ; i--) {
            prefix[i] = prefix[i - 1];
        }
        prefix[0] = -1;
    }

    public static boolean KMP(String text, String pattern){
        int n = pattern.length();
        int m = text.length();
        int[] prefix = new int[n];
        prefix_table(pattern,prefix);
        move_table(prefix);
        int i = 0;
        int j = 0;
        while (i < m){
            if(j == n-1 && text.charAt(i) == pattern.charAt(j)){
                return true;
            }
            if(text.charAt(i) == pattern.charAt(j)){
                i += 1;
                j += 1;
            }else {
                j = prefix[j];
                if(j == -1){
                    i += 1;
                    j += 1;
                }
            }
        }
        return false;
    }

    public boolean[] chkSubStr(String[] p, int n, String s) {
        // write code here
        boolean[] res = new boolean[p.length];
        for (int i = 0; i < p.length; i++) {
            if(KMP(s, p[i])){
                res[i] = true;
            }else {
                res[i] = false;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String pattern = "ABABCABAA";
        String text    = "ABABCABAASDLKAJDLAKSDJALJD";
        boolean res = KMP(text, pattern);
        System.out.println(res);
    }
//         int[] prefix = new int[pattern.length()];
//         prefix_table(pattern,prefix);
//        for (int i = 0; i < prefix.length; i++) {
//            System.out.println(prefix[i]);
//        }
//    }

//    public static int[] getNext(String target){
//        int[] next = new int[target.length()];
//        next[0] = -1; //一个字母的时候，不存在相等最长前缀和最长后缀，所以值为0；
//        int k = -1;//若下一个待求位置是next[i+1],则k的初始值为next[i],因为next[0]是固定的，下一个待求位置是next[1]，
//        // 所以k=next[0]=-1
//
//        /*
//         * 这里更符合意义的写法是：
//         * int i = 0;
//         * next[i] = -1;
//         * int k = next[i];
//         * 直接给k=-1，如果不太理解过程就很难明白到底在干嘛，还有就是需要注意，k+1就等于我们上面谈到的m
//         */
//
//        for(int i = 1; i < target.length(); i++){
//            while(k > -1 && target.charAt(k + 1) !=  target.charAt(i)){
//                //能运行到这里，就说明不是我们最希望的状况，而这个循环就是当状况不好时，退而求其次，“缩短”能偷得懒。
//                //k > -1有两个作用，1是是防止访问越界2是k如果<=-1表示不存在最长前/后缀，就没有必要找了
//                k = next[k];
//            }
//            //跳出循环有两种情况，一种是找到了一个缩短后能用的，一个就是k等于-1了
//            if (target.charAt(k + 1) ==  target.charAt(i)){
//                k = k + 1;
//            }
//            next[i] = k;
//        }
//
//        return next;
//    }
//
//    public static int KMP(String target, String source){
//        int next[] = getNext(target);
//        int k = next[0];
//        for(int i = 0; i < source.length(); i++){
//            while(k > -1 && target.charAt(k + 1) != source.charAt(i)){
//                k = next[k];
//            }
//            if(target.charAt(k + 1) == source.charAt(i)){
//                k = k + 1;//成功匹配一个节点
//            }
//            if(k == target.length()-1){//上面一直说k等于已经匹配的长度-1
//                return i - target.length() + 1;
//            }
//
//        }
//        return -1;
//    }

}
