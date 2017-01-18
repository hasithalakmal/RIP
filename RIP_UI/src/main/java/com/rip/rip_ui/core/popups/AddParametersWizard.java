package com.rip.rip_ui.core.popups;

import com.google.common.base.CaseFormat;
import com.rip.rip_ui.api.impl.Converter;
import com.rip.rip_ui.core.data.ReservedWords;
import com.rip.rip_ui.core.text.DataLabel;
import com.rip.rip_ui.core.text.Info;
import com.rip.rip_ui.core.text.InputLabel;
import com.rip.rip_ui.core.text.Title;
import com.rip.rip_ui.model.impl.WTableField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * Wizard for adding parameters for a particular resource
 */
public class AddParametersWizard {

    private int row = 0;
    private final String QUERY_PARAM = "query";
    private final String PATH_PARAM = "path";
    private final String BODY_PARAM = "body";

    private final ObservableList<String> PARAMETER_TYPES = FXCollections.observableArrayList(QUERY_PARAM, PATH_PARAM, BODY_PARAM);
    private Spinner<Integer> paramCount;
    private final int MIN_PARAM_COUNT = 0;
    private int MAX_PARAM_COUNT = 8;
    private final int INCREMENT = 0;
    private ChoiceBox bxParamTypes;
    private StringBuilder strBuilder;
    private StringBuilder realPath;

    private HashMap<String, String> paramDetails;

    private String uri;

    private GridPane grid;

    private final String ADD_BTN_NAME = "Add";
    private ArrayList<ChoiceBox> parameters;

    private CheckBox get;
    private CheckBox post;
    private CheckBox put;
    private CheckBox delete;

    private HashMap<String, WTableField> resourceTableFieldMap;

    private ObservableList<String> resourceTableFields;

    private String resourceTable;

    private int checkedCounter = 0;

    private Node addButton;

    public AddParametersWizard(String uri, String resourceTable, HashMap<String, WTableField> resourceTableFields) {
        this.resourceTable = resourceTable;
        this.resourceTableFieldMap = resourceTableFields;
        setMaxParamCount(resourceTableFields.size());
        setResourceTableFields(resourceTableFields);
        this.uri = uri;
        setUIElements();

    }

    private void setUIElements() {
        Dialog<HashMap<String, String>> dialog = new Dialog();
        dialog.setTitle(Title.ADD_PARAMETERS);
        dialog.setHeaderText(Info.ADD_PARAMETERS_INFO);

        ButtonType addButtonType = new ButtonType(ADD_BTN_NAME);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        // Create the username and password labels and fields.
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label(InputLabel.PARAMETER_TYPE), 0, row);
        bxParamTypes = new ChoiceBox();

        bxParamTypes.setItems(PARAMETER_TYPES);

        grid.add(bxParamTypes, 1, row++);

        grid.add(new Label(InputLabel.PARAMETER_COUNT), 0, row);
        paramCount = new Spinner<>(MIN_PARAM_COUNT, MAX_PARAM_COUNT, INCREMENT);
        paramCount.valueProperty().addListener(onCounterChangeListner);
        grid.add(paramCount, 1, row++);

        grid.add(new Label(InputLabel.METHODS), 0, row++);

        get = new CheckBox();
        post = new CheckBox();
        put = new CheckBox();
        delete = new CheckBox();

        get.selectedProperty().addListener(onChangeChecked);
        post.selectedProperty().addListener(onChangeChecked);
        put.selectedProperty().addListener(onChangeChecked);
        delete.selectedProperty().addListener(onChangeChecked);

        HBox hboxGet = new HBox(get, new Label(InputLabel.GET));
        grid.add(hboxGet, 0, row);

        HBox hboxPost = new HBox(post, new Label(InputLabel.POST));
        grid.add(hboxPost, 1, row++);

        HBox hboxPut = new HBox(put, new Label(InputLabel.PUT));
        grid.add(hboxPut, 0, row);

