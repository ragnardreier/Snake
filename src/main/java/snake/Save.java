package snake;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Save implements SaveHandler {

    GradeAverageController gradeAverageController;

    @Override
    public void saveCourseList(ObservableList<Course> listOfCourses) throws FileNotFoundException {
        try { 
            Writer writer = new FileWriter("src\\main\\resources\\karakterkalkulator\\saves\\save.txt");
            for (Course course : listOfCourses) {
                writer.write(course.getCourse() + "," + course.getGrade() + "," +  course.getCredits() + System.lineSeparator());
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Course> loadCourseList() throws IOException {

        String line = "";

        ObservableList<Course> tempListOfCourses = FXCollections.observableArrayList();

        BufferedReader br = new BufferedReader(new FileReader(getFilePath()));

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            tempListOfCourses.add(new Course(values[0], values[1], values[2]));
        }
        return tempListOfCourses;        
    }

    public static File getFilePath() {
		return new File(Save.class.getResource("saves/").getFile() + "save.txt");
	}
}
