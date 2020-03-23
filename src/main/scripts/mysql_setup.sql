CREATE DATABASE tinyurl;

-- Create user
CREATE USER 'tinyurl_user'@'localhost' IDENTIFIED BY 'tinyurl_password';

GRANT ALL PRIVILEGES ON tinyurl.* TO 'tinyurl_user'@'localhost';

FLUSH PRIVILEGES;

-- Create table schema
CREATE TABLE IF NOT EXISTS url_mapping (
    tiny_url VARCHAR(64) PRIMARY KEY,
    original_url VARCHAR(1024),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)  ENGINE=INNODB;