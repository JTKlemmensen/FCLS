package view;

import javafx.scene.layout.Pane;

public interface View
{
	public Pane getSceneGUI();
	public boolean onClose();
}