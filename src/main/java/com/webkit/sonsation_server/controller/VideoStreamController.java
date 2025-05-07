package com.webkit.sonsation_server.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stream")
@CrossOrigin(origins = "http://localhost:5173")
public class VideoStreamController {

    private final List<Long> elapsedList = new ArrayList<>();
    private final List<String> timeline = new ArrayList<>();
    private long lastElapsed = 0L;

    @Value("${FTP_HOST}")
    private String ftpHost;

    @Value("${FTP_PORT}")
    private int ftpPort;

    @Value("${FTP_USER}")
    private String ftpUser;

    @Value("${FTP_PASS}")
    private String ftpPass;

    // 영상 스트리밍 API
    @GetMapping("/{filename}")
    public ResponseEntity<StreamingResponseBody> streamVideo(@PathVariable String filename) {
        FTPClient ftpClient = new FTPClient();

        try {
            System.out.println("▶ FTP 연결 시도...");
            ftpClient.connect(ftpHost, ftpPort); // FTP 서버
            ftpClient.login(ftpUser, ftpPass); // 로그인 정보
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            System.out.println("✅ FTP 로그인 및 설정 완료");

            String remoteFilePath = "/videos/" + filename; // 파일 경로 (FTP 서버)
            System.out.println("▶ FTP 경로 요청: " + remoteFilePath);

            InputStream inputStream = ftpClient.retrieveFileStream(remoteFilePath);
            if (inputStream == null) {
                System.out.println("❌ 파일 없음: " + ftpClient.getReplyString());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            long startTime = System.currentTimeMillis();

            StreamingResponseBody body = outputStream -> {
                try (InputStream in = inputStream) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        outputStream.flush();
                    }
                    ftpClient.completePendingCommand();
                } finally {
                    ftpClient.logout();
                    ftpClient.disconnect();
                    lastElapsed = System.currentTimeMillis() - startTime;
                    elapsedList.add(lastElapsed);
                    timeline.add(String.format("📦 [%s] %d ms", filename, lastElapsed));
                    System.out.printf("📦 [%s] %d ms 소요됨%n", filename, lastElapsed);
                    System.out.println("🔌 스트리밍 완료 후 연결 종료");
                }
            };

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf("video/mp4"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(body);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 실시간 스트리밍 통계 API
    record StreamStats(long lastElapsed, double averageElapsed, long maxElapsed, long minElapsed, long totalElapsed, List<String> timeline) {}

    @GetMapping("/stats")
    public StreamStats getStats() {
        if (elapsedList.isEmpty()) {
            return new StreamStats(0, 0, 0, 0, 0, new ArrayList<>());
        }

        long sum = elapsedList.stream().mapToLong(Long::longValue).sum();
        double average = sum / (double) elapsedList.size();
        long max = elapsedList.stream().mapToLong(Long::longValue).max().orElse(0);
        long min = elapsedList.stream().mapToLong(Long::longValue).min().orElse(0);

        return new StreamStats(lastElapsed, average, max, min, sum, timeline);
    }
}
