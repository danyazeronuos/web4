package org.zero.web4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private Integer id;
    private String name;
    private Short foundationYear;
    private Short area;
}
