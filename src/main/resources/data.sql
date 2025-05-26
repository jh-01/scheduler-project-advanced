-- 1. member 테이블
CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        nickname VARCHAR(255) NOT NULL UNIQUE,
                        refresh_token VARCHAR(255),
                        created_at DATETIME     NOT NULL,
                        updated_at DATETIME     NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. schedule 테이블
CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          member_id BIGINT         NOT NULL,
                          title VARCHAR(255)       NOT NULL,
                          content LONGTEXT         NOT NULL,
                          created_at DATETIME      NOT NULL,
                          updated_at DATETIME      NOT NULL,
                          CONSTRAINT fk_schedule_member
                              FOREIGN KEY (member_id)
                                  REFERENCES member(id)
                                  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. comment 테이블
CREATE TABLE comment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         member_id BIGINT         NOT NULL,
                         schedule_id BIGINT       NOT NULL,
                         content TEXT             NOT NULL,
                         created_at DATETIME      NOT NULL,
                         updated_at DATETIME      NOT NULL,
                         CONSTRAINT fk_comment_member
                             FOREIGN KEY (member_id)
                                 REFERENCES member(id)
                                 ON DELETE CASCADE,
                         CONSTRAINT fk_comment_schedule
                             FOREIGN KEY (schedule_id)
                                 REFERENCES schedule(id)
                                 ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
