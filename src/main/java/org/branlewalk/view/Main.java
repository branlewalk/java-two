package org.branlewalk.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.branlewalk.domain.Appointment;
import org.branlewalk.domain.Customer;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class Main extends Application {

    private String userName;

    public static void main(String[] args) throws ClassNotFoundException {
        String driver = "com.mysql.cj.jdbc.Driver";
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.systemDefault()));
        Class.forName(driver);
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Appoint - Sign in");
        primaryStage.setScene(new Scene(root, 300,250));
        primaryStage.show();
    }

    public static Connection connection() throws SQLException {
        String db = "U05vOU";
        String url = "jdbc:mysql://52.206.157.109/" + db;
        String user = "U05vOU";
        String pass = "53688621490";
        Connection connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }

    public static void newWindow(String title, String view) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(view));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static void newCustomerWindow(String title, String view, Customer customer) throws Exception {
        CustomerController controller = new CustomerController(customer);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(view));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static boolean newAppointmentWindow(String title, String view, Date date) throws Exception {
        AppointmentController controller = new AppointmentController(null);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(view));
        loader.setController(controller);
        Parent root = loader.load();
        controller.setDate(date);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.showAndWait();
        return controller.wasCancel();
    }

    public static boolean editAppointmentWindow(String title, String view, Appointment appointment) throws Exception {
        AppointmentController controller = new AppointmentController(appointment);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(view));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.showAndWait();
        return controller.wasCancel();
    }




    public static void newReportWindow(String title, String view, String type, String data) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(view));
        Parent root = loader.load();
        ReportController controller = loader.getController();
        controller.setData(data);
        controller.setTypeOfReport(type);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static void closeWindow(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
