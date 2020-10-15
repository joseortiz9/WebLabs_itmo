package ru.students.lab.beans;

import ru.students.lab.dao.PointDao;
import ru.students.lab.models.Point;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "pointBean")
@SessionScoped
public class PointBean implements Serializable {

    private PointDao pointDao = new PointDao();
    private List<Point> pointsList;
    private Point point;

    public PointBean() {
        pointsList = new ArrayList<>();
        point = new Point();
    }

    public List<Point> getSavedPoints() {
        pointsList = pointDao.getPoints();
        return pointsList;
    }

    public void addPoint() {
        point.checkInsideFunc();
        pointDao.add(point);
        point = new Point();
    }

    public List<Point> getPointsList() {
        return pointsList;
    }
    public void setPointsList(List<Point> pointsList) {
        this.pointsList = pointsList;
    }
    public Point getPoint() {
        return point;
    }
    public void setPoint(Point point) {
        this.point = point;
    }
}
