package org.example.memocrud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Memo {

     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private  String name;

     public Memo(String name) {this.name = name;}

     public void updateName(String name){this.name = name;}

}
