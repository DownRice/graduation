package cn.downrice.graduation.library.service.preview;

import org.springframework.ui.Model;

/**
 * 预览接口
 * @author 下饭
 */
public interface FilePreview {
    String filePreviewHandle(String url, Model model);
}
