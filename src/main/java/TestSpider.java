import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author: laoyu
 * @Date: 2020/4/28 22:39
 * @Description:
 */
public class TestSpider {
    public static void main(String[] args) throws IOException {
//        spider();
        spiderWithPaper();
    }

    public static void spider() {
        String url = "https://www.lagou.com/jobs/6778599.html";
        Connection connection = Jsoup.connect(url).timeout(3000);
        try {
            Document document = connection.get();
//            System.out.println(document.html());

            Elements elements = document.select("body > div.position-head > div > div.position-content-l");
//            System.out.println(elements.html());
            String jobName = elements.select("div > h1").text();
            String salary = elements.select("dd > h3 > span.salary").text();
            String jobAddress = elements.select("dd > h3 > span:nth-child(2)").text().replaceAll("/","");
            String workExperience = elements.select("dd > h3 > span:nth-child(3)").text().replaceAll("/","");
            String educationalBackground = elements.select("dd > h3 > span:nth-child(4)").text().replaceAll("/","");
            String description = document.select("#job_detail > dd.job_bt > div").text();


            System.out.println("职位名称：" + jobName);
            System.out.println("薪资：" + salary);
            System.out.println("工作地址：" + jobAddress);
            System.out.println("工作经验：" + workExperience);
            System.out.println("学历要求：" + educationalBackground);
            System.out.println("职位描述：" + description);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void spiderWithPaper(){
        String pageUrlList= "https://www.lagou.com/beijing-zhaopin/Java/";
        //10页
        for (int i = 1; i <= 10; i++) {
            String listUrl = pageUrlList+i+"/";
            Connection connection = Jsoup.connect(listUrl).timeout(30000);
            try {
                Document document = connection.get();
                Elements elements = document.select("#s_position_list > ul > li:nth-child(1) > div.list_item_top > div.position > div.p_top > a");
                for (int j = 0; j < elements.size(); j++) {
                    System.out.println(elements.get(j).attr("href"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
