package wpgm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnit {

    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    List<AdminUnit> children = new ArrayList<>();;
    List<AdminUnit> neighbors = new ArrayList<>();
    BoundingBox bbox = new BoundingBox();
}
