package org.zero.web4.entity;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private Integer id;
    private String name;
    private Short foundationYear;
    private Short area;
}
