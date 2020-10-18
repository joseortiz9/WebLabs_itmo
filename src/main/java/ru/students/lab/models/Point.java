package ru.students.lab.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "points", schema = "s288867", catalog = "orbis")
public class Point implements Serializable {

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
        this.createTime = LocalDateTime.now();
    }

    public Point(double x, double y, double r) {
        this();
        this.x = x;
        this.y = y;
        this.r = r;
        checkInsideFunc();
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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        this.x = x;
    }
    public Double getY() {
        return y;
    }
    public void setY(Double y) {
        this.y = y;
    }
    public Double getR() {
        return r;
    }
    public void setR(Double r) {
        this.r = r;
    }
    public Boolean getResult() {
        return result;
    }
    public void setResult(Boolean result) {
        this.result = result;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(x, point.x) &&
                Objects.equals(y, point.y) &&
                Objects.equals(r, point.r) &&
                Objects.equals(result, point.result) &&
                Objects.equals(createTime, point.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, result, createTime);
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", result=" + result +
                ", createTime=" + createTime +
                '}';
    }
}
