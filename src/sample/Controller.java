package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.Long.parseLong;
import static sample.Alg_Luna.luhn;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button info_program;

    @FXML
    private Button exit_programs;

    @FXML
    private ImageView extract_message;

    @FXML
    private Button check_cards;

    @FXML
    private TextField status_text;

    @FXML
    private TextField input_field;

    @FXML
    private Button clear;

    @FXML
    private TextField data;

    @FXML
    void initialize() {
        info_program.setOnAction(event -> {
            //Mp3.playSound("\\sample\\res\\click.wav"); // звук
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("information.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root2 = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root2));
            stage.showAndWait();
        });
        check_cards.setOnAction(event -> { // Вызов самого луна
            try {
                checking_card(); // вызов метода(функции)
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        exit_programs.setOnAction(event -> {
            //Mp3.playSound("sample\\res\\click.wav");
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close(); // Выход из програмы
        });
        clear.setOnAction(event -> {
            //Mp3.playSound("sample\\res\\click.wav"); // звук
            input_field.clear(); // очистка текста
        });
    }

    private void checking_card() throws IOException { // работа всей прогы тут. Сорри за плохой код((((((
        //Mp3.playSound("C:\\Users\\Henesy\\Desktop\\IdeaProjects\\Project\\src\\sample\\res\\click.wav"); // звук
        DB_Handler dbHandler = new DB_Handler(); //
        String kode = input_field.getText(); // записываю в переменную полученый текст введёных пользователём
        kode = RemoveSpace.removeSpace(kode); // убираем пробелы, на будущее trim();
        String date = String.valueOf(new Date()); // запись текущей даты
        System.out.println(date);
        long num = 0;
        if (IsNumber.isNumber(kode)) {
            NumberKode nm = new NumberKode();
            num = nm.getCountsOfDigits(parseLong((kode))); // получаю количество чисел в числе
        }
        if (num >= 9) {

            boolean a1; // Оглашения переменной для получения true либо false
            a1 = luhn(kode, num); //

            if (a1) { // если a1 равна true то показывает Valid
                status_text.setStyle("-fx-text-fill: green"); // цвет текста
                status_text.setText(String.valueOf(" Карта является валидной ")); // сам текст
                dbHandler.recordCardsMysql(kode, "Валидный", date);
            } else { // если a1 не равна true то показывает No Valid
                status_text.setStyle("-fx-text-fill: red");
                status_text.setText(String.valueOf(" Карта не является валидной "));
                dbHandler.recordCardsMysql(kode, "Не валидный", date);
            }
        } else if (!IsNumber.isNumber(kode)) { // ответ отрицательной проверке на алфавит
            status_text.setStyle("-fx-Text-fill: red"); // цвет текста
            status_text.setText(String.valueOf(" Не верный формат ")); // предупреждения о неверном формате текста
            dbHandler.recordCardsMysql(kode, "Не по формату", date);
        } else {
            System.out.println(kode);
            status_text.setStyle("-fx-Text-fill: red"); // цвет текста
            status_text.setText(String.valueOf(" Не верно введён номер карты, введите ещё раз ")); // Показывает если число чисел в числе не равна 16
        }
    }

}
