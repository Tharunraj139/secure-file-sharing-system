import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String otp = request.getParameter("otp");

        String sessionId = OTPVerificationService.verifyOTP(otp);

        if (sessionId == null) {
            response.getWriter().println("Invalid OTP!");
            return;
        }

        String folderPath = getServletContext().getRealPath("/") + "uploads/" + sessionId;

        File folder = new File(folderPath);

        if (!folder.exists()) {
            response.getWriter().println("Files not found.");
            return;
        }

        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            response.getWriter().println("No files available.");
            return;
        }

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"files.zip\"");

        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());

        byte[] buffer = new byte[4096];

        for (File f : files) {

            FileInputStream fis = new FileInputStream(f);

            zos.putNextEntry(new ZipEntry(f.getName()));

            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                zos.write(buffer, 0, bytesRead);
            }

            fis.close();
            zos.closeEntry();
        }

        zos.close();

        DownloadService.markAsDownloaded(sessionId);
        FileDeletionService.deleteSessionFolder(sessionId);
    }
}