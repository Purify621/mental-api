DROP TABLE IF EXISTS student;
# 创建表
CREATE TABLE student (
  id BIGINT PRIMARY KEY AUTO_INCREMENT comment 'id',
	sid VARCHAR(255) unique comment '学号',
	username VARCHAR(255) comment '账号',
	password VARCHAR(255) comment '密码',
    name VARCHAR(255) comment '姓名',
    age INT comment '年龄',
    gender CHAR(1) comment '性别',
    grade VARCHAR(255) comment '年纪',
    college VARCHAR(255) comment '学院',
    school VARCHAR(255) comment '学校',
	address VARCHAR(255) comment '地址',
	avatar VARCHAR(255) comment '头像',
	signature VARCHAR(255)  comment '个性签名'
);

insert into student (`sid`,`username`, `password`, `name`, `age`, `gender`, `grade`, `college`, `school`, `address`, `avatar`, `signature`)
values ('202222301400', 'student', 'student', '廖耀杰', 18, '男', '19级', '信息工程学院', '郑州科技学院', '库尔勒', 'https://passport.baidu.com/v6/ucenter', '日暮诗成天又雪，与梅并作十分春')
, ('202222301401', 'student1', 'student1', '赖越泽', 20, '男', '19级', '信息工程学院', '郑州科技学院', '梅州', 'https://passport.baidu.com/v6/ucenter', ' 日暮苍山远，天寒白屋贫')
, ('202222301402', 'student2', 'student2', '蔡晟睿', 22, '女', '19级', '信息工程学院', '郑州科技学院', '聊城', 'https://passport.baidu.com/v6/ucenter', ' 晴川落日初低，惆怅孤舟解携')
, ('202222301403', 'student3', 'student3', '严琪', 23, '女', '19级', '信息工程学院', '郑州科技学院', '临安', 'https://passport.baidu.com/v6/ucenter', ' 梅花不肯傍春光，自向深冬著艳阳')
, ('202222301404', 'student4', 'student4', '彭越彬', 20, '女', '19级', '信息工程学院', '郑州科技学院', '青岛', 'https://passport.baidu.com/v6/ucenter', ' 竹外一枝斜，想佳人，天寒日暮')
, ('202222301405', 'student5', 'student5', '董思淼', 20, '女', '19级', '信息工程学院', '郑州科技学院', '昆明', 'https://passport.baidu.com/v6/ucenter', ' 侵陵雪色还萱草，漏泄春光有柳条')
, ('202222301406', 'student6', 'student6', '严浩轩', 21, '男', '19级', '信息工程学院', '郑州科技学院', '葫芦岛', 'https://passport.baidu.com/v6/ucenter', ' 绿池芳草满晴波，春色都从雨里过')
, ('202222301407', 'student7', 'student7', '莫苑博', 21, '男', '19级', '信息工程学院', '郑州科技学院', '克拉玛依', 'https://passport.baidu.com/v6/ucenter', ' 灞陵千万树，日暮别离回')
, ('202222301408', 'student8', 'student8', '唐睿渊', 21, '男', '18级', '信息工程学院', '郑州科技学院', '攀枝花', 'https://passport.baidu.com/v6/ucenter', ' 千古斜阳，无处问长安')
, ('202222301409', 'student9', 'student9', '贾致远', 22, '男', '18级', '信息工程学院', '郑州科技学院', '株洲', 'https://passport.baidu.com/v6/ucenter', ' 游人日暮相将去，醒醉喧哗')
, ('202222301410', 'student10', 'student10', '覃振家', 21, '男', '18级', '信息工程学院', '郑州科技学院', '阳泉', 'https://passport.baidu.com/v6/ucenter', ' 暮霭生深树，斜阳下小楼')
, ('202222301411', 'student11', 'student11', '姚修杰', 23, '男', '18级', '信息工程学院', '郑州科技学院', '洛阳', 'https://passport.baidu.com/v6/ucenter', ' 芳草无情，更在斜阳外')
, ('202222301412', 'student12', 'student12', '雷荣轩', 19, '男', '18级', '信息工程学院', '郑州科技学院', '常熟', 'https://passport.baidu.com/v6/ucenter', ' 日暮，望高城不见，只见乱山无数')
, ('202222301413', 'student13', 'student13', '任钰轩', 20, '女', '18级', '信息工程学院', '郑州科技学院', '荣成', 'https://passport.baidu.com/v6/ucenter', ' 常记溪亭日暮，沉醉不知归路')
, ('202222301414', 'student14', 'student14', '秦风华', 21, '男', '18级', '信息工程学院', '郑州科技学院', '常州', 'https://passport.baidu.com/v6/ucenter', ' 日暮乡关何处是，烟波江上使人愁')
, ('202222301415', 'student15', 'student15', '钟绍辉', 20, '男', '18级', '信息工程学院', '郑州科技学院', '营口', 'https://passport.baidu.com/v6/ucenter', ' 高卷水晶帘额、衬斜阳')
, ('202222301416', 'student16', 'student16', '陆天磊', 22, '男', '18级', '信息工程学院', '郑州科技学院', '章丘', 'https://passport.baidu.com/v6/ucenter', ' 长江一帆远，落日五湖春')
, ('202222301417', 'student17', 'student17', '曹峻熙', 22, '男', '18级', '信息工程学院', '郑州科技学院', '包头', 'https://passport.baidu.com/v6/ucenter', ' 春意已无多，斜日满帘飞燕')
, ('202222301418', 'student18', 'student18', '韩绍辉', 21, '男', '18级', '信息工程学院', '郑州科技学院', '营口', 'https://passport.baidu.com/v6/ucenter', ' 浮云游子意，落日故人情');

