package happyperson.fitisland.common.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStore {

//    @Value()
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()){
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();

        //uuid + 확장자명으로 서버에 업로드할 파일명 생성
        String serverFileName = generateFileName(originalFilename);
        //파일 업로드
        multipartFile.transferTo(new File(getFullPath(serverFileName)));

        return new UploadFile(originalFilename, serverFileName);
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> uploadFileList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()){
                uploadFileList.add(storeFile(multipartFile));
            }
        }
        return uploadFileList;
    }

    String generateFileName(String originalFilename) {
        //확장자명 가져오기
        String extension = StringUtils.getFilenameExtension(originalFilename);
        String uuid = UUID.randomUUID().toString();

        // fileType: "images", "videos" 등
        return String.format("%s.%s", uuid, extension);
        // ex: "123e4567-e89b-12d3-a456-426614174000.jpg"
    }


}
