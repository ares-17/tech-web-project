package com.unina.techweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    private Integer uid;
    private String title;
    private String description;
    private String createdAt;
    private Object createdBy;
    private Integer maxErrors;
    private boolean isOpen;
}
