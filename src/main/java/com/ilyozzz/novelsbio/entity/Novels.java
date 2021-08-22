package com.ilyozzz.novelsbio.entity;


import com.ilyozzz.novelsbio.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.text.SimpleDateFormat;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Novels extends AbsEntity {

    private String firstName;
    private SimpleDateFormat birthDate;

    @OneToOne
    private Attachment attachment;

    @OneToOne
    private NovelHistory novelHistory;

    @OneToMany
    private List<NovelsContents> novelsContents;


    private String nationality;

    public Novels(String firstName, SimpleDateFormat birthDate, Attachment attachment, String nationality) {
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.attachment = attachment;
        this.nationality = nationality;
    }
}
