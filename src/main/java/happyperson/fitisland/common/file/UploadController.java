package happyperson.fitisland.common.file;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UploadController {

    private final FileStore fileStore;

    @GetMapping("/upload")
    public String showUploadForm() {
        return "uploadForm"; // uploadForm.html 파일 이름
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            UploadFile uploadFile = fileStore.storeFile(file);
            System.out.println(uploadFile.toString());

            if (uploadFile != null) {
                redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + uploadFile.getUploadFileName() + "!");
            } else {
                redirectAttributes.addFlashAttribute("message", "Failed to upload file. Please select a file.");
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Failed to upload file: " + e.getMessage());
        }

        return "redirect:/upload";
    }
}
