package one;

import java.util.Random;
import java.util.Scanner;

/**编写一个程序 求解字谜游戏问题
 *没有什么好的想法呀，也不是很理解猜字谜到底是什么意思
 *
 * 现用26个字母 随机生成一个字符串，键盘输入，判断是否正确 不正确给出提示继续输入
 * Created by ydx on 2016/8/9.
 */
public class Practice1_2 {
    public static void main(String[] args){
        Practice1_2 practice=new Practice1_2();
        String word=practice.createWord();
        System.out.println("word length is "+word.length()+",please input a word");
        practice.reTry(practice.input(),word);
    }
    char[] letter={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private StringBuilder inputRight=new StringBuilder();
    private String createWord(){
        int length= new Random().nextInt(13);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            sb.append(letter[new Random().nextInt(26)]);
        }
       return sb.toString();
    }
    private String input(){
        Scanner scanner=new Scanner(System.in);
        return scanner.next();
    }
    private boolean isRight(String inWord,String word){
        String w=inputRight.toString()+inWord;
        if(w.length()!=word.length()){
            System.out.println("please input correct length word");
            return false;
        }
        char[] inLetters=w.toCharArray();
        char[] letters=word.toCharArray();
        for (int i=0;i<inLetters.length;i++){
            if(((int) inLetters[i])== ((int) letters[i])){
                if(i>=inputRight.length()) {
                    inputRight.append(inLetters[i]);
                }
                System.out.println("u guess the "+(i+1)+" letter");
                continue;
            }else if(((int) inLetters[i])> ((int) letters[i])){
                System.out.println("u input the "+(i+1)+" letter is behind correct");

                return false;
            }else {
                System.out.println("u input the "+(i+1)+" letter is above correct");
                return false;
            }
        }
        return true;
    }
    private void reTry(String in,String word){
        if(isRight(in,word)){
            System.out.println("you r right");
        }else {
            System.out.print(inputRight.toString());
            reTry(input(),word);
        }
    }
}
