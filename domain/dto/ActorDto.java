package domain.dto;

import java.time.LocalDate;

public class ActorDto {
    private String name;
    private LocalDate birthDate;
    private String nation;
    private String gender;
    private int actor_seq;

    public ActorDto() {};

    public ActorDto(String name, LocalDate birthDate, String nation, String gender, int actor_seq) {
        this.name = name;
        this.birthDate = birthDate;
        this.nation = nation;
        this.gender = gender;
        this.actor_seq = actor_seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getActor_seq() {
        return actor_seq;
    }

    public void setActor_seq(int actor_seq) {
        this.actor_seq = actor_seq;
    }
}
