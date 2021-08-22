package com.ilyozzz.novelsbio.resModels;

import java.util.UUID;

public interface ResAllContentsPaged {

    UUID getContentId();
    String getContentType();
    String getParagraph();
    String getAbout();
    UUID getPhotoId();


}
