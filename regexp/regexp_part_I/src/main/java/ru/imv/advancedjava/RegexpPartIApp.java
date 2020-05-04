package ru.imv.advancedjava;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegexpPartIApp
 */
public class RegexpPartIApp {
    public static void main(String[] args) {
        /*RegExLib.com*/
        String text = "Every day, the citizens of the Internet send each other billions of e-mail messages." +
                " If you're online a mm@yahoo.com lot, you yourself may send a dozen or more e-mails each day without even " +
                "thinking about it. Obviously, e-mail tt@gmail.com has become an extremely popular communication tool. " +
                "Have you ever wondered how e-mail gets from your computer to a friend halfway around the world? " +
                "What is a POP3 server, and how does it hold ww@ya.ru your mail? The answers may surprise you, because it turns out that e-mail is an " +
                "incredibly simple system at its core. d.d.mss_1o@mm.moscow";
        Pattern emailPattern = Pattern.compile("([\\w\\.]+)@(\\w+)\\.(\\w{2,6})");
        Matcher matcher = emailPattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group() + " - " + matcher.group(2) + "." + matcher.group(3));
        }
    }
}
