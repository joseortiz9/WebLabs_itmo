package ru.students.lab.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "points", catalog = "studs")
public @Data class Point implements Serializable {

    @Setter(value = AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Double x;
    private Double y;
    private Double r;
    private Boolean result;
    private LocalDateTime createTime;

    public Point() {
    }

    public Point(double x, double y, double r, boolean result, LocalDateTime createTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.createTime = createTime;
    }

    public void checkInsideFunc() {
        if (x == null || y == null || r == null)
            throw new NullPointerException();

        this.result = (x <= 0 && y >= 0 && y <= 2*x + r) //linear function
                || (y <= 0 && x >= 0 && y >= -Math.sqrt(r * r / 4 - x * x)) //circular function
                || (y >= 0 && x >= 0 && y <= r && x <= r); //lines on r
    }

    public String getCreatedTimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return createTime.format(formatter);
    }
}
