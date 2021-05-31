package me.mattharper.floppy.input;

import me.mattharper.floppy.game.GameView;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Input implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

    public static Input instance;
    public Input() {
        instance = this;
    }

    public static boolean isInputEnabled = true;
    public static float mouseScreenX;
    public static float mouseScreenY;
    public static float mouseX;
    public static float mouseY;

    private static final Set<InputBind> heldThisFrame = new HashSet<>();
    private static final Set<InputBind> downThisFrame = new HashSet<>();
    private static final Set<InputBind> upThisFrame = new HashSet<>();

    private static final Map<InputAxis, Float> axes = new HashMap<>();
    static {
        axes.put(InputAxis.MOUSE_X, 0.f);
        axes.put(InputAxis.MOUSE_Y, 0.f);
        axes.put(InputAxis.MOUSE_WHEEL, 0.f);
    }

    public static boolean isInputJustPressed(InputBind button) {
        return isInputEnabled && downThisFrame.contains(button); // [Rubric A] compound condition
    }

    public static boolean isInputJustReleased(InputBind button) {
        return isInputEnabled && upThisFrame.contains(button);
    }

    public static boolean isInputHeld(InputBind button) {
        return isInputEnabled && heldThisFrame.contains(button);
    }

    public static float getAxis(InputAxis axis) {
        return axes.get(axis); // [Rubric A] get method
    }

    private static void setDownThisFrame(InputBind bind) {
        downThisFrame.add(bind);
        GameView.endOfFrame.add(() -> downThisFrame.remove(bind));
    }

    private static void setUpThisFrame(InputBind bind) {
        upThisFrame.add(bind);
        GameView.endOfFrame.add(() -> upThisFrame.remove(bind));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        InputBind bind = null;
        if(SwingUtilities.isLeftMouseButton(e))
            bind = InputBind.MOUSE_LEFT;
        if(SwingUtilities.isRightMouseButton(e))
            bind = InputBind.MOUSE_RIGHT;
        if(SwingUtilities.isMiddleMouseButton(e))
            bind = InputBind.MOUSE_MIDDLE;
        if(bind == null) return;
        setDownThisFrame(bind);
        heldThisFrame.add(bind);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        InputBind bind = null;
        if(SwingUtilities.isLeftMouseButton(e))
            bind = InputBind.MOUSE_LEFT;
        if(SwingUtilities.isRightMouseButton(e))
            bind = InputBind.MOUSE_RIGHT;
        if(SwingUtilities.isMiddleMouseButton(e))
            bind = InputBind.MOUSE_MIDDLE;
        if(bind == null) return;
        setUpThisFrame(bind);
        heldThisFrame.remove(bind);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        float deltaX = e.getXOnScreen() - mouseScreenX;
        float deltaY = e.getYOnScreen() - mouseScreenY;
        axes.put(InputAxis.MOUSE_X, deltaX);
        axes.put(InputAxis.MOUSE_Y, deltaY);
        mouseScreenX = e.getXOnScreen();
        mouseScreenY = e.getYOnScreen();
        GameView.endOfFrame.add(() -> axes.put(InputAxis.MOUSE_X, 0.f));
        GameView.endOfFrame.add(() -> axes.put(InputAxis.MOUSE_Y, 0.f));
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        axes.put(InputAxis.MOUSE_WHEEL, (float) e.getPreciseWheelRotation());
        GameView.endOfFrame.add(() -> axes.put(InputAxis.MOUSE_WHEEL, 0.f));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        float deltaX = e.getXOnScreen() - mouseScreenX;
        float deltaY = e.getYOnScreen() - mouseScreenY;
        axes.put(InputAxis.MOUSE_X, deltaX);
        axes.put(InputAxis.MOUSE_Y, deltaY);
        mouseScreenX = e.getXOnScreen();
        mouseScreenY = e.getYOnScreen();
        GameView.endOfFrame.add(() -> axes.put(InputAxis.MOUSE_X, 0.f));
        GameView.endOfFrame.add(() -> axes.put(InputAxis.MOUSE_Y, 0.f));
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
