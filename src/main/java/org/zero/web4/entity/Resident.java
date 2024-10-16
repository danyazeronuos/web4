package org.zero.web4.entity;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Resident {
    private Integer id;
    private String name;
    private City city;
    private Language language;
}
