import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    static {
    BackgroundScheduler.startCleanupTask();
}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sessionId = OTPGenerator.generateSessionId();
        String otp = OTPGenerator.generateOTP();

        boolean burn = request.getParameter("burn") != null;

        String uploadPath = getServletContext().getRealPath("/") + "uploads/" + sessionId;

        File folder = new File(uploadPath);
        folder.mkdirs();

        for (Part part : request.getParts()) {

            if (part.getName().equals("files") && part.getSize() > 0) {

                String fileName = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + fileName);
            }
        }

        SessionService.saveSession(sessionId, otp, burn);

        response.setContentType("text/html");
PrintWriter out = response.getWriter();

out.println("<html>");
out.println("<head>");
out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css'>");
out.println("</head>");

out.println("<body class='bg-light'>");

out.println("<div class='container mt-5'>");
out.println("<div class='card p-4 text-center shadow'>");

out.println("<h3>Files Uploaded Successfully</h3>");
out.println("<p class='mt-3'>Your OTP:</p>");
out.println("<h2 class='text-primary'>" + otp + "</h2>");

out.println("<p class='mt-3'>Share this OTP with the receiver.</p>");

out.println("<a href='download.html' class='btn btn-success mt-3'>Go to Download Page</a>");

out.println("</div>");
out.println("</div>");

out.println("</body>");
out.println("</html>");
    }
}