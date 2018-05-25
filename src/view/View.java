package view;

import javafx.scene.layout.Pane;

public interface View
{
	public Pane getContent();
	public boolean onClose();
}