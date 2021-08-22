package com.ilyozzz.novelsbio.service;

import com.ilyozzz.novelsbio.entity.Attachment;
import com.ilyozzz.novelsbio.entity.Novels;
import com.ilyozzz.novelsbio.payload.ApiResponse;
import com.ilyozzz.novelsbio.payload.ReqSaveNewNovel;
import com.ilyozzz.novelsbio.repository.NovelsRep;
import com.ilyozzz.novelsbio.resModels.ResNovels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NovelsService {

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    NovelsRep novelsRep;

    public ApiResponse saveNewNovels(ReqSaveNewNovel reqSaveNewNovel) throws IOException {
        Attachment attachment = attachmentService.saveAttachment(reqSaveNewNovel.getPhoto());
        novelsRep.save(new Novels(reqSaveNewNovel.getFirstName(),reqSaveNewNovel.getBirthDate(),attachment,reqSaveNewNovel.getNationality()));
        return new ApiResponse("success",true);
    }

    public ApiResponse getNovelsDefault(Integer size, Integer page){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<ResNovels> novelsAsDefault = novelsRep.getNovelsAsDefault(pageable);
        return new ApiResponse("success",true,novelsAsDefault);
    }

    public ApiResponse searchNovelsByNameAndFilterByNationality(Integer page, Integer size, String name , String nationality){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<ResNovels> filteredNovels = novelsRep.getFilteredNovels(name, nationality,pageable);
        return new ApiResponse("success",true,filteredNovels);
    }




}
