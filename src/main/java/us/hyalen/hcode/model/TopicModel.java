package us.hyalen.hcode.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TOPIC")
@Data
public class TopicModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
