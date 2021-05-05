package idata2001.mappe2.patientregister;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//A factory for GUI-instances. Just a simple class to showcase some understanding of the factory pattern.
public class GUIfactory {
    public Node VBox(){return new VBox();}
    public Node TableView(){return new TableView<>();}
    public Node ToolBar(){return new ToolBar();}
    public Node MenuBar(){return new MenuBar();}
}