        HBox hboxDelete = new HBox(delete, new Label(InputLabel.DELETE));
        grid.add(hboxDelete, 1, row++);

        row++;

        initializeParameterBoxes();

        bxParamTypes.valueProperty().addListener(onChangeTypeListner);

        bxParamTypes.getSelectionModel().selectFirst();

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                HashMap<String, String> paramDetails = new HashMap<String, String>();
                paramDetails.put(DataLabel.PARAMETER_TYPE, bxParamTypes.getValue().toString());
                paramDetails.put(DataLabel.PARAMETER_COUNT, paramCount.getValue().toString());
                paramDetails.put(DataLabel.GET, (get.isSelected() ? "true" : "false"));
                paramDetails.put(DataLabel.POST, (post.isSelected() ? "true" : "false"));
                paramDetails.put(DataLabel.PUT, (put.isSelected() ? "true" : "false"));
                paramDetails.put(DataLabel.DELETE, (delete.isSelected() ? "true" : "false"));
                paramDetails.put(DataLabel.URI, getURIPath());
                paramDetails.put(DataLabel.RESOURCE_TABLE, this.resourceTable);
                paramDetails.put(DataLabel.REAL_PATH, getRealPath());

                return paramDetails;
            }
            return null;
        });

        Optional<HashMap<String, String>> result = dialog.showAndWait();

        result.ifPresent(paramDetails -> {
            this.paramDetails = paramDetails;
            setParameterList();
        });

    }

    private String getRealPath() {

        realPath = new StringBuilder(this.uri);

        if (bxParamTypes.getValue().toString().equals(PATH_PARAM)) {
            for (int i = 0; i < paramCount.getValue(); i++) {
                realPath.append("/{");
                realPath.append(parameters.get(i).getValue().toString());
                realPath.append("}");
            }
        }

        return realPath.toString();
    }

    private void initializeParameterBoxes() {

        parameters = new ArrayList<>();

        for (int i = 0; i < MAX_PARAM_COUNT; i++) {

            ChoiceBox chBox = createParamBoxes();
            chBox.setItems(resourceTableFields);

            parameters.add(chBox);

            HBox hbox = new HBox(new Label("Parameter" + i + ": "), chBox);

            if (i % 2 == 0) {
                grid.add(hbox, 0, row);
            } else {
                grid.add(hbox, 1, row++);
            }
        }

    }

    private void setMaxParamCount(int noOfFields) {
        if (MAX_PARAM_COUNT > noOfFields) {
            MAX_PARAM_COUNT = noOfFields;
        }
    }

    private void setResourceTableFields(HashMap<String, WTableField> resourceTableFields) {
        this.resourceTableFields = FXCollections.observableArrayList();

        for (String key : resourceTableFields.keySet()) {
            key = Converter.snakeToCamel(key);
            this.resourceTableFields.add(key);
        }

        for (String key : ReservedWords.KEYS_WITH_VALUES.keySet()) {
            this.resourceTableFields.add(key);
        }

    }

    private ChoiceBox createParamBoxes() {
        ChoiceBox chBox = new ChoiceBox();
        chBox.setDisable(true);
        return chBox;
    }

    private String getURIPath() {
        strBuilder = new StringBuilder(uri);

        String choosenParamType = bxParamTypes.getValue().toString();

        if (choosenParamType.equals(PATH_PARAM)) {
            makePathParam();

        } else if (choosenParamType.equals(QUERY_PARAM)) {
            makeQueryParam();
        } else {
            strBuilder.append("/<body>");
        }

        return strBuilder.toString();
    }

    private void makeQueryParam() {

        if (paramCount.getValue() == 0) {
            strBuilder.append("/<q>");
        } else {
            strBuilder.append("/?");
        }

        for (int i = 0; i < paramCount.getValue(); i++) {
            strBuilder.append(parameters.get(i).getValue().toString());
            strBuilder.append("&");
        }

        strBuilder.deleteCharAt(strBuilder.length() - 1);
    }

    private void makePathParam() {

        if (paramCount.getValue() == 0) {
            strBuilder.append("/<p>");
        }

        for (int i = 0; i < paramCount.getValue(); i++) {
            strBuilder.append("/{");
            strBuilder.append(parameters.get(i).getValue().toString());
            strBuilder.append("}");
        }

    }

    public HashMap<String, String> getParamDetails() {
        return paramDetails;
    }

    ChangeListener<String> onChangeTypeListner = new ChangeListener<String>() {

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            untickAllMethods();

            if (newValue.equals(QUERY_PARAM)) {
                enablePathQueryControllers();

            } else if (newValue.equals(PATH_PARAM)) {
                enablePathQueryControllers();

            } else {
                enablePutPostControllers();
            }
        }

    };

    ChangeListener<Integer> onCounterChangeListner = new ChangeListener<Integer>() {

        @Override
        public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
            disableAllParamBoxes(oldValue);
            enableAllParamBoxes(newValue);
        }

    };

    ChangeListener<Boolean> onChangeChecked = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            updateCheckedCounter(newValue);
            adjustAddButton();
        }
    };

    private void updateCheckedCounter(Boolean newValue) {

        if (newValue == true) {
            checkedCounter++;
        } else {
            checkedCounter--;
        }

    }

    private void adjustAddButton() {

        if (checkedCounter > 0) {
            addButton.setDisable(false);
        } else {
            addButton.setDisable(true);
        }

    }

    private void disableAllParamBoxes(Integer oldValue) {
        for (int i = 0; i < oldValue; i++) {
            parameters.get(i).setDisable(true);
        }
    }

    private void enableAllParamBoxes(Integer newValue) {
        for (int i = 0; i < newValue; i++) {
            parameters.get(i).setDisable(false);
        }
    }

    private void enablePathQueryControllers() {
        enableGetDelete();
        disablePutPost();
        enableSpinner();

    }

    private void enablePutPostControllers() {
        disableGetDelete();
        enablePutPost();
        disableSpinner();
    }

    private void disableGetDelete() {
        get.setDisable(true);
        delete.setDisable(true);
    }

    private void enableGetDelete() {
        get.setDisable(false);
        delete.setDisable(false);
    }

    private void disablePutPost() {
        put.setDisable(true);
        post.setDisable(true);
    }

    private void enablePutPost() {
        put.setDisable(false);
        post.setDisable(false);
    }

    private void disableSpinner() {
        paramCount.setDisable(true);
        paramCount.decrement(MAX_PARAM_COUNT);
    }

    private void enableSpinner() {
        paramCount.setDisable(false);
    }

    private void untickAllMethods() {
        get.setSelected(false);
        post.setSelected(false);
        put.setSelected(false);
        delete.setSelected(false);

    }

    public void setParameterList() {

        String parameterType = paramDetails.get(DataLabel.PARAMETER_TYPE);

        if (parameterType.equals(BODY_PARAM)) {
            paramDetails.put(DataLabel.PARAM_PREFIX + 0, resourceTable);
            paramDetails.put(DataLabel.DATA_TYPE_PREFIX + 0, resourceTable);

        } else {

            for (int i = 0; i < paramCount.getValue(); i++) {
                String param = parameters.get(i).getValue().toString();
                String snakeParam = Converter.camelToSnake(param);

                String fieldType;
                
                try {
                    fieldType = resourceTableFieldMap.get(snakeParam).getdTableField().getData_type();
                } catch (Exception e) {
                    fieldType = ReservedWords.KEYS_WITH_VALUES.get(snakeParam);
                }
                
                String paramFieldType = parameterTypeConversion(fieldType);

                paramDetails.put(DataLabel.PARAM_PREFIX + i, param);
                paramDetails.put(DataLabel.DATA_TYPE_PREFIX + i, paramFieldType);

            }
        }

    }

    private String parameterTypeConversion(String fieldType) {
        return fieldType;

    }

}
