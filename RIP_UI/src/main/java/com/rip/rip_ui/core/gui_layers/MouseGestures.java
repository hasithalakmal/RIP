package com.rip.rip_ui.core.gui_layers;

import com.rip.rip_ui.api.ProjectHandler;
import com.rip.rip_ui.core.text.DataLabel;
import com.rip.rip_ui.model.impl.WNode;
import com.rip.rip_ui.model.impl.WParamField;
import com.rip.rip_ui.model.impl.WResourceNode;
import com.rip.rip_ui.model.impl.WTableNode;
import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class MouseGestures {

    final DragContext dragContext = new DragContext();

    private ProjectHandler projectHandler;

    public MouseGestures(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
    }

    public void makeDraggable(final Node node) {

        node.setOnMousePressed(onMousePressedEventHandler);
        node.setOnMouseDragged(onMouseDraggedEventHandler);
        node.setOnMouseReleased(onMouseReleasedEventHandler);

    }

    public void makeTableClickable(WTableNode node) {
        node.getTableSkinAddButton().setOnMouseClicked(onTableAddClickedEventHandler);
        node.getTableSkinDeleteButton().setOnMouseClicked(onTableDeleteClickedEventHandler);
    }

    public void makeResourceClickable(WResourceNode node) {
        node.getResourceSkinAddButton().setOnMouseClicked(onResourceAddClickedEventHandler);
        node.getResourceSkinDeleteButton().setOnMouseClicked(onResourceDeleteClickedEventHandler);

    }

    public void makeParamFieldClickable(WParamField node, HashMap<String, String> paramDetails) {
        if (paramDetails.get(DataLabel.GET).equals("true")) {
            node.getGetButton().setOnMouseClicked(onGetBtnClickedEventHandler);
        }

        if (paramDetails.get(DataLabel.POST).equals("true")) {
            node.getPostButton().setOnMouseClicked(onPostBtnClickedEventHandler);
        };

        if (paramDetails.get(DataLabel.PUT).equals("true")) {
            node.getPutButton().setOnMouseClicked(onPutBtnClickedEventHandler);
        }

        if (paramDetails.get(DataLabel.DELETE).equals("true")) {
            node.getDeleteButton().setOnMouseClicked(onDeleteBtnClickedEventHandler);
        }

    }

    public void makeClickable(final Node node) {
        node.setOnMouseClicked(onMouseDoubleClickedEventHandler);

    }

    EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            Node node = (Node) event.getSource();

            double scale = projectHandler.getScale();

            dragContext.x = node.getBoundsInParent().getMinX() * scale - event.getScreenX();
            dragContext.y = node.getBoundsInParent().getMinY() * scale - event.getScreenY();

        }
    };

    EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            Node node = (Node) event.getSource();

            double offsetX = event.getScreenX() + dragContext.x;
            double offsetY = event.getScreenY() + dragContext.y;

            // adjust the offset in case we are zoomed
            double scale = projectHandler.getScale();

            offsetX /= scale;
            offsetY /= scale;

            node.relocate(offsetX, offsetY);

        }
    };

    EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

        }
    };

    EventHandler<MouseEvent> onMouseDoubleClickedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 2) {

                WNode node = (WNode) event.getSource();
                String nodeId = node.getCellId();

                System.out.println("Double clicked " + nodeId);
            }
        }

    };

    EventHandler<MouseEvent> onTableAddClickedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 1) {

                Button btn = (Button) event.getSource();
                WTableNode node = (WTableNode) btn.getParent().getParent().getParent();
                String nodeId = node.getCellId();

                projectHandler.addTableField(nodeId);
            }
        }

    };

    EventHandler<MouseEvent> onTableDeleteClickedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 1) {

                Button btn = (Button) event.getSource();
                WTableNode node = (WTableNode) btn.getParent().getParent().getParent();
                String nodeId = node.getCellId();

                projectHandler.removeTableNode(nodeId, node);

            }
        }

    };

    class DragContext {

        double x;
        double y;

    }

    /*
    * Event Handlers related to resource elements 
     */
    //Event Handler - Pop a wizard to add a path
    EventHandler<MouseEvent> onResourceAddClickedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            WResourceNode node = findClickedResource(event);
            String nodeId = node.getCellId();
            projectHandler.addParameters(nodeId);
        }

    };

    //Event Handler - Pop a dialog to delete a resource
    EventHandler<MouseEvent> onResourceDeleteClickedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            WResourceNode node = findClickedResource(event);
            String nodeId = node.getCellId();
            projectHandler.removeResourceNode(nodeId,node);
        }

    };

    //Event Handler - Pop a wizard to add details of a get method
    EventHandler<MouseEvent> onGetBtnClickedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            WParamField pathNode = findClickedPathNode(event);
            WResourceNode resourceNode = findRelevantResource(pathNode);
            projectHandler.editGetParameters(resourceNode.getCellId(), pathNode.getCellId());

        }
    };

    //Event Handler - Pop a wizard to add details of a post method
    EventHandler<MouseEvent> onPostBtnClickedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            WParamField pathNode = findClickedPathNode(event);
            WResourceNode resourceNode = findRelevantResource(pathNode);
            projectHandler.editPostParameters(resourceNode.getCellId(), pathNode.getCellId());

        }
    };

    //Event Handler - Pop a wizard to add details of a put method
    EventHandler<MouseEvent> onPutBtnClickedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            WParamField pathNode = findClickedPathNode(event);
            WResourceNode resourceNode = findRelevantResource(pathNode);
            projectHandler.editPutParameters(resourceNode.getCellId(), pathNode.getCellId());

        }
    };

    //Event Handler - Pop a wizard to add details of a delete method
    EventHandler<MouseEvent> onDeleteBtnClickedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            WParamField pathNode = findClickedPathNode(event);
            WResourceNode resourceNode = findRelevantResource(pathNode);
            projectHandler.editDeleteParameters(resourceNode.getCellId(), pathNode.getCellId());

        }
    };

    private WResourceNode findClickedResource(MouseEvent event) {
        Button btn = (Button) event.getSource();
        WResourceNode node = (WResourceNode) btn.getParent().getParent().getParent().getParent().getParent();
        return node;
    }

    private WParamField findClickedPathNode(MouseEvent event) {
        Button btn = (Button) event.getSource();
        return (WParamField) btn.getParent().getParent().getParent().getParent();
    }
    
    private WResourceNode findRelevantResource(WParamField pathNode){
        return (WResourceNode) pathNode.getParent().getParent().getParent().getParent();
    }
}