DROP TABLE IF EXISTS teacher;

CREATE TABLE teacher (
    id BIGINT PRIMARY KEY AUTO_INCREMENT comment 'id',
	tid VARCHAR(255) unique comment '工号',
	username VARCHAR(255) comment '账号',
	password VARCHAR(255) comment '密码',
    name VARCHAR(255) comment '姓名',
    age INT comment '年龄',
    gender CHAR(1) comment '性别',
    title VARCHAR(255) comment '职称',
    department VARCHAR(255) comment '部门',
    school VARCHAR(255) comment '学校',
	avatar VARCHAR(255) comment '头像',
	signature VARCHAR(255) comment '个性签名'
);

insert into mental.`teacher` (`tid`,`username`, `password`, `name`, `age`, `gender`, `title`, `department`, `school`, `avatar`, `signature`)
values ('202202031901', 'teacher', 'teacher', '赖雪松', 45, '男', '高级教师', '信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '仙路尽头谁为峰，一见无始道成空')
, ('202202031902', 'teacher1', 'teacher1', '林熠彤', 40, '男', '高级教师', '电子信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '千秋无绝色，悦目为佳人')
, ('202202031903', 'teacher2', 'teacher2', '邱晟睿', 43, '男', '高级教师', '信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '不以物喜，不以己悲')
, ('202202031904', 'teacher3', 'teacher3', '贺博文', 43, '男', '高级教师', '信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '在隆冬，我终于知道，我身上有一个不可战胜的夏天')
, ('202202031905', 'teacher4', 'teacher4', '尹昊天', 40, '男', '高级教师', '土木工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '夜雨声烦以极其强硬的姿态，骑士般地护卫在了索克萨身前')
, ('202202031906', 'teacher5', 'teacher5', '廖鹏', 45, '男', '教授', '信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '在每一个历史断面上，你都能找到一大堆丢失的机遇')
, ('202202031907', 'teacher6', 'teacher6', '刘旭尧', 42, '男', '高级教师', '信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '为学日益，为道日损')
, ('202202031908', 'teacher7', 'teacher7', '苏子默', 35, '男', '高级教师', '信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '生命中，再无聊的时光，也都是限量版')
, ('202202031909', 'teacher8', 'teacher8', '史锦程', 37, '男', '高级教师', '信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '我们登上并非我们所选择的舞台，演出并非我们所选择的剧本')
, ('202202031910', 'teacher9', 'teacher9', '贾雨泽', 40, '女', '教授', '外国语学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '我们登上并非我们所选择的舞台，演出并非我们所选择的剧本')
, ('202202031911', 'teacher10', 'teacher10', '马皓轩', 41, '男', '高级教师', '电子信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '明月也不曾记得它点亮过世界')
, ('202202031912', 'teacher11', 'teacher11', '石瑾瑜', 38, '男', '高级教师', '信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '爱欲于人，犹如执炬，逆风而行，必有烧手之患')
, ('202202031913', 'teacher12', 'teacher12', '周弘文', 38, '男', '高级教师', '外国语学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', '人能够能动地认识世界；人能够能动地改造世界')
, ('202202031914', 'teacher13', 'teacher13', '张风华', 40, '女', '高级教师', '信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', ' 剩水残山无态度，被疏梅料理成风月')
, ('202202031915', 'teacher14', 'teacher14', '孟晟睿', 39, '女', '教授', '外国语学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', ' 来日绮窗前，寒梅著花未')
, ('202202031916', 'teacher15', 'teacher15', '贺锦程', 41, '女', '高级教师', '信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', ' 岑寂，高柳晚蝉，说西风消息')
, ('202202031917', 'teacher16', 'teacher16', '邓熠彤', 40, '女', '高级教师', '信息工程学院', '郑州科技学院', 'https://passport.baidu.com/v6/ucenter', ' 木叶纷纷归路，残月晓风何处');


DROP TABLE IF EXISTS admin;

CREATE TABLE admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT comment 'id',
    username VARCHAR(255) comment '用户名',
    password VARCHAR(255) comment '密码',
    email VARCHAR(255) comment '电子邮件',
    role VARCHAR(255) comment '角色',
	avatar VARCHAR(255) comment '头像信息'
);

insert into mental.`admin` (`username`, `password`,`email`, `role`,`avatar`) VALUES('admin','admin','2530403643@qq.com','SuperAdmin','https://passport.baidu.com/v6/ucenter')

################################################################
# 题库表
drop table if exists tb_question_bank;
create table tb_question_bank
(
    id      bigint auto_increment comment 'id'
        primary key,
    title   varchar(255) null comment '标题',
    details varchar(255) null comment '详情',
    number int default 0 comment '人数',
    status  int default 0 comment '状态  0启用，1禁用'
);

# 问题表
drop table if exists tb_question;
create table tb_question
(
    id      bigint auto_increment comment 'id'
        primary key,
    bank_id bigint       null comment '题库id',
    title   varchar(255) null comment '标题'
);

# 答案表
drop table if exists tb_answer;
create table tb_answer
(
    id           bigint auto_increment comment 'id'
        primary key,
    question_id bigint null comment '问题id',
    answer       varchar(255) null comment '答案',
    answer_score int         null comment '答案得分'
);