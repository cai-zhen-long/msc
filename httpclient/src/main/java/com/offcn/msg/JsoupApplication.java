package com.offcn.msg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.text.html.HTML;

/**
 * Created by Administrator on 2019/10/11.
 */
public class JsoupApplication {
    public static void main(String[] args) {
        String html="    <html>\n" +
                "        <head>\n" +
                "            <title>First parse</title>\n" +
                "        </head>\n" +
                "        <body>\n" +
                "            <p id='doc1' value='sdf'>Parsed HTML into a doc 01.</p>\n" +
                "            <p>Parsed HTML into a doc 02.</p>\n" +
                "        </body>\n" +
                "    </html>";


        Document document = Jsoup.parse(html);

        System.out.println(document.select("html body p").get(0).attr("value"));
    }
}
