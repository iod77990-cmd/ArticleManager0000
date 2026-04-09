package org.example;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Article> articles = new LinkedHashMap<>();
        int lastId = 0;
        System.out.println("====프로그램 시작====");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("명령어 > ");
            String cmd = sc.nextLine().trim();
            if (cmd.equals("article write")) {
                System.out.print(" 제목: ");
                String title = sc.nextLine().trim();
                System.out.print(" 내용: ");
                String content = sc.nextLine().trim();
                lastId++;

                String regDate = Util.getNowStr();
                String updateDate = Util.getNowStr();
                Article article = new Article(lastId, title, content, regDate, updateDate);
                articles.put(lastId, article);
                System.out.printf("%d 게시물 작성\n", lastId);
            }

            else if (cmd.equals("article list")) {
                System.out.println("게시물의 리스트 입니다.");
                System.out.println("번호 | 제목 | 내용 | 작성일");
                if (articles.isEmpty()) {
                    System.out.println("게시물이 존재하지 않습니다.");
                    continue;
                }
                for (Integer id : articles.keySet()) { // 향상된 for문
                    Article article = articles.get(id);
                    System.out.printf("%d | %s | %s | %s\n", article.getId(), article.getTitle(), article.getContent(), article.getRegDate());
                }
            }

            else if (cmd.equals("article detail")) {
                System.out.println("게시물 상세를 볼 게시물 번호를 입력하세요.");
                System.out.print("게시판 번호 : ");
                int id = Integer.parseInt(sc.nextLine().trim());
                Article article = articles.get(id);
                if (article != null) {
                    System.out.printf("번호: %d | 제목: %s | 내용: %s | 작성일: %s | 수정일: %s\n" , article.getId(), article.getTitle(), article.getContent(), article.getRegDate(), article.getUpdateDate());
                }
                else{
                    System.out.println("해당 게시물은 존재하지 않습니다.");
                }
            }
            else if (cmd.equals("article delete")) {

                System.out.print("삭제할 게시물의 번호 : ");
                int id = Integer.parseInt(sc.nextLine().trim());

                if (articles.remove(id) != null) {
                    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
                } else {
                    System.out.println("존재하지 않는 게시물입니다.");
                }
            }
            else if (cmd.equals("article modify")) {
                System.out.print("수정할 게시물 번호 : ");
                int id = Integer.parseInt(sc.nextLine().trim());
                Article article = articles.get(id);
                if (article != null) {
                    System.out.print("새 제목: ");
                    article.setTitle(sc.nextLine().trim());
                    System.out.print("새 내용: ");
                    article.setContent(sc.nextLine().trim());

                    System.out.printf("%d번 게시물이 수정되었습니다.\n",id);
                }
                else{
                    System.out.println("존재하지 않는 게시물입니다.");
                }
            }
            else if (cmd.equals("exit")) {
                System.out.println("끝");
                break;
            } else{
                System.out.println("명령어 입력하세요.");
            }
        }
        System.out.println("====프로그램 종료====");
    }
}
class Article {
    private int id;
    private String title;
    private String content;
    private String updateDate;
    private String regDate;
    public Article(int id, String title, String content, String updateDate, String regDate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.updateDate = updateDate;
        this.regDate = regDate;
    }
    public int getId(){ return id; } // id getter
    public void setId(int id){ this.id = id; } // id setter
    public String getTitle(){ return title; } // title getter
    public void setTitle(String title){ this.title = title;
        this.updateDate = Util.getNowStr(); } // title setter
    public String getContent(){ return content; } // content getter
    public void setContent(String content){this.content = content;
        this.updateDate = Util.getNowStr(); } // content setter
    public String getRegDate(){ return regDate; } // regDate getter
    public  void setRegDate(String regDate){ this.regDate = regDate; } // regDate setter
    public String getUpdateDate(){ return updateDate; } // updateDate getter
    public void setUpdateDate(String updateDate){ this.updateDate = updateDate; } // update setter
}

