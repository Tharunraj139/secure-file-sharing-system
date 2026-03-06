CREATE DATABASE secure_file_share;

USE secure_file_share;

CREATE TABLE file_session (
    session_id VARCHAR(50) PRIMARY KEY,
    otp VARCHAR(6),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expiry_time TIMESTAMP,
    burn_after_download BOOLEAN,
    downloaded BOOLEAN DEFAULT 0
);