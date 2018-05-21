package view;

import javafx.scene.layout.Pane;

public interface View
{
	public Pane getViewContent();
	public boolean onClose();
}