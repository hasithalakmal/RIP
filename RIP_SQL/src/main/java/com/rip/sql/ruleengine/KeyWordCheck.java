package com.rip.sql.ruleengine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sasanka
 */
@Component
public class KeyWordCheck {

    public String controler(String sample_string) {
        String msg_kw = "success";
        String key_words[] = {"create", "public", "this", "new", "return"};
        for (int i = 0; i < key_words.length; i++) {
            Pattern p = Pattern.compile("\\b"+key_words[i]+"\\b");
            Matcher m = p.matcher(sample_string);
            boolean isContainkey = m.find();
            if (isContainkey) {
                msg_kw = "Key word exsist in your design = " + key_words[i];
            }
        }
        return msg_kw;
    }
}
