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

INSERT INTO task (task_name, status, due_date, user_id) VALUES
('撰寫專案規劃書', 0, '2025-06-15', 1),
('設計資料庫結構', 1, '2025-06-10', 2),
('開發後端API', 1, '2025-06-20', 1),
('前端頁面製作', 0, '2025-06-25', 3),
('撰寫測試案例', 2, '2025-06-12', 2),
('系統整合測試', 0, '2025-07-01', 3),
('修正BUG', 1, '2025-06-18', 1),
('撰寫使用手冊', 0, '2025-07-05', 2),
('部署到測試環境', 3, '2025-06-22', 3),
('準備專案發表', 0, '2025-07-10', 1);

ALTER TABLE user ADD COLUMN role VARCHAR(20) NOT NULL DEFAULT 'STUDENT';

CREATE TABLE time_schedule (
                               id INT AUTO_INCREMENT PRIMARY KEY COMMENT '主鍵 ID',
                               user_id INT NOT NULL COMMENT '使用者 ID',
                               start_time DATETIME NOT NULL COMMENT '開始時間',
                               end_time DATETIME NOT NULL COMMENT '結束時間',
                               monday VARCHAR(500)  COMMENT '星期一的行程資訊',
                               tuesday VARCHAR(500)  COMMENT '星期二的行程資訊',
                               wednesday VARCHAR(500)  COMMENT '星期三的行程資訊',
                               thursday VARCHAR(500)  COMMENT '星期四的行程資訊',
                               friday VARCHAR(500)  COMMENT '星期五的行程資訊',
                               saturday VARCHAR(500)  COMMENT '星期六的行程資訊',
                               sunday VARCHAR(500)  COMMENT '星期日的行程資訊',
                               status CHAR(1) NOT NULL DEFAULT '0' COMMENT '狀態：0=未完成, 1=已完成'
) COMMENT='時間行程表';

ALTER TABLE time_schedule
    MODIFY COLUMN start_time TIME,
    MODIFY COLUMN end_time TIME;



