package me.mattharper.floppy.input;

import me.mattharper.floppy.game.GameView;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Input implements MouseListener, MouseMotionListener, MouseWheelListener {

    public static Input instance;
    public Input() {
        instance = this;
    }

    public static boolean isInputEnabled = true;
    public static float mouseScreenX;
    public static float mouseScreenY;
    public static float mouseX;
    public static float mouseY;

    private static final Set<InputBind> heldThisFrame = new HashSet<>(); // [Rubric A] static array
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
        heldThisFrame.add(bind);
        GameView.endOfFrame.add(() -> downThisFrame.remove(bind));
    }

    private static void setUpThisFrame(InputBind bind) {
        heldThisFrame.remove(bind);
        upThisFrame.add(bind);
        GameView.endOfFrame.add(() -> upThisFrame.remove(bind));
    }

    public void setupKeybindings(JComponent component) {
        InputMap im = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "move.left.pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "move.left.released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "move.right.pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "move.right.released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "jump.pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "jump.released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "jump.pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "jump.released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "camera.left.pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "camera.left.released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "camera.right.pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "camera.right.released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "camera.up.pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "camera.up.released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "camera.down.pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "camera.down.released");

        ActionMap am = component.getActionMap();
        class PressedAction extends AbstractAction {
            final InputBind bind;
            public PressedAction(InputBind bind) {
                this.bind = bind;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                Input.setDownThisFrame(bind);
            }
        }
        class ReleasedAction extends AbstractAction {
            final InputBind bind;
            public ReleasedAction(InputBind bind) {
                this.bind = bind;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                Input.setUpThisFrame(bind);
            }
        }
        am.put("move.left.pressed", new PressedAction(InputBind.MOVE_LEFT));
        am.put("move.left.released", new ReleasedAction(InputBind.MOVE_LEFT));
        am.put("move.right.pressed", new PressedAction(InputBind.MOVE_RIGHT));
        am.put("move.right.released", new ReleasedAction(InputBind.MOVE_RIGHT));
        am.put("jump.pressed", new PressedAction(InputBind.JUMP));
        am.put("jump.released", new ReleasedAction(InputBind.JUMP));
        am.put("camera.left.pressed", new PressedAction(InputBind.CAMERA_LEFT));
        am.put("camera.left.released", new ReleasedAction(InputBind.CAMERA_LEFT));
        am.put("camera.right.pressed", new PressedAction(InputBind.CAMERA_RIGHT));
        am.put("camera.right.released", new ReleasedAction(InputBind.CAMERA_RIGHT));
        am.put("camera.up.pressed", new PressedAction(InputBind.CAMERA_UP));
        am.put("camera.up.released", new ReleasedAction(InputBind.CAMERA_UP));
        am.put("camera.down.pressed", new PressedAction(InputBind.CAMERA_DOWN));
        am.put("camera.down.released", new ReleasedAction(InputBind.CAMERA_DOWN));
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
}
