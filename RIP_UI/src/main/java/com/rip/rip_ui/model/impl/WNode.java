/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;


import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


/**
 *
 * keeps project data, visual data of 
 * a node and responsible for node related activities
 */
public class WNode extends Pane{
    
    String nodeId;
    
    List<WNode> children = new ArrayList<>();
    List<WNode> parents = new ArrayList<>();

    Node view;
    
    public WNode(String nodeId){
        this.nodeId = nodeId;
    }
    
    public WNode(String nodeId, String dataType, String keyType){
    
    }
    
    public void addCellChild(WNode node) {
        children.add(node);
    }

    public List<WNode> getCellChildren() {
        return children;
    }

    public void addCellParent(WNode node) {
        parents.add(node);
    }

    public List<WNode> getCellParents() {
        return parents;
    }

    public void removeCellChild(WNode node) {
        children.remove(node);
    }

    public void setView(Node view) {
        
        this.view = view;
        getChildren().addAll(view);

    }

    public Node getView() {
        return this.view;
    }

    public String getCellId() {
        return nodeId;
    }
    
}
