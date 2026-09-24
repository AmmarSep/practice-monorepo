// write a java code - if condition for subahanallah, alhamdulilah, allahu akbar
// by default it should be subahanallah dhirk, if anything out of sudden happens then it should be allahu akbar
// if it is a good news then it should be alhamdulilah

public class dhikr {
    public static void main(String[] args) {
        String dhikr = "subahanallah";
        String news = "good news";
        if (news.equals("good news")) {
            dhikr = "alhamdulilah";
        } else {
            dhikr = "allahu akbar";
        }
        System.out.println(dhikr + " dhikr");
    }
}