package com.mental;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mental.pojo.Answer;
import com.mental.pojo.Question;
import com.mental.pojo.QuestionBank;
import com.mental.service.AnswerService;
import com.mental.service.QuestionBankService;
import com.mental.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.*;

@SpringBootTest()
public class ReadJsonTest {
    @Autowired
    private QuestionBankService questionBankService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    /**
     * 读取文件下的所有json文件
     */
    @Test
    void readFile() throws IOException {
        File file = new File("C:\\Users\\Lonely\\Desktop\\题库json\\1.json");
        if (!file.isDirectory()) {
            System.out.println("该文件不是文件夹！");
            return;
        }
        File[] files = file.listFiles();
        if (files == null){
            System.out.println("该文件夹下没有文件！");
            return;
        }
        for (File f : files) {
            readFileToDatabase(f);
        }
    }

    /**
     * 提取json文件中题库数据到数据库
     * @throws Exception
     */
    @Test
    void readJson() throws Exception {
        File file = new File("C:\\Users\\ME\\Desktop\\毕设\\题库json\\PDP性格测评.json");

        //读取文件中json数据
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(file, Map.class);

        //获取题库信息，并创建题库对象
        LinkedHashMap dataMap = (LinkedHashMap) map.get("data");
        String title = dataMap.get("title").toString(); //标题
        String details = dataMap.get("sub_title").toString(); //详情

        QuestionBank questionBank = new QuestionBank(title, details);
        //保存题库到数据库
        questionBankService.save(questionBank);
        Long bankId = questionBank.getId();

        //获取问题
        LinkedHashMap scaleMap = (LinkedHashMap) dataMap.get("scale");
        ArrayList contents = (ArrayList) scaleMap.get("contents");
        for (Object o : contents) {
            LinkedHashMap content = (LinkedHashMap)o;
            String questionTitle = content.get("title").toString();
            //创建问题对象
            Question question = new Question(bankId, questionTitle);
            //保存问题到数据库
            questionService.save(question);
            Long questionId = question.getId();

            //获取答案
            LinkedHashMap anwsers = (LinkedHashMap) content.get("anwsers");
            Set set = anwsers.keySet();
            for (Object o1 : set) {
                String key = o1.toString();
                LinkedHashMap answerMap = (LinkedHashMap) anwsers.get(key);
                //创建答案对象
                String str = answerMap.get("anwser").toString(); //答案
                Integer score = Integer.parseInt(answerMap.get("anwser_score").toString()); //分数
                Answer answer = new Answer(questionId,str, score);
                //保存答案到数据库
                answerService.save(answer);
            }
        }
    }

    /**
     * 将问价读取到数据库
     * @param file
     * @throws IOException
     */
    void readFileToDatabase(File file) throws IOException {
        //读取文件中json数据
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(file, Map.class);

        //获取题库信息，并创建题库对象
        LinkedHashMap dataMap = (LinkedHashMap) map.get("data");
        String title = dataMap.get("title").toString(); //标题
        String details = dataMap.get("sub_title").toString(); //详情
        QuestionBank questionBank = new QuestionBank(title, details);
        //保存题库到数据库
        questionBankService.save(questionBank);
        Long bankId = questionBank.getId();

        //获取问题
        LinkedHashMap scaleMap = (LinkedHashMap) dataMap.get("scale");
        ArrayList contents = (ArrayList) scaleMap.get("contents");
        for (Object o : contents) {
            LinkedHashMap content = (LinkedHashMap)o;
            String questionTitle = content.get("title").toString();
            //创建问题对象
            Question question = new Question(bankId, questionTitle);
            //保存问题到数据库
            questionService.save(question);
            Long questionId = question.getId();

            //获取答案
            LinkedHashMap anwsers = (LinkedHashMap) content.get("anwsers");
            Set set = anwsers.keySet();
            for (Object o1 : set) {
                String key = o1.toString();
                LinkedHashMap answerMap = (LinkedHashMap) anwsers.get(key);
                //创建答案对象
                String str = answerMap.get("anwser").toString(); //答案
                Integer score = Integer.parseInt(answerMap.get("anwser_score").toString()); //分数
                Answer answer = new Answer(questionId,str, score);
                //保存答案到数据库
                answerService.save(answer);
            }
        }
    }

    @Test
    public void testSplit(){
        String str = "1.admin";
        String[] split = str.split("\\.");
        System.out.println(Arrays.toString(split));
    }
}
