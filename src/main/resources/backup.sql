CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY COMMENT '唯一值(主鍵)自動增加',
                      name VARCHAR(10) COMMENT '姓名(可異動)',
                      account VARCHAR(20) COMMENT '帳號',
                      password VARCHAR(255) COMMENT '密碼'
) COMMENT='使用者';

CREATE TABLE task (
                      id INT AUTO_INCREMENT PRIMARY KEY COMMENT '唯一值(主鍵)自動增加',
                      task_name VARCHAR(255) NOT NULL COMMENT '任務名稱',
                      status TINYINT NOT NULL DEFAULT 0 COMMENT '狀態 0:未開始 1:進行中 2:已完成 3:終止',
                      due_date DATE COMMENT '到期日',
                      user_id INT NOT NULL COMMENT '操作者(外鍵)'
) COMMENT='任務';

INSERT INTO user (name, account, password) VALUES
('小明', 'xiaoming', 'password123'),
('小美', 'xiaomei', 'abc123456'),
('阿強', 'aqiang', 'pass456789'),
('婷婷', 'tingting', 't1nGt1nG!'),
('阿豪', 'ahao', 'myp@ssword'),
('志偉', 'zhiwei', 'wei_2025'),
('佳佳', 'jiajia', 'jjpass001'),
('小安', 'xiaoan', 'an_secure123'),
('小華', 'xiaohua', 'hU@huapwd'),
('阿信', 'axin', 'ax1nPwd!');


ALTER TABLE user ADD COLUMN role VARCHAR(20) NOT NULL DEFAULT 'STUDENT';

