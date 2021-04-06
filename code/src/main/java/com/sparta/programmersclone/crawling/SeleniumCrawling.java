package com.sparta.programmersclone.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class SeleniumCrawling {

    private WebDriver driver;
    private WebElement element;
    private String url;

    // 1. 드라이버 설치 경로
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "/Users/geonyeolpark/Documents/Hanghae99/chromedriver";

    public SeleniumCrawling() {
        // WebDriver 경로 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // 2. WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);

        url = "https://programmers.co.kr/learn/challenges?tab=all_challenges";
    }

    public String[][] activateBot() throws InterruptedException {
        driver.get(url);
        Thread.sleep(1000);
        int[] endPage = {0, 3, 4, 3, 2, 1}; // 난이도별 페이지수 저장
        int problemNum = 1;
//
        String AllInfo[][] = new String[211][5 + 1]; // 전체 문제 개수가 211 ??

        for (int level = 1; level <= 5; level++) {
            System.out.println("@@@@@@@@@@@난이도 : " + level);
            if (level >= 2) {
                int prevLevel = level - 1;
                Thread.sleep(3000);
                driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/section/div/div[1]/div[1]/ul/li[" + prevLevel + "]/label/span")).click();

                // 초기화 버튼 클
                //*[@id="resetFilter"]
//                driver.findElement(By.xpath("//*[@id=\"resetFilter\"]")).click();
                Thread.sleep(1000);
            }
            driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/section/div/div[1]/div[1]/ul/li[" + level + "]/label/span")).click();
            Thread.sleep(1000);


            for (int page = 1; page <= endPage[level]; page++) { // 마지막 페이지로 긁어와서 설정
                try {
                    System.out.println("\n\n\n**********************************************************************************");
                    System.out.println(page + "페이지");
                    System.out.println("**********************************************************************************");

                    String html = driver.getPageSource();
                    String[] divideProblems = html.split("<div class=\"col-item\">");
                    String[][] problemLanguages = new String[divideProblems.length][];

                    for (int i = 1; i < divideProblems.length; i++) {
                        String[] languages = divideProblems[i].split("data-original-title=\"");
                        problemLanguages[i] = new String[languages.length];
                        for (int j = 1; j < languages.length; j++) {
                            problemLanguages[i][j] = languages[j].split("\"")[0];
                        }
                    }

                    List<WebElement> el1 = driver.findElements(By.className("col-item"));
                    for (int i = 0; i < el1.size(); i++) {
                        System.out.println("----------------------------------------------------------");
                        System.out.println(problemNum + " : " + el1.get(i).getText());
                        String problemInfo = el1.get(i).getText();

                        String[] enterTok = problemInfo.split("\n");

                        // 1.제목
                        String problemTitle = enterTok[0];
                        //                    System.out.println(problemTitle);

                        // 2.완료자수
                        String finishedCount = enterTok[1].split("명 완료")[0];
                        String[] spaceTok = finishedCount.split(" ");
                        finishedCount = spaceTok[spaceTok.length - 1];
                        //                    System.out.println(finishedCount);


                        // 3.출처
                        String problemSource = "";
                        for (int j = 0; j < spaceTok.length - 1; j++) {
                            //                        System.out.print(spaceTok[j] + " ");
                            problemSource += spaceTok[j] + " ";
                        }
                        //                    System.out.println(problemSource);

                        // 4.지원언어
                        String problemLanguage = "";
                        //                    System.out.print("지원 언어 : ");
                        for (int j = 1; j < problemLanguages[i + 1].length; j++) {
                            //                        System.out.print(problemLanguages[i + 1][j] + " ");
                            problemLanguage += problemLanguages[i + 1][j] + " ";
                        }
                        //                    System.out.println(problemLanguage);

                        // 5. 문제 난이도

                        AllInfo[problemNum][1] = problemTitle;
                        AllInfo[problemNum][2] = finishedCount;
                        AllInfo[problemNum][3] = problemSource;
                        AllInfo[problemNum][4] = problemLanguage;
                        AllInfo[problemNum][5] = String.valueOf(level);
                        problemNum++;
                    }
                    //                System.out.println("----------------------------------------------------------\n");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //*[@id="tab_all_challenges"]/section/div/div[2]/div[2]/div[2]/nav/ul/li[5]/a
                //*[@id="tab_all_challenges"]/section/div/div[2]/div[2]/div[2]/nav/ul/li[6]/a
                //*[@id="tab_all_challenges"]/section/div/div[2]/div[2]/div[2]/nav/ul/li[5]/a

                // 아래는 다음페이지로 이동하는 부분인데 페이지마다 리스트 크기가 조금씩 바뀌어서 저렇게 명시를 해주었음
                // 아마 조건 안나눌 방법이 있을 것 같음
                if (page < endPage[level]) {
                    int nextBtnPos = 2 + endPage[level];
                    driver.findElement(By.xpath("//*[@id=\"tab_all_challenges\"]/section/div/div[2]/div[2]/div[2]/nav/ul/li[" + nextBtnPos + "]/a")).click();
                }
                // 전체 문제 긁어오기
//                if (page == 11) break;
//                if (page <= 3) {
//                    driver.findElement(By.xpath("//*[@id=\"tab_all_challenges\"]/section/div/div[2]/div[2]/div[2]/nav/ul/li[9]/a")).click();
//                } else if (page == 4) {
//                    driver.findElement(By.xpath("//*[@id=\"tab_all_challenges\"]/section/div/div[2]/div[2]/div[2]/nav/ul/li[10]/a")).click();
//                } else if (page <= 7) {
//                    driver.findElement(By.xpath("//*[@id=\"tab_all_challenges\"]/section/div/div[2]/div[2]/div[2]/nav/ul/li[11]/a")).click();
//                } else if (page == 8) {
//                    driver.findElement(By.xpath("//*[@id=\"tab_all_challenges\"]/section/div/div[2]/div[2]/div[2]/nav/ul/li[10]/a")).click();
//                } else {
//                    driver.findElement(By.xpath("//*[@id=\"tab_all_challenges\"]/section/div/div[2]/div[2]/div[2]/nav/ul/li[9]/a")).click();
//                }
                Thread.sleep(500);
            }
        }
        // 2차원 배열 출력확인
//        for (int i = 1; i < problemNum; i++) {
//            System.out.print(i + " : ");
//            for (int j = 1; j < AllInfo[i].length; j++) {
//                System.out.println(AllInfo[i][j]);
//            }
//            System.out.println("--------------------------------------------");
//        }
        driver.close();
        return AllInfo;
    }

    public static void main(String[] args) throws InterruptedException {
        SeleniumCrawling seleniumCrawling = new SeleniumCrawling();
        seleniumCrawling.activateBot();
    }
}