package com.ilyozzz.novelsbio.service;

import com.ilyozzz.novelsbio.entity.*;
import com.ilyozzz.novelsbio.payload.ApiResponse;
import com.ilyozzz.novelsbio.payload.ReqSaveNovelContent;
import com.ilyozzz.novelsbio.repository.*;
import com.ilyozzz.novelsbio.resModels.ResAllContentsPaged;
import com.ilyozzz.novelsbio.resModels.ResContentText;
import com.ilyozzz.novelsbio.resModels.ResContentsWithHistory;
import com.ilyozzz.novelsbio.resModels.ResHistoryOfNovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NovelsContentService {

    @Autowired
    NovelsContentsRep novelsContentsRep;

    @Autowired
    NovelHistoryRep novelHistoryRep;

    @Autowired
    ContentTypesRep contentTypesRep;

    @Autowired
    NovelsContentsTextRep novelsContentsTextRep;

    @Autowired
    LanguageRep languageRep;

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    NovelHistoryTextRep novelHistoryTextRep;

    @Autowired
    NovelsRep novelsRep;



    public ApiResponse saveNewContent(ReqSaveNovelContent reqSaveNovelContent) throws IOException {
        Languages languages = languageRep.findById(reqSaveNovelContent.getLangId()).get();
        Attachment audioOrPdf = attachmentService.saveAttachment(reqSaveNovelContent.getAudioOrPdfFile());
        Attachment photo = attachmentService.saveAttachment(reqSaveNovelContent.getPhoto());
        ContentTypes contentTypes = contentTypesRep.findById(reqSaveNovelContent.getContentTypeId()).get();
        NovelsContentText novelsContentText = novelsContentsTextRep.save(new NovelsContentText(languages, reqSaveNovelContent.getParagraph(),
                reqSaveNovelContent.getMainText(), reqSaveNovelContent.getAdditionalText(), reqSaveNovelContent.getAboutText(), audioOrPdf, reqSaveNovelContent.getReaderName()));
        Optional<NovelsContents> byId = novelsContentsRep.findById(reqSaveNovelContent.getNovelContentId());
        if(byId.isPresent()){
            List<NovelsContentText> novelsContentTextList = byId.get().getNovelsContentTextList();
            novelsContentTextList.add(novelsContentText);

            novelsContentsRep.save(byId.get());
        }
        List<NovelsContentText> novelsContentTextList = new ArrayList<>();
        novelsContentTextList.add(novelsContentText);
        novelsContentsRep.save(new NovelsContents(photo,contentTypes,novelsContentTextList));
        return new ApiResponse("succss",true);
    }

    public ApiResponse getAllContentWithHistory(Integer page , Integer size , Integer langId, UUID novelId){
        Pageable pageable = PageRequest.of(page-1 , size);
        Page<ResAllContentsPaged> contentsPaged = novelsContentsTextRep.getContentsPaged(novelId, langId, pageable);
        ResHistoryOfNovel historyOfNovel = novelHistoryTextRep.getHistoryOfNovel(novelId, langId);
        ResContentsWithHistory resContentsWithHistory = new ResContentsWithHistory(contentsPaged,historyOfNovel);
        return new ApiResponse("success",true,resContentsWithHistory);
    }
    public ApiResponse getCurrentContent(UUID contentId , Integer langId){
        ResContentText currentContentText = novelsContentsTextRep.getCurrentContentText(contentId, langId);
        return new ApiResponse("succss",true,currentContentText);
    }

}
