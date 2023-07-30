package com.bitcoin.autotrading.commandLine.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "ProgramLog")
public class ProgramLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String programid;
    private String runtime;
    private String argument1;
    private String argument2;
    private String argument3;

    @Builder
    public ProgramLog(String programid, String argument1, String argument2, String argument3, String runtime, Integer id){
        this.programid=programid;
        this.argument1=argument1;
        this.argument2=argument2;
        this.argument3=argument3;
        this.runtime=runtime;
        this.id=id;
    }


}