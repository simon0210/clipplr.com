-- ----------------------------
--  Table structure for `users`
-- ----------------------------
CREATE TABLE users
(
  id BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  label_id INT UNSIGNED NOT NULL,
  username VARCHAR(64) DEFAULT '' NOT NULL,
  screen_name VARCHAR(64),
  password VARCHAR(255) DEFAULT '' NOT NULL,
  financial_account_id BIGINT UNSIGNED NOT NULL,
  hierarchy_id INT UNSIGNED NOT NULL,
  locale VARCHAR(20) DEFAULT 'en_US' NOT NULL,
  time_zone VARCHAR(40) DEFAULT 'Asia/Shanghai' NOT NULL,
  phone_number VARCHAR(20),
  email_address VARCHAR(128),
  is_enabled TINYINT DEFAULT 1 NOT NULL,
  is_account_non_expired TINYINT DEFAULT 1 NOT NULL,
  is_account_non_locked TINYINT DEFAULT 1 NOT NULL,
  is_credentials_non_expired TINYINT DEFAULT 1 NOT NULL,
  is_never_deposited TINYINT UNSIGNED DEFAULT 1 NOT NULL,
  login_attempts SMALLINT DEFAULT 0 NOT NULL,
  security_answer_attempts SMALLINT DEFAULT 0 NOT NULL,
  created_at DATETIME NOT NULL,
  sign_up_ip VARCHAR(32) DEFAULT '' NOT NULL,
  session_time_out INT,
  locked_begins TIMESTAMP,
  locked_ends TIMESTAMP,
  rakeback_ratio DECIMAL(3,2),
  bonus_code_id BIGINT DEFAULT 0 NOT NULL,
  FOREIGN KEY (hierarchy_id) REFERENCES business_hierarchy (id),
  FOREIGN KEY (financial_account_id) REFERENCES financial_accounts (id)
);
CREATE UNIQUE INDEX label_id ON users (label_id, username);
CREATE UNIQUE INDEX screen_name ON users (screen_name);
CREATE INDEX bonus_code_id ON users (bonus_code_id);
CREATE INDEX created_at ON users (created_at);
CREATE INDEX email_address ON users (email_address);
CREATE INDEX `lock` ON users (is_account_non_locked, locked_ends);
CREATE INDEX sign_up_ip ON users (sign_up_ip);
CREATE INDEX username ON users (username);