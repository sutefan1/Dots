package com.sewm.defaultteam.leveleditor;

import java.awt.Cursor;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class LevelEditorTool extends DefaultMutableTreeNode {
	protected LevelEditor editor_;
	protected String name_;
	protected ACTION action_;
	protected Icon icon_;
	protected Cursor cursor_;

	public LevelEditorTool(LevelEditor editor, String name, Icon icon, Cursor cursor, ACTION action) {
		super(name);
		editor_ = editor;
		icon_ = icon;
		cursor_ = cursor;
		action_ = action;
		name_ = name;
	}
	
	public Icon getIcon() {
		return icon_;
	}
	
	public Cursor getCursor() {
		return cursor_;
	}
	
	public void clicked(Vector2 pos) {
        switch (action_) {
        case SELECT:
            System.out.println("Select at " + pos);
            break;

        case PLACE:
            System.out.println("Place at " + pos);
            LevelEditorItem item = new LevelEditorItem(name_, pos);
            editor_.addItem(item);
            break;

        default:
            break;
        }
	}
	
	public void draggedAndDropped(Point from, Point to) {
		switch (action_) {
			case MOVE:
				System.out.println("Moved from " + from + " to " + to);
				break;
	
			default:
				break;
		}
	}
	
	public enum ACTION {
		SELECT,
		MOVE,
		PLACE
	}
}