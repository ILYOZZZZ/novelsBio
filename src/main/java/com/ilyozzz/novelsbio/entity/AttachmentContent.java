package com.ilyozzz.novelsbio.entity;

import com.ilyozzz.novelsbio.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AttachmentContent extends AbsEntity {

    private byte[] bytes;

    @OneToOne
    private Attachment attachment;
}
