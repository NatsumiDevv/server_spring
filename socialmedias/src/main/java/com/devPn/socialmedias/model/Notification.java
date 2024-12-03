package com.devPn.socialmedias.model;
import com.devPn.socialmedias.model.Enum.NotificationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;


    @Column(name = "post_id")
    private Integer relatedPost;

    @Column(name = "comment_id")
    private Integer relatedComment;

    @Column(name = "follow_id")
    private Integer relatedFollow;

    @Column(name = "like_id")
    private Integer relatedLike;


    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private Integer senderId;

    private String message;

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;



}
