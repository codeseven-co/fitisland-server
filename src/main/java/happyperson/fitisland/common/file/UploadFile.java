package happyperson.fitisland.common.file;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName;
    private String serverFileName; //서버용 파일명이 필요하다

    public UploadFile(String uploadFileName, String serverFileName) {
        this.uploadFileName = uploadFileName;
        this.serverFileName = serverFileName;
    }
}
