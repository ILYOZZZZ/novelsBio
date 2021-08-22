package com.ilyozzz.novelsbio.repository;

import com.ilyozzz.novelsbio.entity.NovelHistory;
import com.ilyozzz.novelsbio.entity.Novels;
import com.ilyozzz.novelsbio.resModels.ResNovels;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface NovelsRep extends JpaRepository<Novels, UUID> {


    @Query(value = "select first_name as firstName , birth_date as birthDate , attachment_id as photoId , nationality as nationality from novels ORDER by RANDOM()", nativeQuery = true)
    Page<ResNovels> getNovelsAsDefault(Pageable pageable);

    @Query(value = "select first_name as firstName , birth_date as birthDate , attachment_id as photoId , nationality as nationality from novels where first_name like concat('%',:name,'%') or nationality=:nationalityOfNovel", nativeQuery = true)
    Page<ResNovels> getFilteredNovels(@Param(value = "name") String name , @Param(value = "nationality") String nationalityOfNovel , Pageable pageable);

    @Query(value = "select nnh.novel_history_id from novels join novels_novel_history nnh on novels.id = nnh.novels_id where novels.id=:novelId" , nativeQuery = true)
    UUID getNovelHistoryIdIfExists(@Param(value = "novelId") UUID novelId);

}
