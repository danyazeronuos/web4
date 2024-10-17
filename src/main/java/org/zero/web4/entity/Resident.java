package org.zero.web4.entity;
import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resident {
    private Integer id;
    private String name;
    private City city;
    private Language language;
}
