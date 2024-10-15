-- Create user table
CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL,
                      password VARCHAR(100) NOT NULL,
                      email VARCHAR(100) NOT NULL
);

-- Insert initial users
INSERT INTO user (username, password, email) VALUES ('john_doe', 'password123', 'john@example.com');
INSERT INTO user (username, password, email) VALUES ('jane_doe', 'password456', 'jane@example.com');
