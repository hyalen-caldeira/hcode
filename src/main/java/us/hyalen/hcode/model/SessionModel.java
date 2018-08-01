package us.hyalen.hcode.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SESSION")
@Data
public class SessionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
