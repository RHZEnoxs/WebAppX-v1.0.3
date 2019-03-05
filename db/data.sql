-- dept_inf
INSERT  INTO dept_inf(ID,NAME,REMARK) VALUES (1,'技術部','技術部'),(2,'運營部','運營部'),(3,'財務部','財務部'),(5,'總公辦','總公辦'),(6,'市場部','市場部'),(7,'教學部','教學部');

-- job_inf
INSERT  INTO job_inf(ID,NAME,REMARK) VALUES (1,'職員','職員'),(2,'Java開發工程師','Java開發工程師'),(3,'Java中級開發工程師','Java中級開發工程師'),(4,'Java高級開發工程師','Java高級開發工程師'),(5,'系統管理員','系統管理員'),(6,'架構師','架構師'),(7,'主管','主管'),(8,'經理','經理'),(9,'總經理','總經理');

-- user_inf
INSERT  INTO user_inf(ID,loginname,PASSWORD,USERSTATUS,createdate,username) VALUES (1,'admin','123456',2,'2016-03-12 09:34:28','超級管理員');

-- employee_inf
INSERT  INTO employee_inf(ID,DEPT_ID,JOB_ID,NAME,CARD_ID,ADDRESS,POST_CODE,TEL, PHONE,QQ_NUM,EMAIL,SEX,PARTY,BIRTHDAY,RACE,EDUCATION,SPECIALITY,HOBBY,REMARK,CREATE_DATE)
VALUES (1,1,8,'愛麗絲','4328011988','廣州天河','510000','020-77777777', '13902001111','36750066','251425887@qq.com',0,'黨員','1980-01-01 00:00:00','滿','本科','美聲','唱歌','四大天王','2016-03-14 11:35:18'),
(2,2,1,'傑克','22623','43234','42427424','42242','4247242','42424',
'251425887@qq.com',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-03-14 11:35:18'),
 (3,1,2,'bb','432801197711251038','廣州','510000','020-99999999','13907351532', '36750064','36750064@qq.com',1,'黨員','1977-11-25 00:00:00','漢','本科','計算機','爬山','無','2016-07-14 09:54:52');


