package com.example.tableview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserUtility {
        public static ObservableList<User> getUsersList() {
            return FXCollections.observableArrayList(
                    new User("Patryk", "Cygi", LocalDate.of(2001, 10, 1)),
                    new User("Jakub", "Kucu", LocalDate.of(2001, 10, 1)),
                    new User("Mikus", "Buc", LocalDate.of(2001, 10, 1)
                    ));
        }
       public static TableColumn<User,String> getFirstNameColumn(){
               TableColumn<User,String> fNameCol= new TableColumn<>("First Name");//name of column
               fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));//set name
                fNameCol.setSortable(false);

                fNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
                fNameCol.setOnEditCommit(e -> {
                    e.getRowValue().setFirstName(e.getNewValue());
                    System.out.println(e.getRowValue());
                });
               return fNameCol;
            }

    public static TableColumn<User,String> getLastNameColumn(){
        TableColumn<User,String> lNameCol= new TableColumn<>("Last Name");
        lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        return lNameCol;
    }

    public static TableColumn<User,LocalDate> getBirthDateColumn(){
        TableColumn<User,LocalDate> bDateCol= new TableColumn<>("Birth Date");
        bDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        String pattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateStringConverter converter = new LocalDateStringConverter(formatter,null);
        bDateCol.setCellFactory(TextFieldTableCell.forTableColumn(converter));
        bDateCol.setOnEditCommit(e->{
            e.getRowValue().setBirthDate(e.getNewValue());
            System.out.println(e.getRowValue());
        });
        return bDateCol;
    }

    public static TableColumn<User, Button> getDeleteUserColumn(){
            TableColumn<User,Button> dUserCol = new TableColumn<>("Delete");
            dUserCol.setCellFactory(col -> new TableCell<>(){
                @Override
                public void updateItem(Button item, boolean empty){
                    super.updateItem(item,empty);
                    setText(null);
                    setGraphic(null);
                    if(!empty){
                        Button button = new Button("Delete");

                        button.setOnAction(actionEvent -> {
                            int rowIndex = this.getTableRow().getIndex();
                            this.getTableView().getItems().remove(rowIndex);
                        });
                        setText(null);
                        setGraphic(button);
                    }
                }
            });
            return  dUserCol;
    }
}

