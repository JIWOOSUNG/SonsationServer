package com.webkit.sonsation_server.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/stream")
public class VideoStreamController {

    @GetMapping("/{filename}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String filename) throws IOException {
        FTPClient ftpClient = new FTPClient();
        InputStream inputStream = null;

        try {
            // ftpClient.connect("ftp.example.com"); // ✅ FTP 서버 주소
            // ftpClient.login("user", "password"); // ✅ FTP 계정 정보
            ftpClient.connect("127.0.0.1", 21); // 로컬 FTP 서버
            ftpClient.login("testuser", "testpass"); // 로그인 정보
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            String remoteFilePath = "/videos/" + filename;
            inputStream = ftpClient.retrieveFileStream(remoteFilePath);

            if (inputStream == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            InputStreamResource resource = new InputStreamResource(inputStream);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "video/mp4")
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
