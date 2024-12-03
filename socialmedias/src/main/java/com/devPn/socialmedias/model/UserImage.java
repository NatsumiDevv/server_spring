package com.devPn.socialmedias.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_images")
public class UserImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "data", columnDefinition = "BLOB") // Lưu dữ liệu ảnh
    private byte[] data;

    @Column(name = "type")
    private String type;

    // Mỗi User có thể có một ảnh đại diện duy nhất
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
