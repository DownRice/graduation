package cn.downrice.graduation.library.model.preview;

/**
 * 文件类型
 * @author 下饭
 */
public enum FileType {
    picture("PictureFilePreviewImpl"),
    compress("CompressFilePreviewImpl"),
    office("OfficeFilePreviewImpl"),
    simText("SimTextFilePreviewImpl"),
    pdf("PdfFilePreviewImpl"),
    other("OtherFilePreviewImpl");

    private String instanceName;
    FileType(String instanceName){
        this.instanceName=instanceName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
}
