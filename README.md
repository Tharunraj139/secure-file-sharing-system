# Secure File Sharing System

A Java web application that allows users to securely share files using a one-time OTP without requiring login or registration.

## Features
- Upload multiple files
- One-time OTP based download
- Download files as a ZIP
- Burn-after-download option
- Automatic file expiry after 1 hour
- Temporary file storage

## Tech Stack
- Java Servlets
- Apache Tomcat
- MySQL
- HTML + Bootstrap
- JDBC

## How It Works
1. User uploads files through the web interface.
2. Server generates a session ID and OTP.
3. Files are stored temporarily on the server.
4. User shares the OTP with the recipient.
5. Recipient enters the OTP to download files as a ZIP archive.
6. Files are deleted after download or automatically after expiry.

## How to Run
1. Start MySQL server.
2. Start Apache Tomcat.
3. Deploy the project in the Tomcat `webapps` folder.
4. Open  
   http://localhost:8080/securefileshare
