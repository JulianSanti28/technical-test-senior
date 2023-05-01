package co.soft.technical.test.model;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "play_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayList {
    // ~ Attributes
    // ====================================================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String description;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Song> songs;
}
