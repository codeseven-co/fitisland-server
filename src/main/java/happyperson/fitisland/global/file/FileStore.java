package happyperson.fitisland.global.file;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileStore {

    private final AmazonS3 amazonS3; // AmazonS3Client 대신 AmazonS3 사용
    @Value("${ncp.object-storage.bucket-name}")
    private String bucketName;
    @Value("${ncp.object-storage.folder-name}")
    private String folderName;

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> uploadFileList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()){
                uploadFileList.add(storeFile(multipartFile));
            }
        }
        return uploadFileList;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()){
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();

        //uuid + 확장자명으로 서버에 업로드할 파일명 생성
        String serverFileName = generateFileName(originalFilename);
        String uploadFileName = getFullPath(folderName, serverFileName);
        log.info(folderName);
        log.info(serverFileName);
        log.info(uploadFileName);
        //폴더 생성코드
        createFolderIfNotExist(bucketName, folderName); // folderName만 전달하도록 수정

        //파일 업로드
        // 파일 업로드
        ObjectMetadata objectMetadata = createObjectMetadata(multipartFile); // ObjectMetadata 생성 메서드 호출

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucketName,  uploadFileName, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new IOException("Failed to upload file", e);
        }

        return new UploadFile(originalFilename, serverFileName);

    }

    private void createFolderIfNotExist(String bucketName, String folderName) {

        if (!amazonS3.doesObjectExist(bucketName, folderName)) {
            // 폴더가 존재하지 않는 경우에만 생성
            ObjectMetadata folderObjectMetadata = createFolderObjectMetadata();

            // 폴더 생성 요청
            PutObjectRequest putObjectRequest
                = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), folderObjectMetadata);

            try {
                amazonS3.putObject(putObjectRequest);
                log.info("Folder {} has been created.", folderName); // log.info 사용
            } catch (AmazonS3Exception e) {
                log.error("Failed to create folder1: {}", folderName, e);
            } catch (SdkClientException e) {
                log.error("Failed to create folder2: {}", folderName, e);
            }
        } else {
            log.info("Folder {} already exists.", folderName);
        }
    }

    String generateFileName(String originalFilename) {
        //확장자명 가져오기
        String extension = StringUtils.getFilenameExtension(originalFilename);
        String uuid = UUID.randomUUID().toString();

        // fileType: "images", "videos" 등
        return String.format("%s.%s", uuid, extension);
        // ex: "123e4567-e89b-12d3-a456-426614174000.jpg"
    }

    public String getFullPath(String folderName, String filename) {
        return String.format("%s/%s", folderName, filename);
    }

    // ObjectMetadata 생성 및 설정 메서드 (파일용)
    private ObjectMetadata createObjectMetadata(MultipartFile multipartFile) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        return objectMetadata;
    }

    // ObjectMetadata 생성 및 설정 메서드 (폴더용)
    private ObjectMetadata createFolderObjectMetadata() {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(0L);
        objectMetadata.setContentType("application/x-directory");
        return objectMetadata;
    }

}
