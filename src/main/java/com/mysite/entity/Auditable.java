package com.mysite.entity;

import java.time.LocalDateTime;

public interface Auditable {

    Auditable setCreateBy(Long by);

    Auditable setCreateDatetime(LocalDateTime ldt);

    Auditable setUpdateBy(Long by);

    Auditable setUpdateDatetime(LocalDateTime ldt);

    default Auditable auditCreate(Long by) {
        LocalDateTime now = LocalDateTime.now();
        this.setCreateBy(by).setCreateDatetime(now).setUpdateBy(by).setUpdateDatetime(now);
        return this;
    }

    default Auditable auditUpdate(Long by) {
        LocalDateTime now = LocalDateTime.now();
        this.setUpdateBy(by).setUpdateDatetime(now);
        return this;
    }
}
